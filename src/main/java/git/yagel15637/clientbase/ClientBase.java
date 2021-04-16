package git.yagel15637.clientbase;

import com.google.common.eventbus.EventBus;
import git.yagel15637.clientbase.api.event.EventProcessor;
import git.yagel15637.clientbase.impl.gui.ClientGUI;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.Mod;

@Mod(
        modid = ClientBase.MOD_ID,
        name = ClientBase.MOD_NAME,
        version = ClientBase.VERSION
)
public final class ClientBase {
    public static final String MOD_ID = "clientbase";
    public static final String MOD_NAME = "ClientBase";
    public static final String VERSION = "1.0-SNAPSHOT";

    public static final EventBus EVENT_BUS = new EventBus();

    @Mod.EventHandler
    public void onPreInit(final FMLPreInitializationEvent event) {
        EventProcessor.INSTANCE.init();
    }

    @Mod.EventHandler
    public void onInit(final FMLInitializationEvent event) {
        ClientGUI.INSTANCE.init();
    }

    @Mod.EventHandler
    public void onPostInit(final FMLPostInitializationEvent event) {

    }
}
