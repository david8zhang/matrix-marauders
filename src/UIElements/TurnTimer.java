package UIElements;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Paint;

public class TurnTimer {
    private StackPane pane;
    private static final int WINDOW_WIDTH = 200;
    private static final int WINDOW_LENGTH = 50;

    private Label turnTextLabel;
    private String turnText;
    private int numTurns;

    public TurnTimer(int numTurns, String turnText) {
        pane = new StackPane();
        this.numTurns = numTurns;
        this.turnText = turnText;

        turnTextLabel = new Label(turnText + numTurns);
        VBox parent = new VBox();
        parent.setPadding(new Insets(10));
        parent.setMaxWidth(WINDOW_WIDTH);
        parent.setMaxHeight(WINDOW_LENGTH);
        parent.setBackground(new Background(new BackgroundFill(Paint.valueOf("#FFFFFF"), CornerRadii.EMPTY, Insets.EMPTY)));
        parent.getChildren().addAll(turnTextLabel);

        pane.getChildren().addAll(parent);
        pane.setAlignment(Pos.TOP_RIGHT);
    }

    public void setNumTurns(int numTurns) {
        this.numTurns = numTurns;
        this.turnTextLabel.setText(turnText + this.numTurns);
    }

    public void decrementTurns() {
        this.numTurns--;
        this.turnTextLabel.setText(turnText + numTurns);
    }

    public boolean hasNoRemainingTurns() {
        return this.numTurns == 0;
    }

    public Pane getPane() {
        return this.pane;
    }
}
