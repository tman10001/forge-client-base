package git.yagel15637.clientbase.api.setting.settings;

import git.yagel15637.clientbase.api.setting.Setting;

import java.awt.*;
import java.util.function.Predicate;

public final class ColorSetting extends Setting<Color> implements com.lukflug.panelstudio.settings.ColorSetting {
    private boolean rainbow = false;

    public ColorSetting(final String name, final String description, final Color value, final Predicate<Color> visibility) {
        super(name, description, value, visibility);
    }

    public ColorSetting(final String name, final String description, final Color value) {
        super(name, description, value);
    }

    @Override
    public Color getColor() {
        return value;
    }

    @Override
    public boolean getRainbow() {
        return rainbow;
    }

    @Override
    public void setRainbow(final boolean rainbow) {
        this.rainbow = rainbow;
    }
}
