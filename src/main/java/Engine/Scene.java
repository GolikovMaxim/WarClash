package Engine;

import Engine.Events.Event;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class Scene {
    @Getter private List<GameObject> gameObjects = new ArrayList<>();
    @Getter private Event onExitEvent = new Event();

    public final GameObject instantiate(String name) {
        var gameObject = new GameObject(name);
        gameObjects.add(gameObject);
        gameObject.setScene(this);
        gameObject.start();
        return gameObject;
    }

    public final void destroy(GameObject gameObject) {
        gameObject.onDestroy();
        gameObjects.remove(gameObject);
    }

    public final void exit() {
        gameObjects.forEach(gameObject -> gameObject.onDestroy());
        gameObjects.clear();
        onExitEvent.invoke();
    }
}
