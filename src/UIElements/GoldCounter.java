package UIElements;

import javafx.geometry.Pos;
import javafx.scene.control.Label;

public class GoldCounter extends Window {
    private static final int WINDOW_WIDTH = 75;
    private static final int WINDOW_LENGTH = 50;
    private Label goldAmtLabel;

    public GoldCounter(int goldAmount) {
        super(WINDOW_WIDTH, WINDOW_LENGTH);
        goldAmtLabel = new Label("Gold: " + goldAmount);
        parent.getChildren().addAll(goldAmtLabel);
        pane.setAlignment(Pos.TOP_RIGHT);
    }

    public void setGoldAmt(int goldAmt) {
        this.goldAmtLabel.setText("Gold: " + goldAmt);
    }
}
