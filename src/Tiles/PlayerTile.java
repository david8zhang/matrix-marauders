package Tiles;

public class PlayerTile extends ShipTile {
    public PlayerTile(int x, int y, int tileSize) {
        super(x, y, tileSize, "https://firebasestorage.googleapis.com/v0/b/matrix-marauders.appspot.com/o/pirate-ship.png?alt=media&token=4f308796-3770-470f-835a-6265a7b8e979");
    }

    @Override
    public void move(int xDirection, int yDirection, Tile[][] tiles) {
        int newX = restrictBounds(this.x + xDirection, 0, tiles.length - 1);
        int newY = restrictBounds(this.y + yDirection, 0, tiles[0].length - 1);
        super.move(newX, newY, tiles);
    }
}
