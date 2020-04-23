import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

public class Modal {
    private StackPane pane;
    private static final int MODAL_HEIGHT = 50;
    private static final int MODAL_WIDTH = 200;
    public Modal(String content) {
        pane = new StackPane();
        Rectangle tileRect = new Rectangle();

        Label textLabel = new Label(content);
        HBox parent = new HBox();
        parent.setPrefSize(MODAL_WIDTH, MODAL_HEIGHT);
        parent.setAlignment(Pos.CENTER);
        parent.getChildren().addAll(textLabel);

        pane.getChildren().addAll(tileRect, parent);
    }

    public StackPane getPane() {
        return pane;
    }
}
