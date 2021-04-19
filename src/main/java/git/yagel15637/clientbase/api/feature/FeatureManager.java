package git.yagel15637.clientbase.api.feature;

import git.yagel15637.clientbase.ClientBase;
import git.yagel15637.clientbase.api.event.events.KeybindPressedEvent;
import git.yagel15637.clientbase.impl.feature.client.*;
import git.yagel15637.clientbase.impl.feature.movement.Sprint;
import git.yagel15637.clientbase.impl.feature.player.*;
import me.zero.alpine.listener.EventHandler;
import me.zero.alpine.listener.Listener;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

public final class FeatureManager {
    public static final FeatureManager INSTANCE = new FeatureManager();

    private final List<Feature> features = Arrays.asList(
            new GUI(),

            new Sprint(),

            new FastUse()
    );

    public List<Feature> getFeatures() {
        return features;
    }

    public <T extends Feature> T getFeature(final Class<T> clazz) throws NoSuchElementException {
        for (final Feature feature : features)
            if (feature.getClass().isAssignableFrom(clazz)) return (T) feature;

        throw new NoSuchElementException();
    }

    public Feature getFeature(final String id) throws NoSuchElementException {
        for (final Feature feature : features)
            if (feature.getId().equalsIgnoreCase(id)) return feature;

        throw new NoSuchElementException();
    }

    public List<Feature> getFeaturesByCategory(final FeatureCategory category) {
        return features.stream().filter(it -> it.category == category).collect(Collectors.toList());
    }

    public void init() {
        ClientBase.EVENT_BUS.subscribe(this);

        features.forEach(feature -> feature.addSettings(feature.bind, feature.enabled, feature.hidden));
    }

    @EventHandler
    private final Listener<KeybindPressedEvent> onKeybindPressed = new Listener<>(event -> features.forEach(it -> {
        if (it.bind.getValue() == event.getKey()) it.enabled.setValue(!it.enabled.getValue());
    }));

    private FeatureManager() {}
}
