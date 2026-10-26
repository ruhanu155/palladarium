package com.fieldstation.registry;

import com.fieldstation.FieldStation;
import com.fieldstation.api.FieldStationApi;
import com.fieldstation.sim.CoveSim;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.util.RandomSource;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SnowLayerBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * All DeferredRegisters and universal block/item classes (Content registry).
 */
public final class FieldStationContent {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(FieldStation.MOD_ID);
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(FieldStation.MOD_ID);
    public static final DeferredRegister<CreativeModeTab> TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, FieldStation.MOD_ID);

    public static final BooleanProperty PACKED = BooleanProperty.create("packed");
    public static final EnumProperty<SeededType> SEEDED = EnumProperty.create("seeded", SeededType.class);

    public enum SeededType implements StringRepresentable {
        NONE("none"), GRASS("grass"), REED("reed");
        private final String name;
        SeededType(String name) { this.name = name; }
        @Override public String getSerializedName() { return name; }
    }

    /** Universal 8-layer soil block extending SnowLayerBlock with packed=true|false (and seeded on topsoil). */
    public static class LayeredSoilBlock extends SnowLayerBlock {
        public final byte soilKind;

        public LayeredSoilBlock(byte soilKind, BlockBehaviour.Properties props) {
            super(props);
            this.soilKind = soilKind;
            this.registerDefaultState(this.stateDefinition.any()
                .setValue(LAYERS, 8)
                .setValue(PACKED, false));
        }

        @Override
        protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
            super.createBlockStateDefinition(builder);
            builder.add(PACKED);
        }

        @Override
        protected void randomTick(BlockState state, net.minecraft.server.level.ServerLevel level, BlockPos pos, RandomSource random) {
            // Soil layers never melt like snow
        }
    }

    public static final class TopsoilLayerBlock extends LayeredSoilBlock {
        public TopsoilLayerBlock(BlockBehaviour.Properties props) {
            super(CoveSim.SOIL_TOPSOIL, props);
            this.registerDefaultState(this.defaultBlockState().setValue(SEEDED, SeededType.NONE));
        }

        @Override
        protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
            super.createBlockStateDefinition(builder);
            builder.add(SEEDED);
        }
    }

    /** Universal interactive station block (RV Bed, RV Computer, Lab Bench, Drone Pad, Spring Head). */
    public static final class StationBlock extends Block {
        public final String stationKind;

        public StationBlock(String stationKind, BlockBehaviour.Properties props) {
            super(props);
            this.stationKind = stationKind;
        }

        @Override
        protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hit) {
            if (!level.isClientSide()) {
                CoveSim sim = FieldStationApi.sim();
                switch (stationKind) {
                    case "rv_bed" -> {
                        sim.sleepUntilMorning();
                        player.sendSystemMessage(Component.literal("[RV Bed] Slept until 06:00. Energy restored to 100! Cash: $" + sim.cashDollars));
                    }
                    case "rv_computer" -> {
                        player.sendSystemMessage(Component.literal(
                            "=== RV Field Station Computer ===\n" +
                            "Cash: $" + sim.cashDollars + " | Reputation: " + sim.reputation + " | SC: " + sim.scienceCredits + "\n" +
                            "Habitats: " + sim.habitats.size() + " | Logged Species: " + sim.loggedSpecies.size() +
                            " | Soil Lost: " + (sim.soilLostLayersTotal / 8) + " blocks"));
                    }
                    case "lab_bench" -> {
                        sim.fileWaterLabReport();
                        player.sendSystemMessage(Component.literal("[Lab Bench] Water sample tested (Nitrate < 10 mg/L). Report filed: +$15!"));
                    }
                    default -> player.sendOverlayMessage(Component.literal("[Field Station] " + stationKind + " active."));
                }
            }
            return InteractionResult.SUCCESS;
        }
    }

    /** Universal Field Tool Item (Shovel, Soil Bag, Tamper, Seed Pouch, Binoculars, Bucket, Stakes, Journal, etc.). */
    public static final class FieldToolItem extends Item {
        public final String toolKind;

        public FieldToolItem(String toolKind, Item.Properties props) {
            super(props);
            this.toolKind = toolKind;
        }

        @Override
        public InteractionResult useOn(UseOnContext ctx) {
            Level level = ctx.getLevel();
            BlockPos pos = ctx.getClickedPos();
            Player player = ctx.getPlayer();
            if (!level.isClientSide()) {
                CoveSim sim = FieldStationApi.sim();
                int x = Math.floorMod(pos.getX(), CoveSim.SIZE);
                int z = Math.floorMod(pos.getZ(), CoveSim.SIZE);
                int col = CoveSim.idx(x, z);
                switch (toolKind) {
                    case "field_shovel" -> {
                        sim.removeSoilLayers(col, 8);
                        sim.playerDug[col] = true;
                        sim.playerEnergy = Math.max(0.0f, sim.playerEnergy - 0.25f);
                        if (player != null) player.sendOverlayMessage(Component.literal("[Shovel] Dug 1 block into soil bag. Ground: " + (sim.groundEighths(col) / 8.0f) + "m"));
                    }
                    case "soil_bag" -> {
                        sim.addSoilLayers(col, CoveSim.SOIL_RED_CLAY, 8);
                        sim.loose[col] = true;
                        sim.packed[col] = false;
                        sim.playerEnergy = Math.max(0.0f, sim.playerEnergy - 0.10f);
                        if (player != null) player.sendOverlayMessage(Component.literal("[Soil Bag] Dumped loose layer (tamp to seal)."));
                    }
                    case "hand_tamper" -> {
                        sim.packed[col] = true;
                        sim.loose[col] = false;
                        sim.playerEnergy = Math.max(0.0f, sim.playerEnergy - 0.50f);
                        BlockState bs = level.getBlockState(pos);
                        if (bs.hasProperty(PACKED)) {
                            level.setBlock(pos, bs.setValue(PACKED, true), Block.UPDATE_CLIENTS);
                        }
                        if (player != null) player.sendOverlayMessage(Component.literal("[Tamper] Ground packed (soak rate reduced to 8%)."));
                    }
                    case "seed_pouch", "reed_plug", "planting_trowel" -> {
                        byte seed = "reed_plug".equals(toolKind) ? CoveSim.SEED_REED : CoveSim.SEED_GRASS;
                        for (int dz = -1; dz <= 1; dz++) {
                            for (int dx = -1; dx <= 1; dx++) {
                                if (CoveSim.inBounds(x + dx, z + dz)) {
                                    int c = CoveSim.idx(x + dx, z + dz);
                                    sim.seeded[c] = seed;
                                    sim.plantCover[c] = Math.max(sim.plantCover[c], 0.35f);
                                }
                            }
                        }
                        if (player != null) player.sendOverlayMessage(Component.literal("[Planting] Sown 3x3 native cover."));
                    }
                    case "field_bucket" -> {
                        sim.waterLevels[col] += 8;
                        sim.dirtyColumns.set(col);
                        if (player != null) player.sendOverlayMessage(Component.literal("[Field Bucket] Poured 8 water levels (1.0m)."));
                    }
                    case "binoculars", "field_journal", "field_tablet" -> {
                        int hid = sim.habitatRecordId[col];
                        sim.logSightingWithBinoculars("green_frog", hid);
                        if (player != null) player.sendSystemMessage(Component.literal(
                            "[Journal] Logged Green Frog! Species: " + sim.loggedSpecies.size() +
                            " | Tag: " + CoveSim.TAG_NAMES[sim.microhabitatTag[col]] +
                            " | Moisture: " + sim.moisture[col] + "/7"));
                    }
                    default -> {
                        if (player != null) player.sendOverlayMessage(Component.literal(
                            "[" + toolKind + "] Col (" + x + "," + z + ") Tag=" + CoveSim.TAG_NAMES[sim.microhabitatTag[col]] +
                            " Water=" + sim.waterLevels[col] + "/8 Moist=" + sim.moisture[col] + "/7"));
                    }
                }
            }
            return InteractionResult.SUCCESS;
        }
    }

    public static final Map<String, DeferredBlock<Block>> REGISTERED_BLOCKS = new LinkedHashMap<>();
    public static final Map<String, DeferredItem<? extends Item>> REGISTERED_ITEMS = new LinkedHashMap<>();

    static {
        // 1. Register Layered Soil Blocks
        regLayered("topsoil", CoveSim.SOIL_TOPSOIL, true);
        regLayered("subsoil", CoveSim.SOIL_SUBSOIL, false);
        regLayered("red_clay", CoveSim.SOIL_RED_CLAY, false);
        regLayered("gravel_layer", CoveSim.SOIL_GRAVEL, false);
        regLayered("silt", CoveSim.SOIL_SILT, false);
        regLayered("mud", CoveSim.SOIL_MUD, false);
        regLayered("trail_tread", CoveSim.SOIL_TOPSOIL, false);
        regLayered("ice_layer", CoveSim.SOIL_STONE, false);

        // 2. Register Simple / Station Blocks
        String[] simpleIds = {
            "clay_film", "spring_head", "soft_rush", "meadow_grass", "native_shrub",
            "silt_fence", "coir_log", "bluebird_box", "wood_duck_box", "bat_box",
            "rv_bed", "rv_computer", "lab_bench", "rv_door", "drone_pad",
            "field_beacon", "beaver_dam_analog", "apple_tree", "seed_vault", "compost_bin"
        };
        for (String id : simpleIds) {
            DeferredBlock<Block> blk = BLOCKS.registerBlock(id,
                props -> new StationBlock(id, props.strength(1.0f).sound(SoundType.WOOD)));
            REGISTERED_BLOCKS.put(id, blk);
            REGISTERED_ITEMS.put(id, ITEMS.registerSimpleBlockItem(id, blk));
        }

        // 3. Register All Field Tools & Items
        String[] itemIds = {
            "field_shovel", "soil_bag", "wheelbarrow", "hand_tamper", "seed_pouch",
            "binoculars", "sample_bottle", "test_reagent", "field_journal", "field_tablet",
            "rental_key", "reed_plug", "field_bucket", "survey_stake", "benchmark_stake",
            "planting_trowel", "work_gloves", "wading_gloves", "compost", "worm_castings",
            "mycorrhizal_inoculant", "grocery_box", "sandwich", "camp_stew", "coffee",
            "blackberries", "ramps", "chanterelles", "kick_net", "sorting_tray",
            "cover_board", "tag_reader", "sketchbook", "loppers", "post_driver",
            "fencing_pliers", "mcleod", "clinometer", "grafting_knife", "edna_kit", "data_logger"
        };
        for (String id : itemIds) {
            REGISTERED_ITEMS.put(id, ITEMS.registerItem(id, props -> new FieldToolItem(id, props)));
        }

        TABS.register("main", () -> CreativeModeTab.builder()
            .title(Component.translatable("itemGroup.fieldstation"))
            .icon(() -> new ItemStack(REGISTERED_ITEMS.get("field_shovel").get()))
            .displayItems((params, output) -> {
                for (DeferredHolder<Item, ? extends Item> item : ITEMS.getEntries()) {
                    output.accept(item.get());
                }
            })
            .build());
    }

    private static void regLayered(String id, byte soilKind, boolean isTopsoil) {
        DeferredBlock<Block> blk = BLOCKS.registerBlock(id, props -> {
            BlockBehaviour.Properties p = props.strength(0.6f).sound(SoundType.GRAVEL);
            return isTopsoil ? new TopsoilLayerBlock(p) : new LayeredSoilBlock(soilKind, p);
        });
        REGISTERED_BLOCKS.put(id, blk);
        REGISTERED_ITEMS.put(id, ITEMS.registerSimpleBlockItem(id, blk));
    }

    public static void register(IEventBus modBus) {
        BLOCKS.register(modBus);
        ITEMS.register(modBus);
        TABS.register(modBus);
    }
}
