package com.techern.minecraft.permissions;

import com.techern.minecraft.permissions.proxy.CommonProxy;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
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
     * The {@link SidedProxy}, set to {@link com.techern.minecraft.permissions.proxy.ClientProxy} or {@link com.techern.minecraft.permissions.proxy.ServerProxy}
     *
     * @since 0.0.1
     */
    @SidedProxy(clientSide = "com.techern.minecraft.permissions.proxy.ClientProxy",
                serverSide = "com.techern.minecraft.permissions.proxy.ServerProxy")
    public static CommonProxy PROXY;

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
    @Mod.EventHandler
    public static void onPreInit(FMLPreInitializationEvent event) {
        LOGGER = event.getModLog();

        LOGGER.info("PermissionsManaged logging set up");

    }

}
