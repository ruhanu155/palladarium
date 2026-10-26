package com.fieldstation.world;

import com.fieldstation.api.FieldStationApi;
import com.fieldstation.sim.CoveSim;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SnowLayerBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gamerules.GameRules;
import net.minecraft.world.level.material.Fluids;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.RegisterCommandsEvent;
import net.neoforged.neoforge.event. RegisterGameTestsEvent;
import net.neoforged.neoforge.event.entity.EntityJoinLevelEvent;
import net.neoforged.neoforge.event.level.LevelEvent;
import net.neoforged.neoforge.event.tick.ServerTickEvent;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import traben.flowing_fluids.FFFluidUtils;

import java.util.Set;

/**
 * World rules, hostile mob blocker, World Writer, commands, and Flowing Fluids water bridge.
 */
public final class FieldStationWorld {
    public static final Set<String> ALLOWED_ENTITY_IDS = Set.of(
        "minecraft:frog", "minecraft:tadpole", "minecraft:turtle",
        "minecraft:salmon", "minecraft:cod", "minecraft:bat",
        "minecraft:bee", "minecraft:rabbit", "minecraft:fox",
        "minecraft:wolf", "minecraft:goat", "minecraft:mule",
        "minecraft:villager", "minecraft:allay"
    );
    private static boolean coveBuilt = false;

    private FieldStationWorld() {}

    public static void register(IEventBus modBus) {
        NeoForge.EVENT_BUS.addListener(FieldStationWorld::onEntityJoin);
        NeoForge.EVENT_BUS.addListener(FieldStationWorld::onServerTick);
        NeoForge.EVENT_BUS.addListener(FieldStationWorld::onLevelLoad);
        NeoForge.EVENT_BUS.addListener(FieldStationWorld::onRegisterCommands);
    }

    private static void onLevelLoad(LevelEvent.Load event) {
        if (!event.getLevel().isClientSide() && !coveBuilt) {
            coveBuilt = true;
            FieldStationApi.sim().buildAppalachianCove();
        }
    }

    private static void onRegisterCommands(RegisterCommandsEvent event) {
        event.getDispatcher().register(Commands.literal("fieldstation")
            .then(Commands.literal("status").executes(ctx -> {
                CoveSim sim = FieldStationApi.sim();
                ctx.getSource().sendSuccess(() -> Component.literal(
                    "[Field Station] Year " + sim.year() + " " + CoveSim.SEASON_NAMES[sim.season()] +
                    " Day " + sim.dayOfSeason() + " | Cash: $" + sim.cashDollars +
                    " | Rep: " + sim.reputation + " | SC: " + sim.scienceCredits +
                    " | Habitats: " + sim.habitats.size() + " | Logged: " + sim.loggedSpecies.size()), false);
                return 1;
            }))
            .then(Commands.literal("sleep").executes(ctx -> {
                FieldStationApi.sim().sleepUntilMorning();
                ctx.getSource().sendSuccess(() -> Component.literal("[Field Station] Slept until 06:00!"), false);
                return 1;
            }))
            .then(Commands.literal("survey").executes(ctx -> {
                CoveSim sim = FieldStationApi.sim();
                for (int hid : sim.habitats.keySet()) {
                    sim.runSurveyVisit(hid, "camera_trap");
                }
                ctx.getSource().sendSuccess(() -> Component.literal(
                    "[Field Station] Surveyed all " + sim.habitats.size() + " habitats. Open mystery cards: " + sim.mysteryCards.size()), false);
                return 1;
            }))
        );
    }

    /** Returns true if a Mob entity is permitted in Field Station; false if it must be rejected (Gate no_hostiles). */
    public static boolean isMobAllowed(Entity entity) {
        if (!(entity instanceof Mob mob)) return true;
        if (entity instanceof Enemy) return false;
        Identifier typeId = BuiltInRegistries.ENTITY_TYPE.getKey(mob.getType());
        if (typeId == null || !ALLOWED_ENTITY_IDS.contains(typeId.toString())) {
            return false;
        }
        return mob.entityTags().contains("fieldstation:spawned");
    }

    private static void onEntityJoin(EntityJoinLevelEvent event) {
        if (event.getLevel().isClientSide()) return;
        if (!isMobAllowed(event.getEntity())) {
            event.setCanceled(true);
        }
    }

    private static void onServerTick(ServerTickEvent.Post event) {
        ServerLevel overworld = event.getServer().overworld();
        if (overworld == null) return;

        // Enforce Field Station peaceful/no-hostile game rules
        GameRules rules = overworld.getGameRules();
        rules.set(GameRules.SPAWN_MONSTERS, false, event.getServer());
        rules.set(GameRules.SPAWN_PATROLS, false, event.getServer());
        rules.set(GameRules.SPAWN_PHANTOMS, false, event.getServer());
        rules.set(GameRules.SPAWN_WANDERING_TRADERS, false, event.getServer());
        rules.set(GameRules.SPAWN_WARDENS, false, event.getServer());
        rules.set(GameRules.RAIDS, false, event.getServer());
        rules.set(GameRules.MOB_DROPS, false, event.getServer());
        rules.set(GameRules.KEEP_INVENTORY, true, event.getServer());
        rules.set(GameRules.MAX_ENTITY_CRAMMING, 0, event.getServer());

        CoveSim sim = FieldStationApi.sim();
        // Write up to 256 dirty columns per tick (World Writer budget)
        int written = 0;
        for (int col = sim.dirtyColumns.nextSetBit(0); col >= 0 && written < 256; col = sim.dirtyColumns.nextSetBit(col + 1)) {
            sim.dirtyColumns.clear(col);
            int x = col % CoveSim.SIZE;
            int z = col / CoveSim.SIZE;
            BlockPos checkPos = new BlockPos(x, 64, z);
            if (!overworld.isLoaded(checkPos)) continue;

            int groundEighths = sim.groundEighths(col);
            int topBlockY = groundEighths / 8;
            int remLayers = groundEighths % 8;
            int waterLvls = sim.waterLevels[col];

            // Under water: render top soil block as whole block if >= 4 layers, else remove (no air gap)
            if (waterLvls > 0) {
                int waterBaseY = (remLayers >= 4) ? topBlockY + 1 : topBlockY;
                BlockState waterState = FFFluidUtils.getBlockForFluidByAmount(Fluids.WATER, Math.min(8, waterLvls));
                overworld.setBlock(new BlockPos(x, waterBaseY, z), waterState, Block.UPDATE_CLIENTS | Block.UPDATE_KNOWN_SHAPE);
            } else if (remLayers > 0) {
                BlockState layerState = Blocks.DIRT.defaultBlockState();
                Block topsoil = BuiltInRegistries.BLOCK.getValue(Identifier.fromNamespaceAndPath("fieldstation", "topsoil"));
                if (topsoil != null && topsoil != Blocks.AIR && topsoil.defaultBlockState().hasProperty(SnowLayerBlock.LAYERS)) {
                    layerState = topsoil.defaultBlockState().setValue(SnowLayerBlock.LAYERS, remLayers);
                }
                overworld.setBlock(new BlockPos(x, topBlockY, z), layerState, Block.UPDATE_CLIENTS | Block.UPDATE_KNOWN_SHAPE);
            }
            written++;
        }
    }
}
