package git.yagel15637.clientbase.api.event;

import git.yagel15637.clientbase.ClientBase;
import git.yagel15637.clientbase.api.event.events.KeybindPressedEvent;
import git.yagel15637.clientbase.api.event.events.UpdateEvent;
import net.minecraft.client.Minecraft;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import org.lwjgl.input.Keyboard;

public final class EventProcessor {
    public static final EventProcessor INSTANCE = new EventProcessor();
    private final Minecraft mc = Minecraft.getMinecraft();

    public void init() {
        MinecraftForge.EVENT_BUS.register(this);
        ClientBase.EVENT_BUS.subscribe(this);
    }

    @SubscribeEvent
    public void onPlayerTick(final TickEvent.PlayerTickEvent event) {
        if (mc.player != null) ClientBase.EVENT_BUS.post(new UpdateEvent());
    }

    @SubscribeEvent
    public void onKeyInput(final InputEvent.KeyInputEvent event) {
        final int eventKey = Keyboard.getEventKey();

        if (eventKey != Keyboard.KEY_NONE && Keyboard.isKeyDown(eventKey) && !Keyboard.isRepeatEvent()) {
            System.out.println("Key pressed: " + Keyboard.getKeyName(eventKey));
            ClientBase.EVENT_BUS.post(new KeybindPressedEvent(eventKey));
        }
    }

    private EventProcessor() {}
}
