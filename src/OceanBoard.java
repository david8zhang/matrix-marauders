import UIElements.Modal;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

import java.util.ArrayList;
import java.util.List;

public class OceanBoard extends Board {
    private Player player;
    private int maxMerchantCount = 3;
    private int currMerchantCount = 0;
    private List<Merchant> merchantList = new ArrayList<>();
    private Modal collisionModal;

    public OceanBoard(int numXTiles, int numYTiles, int tileSize, Callback cmd) {
        super(numXTiles, numYTiles, tileSize);
        this.initializeBoardState();
        this.addPlayer();
        collisionModal = new Modal("You have collided with a merchant!", "Go to battle!", new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                cmd.execute();
            }
        });
    }

    public void spawnMerchant(Tile[][] tiles, int i, int j) {
        Merchant m = new Merchant(i, j, this.tileSize);
        tiles[i][j] = m;
        this.merchantList.add(m);
        this.currMerchantCount++;
    }

    public void spawnOceanTile(Tile[][] tiles, int i, int j) {
        tiles[i][j] = new Tile(i, j, this.tileSize);
    }

    public void reset() {
        this.currMerchantCount = 0;
        this.merchantList.clear();
        this.initializeBoardState();
        this.addPlayer();
        this.collisionModal.closeModal();
    }

    @Override
    public void initializeBoardState() {
        for (int i = 0; i < tiles.length; i++) {
            for (int j = 0; j < tiles[0].length; j++) {
                boolean shouldMerchantSpawn = ((int)(Math.random() * 100) <= 5) && currMerchantCount < maxMerchantCount;
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
            boolean shouldMove = (int)((Math.random() * 100) + 1) <= 50;
            if (shouldMove) {
                m.move(this.tiles);
                this.updateItemBoardPos(new int[] { oldX, oldY }, m);
            }
        }
    }

    public boolean didPlayerCollideWithMerchant() {
        int playerX = player.getX();
        int playerY = player.getY();
        for (Merchant m : this.merchantList) {
            int merchantX = m.getX();
            int merchantY = m.getY();
            if (merchantX == playerX && merchantY == playerY) {
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

    public void movePlayer(int xDirection, int yDirection) {
        this.moveMerchants();
        int oldX = player.getX();
        int oldY = player.getY();
        player.move(xDirection, yDirection, this.tiles);
        this.updateItemBoardPos(new int[]{oldX, oldY}, player);
        if (didPlayerCollideWithMerchant()) {
            this.collisionModal.showModal();
        }
    }

    @Override
    public Pane getBoard() {
        StackPane wrapper = new StackPane();
        Pane pane = super.getBoard();
        wrapper.getChildren().addAll(pane, collisionModal.getPane());
        return wrapper;
    }
}
