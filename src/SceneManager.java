import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Map;

public class SceneManager {
    private Stage stage;
    private Map<String, GameScene> sceneMap;
    public SceneManager(Stage stage) {
        this.sceneMap = new HashMap<String, GameScene>();
        this.stage = stage;
    }

    public void addScene(GameScene scene) {
        this.sceneMap.put(scene.getSceneName(), scene);
    }

    public void showScene(String sceneName) {
        GameScene gameScene = sceneMap.get(sceneName);
        this.stage.setScene(gameScene.getScene());
        this.stage.show();
    }
}
