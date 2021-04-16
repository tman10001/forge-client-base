package git.yagel15637.clientbase.api.setting.settings;

import git.yagel15637.clientbase.api.setting.Setting;

import java.util.function.Predicate;

public final class EnumSetting<E extends Enum<E>> extends Setting<E> implements com.lukflug.panelstudio.settings.EnumSetting {
    public EnumSetting(final String name, final String description, final E value, final Predicate<E> visibility) {
        super(name, description, value, visibility);
    }

    public EnumSetting(final String name, final String description, final E value) {
        super(name, description, value);
    }

    @Override
    public void increment() {
        final E[] values = value.getDeclaringClass().getEnumConstants();
        final int newOrdinal = value.ordinal() + 1 >= values.length ? 0 : value.ordinal() + 1;
        value = values[newOrdinal];
    }

    @Override
    public String getValueName() {
        return value.name();
    }
}
