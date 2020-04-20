import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    private static final int TILE_SIZE = 40;
    private static final int BOARD_HEIGHT = 600;
    private static final int BOARD_WIDTH = 800;

    private static final int NUM_X_TILES = BOARD_WIDTH / TILE_SIZE;
    private static final int NUM_Y_TILES = BOARD_HEIGHT / TILE_SIZE;

    private Board board;
    private Scene scene;

    private Pane renderBoard() {
        Pane root = board.getBoard();
        root.setMinHeight(BOARD_HEIGHT);
        root.setPrefSize(BOARD_WIDTH, BOARD_HEIGHT);
        return root;
    }

    private void render(Stage primaryStage) {
        scene = new Scene(renderBoard());
        primaryStage.setScene(scene);
        primaryStage.show();
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                switch (keyEvent.getCode()) {
                    case UP:
                        board.movePlayer(0, -1);
                        break;
                    case DOWN:
                        board.movePlayer(0, 1);
                        break;
                    case RIGHT:
                        board.movePlayer(1, 0);
                        break;
                    case LEFT:
                        board.movePlayer(-1, 0);
                        break;
                    default:
                        break;
                }
                update();
            }
        });
    }

    private void update() {
        scene.setRoot(renderBoard());
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        board = new Board(NUM_X_TILES, NUM_Y_TILES, TILE_SIZE);
        render(primaryStage);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
