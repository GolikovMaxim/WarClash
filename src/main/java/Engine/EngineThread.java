package Engine;

import java.util.TimerTask;

public class EngineThread extends TimerTask {
    private Scene scene;

    public EngineThread(Scene scene) {
        super();
        this.scene = scene;
    }

    @Override
    public void run() {
        for (var gameObject : scene.getGameObjects()){
            gameObject.update();
        }
    }
}
