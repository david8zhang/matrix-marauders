package UIElements;

import javafx.geometry.Insets;
import javafx.scene.layout.*;
import javafx.scene.paint.Paint;

public class Window {
    protected StackPane pane;
    protected VBox parent;

    public Window(int width, int height) {
        this.pane = new StackPane();
        parent = new VBox();
        parent.setPadding(new Insets(10));
        parent.setMaxWidth(width);
        parent.setMaxHeight(height);
        parent.setBackground(new Background(new BackgroundFill(Paint.valueOf("#FFFFFF"), CornerRadii.EMPTY, Insets.EMPTY)));
        pane.getChildren().addAll(parent);
    }

    public Pane getPane() {
        return pane;
    }
}
