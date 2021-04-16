package git.yagel15637.clientbase.impl.feature.movement;

import com.google.common.eventbus.Subscribe;
import git.yagel15637.clientbase.api.event.events.UpdateEvent;
import git.yagel15637.clientbase.api.feature.Feature;
import git.yagel15637.clientbase.api.feature.FeatureCategory;
import git.yagel15637.clientbase.api.setting.settings.BooleanSetting;

public final class Sprint extends Feature {
    public static final BooleanSetting rage = new BooleanSetting("Rage", "", true);

    public Sprint() {
        super(FeatureCategory.MOVEMENT);
    }

    @Subscribe
    public void onUpdate(final UpdateEvent event) {
        if (!mc.player.isSprinting()) mc.player.setSprinting(rage.getValue() || mc.gameSettings.keyBindForward.isKeyDown());
    }
}
