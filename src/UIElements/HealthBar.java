package UIElements;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

public class HealthBar {
    private StackPane pane;
    private double health;
    private double currHealth;
    private String healthLabelText;
    private Label healthLabel;
    private Rectangle healthFillRect;

    private static final int HEALTH_BAR_WIDTH = 30;
    private static final int HEALTH_BAR_LENGTH = 200;

    private static final int WINDOW_WIDTH = 200;
    private static final int WINDOW_LENGTH = 50;

    public HealthBar(double health, double currHealth, String healthTitle) {
        this.pane = new StackPane();
        this.health = health;
        this.currHealth = currHealth;
        this.healthLabelText = healthTitle;

        double healthPercentage = currHealth / health;
        String healthRatio = (int)currHealth + "/" + (int)health;

        this.healthLabel = new Label(healthTitle + ": " + healthRatio);

        StackPane healthBarPane = new StackPane();
        Rectangle healthHolder = new Rectangle(HEALTH_BAR_LENGTH, HEALTH_BAR_WIDTH);
        healthHolder.setStroke(Paint.valueOf("#000000"));

        healthFillRect = new Rectangle((int)(HEALTH_BAR_LENGTH * healthPercentage), HEALTH_BAR_WIDTH);
        healthFillRect.setFill(Paint.valueOf("#FF0000"));

        healthBarPane.setAlignment(Pos.CENTER_LEFT);
        healthBarPane.getChildren().addAll(healthHolder, healthFillRect);

        VBox parent = new VBox();
        parent.setPadding(new Insets(10));
        parent.setMaxWidth(WINDOW_WIDTH);
        parent.setMaxHeight(WINDOW_LENGTH);
        parent.setBackground(new Background(new BackgroundFill(Paint.valueOf("#FFFFFF"), CornerRadii.EMPTY, Insets.EMPTY)));
        parent.getChildren().addAll(healthLabel, healthBarPane);

        pane.getChildren().addAll(parent);
        pane.setAlignment(Pos.TOP_LEFT);
    }

    public void setCurrHealth(double health) {
        this.currHealth = health;
        this.updateHealthFillRect();
    }

    public void updateHealthFillRect() {
        double healthPercentage = currHealth / health;
        healthFillRect.setWidth((int)(HEALTH_BAR_LENGTH * healthPercentage));
        String healthRatio = this.healthLabelText + ": " + (int)currHealth + "/" + (int)health;
        healthLabel.setText(healthRatio);
    }

    public void takeDamage(int damage) {
        this.currHealth -= damage;
        if (this.currHealth < 0) {
            this.currHealth = 0;
        }
        this.updateHealthFillRect();
    }

    public Pane getPane() {
        return this.pane;
    }
}
