public class BattleGrid extends Board {
    public BattleGrid(int numXTiles, int numYTiles, int tileSize) {
        super(numXTiles, numYTiles, tileSize);
        this.initializeBoardState();
    }

    @Override
    public void initializeBoardState() {
        for (int i = 0; i < tiles.length; i++) {
            for (int j = 0; j < tiles[0].length; j++) {
                tiles[i][j] = new SkyTile(i, j, this.tileSize);
            }
        }
    }
}
