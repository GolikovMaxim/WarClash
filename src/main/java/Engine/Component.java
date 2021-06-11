package Engine;

import lombok.Getter;

@Getter
public abstract class Component {
    GameObject gameObject;

    Component() {

    }

    public void start() {

    }

    public  void update() {

    }

    public  void onDestroy() {

    }
}
