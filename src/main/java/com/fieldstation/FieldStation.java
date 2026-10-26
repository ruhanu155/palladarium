package com.fieldstation;

import com.fieldstation.registry.FieldStationContent;
import com.fieldstation.world.FieldStationWorld;
import com.mojang.logging.LogUtils;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import org.slf4j.Logger;

/**
 * Field Station mod entry point (common side).
 */
@Mod(FieldStation.MOD_ID)
public final class FieldStation {
    public static final String MOD_ID = "fieldstation";
    public static final Logger LOGGER = LogUtils.getLogger();

    public FieldStation(IEventBus modBus, ModContainer container) {
        FieldStationContent.register(modBus);
        FieldStationWorld.register(modBus);
        LOGGER.info("Field Station v1.0 systems registered");
    }
}
