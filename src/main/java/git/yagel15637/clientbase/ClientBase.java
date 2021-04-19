package git.yagel15637.clientbase;

import git.yagel15637.clientbase.api.event.EventProcessor;
import git.yagel15637.clientbase.api.feature.FeatureManager;
import git.yagel15637.clientbase.impl.gui.ClientGUI;
import me.zero.alpine.EventBus;
import me.zero.alpine.EventManager;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.Mod;
import org.lwjgl.opengl.Display;

@Mod(modid = ClientBase.MOD_ID, name = ClientBase.MOD_NAME, version = ClientBase.VERSION)
public final class ClientBase {
    public static final String MOD_ID = "clientbase";
    public static final String MOD_NAME = "ClientBase";
    public static final String VERSION = "1.0-SNAPSHOT";

    public static final EventBus EVENT_BUS = new EventManager();

    @Mod.EventHandler
    public void onPreInit(final FMLPreInitializationEvent event) {
        EventProcessor.INSTANCE.init();
    }

    @Mod.EventHandler
    public void onInit(final FMLInitializationEvent event) {
        FeatureManager.INSTANCE.init();
    }

    @Mod.EventHandler
    public void onPostInit(final FMLPostInitializationEvent event) {
        ClientGUI.INSTANCE.init();

        Display.setTitle(MOD_NAME + " v" + VERSION + " | Client Base by Reap!");
    }
}
