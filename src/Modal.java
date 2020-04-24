import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Paint;

public class Modal {
    private StackPane pane;
    private static final int MODAL_HEIGHT = 100;
    private static final int MODAL_WIDTH = 300;
    public Modal(String content, String buttonText, EventHandler<MouseEvent> eventHandler) {
        pane = new StackPane();

        Label textLabel = new Label(content);
        textLabel.setPadding(new Insets(0, 0, 10, 0));
        Button button = new Button();
        button.setText(buttonText);
        button.setOnMouseClicked(eventHandler);

        VBox parent = new VBox();
        parent.setPrefSize(MODAL_WIDTH, MODAL_HEIGHT);
        parent.setAlignment(Pos.CENTER);
        parent.getChildren().addAll(textLabel, button);

        pane.setMaxWidth(MODAL_WIDTH);
        pane.setMaxHeight(MODAL_HEIGHT);
        pane.setPadding(new Insets(10));
        pane.setBackground(new Background(new BackgroundFill(Paint.valueOf("#FFFFFF"), CornerRadii.EMPTY, Insets.EMPTY)));

        pane.getChildren().addAll(parent);
        pane.setVisible(false);
    }

    public StackPane getPane() {
        return pane;
    }

    public void showModal() {
        pane.setVisible(true);
    }
}
