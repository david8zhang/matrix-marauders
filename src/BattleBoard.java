import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

public class BattleBoard extends Board {
    private MerchantGrid merchantGrid;
    private Reticle reticle;
    private Modal alertModal;

    public BattleBoard(int numXTiles, int numYTiles, int tileSize) {
        super(numXTiles, numYTiles, tileSize);
        merchantGrid = new MerchantGrid(numXTiles, numYTiles, tileSize);
        this.initializeBoardState();
    }

    @Override
    public Pane getBoard() {
        StackPane wrapper = new StackPane();
        Pane pane = super.getBoard();
        wrapper.getChildren().addAll(pane);
        wrapper.getChildren().addAll(alertModal.getPane());
        return wrapper;
    }

    @Override
    public void initializeBoardState() {
        alertModal = new Modal();
        Tile[][] merchantTiles = merchantGrid.getBoardState();
        reticle = new Reticle(merchantTiles);
        this.tiles = merchantTiles;
        reticle.start();
    }

    public void showDamageModal(int[] target, Callback updateCallback) {
        String message = merchantGrid.getDamageMessage(target);
        alertModal.showModal(message, "OK", new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                alertModal.closeModal();
                merchantGrid.takeDamage(target);
                merchantGrid.spawnWeakPoints();
                resetReticles();
                updateCallback.execute();
            }
        });
    }

    public void handleReticleStop(Callback updateCallback) {
        reticle.handleReticleStop();
        if (reticle.isFinishedAiming()) {
            int[] target = reticle.getReticleIntersection();
            showDamageModal(target, updateCallback);
        }
    }

    public void resetReticles() {
        reticle.resetReticles();
    }
}
