import Tiles.Tile;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

public class Reticle {

    private Timeline horizontalAnim;
    private Timeline verticalAnim;

    private boolean freezeHorizontalReticle = false;
    private boolean freezeVerticalReticle = false;

    private int horizontalReticleDirection = 0;
    private int verticalReticleDirection = 0;
    private int highlightedRow = 0;
    private int highlightedCol = 0;

    private static final String HIGHLIGHT_COLOR = "#f7ca18";

    private int numXTiles;
    private int numYTiles;
    private Tile[][] tiles;
    private boolean finishedAiming;

    public Reticle(Tile[][] tiles) {
        this.tiles = tiles;
        this.numXTiles = tiles.length;
        this.numYTiles = tiles[0].length;
        this.addReticles(tiles);
    }

    private void addReticles(Tile[][] tiles) {
        for (int i = 0; i < tiles.length; i++) {
            for (int j = 0; j < tiles[0].length; j++) {
                boolean isHighlighted = highlightedCol == i || highlightedRow == j;
                setHighlight(isHighlighted, tiles[i][j]);
            }
        }
    }

    public void moveReticleUp() {
        this.setRowHighlightState(this.highlightedRow, false);
        this.highlightedRow = ((this.highlightedRow - 1) + numYTiles) % numYTiles;
        this.setRowHighlightState(this.highlightedRow, true);
    }

    public void moveReticleDown() {
        this.setRowHighlightState(this.highlightedRow, false);
        this.highlightedRow = (this.highlightedRow + 1) % numYTiles;
        this.setRowHighlightState(this.highlightedRow, true);
    }

    public void moveReticleRight() {
        this.setColHighlightState(this.highlightedCol, false);
        this.highlightedCol = (this.highlightedCol + 1) % numXTiles;
        this.setColHighlightState(this.highlightedCol, true);
    }

    public void moveReticleLeft() {
        this.setColHighlightState(this.highlightedCol, false);
        this.highlightedCol = ((this.highlightedCol - 1) + numXTiles) % numXTiles;
        this.setColHighlightState(this.highlightedCol, true);
    }

    private void cycleHorizontalReticle() {
        if (this.highlightedRow == numYTiles - 1) {
            this.horizontalReticleDirection = 0;
        }
        if (this.highlightedRow == 0) {
            this.horizontalReticleDirection = 1;
        }
        if (this.horizontalReticleDirection == 0) {
            this.moveReticleUp();
        }
        if (this.horizontalReticleDirection == 1) {
            this.moveReticleDown();
        }
    }

    private void cycleVerticalReticle() {
        if (this.highlightedCol == numXTiles - 1) {
            this.verticalReticleDirection = 0;
        }
        if (this.highlightedCol == 0) {
            this.verticalReticleDirection = 1;
        }
        if (this.verticalReticleDirection == 0) {
            this.moveReticleLeft();
        }
        if (this.verticalReticleDirection == 1) {
            this.moveReticleRight();
        }
    }

    private void startHorizontalReticleCycle() {
        horizontalAnim = new Timeline(new KeyFrame(Duration.seconds(0.1), e -> {
            cycleHorizontalReticle();
        }));
        horizontalAnim.setCycleCount(Timeline.INDEFINITE);
        horizontalAnim.play();
    }

    private void stopHorizontalReticleCycle() {
        horizontalAnim.stop();
    }

    private void startVerticalReticleCycle() {
        verticalAnim = new Timeline(new KeyFrame(Duration.seconds(0.1), e -> {
            cycleVerticalReticle();
        }));
        verticalAnim.setCycleCount(Timeline.INDEFINITE);
        verticalAnim.play();
    }

    private void stopVerticalReticleCycle() {
        verticalAnim.stop();
    }

    public void start() {
        this.startHorizontalReticleCycle();
    }

    public void setRowHighlightState(int row, boolean isHighlight) {
        for (int i = 0; i < numXTiles; i++) {
            if (i != this.highlightedCol) {
                Tile highlightedTile = this.tiles[i][row];
                setHighlight(isHighlight, highlightedTile);
            }
        }
    }

    public void setColHighlightState(int col, boolean isHighlight) {
        for (int i = 0; i < numYTiles; i++) {
            if (i != this.highlightedRow) {
                Tile highlightedTile = this.tiles[col][i];
                setHighlight(isHighlight, highlightedTile);
            }
        }
    }

    private void setHighlight(boolean isHighlight, Tile highlightedTile) {
        if (isHighlight) {
            highlightedTile.setTileRectColor(HIGHLIGHT_COLOR);
        } else {
            highlightedTile.resetTileRectColor();
        }
    }

    public void handleReticleStop() {
        if (!freezeHorizontalReticle) {
            freezeHorizontalReticle = true;
            stopHorizontalReticleCycle();
            startVerticalReticleCycle();
        }
        else if (!freezeVerticalReticle) {
            freezeVerticalReticle = true;
            stopVerticalReticleCycle();
            this.finishedAiming = true;
        }
    }

    public boolean isFinishedAiming() {
        return this.finishedAiming;
    }

    public int[] getReticleIntersection() {
        return new int[] { this.highlightedCol, this.highlightedRow };
    }

    public void resetReticles() {
        this.addReticles(tiles);
        if (freezeHorizontalReticle && freezeVerticalReticle) {
            freezeHorizontalReticle = false;
            freezeVerticalReticle = false;
            this.finishedAiming = false;
            startHorizontalReticleCycle();
        }
    }
}
