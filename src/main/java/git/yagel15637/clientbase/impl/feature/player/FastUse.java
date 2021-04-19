package git.yagel15637.clientbase.impl.feature.player;

import git.yagel15637.clientbase.api.event.events.UpdateEvent;
import git.yagel15637.clientbase.api.feature.Feature;
import git.yagel15637.clientbase.api.feature.FeatureCategory;
import git.yagel15637.clientbase.api.setting.settings.NumberSetting;
import git.yagel15637.clientbase.impl.mixin.accessors.IMinecraft;
import me.zero.alpine.listener.EventHandler;
import me.zero.alpine.listener.Listener;

public final class FastUse extends Feature {
    public NumberSetting delay = new NumberSetting("Delay", "", 0.0, 0.0, 2.0);

    private int currentDelay = 0;

    public FastUse() {
        super(FeatureCategory.PLAYER);

        addSettings(delay);
    }

    @EventHandler
    private final Listener<UpdateEvent> onUpdate = new Listener<>(event -> {
        if (currentDelay-- <= 0) {
            ((IMinecraft) mc).setRightClickDelayTimer(0);

            currentDelay = delay.getValue().intValue();
        }
    });
}
