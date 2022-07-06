package frontend;

import backend.CanvasState;
import javafx.scene.layout.VBox;

public class MainFrame extends VBox {

    public MainFrame(CanvasState canvasState) {
        getChildren().add(new AppMenuBar());
        HistoryPane historyPane = new HistoryPane();
        getChildren().add(historyPane);
        StatusPane statusPane = new StatusPane();
        getChildren().add(new PaintPane(canvasState, statusPane, historyPane));
        getChildren().add(statusPane);
    }

}