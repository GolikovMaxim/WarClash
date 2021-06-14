package Engine.Events;

import java.util.EventListener;

public interface GenericListener<T> extends EventListener {
    void invoke(T obj);
}
