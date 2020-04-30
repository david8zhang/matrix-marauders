import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;

public class BattleScene extends GameScene {
    SceneManager sceneManager;
    BattleBoard battleBoard;

    private static final int TILE_SIZE = 20;
    private static final int BOARD_HEIGHT = 600;
    private static final int BOARD_WIDTH = 800;

    private static final int NUM_X_TILES = BOARD_WIDTH / TILE_SIZE;
    private static final int NUM_Y_TILES = BOARD_HEIGHT / TILE_SIZE;
    private Scene scene;

    public BattleScene(SceneManager sceneManager) {
        super("Battle");
        this.sceneManager = sceneManager;
        battleBoard = new BattleBoard(NUM_X_TILES, NUM_Y_TILES, TILE_SIZE, new Callback() {
            @Override
            public void execute() {
                sceneManager.showScene("Overworld");
            }
        });

        battleBoard.attachUpdateCallback(new Callback() {
            @Override
            public void execute() {
                update();
            }
        });
    }

    private Pane renderBoard() {
        Pane root = battleBoard.getBoard();
        root.setMinHeight(BOARD_HEIGHT);
        root.setPrefSize(BOARD_WIDTH, BOARD_HEIGHT);
        return root;
    }

    public Scene getScene() {
        scene = new Scene(renderBoard());
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if (keyEvent.getCode() == KeyCode.SPACE) {
                    battleBoard.handleReticleStop();
                }
            }
        });
        return scene;
    }

    public void update() {
        scene.setRoot(renderBoard());
    }
}
