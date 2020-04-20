public class Player extends Ship {
    public Player(int x, int y, int tileSize) {
        super(x, y, tileSize, "https://vignette.wikia.nocookie.net/scribblenauts/images/2/2f/Pirate_Ship_SU.png/revision/latest/scale-to-width-down/340?cb=20130224173139");
    }

    @Override
    public void move(int xDirection, int yDirection, Tile[][] tiles) {
        int newX = restrictBounds(this.x + xDirection, 0, tiles.length - 1);
        int newY = restrictBounds(this.y + yDirection, 0, tiles[0].length - 1);
        super.move(newX, newY, tiles);
    }
}
