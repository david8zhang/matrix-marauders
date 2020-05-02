import Callbacks.Callback;
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
    private Scene scene;

    private OceanBoard oceanBoard;

    public OverworldScene(SceneManager sceneManager) {
        super("Overworld");
        oceanBoard = new OceanBoard(NUM_X_TILES, NUM_Y_TILES, TILE_SIZE, new Callback() {
            @Override
            public void execute() {
                sceneManager.showScene("Battle");
                oceanBoard.reset();
                update();
            }
        });
    }

    public void addGold(int gold) {
        System.out.println("Player earned " + gold + " Gold!");
    }

    private Pane renderBoard() {
        Pane root = oceanBoard.getBoard();
        root.setMinHeight(BOARD_HEIGHT);
        root.setPrefSize(BOARD_WIDTH, BOARD_HEIGHT);
        return root;
    }

    public Scene getScene() {
        scene = new Scene(renderBoard());
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
                update();
            }
        });
        return scene;
    }

    private void update() {
        scene.setRoot(renderBoard());
    }
}
