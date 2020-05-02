import UIElements.GoldCounter;

public class PlayerMetadata {

    private static final PlayerMetadata instance = new PlayerMetadata();
    private GoldCounter goldCounter;
    private int gold;

    private PlayerMetadata() {
        this.gold = 0;
        this.goldCounter = new GoldCounter(this.gold);
    }

    public static PlayerMetadata getInstance() {
        return instance;
    }

    public void addGold(int gold) {
        this.gold += gold;
        this.goldCounter.setGoldAmt(this.gold);
    }

    public void setGold(int gold) {
        this.gold = gold;
        this.goldCounter.setGoldAmt(this.gold);
    }

    public int getGold() {
        return gold;
    }

    public GoldCounter getGoldCounter() {
        return goldCounter;
    }
}
