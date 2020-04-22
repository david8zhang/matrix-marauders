import javafx.scene.Scene;
public abstract class GameScene {
    protected String sceneName;
    public GameScene(String sceneName) {
        this.sceneName = sceneName;
    }

    public String getSceneName() {
        return sceneName;
    }

    public abstract Scene getScene();
}
