package Engine;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.*;

public class Engine {
    public static final int FIXED_DELTA_TIME = 50;

    @Getter @Setter @AllArgsConstructor
    private static class GameTaskInfo {
        private Timer timer;
        private Scene scene;
        private UUID uuid;
    }

    private Map<UUID, GameTaskInfo> games = new HashMap<>();

    public void createGame(UUID uuid) {
        var timer = new Timer();
        var scene = new Scene();
        var gameTask = new GameTaskInfo(timer, scene, uuid);

        games.put(uuid, gameTask);
        scene.getOnExitEvent().getListeners().add(() -> {
            timer.cancel();
            games.remove(uuid);
        });

        timer.scheduleAtFixedRate(new EngineThread(scene), 0, FIXED_DELTA_TIME);
    }
}
