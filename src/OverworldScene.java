import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;

public class OverworldScene extends GameScene {
    private static final int TILE_SIZE = 40;
    private static final int BOARD_HEIGHT = 600;
    private static final int BOARD_WIDTH = 800;

    private static final int NUM_X_TILES = BOARD_WIDTH / TILE_SIZE;
    private static final int NUM_Y_TILES = BOARD_HEIGHT / TILE_SIZE;
    private SceneManager sceneManager;

    private OceanBoard oceanBoard;

    public OverworldScene(SceneManager sceneManager) {
        super("Overworld");
        this.sceneManager = sceneManager;
        oceanBoard = new OceanBoard(NUM_X_TILES, NUM_Y_TILES, TILE_SIZE, new Callback() {
            @Override
            public void execute() {
                sceneManager.showScene("Battle");
            }
        });
    }

    private Pane renderBoard() {
        Pane root = oceanBoard.getBoard();
        root.setMinHeight(BOARD_HEIGHT);
        root.setPrefSize(BOARD_WIDTH, BOARD_HEIGHT);
        return root;
    }

    public Scene getScene() {
        Scene scene = new Scene(renderBoard());
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                switch (keyEvent.getCode()) {
                    case UP:
                        oceanBoard.movePlayer(0, -1);
                        break;
                    case DOWN:
                        oceanBoard.movePlayer(0, 1);
                        break;
                    case RIGHT:
                        oceanBoard.movePlayer(1, 0);
                        break;
                    case LEFT:
                        oceanBoard.movePlayer(-1, 0);
                        break;
                    default:
                        break;
                }
                update(scene);
            }
        });
        return scene;
    }

    private void update(Scene scene) {
        if (oceanBoard.getShouldTransitionScene()) {
            sceneManager.showScene("Battle");
        } else {
            scene.setRoot(renderBoard());
        }
    }
}
