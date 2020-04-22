import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class OceanBoard extends Board {
    private Player player;
    private int maxMerchantCount = 3;
    private List<Merchant> merchantList = new ArrayList<>();
    private boolean shouldTransitionScene = false;

    public OceanBoard(int numXTiles, int numYTiles, int tileSize) {
        super(numXTiles, numYTiles, tileSize);
        this.initializeBoardState();
        this.addPlayer();
    }

    public void spawnMerchant(Tile[][] tiles, int i, int j) {
        Merchant m = new Merchant(i, j, this.tileSize);
        tiles[i][j] = m;
        this.merchantList.add(m);
        this.maxMerchantCount--;
    }

    public void spawnOceanTile(Tile[][] tiles, int i, int j) {
        tiles[i][j] = new Tile(i, j, this.tileSize);
    }


    @Override
    public void initializeBoardState() {
        for (int i = 0; i < tiles.length; i++) {
            for (int j = 0; j < tiles[0].length; j++) {
                boolean shouldMerchantSpawn = ((int)(Math.random() * 100) <= 5) && maxMerchantCount > 0 ;
                if (shouldMerchantSpawn) {
                    spawnMerchant(tiles, i, j);
                } else {
                    spawnOceanTile(tiles, i, j);
                }
            }
        }
    }

    public void addPlayer() {
        int randX = (int)(Math.random() * tiles.length);
        int randY = (int)(Math.random() * tiles[0].length);
        Player player = new Player(randX, randY, this.tileSize);
        tiles[randX][randY] = new Player(randX, randY, this.tileSize);
        this.player = player;
    }

    public void moveMerchants() {
        for (Merchant m : this.merchantList) {
            int oldX = m.getX();
            int oldY = m.getY();
            m.move(this.tiles);
            this.updateItemBoardPos(new int[] { oldX, oldY }, m);
        }
    }

    public boolean didPlayerCollideWithMerchant() {
        int playerX = player.getX();
        int playerY = player.getY();
        for (Merchant m : this.merchantList) {
            int merchantX = m.getX();
            int merchantY = m.getY();
            if (merchantX == playerX && playerY == merchantY) {
                return true;
            }
        }
        return false;
    }

    public void updateItemBoardPos(int[] oldCoordinates, Tile toMove) {
        int oldX = oldCoordinates[0];
        int oldY = oldCoordinates[1];
        tiles[oldX][oldY] = new Tile(oldX, oldY, this.tileSize);
        tiles[toMove.getX()][toMove.getY()] = toMove;
    }

    public boolean getShouldTransitionScene() {
        return this.shouldTransitionScene;
    }

    public void movePlayer(int xDirection, int yDirection) {
        this.moveMerchants();
        int oldX = player.getX();
        int oldY = player.getY();
        player.move(xDirection, yDirection, this.tiles);
        this.updateItemBoardPos(new int[] { oldX, oldY }, player);
        if (this.didPlayerCollideWithMerchant()) {
            shouldTransitionScene = true;
        }
    }
}