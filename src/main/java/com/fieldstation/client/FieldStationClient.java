package com.fieldstation.client;

import com.fieldstation.FieldStation;
import com.fieldstation.api.FieldStationApi;
import com.fieldstation.registry.FieldStationContent;
import com.fieldstation.sim.CoveSim;
import net.minecraft.world.level.block.Block;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.registries.DeferredBlock;

/**
 * Client-only entry point (Systems spec -> Soil and ground blocks -> Moisture tint).
 * Server decides, client shows: nothing here may change simulation state.
 */
@Mod(value = FieldStation.MOD_ID, dist = Dist.CLIENT)
public final class FieldStationClient {
    public FieldStationClient(IEventBus modBus, ModContainer container) {
        FieldStation.LOGGER.info("Field Station client loaded");
    }

    /** Returns the client-side RGB tint for a soil column based on moisture (0..7) and soil type. */
    public static int computeColumnTint(int x, int z) {
        CoveSim sim = FieldStationApi.sim();
        int col = CoveSim.idx(Math.floorMod(x, CoveSim.SIZE), Math.floorMod(z, CoveSim.SIZE));
        int m = sim.moisture[col];
        // Darker, richer earth tone as moisture increases from 0 to 7
        int shade = 255 - (m * 14);
        return (shade << 16) | (shade << 8) | shade;
    }
}
