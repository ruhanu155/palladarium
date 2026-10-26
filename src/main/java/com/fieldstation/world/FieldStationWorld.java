package com.fieldstation.world;

import com.fieldstation.api.FieldStationApi;
import com.fieldstation.registry.FieldStationContent;
import com.fieldstation.sim.CoveSim;
import net.minecraft.commands.Commands;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SnowLayerBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gamerules.GameRules;
import net.minecraft.world.level.material.Fluids;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.RegisterCommandsEvent;
import net.neoforged.neoforge.event.entity.EntityJoinLevelEvent;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import net.neoforged.neoforge.event.level.LevelEvent;
import net.neoforged.neoforge.event.tick.ServerTickEvent;
import traben.flowing_fluids.FFFluidUtils;

import java.util.Set;

/**
 * World rules, hostile mob blocker, World Writer, physical RV structure builder, and Flowing Fluids bridge.
 */
public final class FieldStationWorld {
    public static final int RV_X = 95;
    public static final int RV_Z = 20;

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
        NeoForge.EVENT_BUS.addListener(FieldStationWorld::onPlayerLoggedIn);
        NeoForge.EVENT_BUS.addListener(FieldStationWorld::onRegisterCommands);
    }

    private static void onLevelLoad(LevelEvent.Load event) {
        if (!event.getLevel().isClientSide() && !coveBuilt) {
            coveBuilt = true;
            FieldStationApi.sim().buildAppalachianCove();
        }
    }

    private static void onPlayerLoggedIn(PlayerEvent.PlayerLoggedInEvent event) {
        if (!(event.getEntity() instanceof ServerPlayer player)) return;
        ServerLevel level = (ServerLevel) player.level();
        // Always ensure the physical RV structure is built at (RV_X, RV_Z)
        BlockPos rvPos = buildPhysicalRvStructure(level);
        if (!player.entityTags().contains("fieldstation:arrived")) {
            player.addTag("fieldstation:arrived");
            player.teleportTo(rvPos.getX() + 0.5, rvPos.getY(), rvPos.getZ() + 0.5);
            giveStarterKit(player);
            player.sendSystemMessage(Component.literal(
                "=== Welcome to Field Station: The Cove ===\n" +
                "Your RV is parked right here at (" + rvPos.getX() + ", " + rvPos.getY() + ", " + rvPos.getZ() + ").\n" +
                "Laurel Spring & Hemlock Run are just to the East (X=100), and Miller Pond is South at (X=88, Z=96).\n" +
                "Use '/fieldstation rv' anytime to return to your RV!"));
        } else if (player.distanceToSqr(rvPos.getX(), rvPos.getY(), rvPos.getZ()) > 250000) {
            player.sendSystemMessage(Component.literal(
                "[Field Station] Your RV & Cove Valley are built at (" + rvPos.getX() + ", " + rvPos.getY() + ", " + rvPos.getZ() + "). Run '/fieldstation rv' to teleport there!"));
        }
    }

    private static void giveStarterKit(ServerPlayer player) {
        String[] starterItems = {
            "field_shovel", "soil_bag", "hand_tamper", "seed_pouch",
            "binoculars", "field_journal", "field_tablet", "field_bucket",
            "sample_bottle", "test_reagent", "planting_trowel", "work_gloves", "wading_gloves"
        };
        for (String id : starterItems) {
            var holder = FieldStationContent.REGISTERED_ITEMS.get(id);
            if (holder != null) {
                Item item = holder.get();
                int count = ("sample_bottle".equals(id) || "test_reagent".equals(id)) ? 8 : 1;
                player.getInventory().add(new ItemStack(item, count));
            }
        }
    }

    /** Builds the physical 5x12 RV structure (3x10 interior + cab + living + lab + drone pad) at (RV_X, surfaceY, RV_Z). */
    public static BlockPos buildPhysicalRvStructure(ServerLevel level) {
        CoveSim sim = FieldStationApi.sim();
        sim.buildAppalachianCove();
        int rvIdx = CoveSim.idx(RV_X, RV_Z);
        int baseY = sim.groundEighths(rvIdx) / 8;

        Block rvBed = FieldStationContent.REGISTERED_BLOCKS.get("rv_bed").get();
        Block rvComputer = FieldStationContent.REGISTERED_BLOCKS.get("rv_computer").get();
        Block labBench = FieldStationContent.REGISTERED_BLOCKS.get("lab_bench").get();
        Block rvDoor = FieldStationContent.REGISTERED_BLOCKS.get("rv_door").get();
        Block dronePad = FieldStationContent.REGISTERED_BLOCKS.get("drone_pad").get();
        Block springHead = FieldStationContent.REGISTERED_BLOCKS.get("spring_head").get();
        Block compostBin = FieldStationContent.REGISTERED_BLOCKS.get("compost_bin").get();
        Block seedVault = FieldStationContent.REGISTERED_BLOCKS.get("seed_vault").get();

        // Solid foundation + Gravel RV Pad (dx=-2..9, dz=-2..11) so it stands solidly even over an ocean
        for (int dz = -2; dz <= 11; dz++) {
            for (int dx = -2; dx <= 9; dx++) {
                for (int y = 58; y < baseY - 1; y++) {
                    level.setBlock(new BlockPos(RV_X + dx, y, RV_Z + dz), Blocks.STONE.defaultBlockState(), 3);
                }
                level.setBlock(new BlockPos(RV_X + dx, baseY - 1, RV_Z + dz), Blocks.GRAVEL.defaultBlockState(), 3);
                for (int dy = 0; dy <= 8; dy++) {
                    level.setBlock(new BlockPos(RV_X + dx, baseY + dy, RV_Z + dz), Blocks.AIR.defaultBlockState(), 3);
                }
            }
        }

        // RV Shell (5 wide: dx=0..4, 10 long: dz=0..9, 4 high: dy=0..3)
        for (int dz = 0; dz <= 9; dz++) {
            for (int dx = 0; dx <= 4; dx++) {
                // Floor & Roof
                level.setBlock(new BlockPos(RV_X + dx, baseY - 1, RV_Z + dz), Blocks.SMOOTH_STONE.defaultBlockState(), 3);
                level.setBlock(new BlockPos(RV_X + dx, baseY + 3, RV_Z + dz), Blocks.QUARTZ_BLOCK.defaultBlockState(), 3);

                boolean wall = (dx == 0 || dx == 4 || dz == 0 || dz == 9);
                if (wall) {
                    for (int dy = 0; dy <= 2; dy++) {
                        boolean window = (dy == 1 && (dz == 2 || dz == 5 || dz == 7) && (dx == 0 || dx == 4));
                        BlockState wallBlock = window ? Blocks.GLASS.defaultBlockState() : Blocks.QUARTZ_BLOCK.defaultBlockState();
                        level.setBlock(new BlockPos(RV_X + dx, baseY + dy, RV_Z + dz), wallBlock, 3);
                    }
                }
            }
        }

        // Door opening on East side (dx=4, dz=4) facing Hemlock Run
        level.setBlock(new BlockPos(RV_X + 4, baseY, RV_Z + 4), rvDoor.defaultBlockState(), 3);
        level.setBlock(new BlockPos(RV_X + 4, baseY + 1, RV_Z + 4), Blocks.AIR.defaultBlockState(), 3);

        // Interior stations (Cab: dz=1..2, Living: dz=3..5, Lab: dz=6..8)
        level.setBlock(new BlockPos(RV_X + 1, baseY, RV_Z + 3), rvBed.defaultBlockState(), 3);
        level.setBlock(new BlockPos(RV_X + 1, baseY, RV_Z + 5), rvComputer.defaultBlockState(), 3);
        level.setBlock(new BlockPos(RV_X + 1, baseY, RV_Z + 7), labBench.defaultBlockState(), 3);
        level.setBlock(new BlockPos(RV_X + 3, baseY, RV_Z + 7), seedVault.defaultBlockState(), 3);
        level.setBlock(new BlockPos(RV_X + 2, baseY + 2, RV_Z + 2), Blocks.LANTERN.defaultBlockState(), 3);
        level.setBlock(new BlockPos(RV_X + 2, baseY + 2, RV_Z + 7), Blocks.LANTERN.defaultBlockState(), 3);

        // Outside Drone Pad & Compost Bin next to RV door
        for (int dz = 3; dz <= 5; dz++) {
            for (int dx = 6; dx <= 8; dx++) {
                level.setBlock(new BlockPos(RV_X + dx, baseY - 1, RV_Z + dz), dronePad.defaultBlockState(), 3);
            }
        }
        level.setBlock(new BlockPos(RV_X + 5, baseY, RV_Z + 2), compostBin.defaultBlockState(), 3);

        // Place Laurel Spring head just East of RV at (RV_X + 10, baseY - 1, RV_Z)
        level.setBlock(new BlockPos(RV_X + 10, baseY - 1, RV_Z), springHead.defaultBlockState(), 3);
        level.setBlock(new BlockPos(RV_X + 10, baseY, RV_Z), Blocks.WATER.defaultBlockState(), 3);

        return new BlockPos(RV_X + 5, baseY, RV_Z + 4);
    }

    private static void onRegisterCommands(RegisterCommandsEvent event) {
        event.getDispatcher().register(Commands.literal("fieldstation")
            .then(Commands.literal("rv").executes(ctx -> {
                ServerPlayer player = ctx.getSource().getPlayerOrException();
                ServerLevel level = (ServerLevel) player.level();
                BlockPos doorPos = buildPhysicalRvStructure(level);
                player.teleportTo(doorPos.getX() + 0.5, doorPos.getY(), doorPos.getZ() + 0.5);
                giveStarterKit(player);
                ctx.getSource().sendSuccess(() -> Component.literal(
                    "[Field Station] Teleported to the RV at (" + doorPos.getX() + ", " + doorPos.getY() + ", " + doorPos.getZ() + ") and replenished starter kit!"), false);
                return 1;
            }))
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
        BlockPos.MutableBlockPos mpos = new BlockPos.MutableBlockPos();
        // Write up to 2500 dirty columns per tick (so the entire 200x200 Cove valley sculpts in ~16 ticks = <1 second!)
        int scanned = 0;
        int written = 0;
        for (int col = sim.dirtyColumns.nextSetBit(0); col >= 0 && written < 2500 && scanned < CoveSim.COLS; col = sim.dirtyColumns.nextSetBit(col + 1)) {
            scanned++;
            int x = col % CoveSim.SIZE;
            int z = col / CoveSim.SIZE;
            // Don't overwrite the RV pad footprint
            if (x >= RV_X - 2 && x <= RV_X + 9 && z >= RV_Z - 2 && z <= RV_Z + 11) {
                sim.dirtyColumns.clear(col);
                continue;
            }

            mpos.set(x, 64, z);
            if (!overworld.isLoaded(mpos)) {
                continue;
            }
            sim.dirtyColumns.clear(col);

            int groundEighths = sim.groundEighths(col);
            int topBlockY = groundEighths / 8;
            int remLayers = groundEighths % 8;
            int waterLvls = sim.waterLevels[col];

            // 1. Fill solid foundation from Y=58 up to topBlockY - 1 so oceans/ravines become the Appalachian Cove valley
            int stoneTop = sim.stoneTopY[col];
            for (int y = 58; y < topBlockY; y++) {
                mpos.set(x, y, z);
                BlockState fillState = (y <= stoneTop)
                    ? Blocks.STONE.defaultBlockState()
                    : (y < topBlockY - 1 ? Blocks.COARSE_DIRT.defaultBlockState() : Blocks.GRASS_BLOCK.defaultBlockState());
                if (waterLvls > 0 && y == topBlockY - 1) {
                    fillState = (sim.clayLayers[col] > 0) ? Blocks.CLAY.defaultBlockState() : Blocks.GRAVEL.defaultBlockState();
                } else if (sim.seeded[col] == CoveSim.SEED_NONE && y == topBlockY - 1 && !sim.treeCanopy[col]) {
                    fillState = Blocks.DIRT_PATH.defaultBlockState();
                }
                overworld.setBlock(mpos, fillState, Block.UPDATE_CLIENTS | Block.UPDATE_KNOWN_SHAPE);
            }

            // 2. Clear any vanilla ocean water or trees above the Cove valley floor
            int clearStartY = topBlockY + (waterLvls > 0 ? 2 : 1);
            for (int y = clearStartY; y <= Math.max(clearStartY + 6, 72); y++) {
                mpos.set(x, y, z);
                BlockState existing = overworld.getBlockState(mpos);
                if (!existing.isAir()) {
                    overworld.setBlock(mpos, Blocks.AIR.defaultBlockState(), Block.UPDATE_CLIENTS | Block.UPDATE_KNOWN_SHAPE);
                }
            }

            // 3. Under water: render top soil block as whole block if >= 4 layers, else remove (no air gap)
            if (waterLvls > 0) {
                int waterBaseY = (remLayers >= 4) ? topBlockY + 1 : topBlockY;
                if (remLayers >= 4) {
                    mpos.set(x, topBlockY, z);
                    overworld.setBlock(mpos, Blocks.GRAVEL.defaultBlockState(), Block.UPDATE_CLIENTS | Block.UPDATE_KNOWN_SHAPE);
                }
                BlockState waterState = FFFluidUtils.getBlockForFluidByAmount(Fluids.WATER, Math.min(8, waterLvls));
                mpos.set(x, waterBaseY, z);
                overworld.setBlock(mpos, waterState, Block.UPDATE_CLIENTS | Block.UPDATE_KNOWN_SHAPE);
            } else if (remLayers > 0) {
                BlockState layerState = Blocks.DIRT.defaultBlockState();
                Block topsoil = BuiltInRegistries.BLOCK.getValue(Identifier.fromNamespaceAndPath("fieldstation", "topsoil"));
                if (topsoil != null && topsoil != Blocks.AIR && topsoil.defaultBlockState().hasProperty(SnowLayerBlock.LAYERS)) {
                    layerState = topsoil.defaultBlockState().setValue(SnowLayerBlock.LAYERS, remLayers);
                }
                mpos.set(x, topBlockY, z);
                overworld.setBlock(mpos, layerState, Block.UPDATE_CLIENTS | Block.UPDATE_KNOWN_SHAPE);
            } else {
                // Surface flora / reeds / grass on full blocks
                mpos.set(x, topBlockY, z);
                if (sim.seeded[col] == CoveSim.SEED_REED) {
                    Block cattail = FieldStationContent.REGISTERED_BLOCKS.get("broadleaf_cattail").get();
                    overworld.setBlock(mpos, cattail.defaultBlockState(), Block.UPDATE_CLIENTS | Block.UPDATE_KNOWN_SHAPE);
                } else if (sim.seeded[col] == CoveSim.SEED_GRASS && (x + z * 7) % 5 == 0) {
                    Block grass = FieldStationContent.REGISTERED_BLOCKS.get("little_bluestem").get();
                    overworld.setBlock(mpos, grass.defaultBlockState(), Block.UPDATE_CLIENTS | Block.UPDATE_KNOWN_SHAPE);
                } else {
                    overworld.setBlock(mpos, Blocks.AIR.defaultBlockState(), Block.UPDATE_CLIENTS | Block.UPDATE_KNOWN_SHAPE);
                }
            }
            written++;
        }
    }
}
