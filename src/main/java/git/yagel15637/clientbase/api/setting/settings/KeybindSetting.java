package git.yagel15637.clientbase.api.setting.settings;

import git.yagel15637.clientbase.api.setting.Setting;
import org.lwjgl.input.Keyboard;

import java.util.function.Predicate;

public final class KeybindSetting extends Setting<Integer> implements com.lukflug.panelstudio.settings.KeybindSetting {
    public KeybindSetting(final String name, final String description, final Integer value, final Predicate<Integer> visibility) {
        super(name, description, value, visibility);
    }

    public KeybindSetting(final String name, final String description, final Integer value) {
        super(name, description, value);
    }

    @Override
    public int getKey() {
        return value;
    }

    @Override
    public void setKey(int key) {
        value = key;
    }

    @Override
    public String getKeyName() {
        return Keyboard.getKeyName(value);
    }
}
