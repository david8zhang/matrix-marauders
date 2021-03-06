import Callbacks.Callback;
import SpriteMaps.MerchantSprite;
import Tiles.ShipPixel;
import Tiles.Tile;
import UIElements.HealthBar;
import UIElements.TurnTimer;
import javafx.scene.paint.Color;
import java.util.HashSet;

public class MerchantGrid {
    private HashSet<String> weakPoints;
    private HashSet<String> shipPoints;
    private int tileSize;
    private double maxHealth = 100;
    private double currHealth;
    private int maxTurns = 10;
    private HealthBar healthBar;
    private TurnTimer turnTimer;
    private static final int GOLD_REWARD = 5;

    private Tile[][] tiles;

    public MerchantGrid(int numXTiles, int numYTiles, int tileSize) {
        this.tiles = new Tile[numXTiles][numYTiles];
        this.currHealth = this.maxHealth;
        this.tileSize = tileSize;
        this.weakPoints = new HashSet();
        this.shipPoints = new HashSet();
        this.healthBar = new HealthBar(this.maxHealth, this.currHealth, "Merchant Health");
        this.turnTimer = new TurnTimer(maxTurns, "Remaining Shots: ");
        this.initMerchantBoard();
    }

    public void reset() {
        this.currHealth = this.maxHealth;
        this.healthBar.setCurrHealth(this.maxHealth);
        this.turnTimer.setNumTurns(maxTurns);
        this.initMerchantBoard();
    }

    private boolean isTransparent(int[] rgbPixel) {
        return rgbPixel[3] == 0;
    }

    private double getOpacity(int[] rgbPixel) {
        return rgbPixel[3] / 255.0;
    }

    public Tile[][] getBoardState() {
        return this.tiles;
    }

    public String stringifyCoordinates(int[] coordinate) {
        return coordinate[0] + ", " + coordinate[1];
    }

    private int calculateDamage(int[] target) {
        if (isMissed(target)) {
            return 0;
        }
        if (isCriticalHit(target)) {
            return 20;
        } else {
            return 1;
        }
    }

    public void decrementShots() {
        turnTimer.decrementTurns();
    }

    public TurnTimer getTurnTimer() {
        return turnTimer;
    }

    public boolean hasNoRemainingShots() {
        return this.turnTimer.hasNoRemainingTurns();
    }

    public boolean isMissed(int[] target) {
        String stringifiedTarget = stringifyCoordinates(target);
        return !this.shipPoints.contains(stringifiedTarget);
    }

    public boolean isCriticalHit(int[] target) {
        String stringifiedTarget = stringifyCoordinates(target);
        return this.weakPoints.contains(stringifiedTarget);
    }

    public boolean isDead() {
        return currHealth == 0;
    }

    public HealthBar getHealthBar() {
        return healthBar;
    }

    public String getDamageMessage(int[] target) {
        if (isMissed(target)) {
            return "You missed!";
        }
        String result = "";
        if (isCriticalHit(target)) {
            result = "CRITICAL HIT! " + result;
        }
        int damage = calculateDamage(target);
        return result + "You dealt " + damage + " damage to the merchant ship!";
    }

    public void takeDamage(int [] target) {
        int damage = calculateDamage(target);
        this.currHealth -= damage;
        if (this.currHealth < 0) {
            this.currHealth = 0;
        }
        this.healthBar.takeDamage(damage);
    }

    public void spawnShipTile(int j, int i, int[] pixel) {
        Color tileColor = Color.rgb(pixel[0], pixel[1], pixel[2], getOpacity(pixel));
        this.tiles[j][i] = new ShipPixel(j, i, this.tileSize, tileColor);
        this.shipPoints.add(stringifyCoordinates(new int[] { j, i }));
    }

    public void clearExistingWeakPoints() {
        int[][][] hull = MerchantSprite.hull;
        for (int i = 0; i < hull.length; i++) {
            for (int j = 0; j < hull[i].length; j++) {
                int[] weakPointCoord = new int[] { j, i };
                if (this.weakPoints.contains(stringifyCoordinates(weakPointCoord))) {
                    spawnShipTile(j, i, hull[i][j]);
                }
            }
        }
        this.weakPoints.clear();
    }

    public int getGoldReward() {
        return GOLD_REWARD;
    }

    public void spawnWeakPoints() {
        this.clearExistingWeakPoints();
        int[][][] hull = MerchantSprite.hull;
        for (int i = 0; i < hull.length; i++) {
            for (int j = 0; j < hull[i].length; j++) {
                int[] pixel = hull[i][j];
                if (pixel[0] + pixel[1] + pixel[2] > 0) {
                    boolean isWeakPoint = (int)(Math.random() * 100) < 3;
                    if (isWeakPoint) {
                        this.tiles[j][i] = new Tile(j, i, this.tileSize, "Ship", "#FF0000");
                        this.weakPoints.add(stringifyCoordinates(new int[] { j, i }));
                    }
                }
            }
        }
    }

    public void initMerchantBoard() {
        int[][][] hull = MerchantSprite.hull;
        int[][][] sails = MerchantSprite.sails;
        for (int i = 0; i < hull.length; i++) {
            for (int j = 0; j < hull[0].length; j++) {
                int[] hullTile = hull[i][j];
                int[] sailsTile = sails[i][j];
                if (!isTransparent(hullTile)) {
                    spawnShipTile(j, i, hullTile);
                } else if (!isTransparent(sailsTile)) {
                    spawnShipTile(j, i, sailsTile);
                } else {
                    if (i >= this.tiles[0].length - 4) {
                        tiles[j][i] = new Tile(j, i, this.tileSize, "ocean", "#00008b");
                    } else {
                        tiles[j][i] = new Tile(j, i, this.tileSize);
                    }
                }
            }
        }
        this.spawnWeakPoints();
    }
}
