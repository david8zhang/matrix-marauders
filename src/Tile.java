import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

public class Tile {
    protected StackPane pane;
    protected Rectangle tileRect;
    protected static final String DEFAULT_COLOR = "#89c4f4";
    protected Color defaultColor;
    int x;
    int y;
    int tileSize;
    String type;

    public Tile(int x, int y, int tileSize, String type, String color) {
        pane = new StackPane();
        tileRect = new Rectangle(tileSize, tileSize);
        tileRect.setStroke(Color.rgb(0, 0, 0, 0.2));
        tileRect.setFill(Color.valueOf(color));
        pane.getChildren().addAll(tileRect);

        this.x = x;
        this.y = y;
        this.type = type;
        this.tileSize = tileSize;
        this.defaultColor = Color.valueOf(color);

        pane.setTranslateX(x * tileSize);
        pane.setTranslateY(y * tileSize);
    }

    public Tile(int x, int y, int tileSize, String type, Color color) {
        pane = new StackPane();
        tileRect = new Rectangle(tileSize, tileSize);
        tileRect.setStroke(Color.rgb(0, 0, 0, 0.2));
        tileRect.setFill(color);
        pane.getChildren().addAll(tileRect);

        this.x = x;
        this.y = y;
        this.type = type;
        this.tileSize = tileSize;
        this.defaultColor = color;

        pane.setTranslateX(x * tileSize);
        pane.setTranslateY(y * tileSize);
    }

    public Tile(int x, int y, int tileSize) {
        this(x, y, tileSize, "Default", DEFAULT_COLOR);
    }

    public Tile(int x, int y, int tileSize, String type) {
        this(x, y, tileSize, type, DEFAULT_COLOR);
    }

    public void setTileRectColor(String color) {
        if (!color.equals("")) {
            Color derivedColor = Color.valueOf(color);
            tileRect.setFill(derivedColor);
        }
    }

    public void resetTileRectColor() {
        tileRect.setFill(defaultColor);
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }
    public int getTileSize() { return this.tileSize; }

    public String getType() { return this.type; }

    public void setX(int x) {
        this.x = x;
    }
    public void setY(int y) {
        this.y = y;
    }

    public StackPane getPane() {
        return pane;
    }
}
