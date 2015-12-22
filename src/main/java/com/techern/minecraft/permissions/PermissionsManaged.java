package com.techern.minecraft.permissions;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.apache.logging.log4j.Logger;

/**
 * A {@link Mod} for permissions in Minecraft
 *
 * @since 0.0.1
 */
@Mod(modid = "PermissionsManaged", version = PermissionsManaged.VERSION)
public class PermissionsManaged {

    /**
     * The instance of {@link PermissionsManaged}
     *
     * @since 0.0.1
     */
    public static PermissionsManaged INSTANCE = new PermissionsManaged();

    /**
     * The version string for {@link PermissionsManaged}
     *
     * @since 0.0.1
     */
    public final static String VERSION = "0.0.1-SNAPSHOT";

    /**
     * The {@link Logger} for {@link PermissionsManaged}
     *
     * @since 0.0.1
     */
    public static Logger LOGGER;

    /**
     * Handles the {@link FMLPreInitializationEvent}
     *
     * @param event The {@link FMLPreInitializationEvent} to be handled
     *
     * @since 0.0.1
     */
    @SubscribeEvent()
    public static void onPreInit(FMLPreInitializationEvent event) {
        LOGGER = event.getModLog();

        LOGGER.info("PermissionsManaged logging set up");
    }

}
