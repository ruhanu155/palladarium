package com.fieldstation.clock;

import com.fieldstation.api.FieldStationApi;
import net.minecraft.core.Holder;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.clock.WorldClock;
import net.minecraft.world.level.gamerules.GameRules;

/**
 * The one clock layer (Minecraft development -> The clock layer).
 * Only this package may call Level, WorldClock, ClockManager or ServerClockManager time methods.
 */
public final class FieldStationClock implements FieldStationApi.ClockService {
    public static final FieldStationClock INSTANCE = new FieldStationClock();

    private FieldStationClock() {}

    @Override
    public long simTicks() {
        return FieldStationApi.sim().simTicks;
    }

    @Override
    public long simHours() {
        return FieldStationApi.sim().simHours();
    }

    @Override
    public int season() {
        return FieldStationApi.sim().season();
    }

    @Override
    public int dayOfSeason() {
        return FieldStationApi.sim().dayOfSeason();
    }

    @Override
    public int year() {
        return FieldStationApi.sim().year();
    }

    @Override
    public void advanceForTest(int simHours) {
        FieldStationApi.sim().advanceForTest(simHours);
    }

    public static void syncDimensionClock(ServerLevel level) {
        level.getGameRules().set(GameRules.ADVANCE_TIME, false, level.getServer());
        level.getGameRules().set(GameRules.ADVANCE_WEATHER, false, level.getServer());
        Holder<WorldClock> clock = level.dimensionType().defaultClock().orElse(null);
        if (clock != null) {
            level.clockManager().setPaused(clock, true);
            level.clockManager().setTotalTicks(clock, FieldStationApi.sim().simTicks);
        }
    }
}
