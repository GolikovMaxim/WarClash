package Engine.Events;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class Event {
    @Getter private List<Listener> listeners = new ArrayList<>();

    public void invoke() {
        listeners.forEach(Listener::invoke);
    }
}
