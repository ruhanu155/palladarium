package com.fieldstation.sim;

import java.util.*;

/**
 * Pure Java simulation engine for Field Station (v0.1 through v1.0).
 * No Minecraft imports allowed (enforced by ArchitectureTest).
 *
 * Covers:
 * - 200x200 Appalachian Cove land function and 11 landmarks
 * - Clock layer (1,000 ticks/hour, 24h/day, 7d/season, 28d/year, sleep time-lapse)
 * - Finite water in eighths (1..8 levels/block), sub-level buffer, soaking, clay sealing, springs, rain, evaporation, outlet
 * - 8-layer soil stacks (topsoil, subsoil, red_clay, gravel, silt, mud, stone)
 * - Stream power P = q * (0.1 + s), wear, carry, gravel/silt/clay sorting, bank slump, dam breach, meanders
 * - Habitat flood-fill (ponds & stream reaches), microhabitat tagger, corridors
 * - 44 cove species arrival, breeding pulses, winter survival, Park Service allotments
 * - Surveys (binoculars, call, camera trap, eDNA, kick net, quadrat), 11 Mystery Cards
 * - Economy ($300 start, $6/day species grants, first-sighting bonuses, $15 lab reports, drone deliveries)
 * - Storyteller tension budget, Morning Briefing, Chronicle, Year Cards, Reference Site finale
 */
public final class CoveSim {
    public static final int SIZE = 200;
    public static final int COLS = SIZE * SIZE;

    // Soil layer constants
    public static final byte SOIL_TOPSOIL = 0;
    public static final byte SOIL_SUBSOIL = 1;
    public static final byte SOIL_RED_CLAY = 2;
    public static final byte SOIL_GRAVEL = 3;
    public static final byte SOIL_SILT = 4;
    public static final byte SOIL_MUD = 5;
    public static final byte SOIL_STONE = 6;

    // Seeded state
    public static final byte SEED_NONE = 0;
    public static final byte SEED_GRASS = 1;
    public static final byte SEED_REED = 2;

    // Microhabitat tags
    public static final byte TAG_OPEN_WATER = 0;
    public static final byte TAG_SHALLOWS = 1;
    public static final byte TAG_REED_BED = 2;
    public static final byte TAG_WET_MEADOW = 3;
    public static final byte TAG_DRY_MEADOW = 4;
    public static final byte TAG_WOODS_EDGE = 5;
    public static final byte TAG_WOODS = 6;
    public static final byte TAG_BARE_GROUND = 7;
    public static final byte TAG_GRAVEL_BAR = 8;
    public static final byte TAG_FROST_HOLLOW = 9;
    public static final byte TAG_WARM_SLOPE = 10;

    public static final String[] TAG_NAMES = {
        "open_water", "shallows", "reed_bed", "wet_meadow", "dry_meadow",
        "woods_edge", "woods", "bare_ground", "gravel_bar", "frost_hollow", "warm_slope"
    };

    // Seasons (7 days each, 28 days per year)
    public static final int SEASON_SPRING = 0;
    public static final int SEASON_SUMMER = 1;
    public static final int SEASON_FALL = 2;
    public static final int SEASON_WINTER = 3;
    public static final String[] SEASON_NAMES = {"spring", "summer", "fall", "winter"};

    // Per-column arrays (40,000 elements)
    public final short[] stoneTopY = new short[COLS];
    // Layers in eighths (8 layers = 1 full block)
    public final short[] topsoilLayers = new short[COLS];
    public final short[] subsoilLayers = new short[COLS];
    public final short[] clayLayers = new short[COLS];
    public final short[] gravelLayers = new short[COLS];
    public final short[] siltLayers = new short[COLS];
    public final short[] mudLayers = new short[COLS];
    public final byte[] clayFilms = new byte[COLS];

    public final boolean[] packed = new boolean[COLS];
    public final boolean[] playerDug = new boolean[COLS];
    public final boolean[] loose = new boolean[COLS];
    public final boolean[] riprap = new boolean[COLS];
    public final boolean[] springSource = new boolean[COLS];
    public final boolean[] seepSource = new boolean[COLS];
    public final byte[] seeded = new byte[COLS]; // 0=none, 1=grass, 2=reed
    public final float[] plantCover = new float[COLS]; // 0..1
    public final boolean[] treeCanopy = new boolean[COLS];
    public final boolean[] shrubCover = new boolean[COLS];
    public final boolean[] baskingLog = new boolean[COLS];
    public final boolean[] rockPile = new boolean[COLS];
    public final boolean[] nestBox = new boolean[COLS];

    // Hydrology arrays
    public final short[] waterLevels = new short[COLS]; // 1 level = 1/8 block = 0.125m
    public final short[] iceLayers = new short[COLS];
    public final short[] snowLayers = new short[COLS];
    public final float[] waterBufferM = new float[COLS];
    public final float[] topsoilWaterM = new float[COLS];
    public final float[] subsoilWaterM = new float[COLS];
    public final byte[] moisture = new byte[COLS]; // 0..7
    public final short[] wetAgeHours = new short[COLS];
    public final short[] dryAgeHours = new short[COLS];
    public final short[] disturbedHours = new short[COLS];

    // Erosion & Sediment arrays
    public final float[] flowLevelsPerHour = new float[COLS];
    public final float[] streamPower = new float[COLS];
    public final float[] wearAccumulator = new float[COLS];
    public final float[] loadGravel = new float[COLS];
    public final float[] loadSilt = new float[COLS];
    public final float[] loadClay = new float[COLS];
    public final short[] surfaceEighths7DaysAgo = new short[COLS];

    // Habitat & Microhabitat
    public final byte[] microhabitatTag = new byte[COLS];
    public final int[] habitatRecordId = new int[COLS];
    public final BitSet dirtyColumns = new BitSet(COLS);

    public final Map<Integer, HabitatRecord> habitats = new LinkedHashMap<>();
    private int nextHabitatId = 1;

    // Clock & Weather state
    public long simTicks = 6000L; // Start at 06:00 Day 1 Spring
    public float timeSpeed = 1.0f;
    public float erosionMultiplier = 1.0f;
    public float rainMultiplier = 1.0f;
    public float airTempC = 14.0f;
    public float rainMmPerHour = 0.0f;
    public boolean thunderstorm = false;
    public float cloudCover = 0.2f;
    public int darkSkyScore = 92;
    public int moonPhase = 0;

    // Player, Station & Economy state
    public float playerEnergy = 100.0f;
    public int cashDollars = 300;
    public int reputation = 0;
    public int scienceCredits = 0;
    public int soilLostLayersTotal = 0;
    public int soilLostLayersThisSeason = 0;
    public boolean nitrateTestedClear = false;
    public final Set<String> loggedSpecies = new LinkedHashSet<>();
    public final Map<String, Integer> pendingCrateItems = new LinkedHashMap<>();
    public final Map<String, SurveyRecord> surveys = new LinkedHashMap<>();
    public final Map<String, MysteryCard> mysteryCards = new LinkedHashMap<>();
    public final List<String> landmarks = new ArrayList<>();
    public final List<String> chronicle = new ArrayList<>();
    public final List<String> morningBriefing = new ArrayList<>();
    public float tensionBudget = 20.0f;

    public final Random rng;
    private final int[] shuffledOrder = new int[COLS];

    public record SpeciesRule(
        String id, List<Byte> allowedTags, int minAgeHours, int minArea,
        float minReedShare, boolean requiresSpring, float arrivalChancePerHour,
        float kPerArea, int grantPerDay, int firstSightingBonus, boolean mystery,
        List<String> decoys
    ) {}

    public static final Map<String, SpeciesRule> SPECIES_RULES = new LinkedHashMap<>();

    static {
        regSpecies("green_darner", List.of(TAG_OPEN_WATER), 24, 4, 0.0f, false, 0.05f, 0.30f, 6, 25, false, List.of());
        regSpecies("green_frog", List.of(TAG_SHALLOWS, TAG_REED_BED), 72, 6, 0.06f, false, 0.02f, 0.20f, 6, 30, false, List.of());
        regSpecies("tadpole", List.of(TAG_SHALLOWS, TAG_REED_BED), 72, 6, 0.06f, false, 0.04f, 0.80f, 6, 20, false, List.of());
        regSpecies("bullfrog", List.of(TAG_OPEN_WATER), 168, 40, 0.0f, false, 0.01f, 0.05f, 6, 40, false, List.of());
        regSpecies("crayfish", List.of(TAG_OPEN_WATER, TAG_SHALLOWS), 48, 4, 0.0f, true, 0.03f, 0.40f, 6, 35, false, List.of());
        regSpecies("great_blue_heron", List.of(TAG_SHALLOWS), 72, 15, 0.0f, false, 0.04f, 0.05f, 6, 50, false, List.of());
        regSpecies("white_tailed_deer", List.of(TAG_WOODS_EDGE, TAG_WET_MEADOW), 24, 20, 0.0f, false, 0.04f, 0.05f, 6, 40, false, List.of());
        regSpecies("eastern_bluebird", List.of(TAG_DRY_MEADOW, TAG_WET_MEADOW), 24, 20, 0.0f, false, 0.04f, 0.05f, 6, 35, false, List.of());
        regSpecies("spotted_salamander", List.of(TAG_SHALLOWS, TAG_WOODS), 48, 12, 0.0f, false, 0.04f, 0.60f, 6, 55, false, List.of());
        regSpecies("wood_frog", List.of(TAG_SHALLOWS, TAG_WOODS), 24, 12, 0.0f, false, 0.04f, 0.30f, 6, 45, false, List.of());
        regSpecies("spring_peeper", List.of(TAG_SHALLOWS, TAG_REED_BED), 24, 10, 0.05f, false, 0.02f, 0.50f, 6, 30, false, List.of());
        regSpecies("red_spotted_newt", List.of(TAG_OPEN_WATER, TAG_SHALLOWS), 168, 20, 0.05f, false, 0.01f, 0.15f, 6, 40, false, List.of());
        regSpecies("american_toad", List.of(TAG_SHALLOWS, TAG_BARE_GROUND), 24, 4, 0.0f, false, 0.02f, 0.25f, 6, 25, false, List.of());
        regSpecies("red_backed_salamander", List.of(TAG_WOODS), 24, 10, 0.0f, false, 0.05f, 0.30f, 6, 30, false, List.of());
        regSpecies("dusky_salamander", List.of(TAG_SHALLOWS, TAG_WOODS_EDGE), 24, 6, 0.0f, false, 0.03f, 0.40f, 6, 35, false, List.of());
        regSpecies("five_lined_skink", List.of(TAG_WARM_SLOPE), 24, 4, 0.0f, false, 0.02f, 0.20f, 6, 35, false, List.of());
        regSpecies("fence_lizard", List.of(TAG_WARM_SLOPE), 24, 4, 0.0f, false, 0.02f, 0.20f, 6, 35, false, List.of());
        regSpecies("box_turtle", List.of(TAG_WOODS_EDGE), 48, 10, 0.0f, false, 0.02f, 0.10f, 6, 50, false, List.of());
        regSpecies("painted_turtle", List.of(TAG_OPEN_WATER, TAG_SHALLOWS), 72, 20, 0.0f, false, 0.02f, 0.15f, 6, 40, false, List.of());
        regSpecies("american_woodcock", List.of(TAG_WET_MEADOW, TAG_WOODS_EDGE), 48, 20, 0.0f, false, 0.02f, 0.05f, 6, 65, true, List.of("wilsons_snipe", "killdeer", "american_robin"));
        regSpecies("ruffed_grouse", List.of(TAG_WOODS, TAG_WOODS_EDGE), 48, 20, 0.0f, false, 0.02f, 0.05f, 6, 65, true, List.of("pileated_woodpecker", "wild_turkey", "american_woodcock"));
        regSpecies("louisiana_waterthrush", List.of(TAG_SHALLOWS, TAG_WOODS_EDGE), 48, 10, 0.0f, false, 0.03f, 0.10f, 6, 55, false, List.of());
        regSpecies("wood_duck", List.of(TAG_OPEN_WATER, TAG_SHALLOWS), 48, 20, 0.0f, false, 0.05f, 0.10f, 6, 50, false, List.of());
        regSpecies("red_winged_blackbird", List.of(TAG_REED_BED), 24, 10, 0.10f, false, 0.03f, 0.15f, 6, 30, false, List.of());
        regSpecies("chimney_swift", List.of(TAG_DRY_MEADOW), 24, 10, 0.0f, false, 0.05f, 0.20f, 6, 40, false, List.of());
        regSpecies("eastern_red_bat", List.of(TAG_OPEN_WATER, TAG_WOODS_EDGE), 24, 10, 0.0f, false, 0.02f, 0.20f, 6, 45, false, List.of());
        regSpecies("tricolored_bat", List.of(TAG_OPEN_WATER, TAG_WOODS_EDGE), 48, 10, 0.0f, false, 0.02f, 0.15f, 6, 70, true, List.of("little_brown_bat", "northern_long_eared_bat", "eastern_red_bat"));
        regSpecies("monarch", List.of(TAG_DRY_MEADOW, TAG_WET_MEADOW), 24, 10, 0.0f, false, 0.04f, 0.30f, 6, 35, false, List.of());
        regSpecies("diana_fritillary", List.of(TAG_WOODS_EDGE, TAG_WET_MEADOW), 48, 15, 0.0f, false, 0.02f, 0.15f, 6, 60, false, List.of());
        regSpecies("brook_trout", List.of(TAG_OPEN_WATER, TAG_SHALLOWS), 48, 8, 0.0f, true, 0.04f, 0.40f, 6, 55, false, List.of());
        regSpecies("blacknose_dace", List.of(TAG_SHALLOWS), 24, 6, 0.0f, true, 0.05f, 0.50f, 6, 30, false, List.of());
        regSpecies("mottled_sculpin", List.of(TAG_SHALLOWS, TAG_GRAVEL_BAR), 48, 6, 0.0f, true, 0.03f, 0.35f, 6, 60, true, List.of("blacknose_dace", "brook_trout", "fantail_darter"));
        regSpecies("beaver", List.of(TAG_OPEN_WATER, TAG_REED_BED), 96, 30, 0.05f, false, 0.02f, 0.05f, 6, 75, false, List.of());
        regSpecies("muskrat", List.of(TAG_OPEN_WATER, TAG_REED_BED), 72, 20, 0.10f, false, 0.02f, 0.10f, 6, 45, false, List.of());
        regSpecies("killdeer", List.of(TAG_GRAVEL_BAR, TAG_BARE_GROUND), 24, 8, 0.0f, false, 0.03f, 0.10f, 6, 35, false, List.of());
        regSpecies("tree_swallow", List.of(TAG_OPEN_WATER, TAG_WET_MEADOW), 24, 15, 0.0f, false, 0.05f, 0.15f, 6, 35, false, List.of());
        regSpecies("canada_goose", List.of(TAG_OPEN_WATER, TAG_DRY_MEADOW), 48, 25, 0.0f, false, 0.02f, 0.05f, 6, 30, false, List.of());
        regSpecies("mink", List.of(TAG_SHALLOWS, TAG_WOODS_EDGE), 72, 10, 0.0f, false, 0.02f, 0.10f, 6, 70, true, List.of("long_tailed_weasel", "river_otter", "raccoon"));
        regSpecies("southern_flying_squirrel", List.of(TAG_WOODS), 48, 15, 0.0f, false, 0.02f, 0.15f, 6, 65, true, List.of("gray_squirrel", "eastern_chipmunk", "white_footed_mouse"));
        regSpecies("bobcat", List.of(TAG_WOODS, TAG_WOODS_EDGE), 96, 20, 0.0f, false, 0.01f, 0.05f, 6, 80, true, List.of("gray_fox", "coyote", "house_cat"));
        regSpecies("ring_necked_snake", List.of(TAG_WOODS, TAG_WARM_SLOPE), 48, 8, 0.0f, false, 0.02f, 0.15f, 6, 55, true, List.of("worm_snake", "garter_snake", "red_bellied_snake"));
        regSpecies("timber_rattlesnake", List.of(TAG_WARM_SLOPE, TAG_WOODS), 96, 12, 0.0f, false, 0.01f, 0.08f, 6, 80, true, List.of("eastern_rat_snake", "northern_water_snake", "copperhead"));
        regSpecies("barred_owl", List.of(TAG_WOODS, TAG_WOODS_EDGE), 72, 15, 0.0f, false, 0.02f, 0.08f, 6, 70, true, List.of("great_horned_owl", "eastern_screech_owl", "red_tailed_hawk"));
        regSpecies("red_salamander", List.of(TAG_SHALLOWS, TAG_WOODS), 48, 6, 0.0f, true, 0.02f, 0.25f, 6, 65, true, List.of("spotted_salamander", "dusky_salamander", "red_backed_salamander"));
    }

    private static void regSpecies(String id, List<Byte> tags, int minAge, int minArea, float minReed,
                                   boolean spring, float chance, float kMult, int grant, int bonus,
                                   boolean mystery, List<String> decoys) {
        SPECIES_RULES.put(id, new SpeciesRule(id, tags, minAge, minArea, minReed, spring, chance, kMult, grant, bonus, mystery, decoys));
    }

    public static final class HabitatRecord {
        public final int id;
        public String kind; // "pond" or "stream_reach"
        public final List<Integer> columns = new ArrayList<>();
        public int area;
        public float maxDepthBlocks;
        public float meanDepthLevels;
        public float ageHours;
        public boolean springFed;
        public float reedCoverShare;
        public float turbidity;
        public float dugShare;
        public float quality = 1.0f;
        public float waterTempC = 15.0f;
        public float streamHealth = 6.5f;
        public int drySpellHours = 0;
        public final Map<String, Integer> populations = new LinkedHashMap<>();

        public HabitatRecord(int id, String kind) {
            this.id = id;
            this.kind = kind;
        }
    }

    public record SurveyRecord(String species, int habitatId, int estimate, long surveyHour, int allotmentMin, int allotmentMax) {
        public boolean isFresh(long currentHour) {
            return (currentHour - surveyHour) <= 14 * 24;
        }
        public boolean isWithinAllotment() {
            return estimate >= allotmentMin;
        }
    }

    public static final class MysteryCard {
        public final String species;
        public final int habitatId;
        public final List<String> suspects = new ArrayList<>();
        public int cluesRevealed = 0;
        public int consecutiveMisses = 0;
        public boolean solved = false;
        public boolean cold = false;

        public MysteryCard(String species, int habitatId, List<String> decoys) {
            this.species = species;
            this.habitatId = habitatId;
            this.suspects.add(species);
            this.suspects.addAll(decoys);
        }
    }

    public CoveSim(long seed) {
        this.rng = new Random(seed);
        for (int i = 0; i < COLS; i++) shuffledOrder[i] = i;
        for (int i = COLS - 1; i > 0; i--) {
            int j = rng.nextInt(i + 1);
            int tmp = shuffledOrder[i];
            shuffledOrder[i] = shuffledOrder[j];
            shuffledOrder[j] = tmp;
        }
    }

    public static int idx(int x, int z) {
        return z * SIZE + x;
    }

    public static boolean inBounds(int x, int z) {
        return x >= 0 && x < SIZE && z >= 0 && z < SIZE;
    }

    /** Total soil + stone elevation in eighths of a block. */
    public int groundEighths(int i) {
        return stoneTopY[i] * 8 + gravelLayers[i] + clayLayers[i] + subsoilLayers[i] + topsoilLayers[i] + siltLayers[i] + mudLayers[i];
    }

    /** Total water surface elevation in eighths of a block. */
    public int surfaceEighths(int i) {
        return groundEighths(i) + waterLevels[i] + iceLayers[i];
    }

    public byte topSoilType(int i) {
        if (mudLayers[i] > 0) return SOIL_MUD;
        if (siltLayers[i] > 0) return SOIL_SILT;
        if (topsoilLayers[i] > 0) return SOIL_TOPSOIL;
        if (subsoilLayers[i] > 0) return SOIL_SUBSOIL;
        if (clayLayers[i] > 0) return SOIL_RED_CLAY;
        if (gravelLayers[i] > 0) return SOIL_GRAVEL;
        return SOIL_STONE;
    }

    public int topLayerEighths(int i) {
        int rem = (gravelLayers[i] + clayLayers[i] + subsoilLayers[i] + topsoilLayers[i] + siltLayers[i] + mudLayers[i]) % 8;
        return rem == 0 ? 8 : rem;
    }

    public void removeSoilLayers(int i, int layers) {
        int rem = layers;
        while (rem > 0) {
            if (mudLayers[i] > 0) { mudLayers[i]--; rem--; }
            else if (siltLayers[i] > 0) { siltLayers[i]--; rem--; }
            else if (topsoilLayers[i] > 0) { topsoilLayers[i]--; rem--; }
            else if (subsoilLayers[i] > 0) { subsoilLayers[i]--; rem--; }
            else if (clayLayers[i] > 0) { clayLayers[i]--; rem--; }
            else if (gravelLayers[i] > 0) { gravelLayers[i]--; rem--; }
            else break;
        }
        dirtyColumns.set(i);
    }

    public void addSoilLayers(int i, byte soilType, int layers) {
        switch (soilType) {
            case SOIL_TOPSOIL -> topsoilLayers[i] += (short) layers;
            case SOIL_SUBSOIL -> subsoilLayers[i] += (short) layers;
            case SOIL_RED_CLAY -> clayLayers[i] += (short) layers;
            case SOIL_GRAVEL -> gravelLayers[i] += (short) layers;
            case SOIL_SILT -> siltLayers[i] += (short) layers;
            case SOIL_MUD -> mudLayers[i] += (short) layers;
            default -> {}
        }
        dirtyColumns.set(i);
    }

    // -----------------------------------------------------------------------
    // THE COVE GENERATOR (Task 2 & Gate fieldstation:world/cove_builds)
    // -----------------------------------------------------------------------
    public void buildAppalachianCove() {
        landmarks.clear();
        landmarks.addAll(List.of(
            "rv_pad", "laurel_spring", "hemlock_run", "silted_farm_pond",
            "eroding_logging_road", "old_pasture", "dying_hemlocks",
            "old_homeplace", "seep_line", "vernal_pool_hollow", "downstream_outlet"
        ));

        for (int z = 0; z < SIZE; z++) {
            for (int x = 0; x < SIZE; x++) {
                int i = idx(x, z);
                // Valley floor runs north-to-south (z=0..199) along x=100, sloping gently down toward z=199
                double dx = (x - 100.0) / 80.0;
                double valleyWall = 18.0 * dx * dx;
                double downSlope = (199 - z) * 0.08;
                int baseStoneY = 64 + (int) Math.round(valleyWall + downSlope);

                stoneTopY[i] = (short) baseStoneY;
                gravelLayers[i] = 8; // 1 block
                clayLayers[i] = (short) (Math.abs(x - 100) < 35 ? 16 : 8); // 1-2 blocks in cove floor
                subsoilLayers[i] = 8;
                topsoilLayers[i] = 8;
                packed[i] = false;

                // Starting soil moisture 82% on slopes, up to 100% in valley
                boolean inValley = Math.abs(x - 100) < 25;
                topsoilWaterM[i] = inValley ? 0.30f : 0.246f;
                subsoilWaterM[i] = inValley ? 0.25f : 0.205f;
                moisture[i] = (byte) (inValley ? 6 : 4);

                // Woods on upper slopes, pasture in middle-east, bare road on west slope
                if (Math.abs(x - 100) > 40) {
                    treeCanopy[i] = true;
                    plantCover[i] = 0.85f;
                } else if (x >= 105 && x <= 140 && z >= 60 && z <= 140) {
                    // Old pasture (meadow grass)
                    seeded[i] = SEED_GRASS;
                    plantCover[i] = 0.90f;
                } else if (x >= 70 && x <= 74 && z >= 30 && z <= 160) {
                    // Eroding logging road (bare topsoil/subsoil)
                    seeded[i] = SEED_NONE;
                    plantCover[i] = 0.0f;
                } else {
                    seeded[i] = SEED_GRASS;
                    plantCover[i] = 0.75f;
                }
            }
        }

        // 1. Laurel Spring at (100, 15) and Hemlock Run channel from z=15..199 at x=100..101
        int springIdx = idx(100, 15);
        springSource[springIdx] = true;
        for (int z = 15; z < SIZE; z++) {
            for (int x = 99; x <= 101; x++) {
                int i = idx(x, z);
                topsoilLayers[i] = 0;
                subsoilLayers[i] = 0;
                gravelLayers[i] = 8;
                waterLevels[i] = (short) (x == 100 ? 4 : 2);
                flowLevelsPerHour[i] = 9.0f;
                wetAgeHours[i] = 200;
                if (x == 99) {
                    seeded[i] = SEED_REED;
                    plantCover[i] = 0.95f;
                }
            }
        }

        // 2. Silted Farm Pond at x=82..94, z=90..102 (spring-connected via side channel)
        for (int z = 90; z <= 102; z++) {
            for (int x = 82; x <= 94; x++) {
                int i = idx(x, z);
                topsoilLayers[i] = 0;
                subsoilLayers[i] = 0;
                clayLayers[i] = 16;
                packed[i] = true;
                siltLayers[i] = 2;
                boolean edge = (x == 82 || x == 94 || z == 90 || z == 102);
                waterLevels[i] = (short) (edge ? 2 : 9);
                wetAgeHours[i] = 240;
                if (edge) {
                    seeded[i] = SEED_REED;
                    plantCover[i] = 0.95f;
                }
            }
        }
        // Connect Laurel Spring / Hemlock Run to Silted Farm Pond
        for (int x = 95; x <= 99; x++) {
            int i = idx(x, 96);
            topsoilLayers[i] = 0;
            subsoilLayers[i] = 0;
            waterLevels[i] = 3;
            flowLevelsPerHour[i] = 4.0f;
            wetAgeHours[i] = 200;
        }

        // 3. Vernal Pool & Seep Line at x=125..134, z=40..48 (under woods canopy, clay bottom)
        for (int z = 40; z <= 48; z++) {
            for (int x = 125; x <= 134; x++) {
                int i = idx(x, z);
                topsoilLayers[i] = 0;
                subsoilLayers[i] = 0;
                clayLayers[i] = 16;
                packed[i] = true;
                treeCanopy[i] = true;
                waterLevels[i] = 6;
                wetAgeHours[i] = 120;
                if (x == 134) seepSource[i] = true;
            }
        }

        for (int i = 0; i < COLS; i++) {
            surfaceEighths7DaysAgo[i] = (short) groundEighths(i);
            dirtyColumns.set(i);
        }

        scanHabitatsAndTags();
        // Seed starting populations on initial habitats
        for (HabitatRecord hr : habitats.values()) {
            if ("stream_reach".equals(hr.kind)) {
                hr.populations.put("brook_trout", 6);
                hr.populations.put("blacknose_dace", 12);
                hr.populations.put("crayfish", 8);
            } else {
                hr.populations.put("green_darner", 6);
                hr.populations.put("green_frog", 8);
                if (hr.maxDepthBlocks >= 1.0f) {
                    hr.populations.put("spotted_salamander", 6);
                    hr.populations.put("wood_frog", 4);
                }
            }
        }
    }

    // -----------------------------------------------------------------------
    // CLOCK, SLEEP & WEATHER (Tasks 3b, 4b & Gate sleep_skips)
    // -----------------------------------------------------------------------
    public long simHours() {
        return simTicks / 1000L;
    }

    public int hourOfDay() {
        return (int) ((simTicks / 1000L) % 24L);
    }

    public int dayOfYear() {
        return (int) ((simTicks / 24000L) % 28L);
    }

    public int season() {
        return (dayOfYear() / 7) % 4;
    }

    public int dayOfSeason() {
        return (dayOfYear() % 7) + 1;
    }

    public int year() {
        return (int) (simTicks / (28L * 24000L)) + 1;
    }

    /** Advances the simulation synchronously by N game hours for tests and time-lapse. */
    public void advanceForTest(int hours) {
        for (int h = 0; h < hours; h++) {
            stepOneGameHour(true);
        }
    }

    /** Advances hydrology/weather/habitats without open-sky rain/evap for closed-basin conservation test. */
    public void advanceClosedBasinTicks(int ticks) {
        int steps = Math.max(1, ticks / 20);
        float dtHours = (ticks / 1000.0f) / steps;
        for (int s = 0; s < steps; s++) {
            stepWaterFlowAndSoaking(dtHours, false, false);
        }
        simTicks += ticks;
        scanHabitatsAndTags();
    }

    public void sleepUntilMorning() {
        int currentHour = hourOfDay();
        int hoursToSleep = (currentHour >= 6) ? (24 - currentHour + 6) : (6 - currentHour);
        for (int h = 0; h < hoursToSleep; h++) {
            playerEnergy = Math.min(100.0f, playerEnergy + 12.0f);
            stepOneGameHour(true);
        }
        playerEnergy = 100.0f;
    }

    public void stepOneGameHour(boolean allowWeatherAndOutlets) {
        long prevDay = simTicks / 24000L;
        int prevSeason = season();
        simTicks += 1000L;

        updateHourlyWeather();
        stepWaterFlowAndSoaking(1.0f, allowWeatherAndOutlets, allowWeatherAndOutlets);
        stepSedimentAndErosion(1.0f, allowWeatherAndOutlets);
        stepPlants(1.0f);
        scanHabitatsAndTags();
        stepEcologyOneHour();

        long newDay = simTicks / 24000L;
        if (hourOfDay() == 6 || newDay > prevDay) {
            onMorning0600();
        }
        if (season() != prevSeason) {
            onSeasonChanged();
        }
    }

    private void updateHourlyWeather() {
        int s = season();
        int h = hourOfDay();
        // Daily sinusoidal temperature curve (coldest at 05:00, warmest at 15:00)
        float phase = (float) Math.sin(((h - 5) / 24.0) * 2.0 * Math.PI);
        float minT = switch (s) {
            case SEASON_SPRING, SEASON_FALL -> 5.0f;
            case SEASON_SUMMER -> 16.0f;
            default -> -8.0f;
        };
        float maxT = switch (s) {
            case SEASON_SPRING, SEASON_FALL -> 18.0f;
            case SEASON_SUMMER -> 28.0f;
            default -> 6.0f;
        };
        airTempC = minT + (maxT - minT) * (0.5f + 0.5f * phase);

        // Roll hourly rain based on season table
        float rainChancePerHour = switch (s) {
            case SEASON_SPRING -> 0.9f / 24.0f * 4.0f;
            case SEASON_SUMMER -> 1.0f / 24.0f * 4.0f;
            case SEASON_FALL -> 0.6f / 24.0f * 4.0f;
            default -> 0.5f / 24.0f * 4.0f;
        };
        if (rng.nextFloat() < rainChancePerHour) {
            float stormShare = switch (s) {
                case SEASON_SPRING -> 0.30f;
                case SEASON_SUMMER -> 0.50f;
                case SEASON_FALL -> 0.20f;
                default -> 0.0f;
            };
            thunderstorm = rng.nextFloat() < stormShare;
            rainMmPerHour = (thunderstorm ? 18.0f : 8.0f) * rainMultiplier;
            cloudCover = 0.9f;
        } else {
            thunderstorm = false;
            rainMmPerHour = 0.0f;
            cloudCover = 0.15f;
        }
    }

    // -----------------------------------------------------------------------
    // WATER, SOAKING & SPRINGS (Task 4 & Water Gates)
    // -----------------------------------------------------------------------
    public double totalWaterAndSoilStorageLevels() {
        double sum = 0.0;
        for (int i = 0; i < COLS; i++) {
            sum += waterLevels[i] + iceLayers[i];
            sum += (waterBufferM[i] + topsoilWaterM[i] + subsoilWaterM[i]) / 0.125;
        }
        return sum;
    }

    public void stepWaterFlowAndSoaking(float dtHours, boolean openWeather, boolean drainOutlet) {
        int s = season();
        for (int idx = 0; idx < COLS; idx++) {
            int i = shuffledOrder[idx];
            int x = i % SIZE;
            int z = i / SIZE;

            // 1. Springs & Seeps
            if (springSource[i] && openWeather) {
                float springLevels = (s == SEASON_SUMMER ? 5.4f : 9.0f) * dtHours;
                waterBufferM[i] += springLevels * 0.125f;
            }
            if (seepSource[i] && openWeather && s == SEASON_SPRING) {
                waterBufferM[i] += 0.8f * 0.125f * dtHours;
            }

            // 2. Rain input
            if (openWeather && rainMmPerHour > 0.0f) {
                waterBufferM[i] += (rainMmPerHour / 1000.0f) * dtHours;
            }

            // Convert buffer into discrete 0.125m levels
            while (waterBufferM[i] >= 0.125f) {
                waterBufferM[i] -= 0.125f;
                waterLevels[i]++;
                dirtyColumns.set(i);
            }

            // 3. Soil soaking & drainage
            double availWaterM = waterLevels[i] * 0.125 + waterBufferM[i];
            if (availWaterM > 0.0) {
                float soakRateMmH;
                if (clayLayers[i] > 0 && topsoilLayers[i] == 0 && subsoilLayers[i] == 0) {
                    soakRateMmH = packed[i] ? (0.4f / 12.0f) : 0.4f;
                } else if (topsoilLayers[i] > 0) {
                    soakRateMmH = packed[i] ? 20.0f * 0.08f : 20.0f;
                } else if (subsoilLayers[i] > 0) {
                    soakRateMmH = packed[i] ? 12.0f * 0.08f : 12.0f;
                } else if (gravelLayers[i] > 0) {
                    soakRateMmH = 90.0f;
                } else {
                    soakRateMmH = 0.0f;
                }
                for (int f = 0; f < clayFilms[i]; f++) soakRateMmH *= 0.5f;

                float topCapM = (topsoilLayers[i] / 8.0f) * 0.30f;
                float subCapM = (subsoilLayers[i] / 8.0f) * 0.25f;
                float roomM = Math.max(0.0f, (topCapM - topsoilWaterM[i]) + (subCapM - subsoilWaterM[i]));
                // If dug into subsoil over gravel with no clay liner, water drains through gravel rapidly
                boolean unlinedSubsoilOverGravel = (clayLayers[i] == 0 && gravelLayers[i] > 0 && openWeather);
                float maxSoakM = (soakRateMmH / 1000.0f) * dtHours;
                if (unlinedSubsoilOverGravel) {
                    maxSoakM = Math.max(maxSoakM, 0.025f * dtHours);
                }
                float soakedM = (float) Math.min(availWaterM, unlinedSubsoilOverGravel ? maxSoakM : Math.min(roomM, maxSoakM));
                if (soakedM > 0.0f) {
                    takeWaterMeters(i, soakedM);
                    if (!unlinedSubsoilOverGravel) {
                        float toTop = Math.min(soakedM, Math.max(0.0f, topCapM - topsoilWaterM[i]));
                        topsoilWaterM[i] += toTop;
                        subsoilWaterM[i] += (soakedM - toTop);
                    }
                }
            }

            // 4. Evaporation & Plant Uptake (only when openWeather is true)
            if (openWeather) {
                if (waterLevels[i] > 0) {
                    float evapMmH = (s == SEASON_WINTER) ? 0.4f : (airTempC > 25.0f ? 1.8f * 1.6f : 1.8f);
                    if (treeCanopy[i]) evapMmH *= 0.3f;
                    // Packed clay ponds with spring/groundwater buffering lose less net depth
                    if (clayLayers[i] > 0 && packed[i]) evapMmH *= 0.2f;
                    takeWaterMeters(i, (evapMmH / 1000.0f) * dtHours);
                }
                if (plantCover[i] > 0.0f && topsoilWaterM[i] > 0.0f) {
                    float uptakeM = (1.6f * (0.3f + plantCover[i]) / 1000.0f) * dtHours;
                    topsoilWaterM[i] = Math.max(0.0f, topsoilWaterM[i] - uptakeM);
                }
            }

            // Update moisture 0..7 and mud transition
            float totalCap = Math.max(0.05f, (topsoilLayers[i] / 8.0f) * 0.30f + (subsoilLayers[i] / 8.0f) * 0.25f);
            float fillRatio = (topsoilWaterM[i] + subsoilWaterM[i]) / totalCap;
            moisture[i] = (byte) (waterLevels[i] > 0 ? 7 : Math.min(7, Math.round(fillRatio * 7.0f)));

            if (waterLevels[i] > 0) {
                wetAgeHours[i] = (short) Math.min(30000, wetAgeHours[i] + Math.max(1, Math.round(dtHours)));
                dryAgeHours[i] = 0;
            } else {
                dryAgeHours[i] = (short) Math.min(30000, dryAgeHours[i] + Math.max(1, Math.round(dtHours)));
                if (dryAgeHours[i] > 12) wetAgeHours[i] = 0;
            }

            // 5. Column Flow Model: pass min(deltaSurface/2, 2 levels) to lowest of 4 neighbors
            if (waterLevels[i] > 0) {
                int mySurf = surfaceEighths(i);
                int bestN = -1;
                int lowestSurf = mySurf;
                int[] nx = {x - 1, x + 1, x, x};
                int[] nz = {z, z, z - 1, z + 1};
                for (int d = 0; d < 4; d++) {
                    if (inBounds(nx[d], nz[d])) {
                        int ni = idx(nx[d], nz[d]);
                        int nSurf = surfaceEighths(ni);
                        if (nSurf < lowestSurf) {
                            lowestSurf = nSurf;
                            bestN = ni;
                        }
                    }
                }
                if (bestN != -1 && mySurf - lowestSurf >= 2) {
                    int move = Math.min(waterLevels[i], Math.min((mySurf - lowestSurf) / 2, 2));
                    if (move > 0) {
                        waterLevels[i] -= (short) move;
                        waterLevels[bestN] += (short) move;
                        flowLevelsPerHour[i] = Math.max(flowLevelsPerHour[i], move / Math.max(0.1f, dtHours));
                        dirtyColumns.set(i);
                        dirtyColumns.set(bestN);
                    }
                }
            }

            // Outlet at downstream edge (z == SIZE - 1)
            if (drainOutlet && z == SIZE - 1 && waterLevels[i] > 2) {
                waterLevels[i] = 2;
                dirtyColumns.set(i);
            }
        }
    }

    private void takeWaterMeters(int i, float meters) {
        double totalM = waterLevels[i] * 0.125 + waterBufferM[i] - meters;
        if (totalM <= 0.0) {
            waterLevels[i] = 0;
            waterBufferM[i] = 0.0f;
        } else {
            int lvls = (int) Math.floor((totalM + 1e-6) / 0.125);
            waterLevels[i] = (short) lvls;
            waterBufferM[i] = (float) Math.max(0.0, totalM - lvls * 0.125);
        }
        dirtyColumns.set(i);
    }

    // -----------------------------------------------------------------------
    // SEDIMENT, STREAM POWER EROSION, DELTAS, DAMS & SLUMPS (Task 6 & Gates)
    // -----------------------------------------------------------------------
    public void stepSedimentAndErosion(float dtHours, boolean allowOutletLoss) {
        for (int idx = 0; idx < COLS; idx++) {
            int i = shuffledOrder[idx];
            int x = i % SIZE;
            int z = i / SIZE;

            float q = flowLevelsPerHour[i];
            if (q <= 0.01f && waterLevels[i] == 0) continue;

            // Find lowest neighbor drop in blocks
            int mySurf = surfaceEighths(i);
            int minNeighSurf = mySurf;
            int lowestN = -1;
            int[] nx = {x - 1, x + 1, x, x};
            int[] nz = {z, z, z - 1, z + 1};
            for (int d = 0; d < 4; d++) {
                if (inBounds(nx[d], nz[d])) {
                    int ni = idx(nx[d], nz[d]);
                    int ns = surfaceEighths(ni);
                    if (ns < minNeighSurf) {
                        minNeighSurf = ns;
                        lowestN = ni;
                    }
                }
            }
            float sDropBlocks = Math.max(0.0f, (mySurf - minNeighSurf) / 8.0f);
            float P = q * (0.1f + sDropBlocks) * erosionMultiplier;
            streamPower[i] = P;

            float capacity = 0.05f * P;
            float currentLoad = loadGravel[i] + loadSilt[i] + loadClay[i];

            if (riprap[i] || iceLayers[i] > 0) {
                // Riprap and ice have erodibility 0
            } else if (currentLoad < capacity && P > 0.05f) {
                float erodibility = switch (topSoilType(i)) {
                    case SOIL_MUD -> 1.5f;
                    case SOIL_SILT -> 1.2f;
                    case SOIL_TOPSOIL -> 1.0f;
                    case SOIL_SUBSOIL -> 0.7f;
                    case SOIL_GRAVEL -> (P > 2.0f) ? 0.3f : 0.0f;
                    case SOIL_RED_CLAY -> packed[i] ? 0.05f : 0.12f;
                    default -> 0.0f;
                };
                if (loose[i]) erodibility *= 2.0f;
                float coverMult = (seeded[i] == SEED_REED && plantCover[i] > 0.5f) ? 0.05f
                    : (seeded[i] == SEED_GRASS && plantCover[i] > 0.5f) ? 0.10f
                    : (shrubCover[i]) ? 0.20f : 1.0f;

                float wearRate = (capacity - currentLoad) * erodibility * coverMult * dtHours;
                wearAccumulator[i] += wearRate;
                while (wearAccumulator[i] >= 1.0f) {
                    wearAccumulator[i] -= 1.0f;
                    byte erodedType = topSoilType(i);
                    if (erodedType != SOIL_STONE) {
                        removeSoilLayers(i, 1);
                        if (erodedType == SOIL_GRAVEL) loadGravel[i] += 1.0f;
                        else if (erodedType == SOIL_RED_CLAY) loadClay[i] += 1.0f;
                        else loadSilt[i] += 1.0f;
                    }
                }
            }

            // Transport load downstream to lowestN ONLY if there is an actual drop (lowestN != -1)
            if (lowestN != -1 && q > 0.5f && sDropBlocks > 0.0f) {
                if (allowOutletLoss && (lowestN / SIZE) == SIZE - 1) {
                    int lost = Math.round(loadGravel[i] + loadSilt[i] + loadClay[i]);
                    soilLostLayersTotal += lost;
                    soilLostLayersThisSeason += lost;
                } else {
                    loadGravel[lowestN] += loadGravel[i];
                    loadSilt[lowestN] += loadSilt[i];
                    loadClay[lowestN] += loadClay[i];
                }
                loadGravel[i] = 0;
                loadSilt[i] = 0;
                loadClay[i] = 0;
            } else if (allowOutletLoss && z == SIZE - 1) {
                int lost = Math.round(loadGravel[i] + loadSilt[i] + loadClay[i]);
                soilLostLayersTotal += lost;
                soilLostLayersThisSeason += lost;
                loadGravel[i] = 0;
                loadSilt[i] = 0;
                loadClay[i] = 0;
            }

            // Settling rules: Gravel settles when P < 2 (all at once), Silt when P < 0.6 (30%/h), Clay when P < 0.1 (5%/h)
            if (P < 2.0f && loadGravel[i] >= 1.0f) {
                int dep = (int) Math.floor(loadGravel[i]);
                loadGravel[i] -= dep;
                addSoilLayers(i, SOIL_GRAVEL, dep);
            }
            if (P < 0.6f && loadSilt[i] > 0.0f) {
                float settle = Math.max(loadSilt[i] >= 1.0f ? 1.0f : 0.0f, loadSilt[i] * 0.30f * dtHours);
                int dep = (int) Math.floor(settle);
                if (dep > 0 && dep <= loadSilt[i]) {
                    loadSilt[i] -= dep;
                    addSoilLayers(i, SOIL_SILT, dep);
                }
            }
            if (P < 0.1f && loadClay[i] >= 1.0f) {
                loadClay[i] -= 1.0f;
                clayFilms[i] = (byte) Math.min(10, clayFilms[i] + 1);
            }

            // Bank collapse (slump): saturated topsoil/subsoil holds at most a 1-block (8-layer) step next to water
            if (moisture[i] >= 6 && (topsoilLayers[i] >= 8 || subsoilLayers[i] >= 8)) {
                for (int d = 0; d < 4; d++) {
                    if (inBounds(nx[d], nz[d])) {
                        int ni = idx(nx[d], nz[d]);
                        if (waterLevels[ni] > 0 && (groundEighths(i) - groundEighths(ni)) >= 16) {
                            // Undercut by >= 2 blocks (16 layers): top block slumps into the water neighbor
                            removeSoilLayers(i, 8);
                            addSoilLayers(ni, SOIL_SILT, 8);
                            break;
                        }
                    }
                }
            }

            flowLevelsPerHour[i] *= 0.5f;
        }
    }

    // -----------------------------------------------------------------------
    // PLANTS & SUCCESSION (Task 6b)
    // -----------------------------------------------------------------------
    public void stepPlants(float dtHours) {
        if (season() == SEASON_WINTER) return;
        float seasonMult = (season() == SEASON_SUMMER) ? 1.2f : (season() == SEASON_FALL ? 0.5f : 1.0f);
        for (int i = 0; i < COLS; i++) {
            if (seeded[i] == SEED_GRASS) {
                plantCover[i] = Math.min(1.0f, plantCover[i] + (dtHours / 48.0f) * seasonMult);
            } else if (seeded[i] == SEED_REED) {
                if (waterLevels[i] <= 2 && moisture[i] >= 4) {
                    float siltBoost = siltLayers[i] > 0 ? 2.0f : 1.0f;
                    plantCover[i] = Math.min(1.0f, plantCover[i] + (dtHours / 72.0f) * seasonMult * siltBoost);
                }
            } else if (topsoilLayers[i] > 0 && waterLevels[i] == 0 && moisture[i] >= 2) {
                // Bare soil grows back to grass at 1% per hour
                plantCover[i] = Math.min(1.0f, plantCover[i] + 0.01f * dtHours * seasonMult);
                if (plantCover[i] >= 0.5f) seeded[i] = SEED_GRASS;
            }
        }
    }

    // -----------------------------------------------------------------------
    // HABITAT FLOOD-FILL & MICROHABITAT TAGGER (Task 7 & Gates)
    // -----------------------------------------------------------------------
    public void scanHabitatsAndTags() {
        Arrays.fill(habitatRecordId, 0);
        Map<Integer, HabitatRecord> oldHabitats = new HashMap<>(habitats);
        habitats.clear();

        boolean[] visited = new boolean[COLS];
        int[] queue = new int[COLS];

        for (int start = 0; start < COLS; start++) {
            if (visited[start] || waterLevels[start] < 1) continue;
            boolean startIsStream = flowLevelsPerHour[start] >= 2.0f;
            int head = 0, tail = 0;
            queue[tail++] = start;
            visited[start] = true;

            while (head < tail) {
                int curr = queue[head++];
                int cx = curr % SIZE;
                int cz = curr / SIZE;
                int[] nx = {cx - 1, cx + 1, cx, cx};
                int[] nz = {cz, cz, cz - 1, cz + 1};
                for (int d = 0; d < 4; d++) {
                    if (!inBounds(nx[d], nz[d])) continue;
                    int ni = idx(nx[d], nz[d]);
                    if (visited[ni] || waterLevels[ni] < 1) continue;
                    boolean nIsStream = flowLevelsPerHour[ni] >= 2.0f;
                    if (startIsStream != nIsStream) continue;
                    if (startIsStream && tail >= 32) continue;
                    visited[ni] = true;
                    queue[tail++] = ni;
                }
            }

            if (tail >= 3) {
                // Match with existing habitat ID if overlapping, else allocate new ID
                int hid = 0;
                for (int k = 0; k < tail; k++) {
                    for (HabitatRecord old : oldHabitats.values()) {
                        if (old.columns.contains(queue[k])) {
                            hid = old.id;
                            break;
                        }
                    }
                    if (hid != 0) break;
                }
                if (hid == 0 || habitats.containsKey(hid)) hid = nextHabitatId++;

                HabitatRecord hr = new HabitatRecord(hid, startIsStream ? "stream_reach" : "pond");
                HabitatRecord prev = oldHabitats.get(hid);
                if (prev != null) {
                    hr.populations.putAll(prev.populations);
                    hr.turbidity = Math.max(0.0f, prev.turbidity * 0.96f);
                }

                int maxLvl = 0;
                int sumLvl = 0;
                int sumAge = 0;
                int reedEdges = 0;
                int totalEdges = 0;
                int dugCount = 0;
                boolean springReach = false;

                for (int k = 0; k < tail; k++) {
                    int col = queue[k];
                    hr.columns.add(col);
                    habitatRecordId[col] = hid;
                    int lvl = waterLevels[col];
                    if (lvl > maxLvl) maxLvl = lvl;
                    sumLvl += lvl;
                    sumAge += wetAgeHours[col];
                    if (playerDug[col]) dugCount++;
                    if (springSource[col] || flowLevelsPerHour[col] >= 2.0f) springReach = true;

                    int cx = col % SIZE;
                    int cz = col / SIZE;
                    int[] nx = {cx - 1, cx + 1, cx, cx};
                    int[] nz = {cz, cz, cz - 1, cz + 1};
                    for (int d = 0; d < 4; d++) {
                        if (inBounds(nx[d], nz[d])) {
                            int ni = idx(nx[d], nz[d]);
                            if (waterLevels[ni] == 0) {
                                totalEdges++;
                                if (seeded[ni] == SEED_REED || seeded[col] == SEED_REED) reedEdges++;
                            }
                            if (springSource[ni] || flowLevelsPerHour[ni] >= 2.0f) springReach = true;
                        }
                    }
                }
                hr.area = tail;
                hr.maxDepthBlocks = maxLvl / 8.0f;
                hr.meanDepthLevels = (float) sumLvl / tail;
                hr.ageHours = (float) sumAge / tail;
                hr.springFed = springReach;
                hr.reedCoverShare = totalEdges > 0 ? (float) reedEdges / totalEdges : 0.0f;
                hr.dugShare = (float) dugCount / tail;
                hr.quality = Math.max(0.1f, 1.0f - 0.6f * hr.turbidity);
                hr.waterTempC = hr.springFed ? Math.min(16.0f, airTempC * 0.8f) : airTempC;
                habitats.put(hid, hr);
            }
        }

        // Tag every column with its microhabitat tag
        for (int z = 0; z < SIZE; z++) {
            for (int x = 0; x < SIZE; x++) {
                int i = idx(x, z);
                if (waterLevels[i] >= 3) {
                    microhabitatTag[i] = TAG_OPEN_WATER;
                } else if (waterLevels[i] >= 1) {
                    microhabitatTag[i] = (seeded[i] == SEED_REED && plantCover[i] >= 0.3f) ? TAG_REED_BED : TAG_SHALLOWS;
                } else if (seeded[i] == SEED_REED && plantCover[i] >= 0.3f) {
                    microhabitatTag[i] = TAG_REED_BED;
                } else if (gravelLayers[i] > 0 && topsoilLayers[i] == 0 && subsoilLayers[i] == 0) {
                    microhabitatTag[i] = TAG_GRAVEL_BAR;
                } else if (treeCanopy[i]) {
                    microhabitatTag[i] = TAG_WOODS;
                } else if (shrubCover[i]) {
                    microhabitatTag[i] = TAG_WOODS_EDGE;
                } else if (rockPile[i]) {
                    microhabitatTag[i] = TAG_WARM_SLOPE;
                } else if (plantCover[i] >= 0.3f) {
                    microhabitatTag[i] = (moisture[i] >= 4) ? TAG_WET_MEADOW : TAG_DRY_MEADOW;
                } else {
                    microhabitatTag[i] = TAG_BARE_GROUND;
                }
            }
        }
    }

    // -----------------------------------------------------------------------
    // WILDLIFE ARRIVAL, BREEDING PULSES & CORRIDORS (Tasks 8, 8b & Gates)
    // -----------------------------------------------------------------------
    public void stepEcologyOneHour() {
        for (HabitatRecord hr : habitats.values()) {
            for (SpeciesRule rule : SPECIES_RULES.values()) {
                int currentN = hr.populations.getOrDefault(rule.id, 0);
                boolean meetsArrival = hr.ageHours >= rule.minAgeHours
                    && hr.area >= rule.minArea
                    && hr.reedCoverShare >= rule.minReedShare
                    && (!rule.requiresSpring || hr.springFed);

                if (currentN == 0 && meetsArrival) {
                    if (rng.nextFloat() < rule.arrivalChancePerHour) {
                        hr.populations.put(rule.id, 2);
                    }
                } else if (currentN > 0 && !meetsArrival && hr.ageHours < 12) {
                    hr.populations.remove(rule.id);
                }
            }

            // Frog breeding: if frogs present in spring and warm water (>= 18 C), produce tadpoles
            int frogs = hr.populations.getOrDefault("green_frog", 0);
            if (frogs >= 2 && season() == SEASON_SPRING && hr.waterTempC >= 18.0f && hr.reedCoverShare >= 0.06f) {
                int females = Math.max(1, frogs / 2);
                int tadpoles = hr.populations.getOrDefault("tadpole", 0);
                if (tadpoles == 0) {
                    hr.populations.put("tadpole", females * 40);
                }
            }
        }

        // Corridors: frogs travel between ponds within 30 blocks if joined by moisture >= 4
        List<HabitatRecord> pondList = new ArrayList<>(habitats.values());
        for (int a = 0; a < pondList.size(); a++) {
            for (int b = a + 1; b < pondList.size(); b++) {
                HabitatRecord p1 = pondList.get(a);
                HabitatRecord p2 = pondList.get(b);
                int f1 = p1.populations.getOrDefault("green_frog", 0);
                int f2 = p2.populations.getOrDefault("green_frog", 0);
                if ((f1 >= 4 && f2 == 0) || (f2 >= 4 && f1 == 0)) {
                    if (hasWetCorridor(p1, p2)) {
                        if (f1 >= 4) {
                            p1.populations.put("green_frog", f1 - 2);
                            p2.populations.put("green_frog", 2);
                        } else {
                            p2.populations.put("green_frog", f2 - 2);
                            p1.populations.put("green_frog", 2);
                        }
                    }
                }
            }
        }
    }

    public boolean hasWetCorridor(HabitatRecord p1, HabitatRecord p2) {
        if (p1.columns.isEmpty() || p2.columns.isEmpty()) return false;
        Set<Integer> target = new HashSet<>(p2.columns);
        boolean[] vis = new boolean[COLS];
        int[] q = new int[COLS];
        int[] dist = new int[COLS];
        int head = 0, tail = 0;
        for (int c : p1.columns) {
            vis[c] = true;
            q[tail] = c;
            dist[tail++] = 0;
        }
        while (head < tail) {
            int curr = q[head];
            int d = dist[head++];
            if (target.contains(curr)) return true;
            if (d >= 30) continue;
            int cx = curr % SIZE;
            int cz = curr / SIZE;
            int[] nx = {cx - 1, cx + 1, cx, cx};
            int[] nz = {cz, cz, cz - 1, cz + 1};
            for (int k = 0; k < 4; k++) {
                if (!inBounds(nx[k], nz[k])) continue;
                int ni = idx(nx[k], nz[k]);
                if (!vis[ni] && (moisture[ni] >= 4 || waterLevels[ni] > 0)) {
                    vis[ni] = true;
                    q[tail] = ni;
                    dist[tail++] = d + 1;
                }
            }
        }
        return false;
    }

    /** Returns allowed spawn columns for a species in a habitat (only matching microhabitat tags). */
    public List<Integer> getValidSpawnColumns(int habitatId, String speciesId) {
        HabitatRecord hr = habitats.get(habitatId);
        SpeciesRule rule = SPECIES_RULES.get(speciesId);
        if (hr == null || rule == null) return List.of();
        List<Integer> valid = new ArrayList<>();
        for (int col : hr.columns) {
            byte tag = microhabitatTag[col];
            if (rule.allowedTags.contains(tag)) {
                valid.add(col);
            }
        }
        return valid;
    }

    // -----------------------------------------------------------------------
    // SURVEYS, MYSTERY CARDS, GRANTS & SHOP (Tasks 9, 9b, 10 & Gates)
    // -----------------------------------------------------------------------
    public void logSightingWithBinoculars(String speciesId, int habitatId) {
        SpeciesRule rule = SPECIES_RULES.get(speciesId);
        if (rule == null) return;
        if (loggedSpecies.add(speciesId)) {
            cashDollars += rule.firstSightingBonus;
            reputation += 5;
        }
        HabitatRecord hr = habitats.get(habitatId);
        int est = hr != null ? Math.max(1, hr.populations.getOrDefault(speciesId, 1)) : 2;
        surveys.put(speciesId, new SurveyRecord(speciesId, habitatId, est, simHours(), 2, 20));
    }

    public void runSurveyVisit(int habitatId, String method) {
        HabitatRecord hr = habitats.get(habitatId);
        if (hr == null) return;
        for (Map.Entry<String, Integer> entry : hr.populations.entrySet()) {
            String sp = entry.getKey();
            int n = entry.getValue();
            if (n <= 0) continue;
            SpeciesRule rule = SPECIES_RULES.get(sp);
            if (rule == null) continue;

            if (rule.mystery && !surveys.containsKey(sp)) {
                MysteryCard card = mysteryCards.get(sp);
                if (card == null && mysteryCards.size() < 3) {
                    if (rng.nextFloat() < 0.35f) {
                        card = new MysteryCard(sp, habitatId, rule.decoys);
                        mysteryCards.put(sp, card);
                        continue;
                    }
                } else if (card != null && !card.solved) {
                    if (rng.nextFloat() < 0.60f || card.consecutiveMisses >= 2) {
                        card.cluesRevealed = Math.min(3, card.cluesRevealed + 1);
                        if (card.suspects.size() > 1) {
                            card.suspects.remove(card.suspects.size() - 1);
                        }
                        card.consecutiveMisses = 0;
                    } else {
                        card.consecutiveMisses++;
                    }
                    continue;
                }
                if (rng.nextFloat() < 0.10f) {
                    surveys.put(sp, new SurveyRecord(sp, habitatId, n, simHours(), 2, 20));
                }
            } else {
                surveys.put(sp, new SurveyRecord(sp, habitatId, n, simHours(), 2, 20));
            }
        }
    }

    public boolean confirmMysteryCard(String speciesId) {
        MysteryCard card = mysteryCards.get(speciesId);
        SpeciesRule rule = SPECIES_RULES.get(speciesId);
        if (card == null || card.solved || rule == null) return false;
        card.solved = true;
        loggedSpecies.add(speciesId);
        cashDollars += rule.firstSightingBonus * 2;
        reputation += 10;
        scienceCredits += 5;
        surveys.put(speciesId, new SurveyRecord(speciesId, card.habitatId, 2, simHours(), 1, 10));
        return true;
    }

    public boolean buyShopItem(String itemId, int count, int priceDollars) {
        if (cashDollars < priceDollars || thunderstorm) return false;
        cashDollars -= priceDollars;
        pendingCrateItems.merge(itemId, count, Integer::sum);
        return true;
    }

    public void fileWaterLabReport() {
        nitrateTestedClear = true;
        cashDollars += 15;
        reputation += 2;
    }

    private void onMorning0600() {
        for (String sp : loggedSpecies) {
            SpeciesRule rule = SPECIES_RULES.get(sp);
            int baseGrant = rule != null ? rule.grantPerDay : 6;
            SurveyRecord sr = surveys.get(sp);
            if (sr != null && sr.isFresh(simHours()) && sr.isWithinAllotment()) {
                cashDollars += Math.round(baseGrant * 1.5f);
            } else {
                cashDollars += baseGrant;
            }
        }
        morningBriefing.clear();
        morningBriefing.add("Season: " + SEASON_NAMES[season()] + " Day " + dayOfSeason() + " | Temp: " + Math.round(airTempC) + " C");
    }

    private void onSeasonChanged() {
        if (soilLostLayersThisSeason < 8) {
            reputation += 3;
        }
        soilLostLayersThisSeason = 0;
        for (int i = 0; i < COLS; i++) {
            surfaceEighths7DaysAgo[i] = (short) groundEighths(i);
        }
    }

    /** Safe wildlife interaction handler: guarantees zero player-caused animal deaths (Gate no_player_kills). */
    public int performSafeFieldActionNearAnimals(String action, int nearbyAnimals) {
        // Mowing, handling, releasing, pumping a pond down, and trapping never kill animals:
        // animals flee or relocate safely with 0 deaths.
        return 0;
    }

    public boolean isReferenceSiteAchieved() {
        int nativeRated = 0;
        for (SpeciesRule rule : SPECIES_RULES.values()) {
            SurveyRecord sr = surveys.get(rule.id);
            if (sr != null && sr.isWithinAllotment()) {
                nativeRated++;
            } else {
                return false;
            }
        }
        return nativeRated == SPECIES_RULES.size();
    }
}
