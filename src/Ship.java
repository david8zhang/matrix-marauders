import javafx.scene.image.ImageView;

public class Ship extends Tile {
    public Ship(int x, int y, int tileSize, String imageUrl) {
        super(x, y, tileSize);
        ImageView imageView = new ImageView(imageUrl);
        imageView.setFitWidth(tileSize);
        imageView.setFitHeight(tileSize);
        pane.getChildren().addAll(imageView);
    }

    protected int restrictBounds(int x, int lower, int upper) {
        if (x > upper) {
            return upper;
        }
        else if (x < lower) {
            return lower;
        }
        return x;
    }

    public void move(int newX, int newY, Tile[][] tiles) {
        this.x = newX;
        this.y = newY;
        pane.setTranslateX(newX * this.tileSize);
        pane.setTranslateY(newY * this.tileSize);
    }
}
