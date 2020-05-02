import Callbacks.Callback;
import Tiles.Tile;
import UIElements.HealthBar;
import UIElements.Modal;
import UIElements.TurnTimer;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

public class BattleBoard extends Board {
    private MerchantGrid merchantGrid;
    private Reticle reticle;
    private Modal alertModal;
    private Modal victoryModal;
    private Modal defeatModal;
    private HealthBar healthBar;
    private Callback onGoToOverworldScene;
    private Callback updateCallback;
    private TurnTimer turnTimer;


    public BattleBoard(int numXTiles, int numYTiles, int tileSize, Callback onGoToOverworldScene) {
        super(numXTiles, numYTiles, tileSize);
        this.onGoToOverworldScene = onGoToOverworldScene;
        this.initMerchantGrid();
        this.initializeBoardState();
        reticle.start();
    }

    @Override
    public Pane getBoard() {
        StackPane wrapper = new StackPane();
        Pane pane = super.getBoard();
        wrapper.getChildren().addAll(pane, healthBar.getPane(), turnTimer.getPane(), alertModal.getPane(), victoryModal.getPane(), defeatModal.getPane());
        return wrapper;
    }

    @Override
    public void initializeBoardState() {
        defeatModal = new Modal();
        alertModal = new Modal();
        victoryModal = new Modal();
        healthBar = merchantGrid.getHealthBar();
        turnTimer = merchantGrid.getTurnTimer();
        Tile[][] merchantTiles = merchantGrid.getBoardState();
        reticle = new Reticle(merchantTiles);
        this.tiles = merchantTiles;
    }

    public void attachUpdateCallback(Callback updateCallback) {
        this.updateCallback = updateCallback;
    }

    public void initMerchantGrid() {
        merchantGrid = new MerchantGrid(numXTiles, numYTiles, tileSize);
    }

    private void handleVictory() {
        PlayerMetadata pm = PlayerMetadata.getInstance();
        pm.addGold(merchantGrid.getGoldReward());
        showOnVictoryModal();
    }

    public void showDamageModal(int[] target) {
        String message = merchantGrid.getDamageMessage(target);
        alertModal.showModal(message, "OK", new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                alertModal.closeModal();
                merchantGrid.takeDamage(target);
                if (merchantGrid.isDead()) {
                    handleVictory();
                } else {
                    if (merchantGrid.hasNoRemainingShots()) {
                        showOnDefeatModal();
                    } else {
                        merchantGrid.spawnWeakPoints();
                        merchantGrid.decrementShots();
                        resetReticles();
                        updateCallback.execute();
                    }
                }
            }
        });
    }

    public void showOnDefeatModal() {
        defeatModal.showModal("You ran out of shots!", "OK", new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                resetBoard();
                onGoToOverworldScene.execute();
            }
        });
    }

    public void showOnVictoryModal() {
        String message = String.format("You defeated the merchant ship and earned %d Gold", merchantGrid.getGoldReward());
        victoryModal.showModal(message, "OK", new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                resetBoard();
                onGoToOverworldScene.execute();
            }
        });
    }

    public void resetBoard() {
        victoryModal.closeModal();
        defeatModal.closeModal();
        merchantGrid.reset();
        Tile[][] merchantTiles = merchantGrid.getBoardState();
        reticle = new Reticle(merchantTiles);
        tiles = merchantTiles;
        reticle.start();
        updateCallback.execute();
    }

    public void handleReticleStop() {
        reticle.handleReticleStop();
        if (reticle.isFinishedAiming()) {
            int[] target = reticle.getReticleIntersection();
            showDamageModal(target);
        }
    }

    public void resetReticles() {
        reticle.resetReticles();
    }
}
