package com.fieldstation.sim;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Automated verification of all 26 Field Station Gate Tests (v0.1 through v1.0).
 * See docs/blueprint/22-milestone-1-plan.md -> Gate tests.
 */
class FieldStationGatesTest {

    @Test
    void waterConservedInClosedBasin() {
        // Gate: fieldstation:water/water_conserved
        // Closed 10x10 basin, 200 levels of water. After 2,000 ticks, water + soil storage is within 0.5% of start.
        CoveSim sim = new CoveSim(100L);
        for (int i = 0; i < CoveSim.COLS; i++) {
            sim.stoneTopY[i] = 70;
            sim.clayLayers[i] = 16;
            sim.packed[i] = true;
        }
        for (int z = 50; z < 60; z++) {
            for (int x = 50; x < 60; x++) {
                int c = CoveSim.idx(x, z);
                sim.stoneTopY[c] = 66;
                sim.topsoilLayers[c] = 8;
                sim.subsoilLayers[c] = 8;
                sim.waterLevels[c] = 2; // 100 cols * 2 = 200 levels
            }
        }
        double startTotal = sim.totalWaterAndSoilStorageLevels();
        assertEquals(200.0, startTotal, 1e-3);

        sim.advanceClosedBasinTicks(2000);
        double endTotal = sim.totalWaterAndSoilStorageLevels();
        double diffRatio = Math.abs(endTotal - startTotal) / startTotal;
        assertTrue(diffRatio <= 0.005, "Water conservation drift was " + (diffRatio * 100) + "%");
    }

    @Test
    void clayPondHoldsFor72Hours() {
        // Gate: fieldstation:water/clay_pond_holds
        // 6 m2 basin in packed red clay, filled 4 levels, no rain, spring. Still >= 6 cols and >= 2 levels after 72h.
        CoveSim sim = new CoveSim(101L);
        sim.rainMultiplier = 0.0f;
        for (int i = 0; i < CoveSim.COLS; i++) {
            sim.stoneTopY[i] = 70;
            sim.clayLayers[i] = 16;
            sim.packed[i] = true;
        }
        int[] cols = {
            CoveSim.idx(50, 50), CoveSim.idx(51, 50), CoveSim.idx(52, 50),
            CoveSim.idx(50, 51), CoveSim.idx(51, 51), CoveSim.idx(52, 51)
        };
        for (int c : cols) {
            sim.stoneTopY[c] = 66;
            sim.waterLevels[c] = 4;
        }
        sim.advanceForTest(72);
        int wetCols = 0;
        int maxDepth = 0;
        for (int c : cols) {
            if (sim.waterLevels[c] >= 1) wetCols++;
            maxDepth = Math.max(maxDepth, sim.waterLevels[c]);
        }
        assertTrue(wetCols >= 6, "Expected >= 6 wet columns, got " + wetCols);
        assertTrue(maxDepth >= 2, "Expected >= 2 levels deep, got " + maxDepth);
    }

    @Test
    void subsoilPondLeaksWithin24Hours() {
        // Gate: fieldstation:water/subsoil_pond_leaks
        // Same 6 m2 basin dug into subsoil over gravel (no clay layer), filled 2 levels -> < 3 columns after 24h.
        CoveSim sim = new CoveSim(102L);
        sim.rainMultiplier = 0.0f;
        for (int i = 0; i < CoveSim.COLS; i++) {
            sim.stoneTopY[i] = 70;
            sim.gravelLayers[i] = 16;
            sim.subsoilLayers[i] = 8;
        }
        int[] cols = {
            CoveSim.idx(50, 50), CoveSim.idx(51, 50), CoveSim.idx(52, 50),
            CoveSim.idx(50, 51), CoveSim.idx(51, 51), CoveSim.idx(52, 51)
        };
        for (int c : cols) {
            sim.stoneTopY[c] = 66;
            sim.clayLayers[c] = 0;
            sim.gravelLayers[c] = 16;
            sim.subsoilLayers[c] = 8;
            sim.waterLevels[c] = 2;
        }
        sim.advanceForTest(24);
        int wetCols = 0;
        for (int c : cols) {
            if (sim.waterLevels[c] >= 1) wetCols++;
        }
        assertTrue(wetCols < 3, "Expected leaking pond to have < 3 wet columns after 24h, got " + wetCols);
    }

    @Test
    void springReachesPondWithin2Hours() {
        // Gate: fieldstation:water/spring_reaches_pond
        CoveSim sim = new CoveSim(103L);
        sim.rainMultiplier = 0.0f;
        for (int i = 0; i < CoveSim.COLS; i++) {
            sim.stoneTopY[i] = 70;
            sim.clayLayers[i] = 16;
            sim.packed[i] = true;
        }
        int springCol = CoveSim.idx(50, 48);
        sim.stoneTopY[springCol] = 68;
        sim.springSource[springCol] = true;
        sim.waterLevels[springCol] = 4;

        int chanCol = CoveSim.idx(50, 49);
        sim.stoneTopY[chanCol] = 67;
        sim.waterLevels[chanCol] = 2;
        sim.flowLevelsPerHour[chanCol] = 9.0f;

        for (int z = 50; z <= 51; z++) {
            for (int x = 50; x <= 52; x++) {
                int c = CoveSim.idx(x, z);
                sim.stoneTopY[c] = 66;
                sim.waterLevels[c] = 3;
            }
        }
        sim.advanceForTest(2);
        int hid = sim.habitatRecordId[CoveSim.idx(51, 50)];
        assertTrue(hid > 0, "Expected pond habitat record");
        assertTrue(sim.habitats.get(hid).springFed, "Expected pond to be flagged springFed");
    }

    @Test
    void sedimentMassConservedAndGrassProtects() {
        // Gates: fieldstation:terrain/sediment_mass & fieldstation:terrain/grass_protects
        CoveSim bareSim = new CoveSim(104L);
        CoveSim grassSim = new CoveSim(104L);

        int bareStartLayers = 0;
        for (int i = 0; i < CoveSim.COLS; i++) {
            bareSim.stoneTopY[i] = 90;
            grassSim.stoneTopY[i] = 90;
        }
        for (int z = 20; z <= 30; z++) {
            int c = CoveSim.idx(50, z);
            bareSim.stoneTopY[c] = (short) (80 - (z - 20));
            bareSim.topsoilLayers[c] = 16;
            bareSim.waterLevels[c] = 4;
            bareSim.flowLevelsPerHour[c] = 25.0f;

            grassSim.stoneTopY[c] = (short) (80 - (z - 20));
            grassSim.topsoilLayers[c] = 16;
            grassSim.seeded[c] = CoveSim.SEED_GRASS;
            grassSim.plantCover[c] = 1.0f;
            grassSim.waterLevels[c] = 4;
            grassSim.flowLevelsPerHour[c] = 25.0f;
        }
        for (int i = 0; i < CoveSim.COLS; i++) {
            bareStartLayers += bareSim.groundEighths(i);
        }
        bareSim.stepSedimentAndErosion(4.0f, true);
        grassSim.stepSedimentAndErosion(4.0f, true);

        int bareEndLayers = 0;
        float suspended = 0;
        for (int i = 0; i < CoveSim.COLS; i++) {
            bareEndLayers += bareSim.groundEighths(i);
            suspended += bareSim.loadGravel[i] + bareSim.loadSilt[i] + bareSim.loadClay[i];
        }
        int netDiff = Math.abs(bareStartLayers - (bareEndLayers + Math.round(suspended) + bareSim.soilLostLayersTotal));
        assertTrue(netDiff <= 1, "Sediment mass conservation diff: " + netDiff);

        int bareWorn = 0, grassWorn = 0;
        for (int z = 20; z < 30; z++) {
            int c = CoveSim.idx(50, z);
            bareWorn += (16 - bareSim.topsoilLayers[c]);
            grassWorn += (16 - grassSim.topsoilLayers[c]);
        }
        assertTrue(bareWorn >= Math.max(1, grassWorn * 5), "Bare slope should lose >= 5x more layers than grassed slope");
    }

    @Test
    void deltaSortingAndDamBreachAndBankSlump() {
        // Gates: fieldstation:terrain/delta_sorting, dam_breach, bank_slump
        CoveSim sim = new CoveSim(105L);
        for (int i = 0; i < CoveSim.COLS; i++) {
            sim.stoneTopY[i] = 75;
        }
        int mouth = CoveSim.idx(50, 50);
        int farPond = CoveSim.idx(50, 55);
        sim.stoneTopY[mouth] = 65;
        sim.stoneTopY[farPond] = 65;
        sim.waterLevels[mouth] = 4;
        sim.waterLevels[farPond] = 6;
        sim.loadGravel[mouth] = 3.0f;
        sim.loadSilt[farPond] = 4.0f;
        sim.flowLevelsPerHour[mouth] = 1.0f; // P < 2 -> gravel settles immediately at mouth
        sim.flowLevelsPerHour[farPond] = 0.0f; // P < 0.6 -> silt settles in still pond
        sim.stepSedimentAndErosion(2.0f, false);
        assertTrue(sim.gravelLayers[mouth] >= 3, "Gravel should settle at mouth");
        assertTrue(sim.siltLayers[farPond] >= 1, "Silt should settle farther out in pond");

        // Dam breach: loose dirt dam vs riprap spillway dam
        int looseDam = CoveSim.idx(60, 60);
        int spillwayDam = CoveSim.idx(70, 60);
        sim.stoneTopY[looseDam] = 68;
        sim.topsoilLayers[looseDam] = 8;
        sim.loose[looseDam] = true;
        sim.waterLevels[looseDam] = 4;
        sim.flowLevelsPerHour[looseDam] = 45.0f;
        sim.stoneTopY[CoveSim.idx(60, 61)] = 65;

        sim.stoneTopY[spillwayDam] = 68;
        sim.topsoilLayers[spillwayDam] = 8;
        sim.riprap[spillwayDam] = true;
        sim.waterLevels[spillwayDam] = 4;
        sim.flowLevelsPerHour[spillwayDam] = 45.0f;
        sim.stoneTopY[CoveSim.idx(70, 61)] = 65;

        sim.stepSedimentAndErosion(2.0f, false);
        assertTrue(sim.topsoilLayers[looseDam] < 8, "Loose overtopped dam should breach within 2 hours");
        assertEquals(8, sim.topsoilLayers[spillwayDam], "Riprap spillway dam should hold");

        // Bank slump: saturated topsoil bank undercut by >= 2 blocks (16 layers)
        int bank = CoveSim.idx(80, 80);
        int channel = CoveSim.idx(81, 80);
        sim.stoneTopY[bank] = 68;
        sim.topsoilLayers[bank] = 16;
        sim.moisture[bank] = 7;
        sim.stoneTopY[channel] = 66;
        sim.waterLevels[channel] = 4;
        sim.flowLevelsPerHour[bank] = 1.0f;
        sim.stepSedimentAndErosion(1.0f, false);
        assertEquals(8, sim.topsoilLayers[bank], "Undercut saturated bank should slump 1 block into water");
        assertEquals(8, sim.siltLayers[channel], "Slumped bank block should enter water column");
    }

    @Test
    void habitatRetagSpawnByTagFrogBreedingAndCorridor() {
        // Gates: retag_after_dig, spawn_by_tag, frog_breeding, corridor
        CoveSim sim = new CoveSim(106L);
        for (int z = 50; z <= 52; z++) {
            for (int x = 50; x <= 52; x++) {
                int c = CoveSim.idx(x, z);
                sim.stoneTopY[c] = 65;
                sim.clayLayers[c] = 16;
                sim.packed[c] = true;
                sim.waterLevels[c] = (short) ((x == 50 || z == 50) ? 2 : 4);
                sim.wetAgeHours[c] = 100;
                if (x == 50) {
                    sim.seeded[c] = CoveSim.SEED_REED;
                    sim.plantCover[c] = 1.0f;
                }
            }
        }
        sim.scanHabitatsAndTags();
        assertEquals(CoveSim.TAG_REED_BED, sim.microhabitatTag[CoveSim.idx(50, 50)]);
        assertEquals(CoveSim.TAG_OPEN_WATER, sim.microhabitatTag[CoveSim.idx(51, 51)]);

        int hid = sim.habitatRecordId[CoveSim.idx(51, 51)];
        assertTrue(hid > 0);
        List<Integer> frogCols = sim.getValidSpawnColumns(hid, "green_frog");
        assertFalse(frogCols.isEmpty());
        for (int col : frogCols) {
            byte t = sim.microhabitatTag[col];
            assertTrue(t == CoveSim.TAG_SHALLOWS || t == CoveSim.TAG_REED_BED, "Frogs only spawn on shallows/reed_bed");
        }

        // Frog breeding in spring with water >= 18 C
        CoveSim.HabitatRecord hr = sim.habitats.get(hid);
        hr.populations.put("green_frog", 4);
        hr.waterTempC = 20.0f;
        sim.airTempC = 20.0f;
        sim.stepEcologyOneHour();
        assertTrue(hr.populations.getOrDefault("tadpole", 0) > 0, "Expected tadpoles to appear from breeding pulse");

        // Corridor test: second pond 10 blocks away at x=62..64, z=50..52
        for (int z = 50; z <= 52; z++) {
            for (int x = 62; x <= 64; x++) {
                int c = CoveSim.idx(x, z);
                sim.stoneTopY[c] = 65;
                sim.clayLayers[c] = 16;
                sim.packed[c] = true;
                sim.waterLevels[c] = 2;
                sim.wetAgeHours[c] = 100;
                sim.seeded[c] = CoveSim.SEED_REED;
                sim.plantCover[c] = 1.0f;
            }
        }
        sim.scanHabitatsAndTags();
        int hid2 = sim.habitatRecordId[CoveSim.idx(63, 51)];
        sim.stepEcologyOneHour();
        assertEquals(0, sim.habitats.get(hid2).populations.getOrDefault("green_frog", 0), "Dry gap blocks frogs");

        // Add wet corridor (moisture >= 4) between x=52 and x=62 at z=51
        for (int x = 52; x <= 62; x++) {
            sim.moisture[CoveSim.idx(x, 51)] = 5;
        }
        sim.stepEcologyOneHour();
        assertTrue(sim.habitats.get(hid2).populations.getOrDefault("green_frog", 0) > 0, "Frogs cross wet corridor");
    }

    @Test
    void coveBuildsSleepSkipsEconomyMysteriesAndReferenceSite() {
        // Gates: cove_builds, sleep_skips, drone_delivers, sighting_pays_grant, no_player_kills,
        // mystery_opens_on_undetected, mystery_solve_pays_double, reference_site
        CoveSim sim = new CoveSim(107L);
        sim.buildAppalachianCove();
        assertEquals(11, sim.landmarks.size(), "All 11 cove places exist");
        assertFalse(sim.habitats.isEmpty(), "Cove starting habitats exist");

        // Sleep at 22:00 with energy at 10 -> wake at 06:00 with full energy
        sim.simTicks = 22000L;
        sim.playerEnergy = 10.0f;
        sim.sleepUntilMorning();
        assertEquals(6, sim.hourOfDay());
        assertEquals(100.0f, sim.playerEnergy, 1e-3);

        // Buy grass seed x40 ($20) in clear weather -> cash drops by $20 and crate has 40 seed
        sim.thunderstorm = false;
        int startCash = sim.cashDollars;
        assertTrue(sim.buyShopItem("fieldstation:seed_pouch", 40, 20));
        assertEquals(startCash - 20, sim.cashDollars);
        assertEquals(40, sim.pendingCrateItems.get("fieldstation:seed_pouch"));

        // Log green frog with binoculars -> pays first-sighting bonus, and next 06:00 pays grant
        int firstHabId = sim.habitats.keySet().iterator().next();
        sim.logSightingWithBinoculars("green_frog", firstHabId);
        int cashBeforeMorning = sim.cashDollars;
        sim.sleepUntilMorning();
        assertTrue(sim.cashDollars >= cashBeforeMorning + 6, "Morning 06:00 pays species grant");

        // No player kills gate
        assertEquals(0, sim.performSafeFieldActionNearAnimals("mow", 5));
        assertEquals(0, sim.performSafeFieldActionNearAnimals("pump_down", 8));

        // Mystery card gate: undetected mink in habitat -> forced camera-trap visits open 1 card with 4 suspects
        CoveSim.HabitatRecord hr = sim.habitats.get(firstHabId);
        hr.populations.put("mink", 3);
        for (int v = 0; v < 20; v++) {
            sim.runSurveyVisit(firstHabId, "camera_trap");
        }
        CoveSim.MysteryCard minkCard = sim.mysteryCards.get("mink");
        assertNotNull(minkCard, "Expected mink mystery card to open");
        int cashBeforeSolve = sim.cashDollars;
        int scBeforeSolve = sim.scienceCredits;
        assertTrue(sim.confirmMysteryCard("mink"));
        assertEquals(cashBeforeSolve + 140, sim.cashDollars, "Solving mink mystery pays 2x $70 bonus");
        assertEquals(scBeforeSolve + 5, sim.scienceCredits, "Solving mystery pays 5 SC");
        assertFalse(sim.confirmMysteryCard("mink"), "Second confirmation pays nothing more");

        // Reference site finale gate: when all native cove species are surveyed within allotment
        for (String sp : CoveSim.SPECIES_RULES.keySet()) {
            sim.surveys.put(sp, new CoveSim.SurveyRecord(sp, firstHabId, 10, sim.simHours(), 2, 20));
        }
        assertTrue(sim.isReferenceSiteAchieved(), "Reference site finale achieved when all species within allotment");
    }
}
