import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

public class Tile {
    protected StackPane pane;
    private static final String DEFAULT_COLOR = "#89c4f4";
    int x;
    int y;
    int tileSize;

    public Tile(int x, int y, int tileSize) {
        pane = new StackPane();
        Rectangle tileRect = new Rectangle(tileSize, tileSize);
        tileRect.setStroke(Color.rgb(0, 0, 0, 0.2));
        tileRect.setFill(Paint.valueOf(DEFAULT_COLOR));
        pane.getChildren().addAll(tileRect);

        this.x = x;
        this.y = y;
        this.tileSize = tileSize;

        pane.setTranslateX(x * tileSize);
        pane.setTranslateY(y * tileSize);
    }

    public Tile(int x, int y, int tileSize, String color) {
        pane = new StackPane();
        Rectangle tileRect = new Rectangle(tileSize, tileSize);
        tileRect.setStroke(Color.rgb(0, 0, 0, 0.2));
        tileRect.setFill(Paint.valueOf(color));
        pane.getChildren().addAll(tileRect);

        this.x = x;
        this.y = y;
        this.tileSize = tileSize;

        pane.setTranslateX(x * tileSize);
        pane.setTranslateY(y * tileSize);
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

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
