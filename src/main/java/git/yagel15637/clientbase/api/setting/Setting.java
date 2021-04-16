package git.yagel15637.clientbase.api.setting;

import java.util.function.Predicate;

public class Setting<T> {
    protected final String id, description;
    protected T value;
    protected Predicate<T> visibility;
    protected Predicate<T> valueCallback = v -> true;

    protected Setting(final String id, final String description, final T value, final Predicate<T> visibility) {
        this.id = id;
        this.description = description;
        this.value = value;
        this.visibility = visibility;
    }

    protected Setting(final String id, final String description, final T value) {
        this(id, description, value, v -> true);
    }

    public final String getId() {
        return id;
    }

    public final String getDescription() {
        return description;
    }

    public final T getValue() {
        return value;
    }

    public void setValue(final T value) {
        if (valueCallback.test(value)) this.value = value;
    }

    public final Predicate<T> getVisibility() {
        return visibility;
    }

    public final void setVisibility(final Predicate<T> visibility) {
        this.visibility = visibility;
    }

    public final Predicate<T> getValueCallback() {
        return valueCallback;
    }

    public final void setValueCallback(final Predicate<T> valueCallback) {
        this.valueCallback = valueCallback;
    }
}
