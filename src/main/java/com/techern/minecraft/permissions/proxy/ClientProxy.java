package com.techern.minecraft.permissions.proxy;

import com.techern.minecraft.permissions.PermissionsManaged;
import com.techern.minecraft.permissions.command.PermissionControlledServerCommandManager;
import com.techern.minecraft.permissions.util.ReflectionUtilities;
import net.minecraft.client.Minecraft;
import net.minecraft.command.ICommandManager;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.integrated.IntegratedServer;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

/**
 * An extension of {@link CommonProxy} used in the client
 *
 * @since 0.0.1
 */
public class ClientProxy extends CommonProxy {

    /**
     * Attempts to set up the command handler
     *
     * @since 0.0.1
     */
    public void setupCommandHandler() {
        

        Class<? extends IntegratedServer> serverClass = Minecraft.getMinecraft().getIntegratedServer().getClass();

        try {
            Field commandManagerField = ReflectionUtilities.getFieldInHierarchy(serverClass, "commandManager", "field_71321_q");
            commandManagerField.setAccessible(true);

            Field modifiersField = Field.class.getDeclaredField("modifiers");
            modifiersField.setAccessible(true);
            try {
                modifiersField.setInt(commandManagerField, commandManagerField.getModifiers() & ~Modifier.FINAL);
            } catch (IllegalAccessException e) {
                PermissionsManaged.LOGGER.error("Cannot bypass static modifier on commandManager!", e);
                Minecraft.getMinecraft().shutdown();
            }

            //Now set the value :)
            try {
                commandManagerField.set(serverClass, new PermissionControlledServerCommandManager());
            } catch (IllegalAccessException e) {
                PermissionsManaged.LOGGER.error("Unable to set the replacement command manager!", e);
                Minecraft.getMinecraft().shutdown();
            }
        } catch (NoSuchFieldException e) {
            PermissionsManaged.LOGGER.error("Could not find IntegratedServer.commandManager / field_71321_q! You on 1.8.8 or 1.9? Wait for me to update :(");
            Minecraft.getMinecraft().shutdown();
        }

    }
}
