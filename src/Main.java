import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {
        SceneManager sceneManager = new SceneManager(primaryStage);
        sceneManager.addScene(new OverworldScene(sceneManager));
        sceneManager.addScene(new BattleScene(sceneManager));
        sceneManager.showScene("Overworld");
    }

    public static void main(String[] args) {
        launch(args);
    }
}
