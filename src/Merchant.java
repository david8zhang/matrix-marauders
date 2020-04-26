import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Merchant extends Ship {
    public Merchant(int x, int y, int tileSize) {
        super(x, y, tileSize, "https://firebasestorage.googleapis.com/v0/b/matrix-marauders.appspot.com/o/merchant-ship.png?alt=media&token=d6f22220-12d1-4d79-b1da-4345d046befb");
    }

    public void move(Tile[][] tiles) {
        int[][] directions = new int[][] { {0, 1}, {-1, 0}, {1, 0}, {0, -1} };
        List<int[]> shuffledDirections = new ArrayList<int[]>(Arrays.asList(directions));
        Collections.shuffle(shuffledDirections);
        for (int[] dir: shuffledDirections) {
            int nextX = restrictBounds(this.x + dir[0], 0, tiles.length - 1);
            int nextY = restrictBounds(this.y + dir[1], 0, tiles[0].length - 1);
            if (!tiles[nextX][nextY].getType().equals("Ship")) {
                super.move(nextX, nextY, tiles);
                return;
            }
        }
    }
}
