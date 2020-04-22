import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Map;

public class SceneManager {
    private Stage stage;
    private Map<String, Scene> sceneMap;
    public SceneManager(Stage stage) {
        this.sceneMap = new HashMap<String, Scene>();
        this.stage = stage;
    }

    public void addScene(GameScene scene) {
        this.sceneMap.put(scene.getSceneName(), scene.getScene());
    }

    public void showScene(String sceneName) {
        Scene scene = sceneMap.get(sceneName);
        this.stage.setScene(scene);
        this.stage.show();
    }
}
