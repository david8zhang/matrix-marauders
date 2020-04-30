import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

public class Board {
    protected Tile[][] tiles;
    protected int tileSize;
    protected int numXTiles;
    protected int numYTiles;

    public Board(int numXTiles, int numYTiles, int tileSize) {
        tiles = new Tile[numXTiles][numYTiles];
        this.numXTiles = numXTiles;
        this.numYTiles = numYTiles;
        this.tileSize = tileSize;
    }

    public void initializeBoardState() {
        for (int i = 0; i < tiles.length; i++) {
            for (int j = 0; j < tiles[0].length; j++) {
                tiles[i][j] = new Tile(i, j, this.tileSize);
            }
        }
    }
    public Pane getBoard() {
        Pane pane = new Pane();
        for (int i = 0; i < tiles.length; i++) {
            for (int j = 0; j < tiles[0].length; j++) {
                StackPane tilePane = tiles[i][j].getPane();
                pane.getChildren().add(tilePane);
            }
        }
        return pane;
    }
}
