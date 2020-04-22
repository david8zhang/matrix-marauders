import javafx.scene.Scene;
import javafx.scene.layout.Pane;

public class BattleScene extends GameScene {
    SceneManager sceneManager;
    BattleGrid battleGrid;

    private static final int TILE_SIZE = 20;
    private static final int BOARD_HEIGHT = 600;
    private static final int BOARD_WIDTH = 800;

    private static final int NUM_X_TILES = BOARD_WIDTH / TILE_SIZE;
    private static final int NUM_Y_TILES = BOARD_HEIGHT / TILE_SIZE;

    public BattleScene(SceneManager sceneManager) {
        super("Battle");
        this.sceneManager = sceneManager;
        battleGrid = new BattleGrid(NUM_X_TILES, NUM_Y_TILES, TILE_SIZE);
    }

    private Pane renderBoard() {
        Pane root = battleGrid.getBoard();
        root.setMinHeight(BOARD_HEIGHT);
        root.setPrefSize(BOARD_WIDTH, BOARD_HEIGHT);
        return root;
    }

    public Scene getScene() {
        return new Scene(renderBoard());
    }
}
