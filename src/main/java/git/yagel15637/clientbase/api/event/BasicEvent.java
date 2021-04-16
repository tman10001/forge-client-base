package git.yagel15637.clientbase.api.event;

public class BasicEvent {
    private boolean cancelled = false;

    protected BasicEvent() {}

    public final boolean isCancelled() {
        return cancelled;
    }

    public final void cancel() {
        cancelled = true;
    }
}
