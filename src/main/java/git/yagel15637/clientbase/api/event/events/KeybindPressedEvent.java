package git.yagel15637.clientbase.api.event.events;

import git.yagel15637.clientbase.api.event.BasicEvent;

public final class KeybindPressedEvent extends BasicEvent {
    private final int key;

    public KeybindPressedEvent(final int key) {
        this.key = key;
    }

    public int getKey() {
        return key;
    }
}
