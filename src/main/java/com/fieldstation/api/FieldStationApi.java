package com.fieldstation.api;

import com.fieldstation.sim.CoveSim;

/**
 * Shared service and event contracts in com.fieldstation.api (Minecraft development -> API events and services).
 * Systems talk to each other only through this package.
 */
public final class FieldStationApi {
    private static CoveSim activeSim = new CoveSim(262093L);

    private FieldStationApi() {}

    public static CoveSim sim() {
        return activeSim;
    }

    public static void setSim(CoveSim sim) {
        activeSim = sim;
    }

    public interface ClockService {
        long simTicks();
        long simHours();
        int season();
        int dayOfSeason();
        int year();
        void advanceForTest(int simHours);
    }

    public interface TerrainService {
        void markDirty(int colIndex);
        int groundEighths(int x, int z);
    }

    public interface WaterService {
        int waterLevels(int x, int z);
        void addWaterLevels(int x, int z, int delta);
    }

    public interface HabitatService {
        int habitatAt(int x, int z);
        String microhabitatTagAt(int x, int z);
    }

    public interface PopulationService {
        int count(int habitatId, String speciesId);
        void add(int habitatId, String speciesId, int delta);
        void remove(int habitatId, String speciesId, int delta);
    }

    public interface EconomyService {
        int cashDollars();
        boolean buy(String itemId, int count, int price);
    }
}
