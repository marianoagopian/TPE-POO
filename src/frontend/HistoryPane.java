package frontend;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class HistoryPane extends BorderPane {
    private final Label undoLabel;
    private final Label redoLabel;

    private int undoMovementsCount;
    private int redoMovementsCount;

    Button redoButton = new Button("Rehacer");
    Button undoButton = new Button("Deshacer");



    public HistoryPane() {
        setStyle("-fx-background-color: #999");

        HBox buttons = new HBox(5);
        buttons.setMinHeight(35);
        buttons.setAlignment(Pos.CENTER);

        undoLabel = new Label();
        redoLabel = new Label();
        redoButton.setMinWidth(90);
        undoButton.setMinWidth(90);

        buttons.getChildren().add(undoLabel);
        buttons.getChildren().add(new Label("" + undoMovementsCount));
        buttons.getChildren().add(undoButton);
        buttons.getChildren().add(redoButton);
        buttons.getChildren().add(new Label("" + redoMovementsCount));
        buttons.getChildren().add(redoLabel);

        setCenter(buttons);
    }

    public void updateRedoLabel(String text) {
        redoLabel.setText(text);
    }

    public void updateRedoMovements(int amount) {
        redoMovementsCount = amount;
    }

    public void updateUndoLabel(String text) {
        undoLabel.setText(text);
    }

    public void updateUndoMovements(int amount) {
        undoMovementsCount = amount;
    }

}