package Engine.Events;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class GenericEvent<T> {
    @Getter private List<GenericEvent<T>> listeners = new ArrayList<>();

    public void invoke(T obj) {
        listeners.forEach(listener -> listener.invoke(obj));
    }
}
