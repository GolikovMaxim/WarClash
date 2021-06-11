package Engine;

public abstract class Component {
    GameObject gameObject;

    public void start() {

    }

    public  void update() {

    }

    public  void onDestroy() {

    }

    public final GameObject getGameObject() {
        return gameObject;
    }
}
