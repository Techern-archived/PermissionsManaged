package com.techern.minecraft.permissions;

import com.techern.minecraft.permissions.proxy.CommonProxy;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Map;

@IFMLLoadingPlugin.Name(value = "Permissions Managed") // The readable mod name
@IFMLLoadingPlugin.MCVersion(value = "1.8") // The MC version it is designed for (Remember? Upwards/Downwards compatibility lost!)
@IFMLLoadingPlugin.TransformerExclusions(value = "com.techern.minecraft.permissions") // Your whole core mod package - Whatever you don't want the transformers to run over to prevent circularity Exceptions
@IFMLLoadingPlugin.SortingIndex(value = 1010) // How early your core mod is called - Use > 1000 to work with srg names
public class PermissionsManaged implements IFMLLoadingPlugin {

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
    public static Logger LOGGER = LogManager.getLogger("PermissionsManaged");


    @Override
    public String[] getASMTransformerClass() {
        return new String[0];
    }

    @Override
    public String getModContainerClass() {
        return "com.techern.minecraft.permissions.PermissionsManagedModContainer";
    }

    @Override
    public String getSetupClass() {
        return null;
    }

    @Override
    public void injectData(Map<String, Object> data) {
        LOGGER.info("Got inject: " + data);
    }

    @Override
    public String getAccessTransformerClass() {
        return null;
    }
}
