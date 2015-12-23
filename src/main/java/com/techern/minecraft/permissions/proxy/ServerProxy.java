package com.techern.minecraft.permissions.proxy;

import com.techern.minecraft.permissions.PermissionsManaged;
import com.techern.minecraft.permissions.command.PermissionControlledServerCommandManager;
import com.techern.minecraft.permissions.util.ReflectionUtilities;
import net.minecraft.server.MinecraftServer;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

/**
 * An extension of {@link CommonProxy} used in the server
 *
 * @since 0.0.1
 */
public class ServerProxy extends CommonProxy {

    /**
     * Attempts to set up the command handler
     *
     * @since 0.0.1
     */
    public void setupCommandHandler() {

        Class<? extends MinecraftServer> serverClass = MinecraftServer.getServer().getClass();

        try {
            Field commandManagerField = ReflectionUtilities.getFieldInHierarchy(serverClass, "commandManager", "field_71321_q");
            commandManagerField.setAccessible(true);

            Field modifiersField = Field.class.getDeclaredField("modifiers");
            modifiersField.setAccessible(true);
            try {
                modifiersField.setInt(commandManagerField, commandManagerField.getModifiers() & ~Modifier.FINAL);
            } catch (IllegalAccessException e) {
                PermissionsManaged.LOGGER.error("Cannot bypass static modifier on commandManager!", e);
                MinecraftServer.getServer().stopServer();
            }

            //Now set the value :)
            try {
                commandManagerField.set(serverClass, new PermissionControlledServerCommandManager());
            } catch (IllegalAccessException e) {
                PermissionsManaged.LOGGER.error("Unable to set the replacement command manager!", e);
                MinecraftServer.getServer().stopServer();
            }
        } catch (NoSuchFieldException e) {
            PermissionsManaged.LOGGER.error("Could not find MinecraftServer.commandManager / field_71321_q! You on 1.8.8 or 1.9? Wait for me to update :(");
            MinecraftServer.getServer().stopServer();
        }

    }
}
