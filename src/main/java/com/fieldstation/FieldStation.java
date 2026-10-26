package com.fieldstation;

import com.mojang.logging.LogUtils;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import org.slf4j.Logger;

/**
 * Field Station mod entry point (common side).
 *
 * <p>Scaffold only (M0). Each system lives in its own package under {@code com.fieldstation.<system>}
 * and talks to the others only through {@code com.fieldstation.api}. See docs/blueprint/minecraft-development.md.
 */
@Mod(FieldStation.MOD_ID)
public final class FieldStation {
    public static final String MOD_ID = "fieldstation";
    public static final Logger LOGGER = LogUtils.getLogger();

    public FieldStation(IEventBus modBus, ModContainer container) {
        LOGGER.info("Field Station loaded (scaffold, no systems yet)");
    }
}
