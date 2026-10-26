package com.fieldstation.client;

import com.fieldstation.FieldStation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;

/** Client-only entry point. Server decides, client shows: nothing here may change simulation state. */
@Mod(value = FieldStation.MOD_ID, dist = Dist.CLIENT)
public final class FieldStationClient {
    public FieldStationClient(IEventBus modBus, ModContainer container) {
        FieldStation.LOGGER.info("Field Station client loaded");
    }
}
