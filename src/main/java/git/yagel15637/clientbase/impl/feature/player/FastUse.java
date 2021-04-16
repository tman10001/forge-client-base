package git.yagel15637.clientbase.impl.feature.player;

import com.google.common.eventbus.Subscribe;
import git.yagel15637.clientbase.api.event.events.UpdateEvent;
import git.yagel15637.clientbase.api.feature.Feature;
import git.yagel15637.clientbase.api.feature.FeatureCategory;
import git.yagel15637.clientbase.api.setting.settings.NumberSetting;
import git.yagel15637.clientbase.impl.mixin.accessors.IMinecraft;

public final class FastUse extends Feature {
    public NumberSetting delay = new NumberSetting("Delay", "", 0.0, 0.0, 2.0);

    private int currentDelay = 0;

    public FastUse() {
        super(FeatureCategory.PLAYER);
    }

    @Subscribe
    public void onUpdate(final UpdateEvent event) {
        if (currentDelay-- <= 0) {
            ((IMinecraft) mc).setRightClickDelayTimer(0);

            currentDelay = delay.getValue().intValue();
        }
    }
}
