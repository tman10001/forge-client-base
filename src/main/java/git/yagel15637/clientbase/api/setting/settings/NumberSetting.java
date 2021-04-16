package git.yagel15637.clientbase.api.setting.settings;

import git.yagel15637.clientbase.api.setting.Setting;

import java.util.function.Predicate;

public final class NumberSetting extends Setting<Double> implements com.lukflug.panelstudio.settings.NumberSetting {
    private final Double min, max;

    public NumberSetting(final String name, final String description, final Double min, final Double value, final Double max, final Predicate<Double> visibility) {
        super(name, description, value, visibility);

        this.min = min;
        this.max = max;
    }

    public NumberSetting(final String name, final String description, final Double min, final Double value, final Double max) {
        super(name, description, value);

        this.min = min;
        this.max = max;
    }

    @Override
    public double getNumber() {
        return value;
    }

    @Override
    public void setNumber(final double value) {
        this.value = value;
    }

    @Override
    public double getMaximumValue() {
        return max;
    }

    @Override
    public double getMinimumValue() {
        return min;
    }

    @Override
    public int getPrecision() {
        return 10;
    }
}
