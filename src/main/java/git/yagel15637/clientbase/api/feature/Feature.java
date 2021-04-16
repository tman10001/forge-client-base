package git.yagel15637.clientbase.api.feature;

import git.yagel15637.clientbase.ClientBase;
import git.yagel15637.clientbase.api.feature.annotation.*;
import git.yagel15637.clientbase.api.setting.Setting;
import git.yagel15637.clientbase.api.setting.settings.BooleanSetting;
import git.yagel15637.clientbase.api.setting.settings.KeybindSetting;
import net.minecraft.client.Minecraft;
import org.lwjgl.input.Keyboard;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Feature {
    protected final String id = this.getClass().getSimpleName(), description;
    protected final FeatureCategory category;

    protected final boolean alwaysEnabled = this.getClass().isAnnotationPresent(AlwaysEnabled.class);
    protected final boolean enabledByDefault = this.getClass().isAnnotationPresent(EnabledByDefault.class);
    protected final boolean hiddenByDefault = this.getClass().isAnnotationPresent(HiddenByDefault.class);

    protected final Minecraft mc = Minecraft.getMinecraft();
    protected final List<Setting<?>> settings;

    public final KeybindSetting bind = new KeybindSetting("Bind", "", Keyboard.KEY_NONE);
    public final BooleanSetting enabled = new BooleanSetting("Enabled", "", alwaysEnabled || enabledByDefault);
    public final BooleanSetting hidden = new BooleanSetting("Hidden", "", hiddenByDefault);

    protected Feature(final String description, final FeatureCategory category) {
        this.description = description;
        this.category = category;

        settings = findSettings();

        bind.setValueCallback(v -> {
            if (v == Keyboard.KEY_DELETE) {
                bind.setValue(Keyboard.KEY_NONE);

                return false;
            }

            return true;
        });

        enabled.setValueCallback(v -> {
            onToggle();

            if (v) {
                onEnable();

                ClientBase.EVENT_BUS.register(this);
            } else {
                onDisable();

                ClientBase.EVENT_BUS.unregister(this);
            }

            return !alwaysEnabled || v;
        });

        settings.addAll(Arrays.asList(bind, enabled, hidden));
    }

    protected Feature(final FeatureCategory category) {
        this("", category);
    }

    protected void onEnable() {}
    protected void onDisable() {}
    protected void onToggle() {}

    public final String getId() {
        return id;
    }

    public final String getDescription() {
        return description;
    }

    public final FeatureCategory getCategory() {
        return category;
    }

    public final List<Setting<?>> getSettings() {
        return settings;
    }

    public final boolean isAlwaysEnabled() {
        return alwaysEnabled;
    }

    public final boolean isEnabledByDefault() {
        return enabledByDefault;
    }

    public final boolean isHiddenByDefault() {
        return hiddenByDefault;
    }

    private List<Setting<?>> findSettings() {
        return Arrays.stream(this.getClass().getDeclaredFields())
                .filter(it -> it.getType().isAssignableFrom(Setting.class))
                .map(it -> {
                    try {
                        System.out.println(it.getName());

                        return (Setting<?>) it.get(this);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }

                    return null;
                }).filter(Objects::nonNull)
                .collect(Collectors.toList());
    }
}
