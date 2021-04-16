package git.yagel15637.clientbase.api.setting.settings;

import com.lukflug.panelstudio.settings.Toggleable;
import git.yagel15637.clientbase.api.setting.Setting;

import java.util.function.Predicate;

public final class BooleanSetting extends Setting<Boolean> implements Toggleable {
    public BooleanSetting(final String name, final String description, final Boolean value, final Predicate<Boolean> visibility) {
        super(name, description, value, visibility);
    }

    public BooleanSetting(final String name, final String description, final Boolean value) {
        super(name, description, value);
    }

    @Override
    public void toggle() {
        value = !value;
    }

    @Override
    public boolean isOn() {
        return value;
    }
}
