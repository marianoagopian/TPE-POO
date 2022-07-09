package frontend;

import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

public class HistoryPane extends BorderPane {
    private final Label undoLabel;
    private final Label redoLabel;
    private final Label movementsUndoLabel;
    private final Label movementsRedoLabel;

    private final Button redoButton = new Button("Rehacer");
    private final Button undoButton = new Button("Deshacer");



    public HistoryPane() {
        setStyle("-fx-background-color: #999");

        HBox buttons = new HBox(5);
        buttons.setMinHeight(35);
        buttons.setAlignment(Pos.CENTER);

        undoLabel = new Label();
        undoLabel.setMinWidth(300);
        undoLabel.setAlignment(Pos.CENTER_RIGHT);

        movementsUndoLabel = new Label("" + 0);
        movementsUndoLabel.setMinWidth(17);
        movementsUndoLabel.setAlignment(Pos.CENTER);

        redoLabel = new Label();
        redoLabel.setMinWidth(300);

        movementsRedoLabel = new Label("" + 0);
        movementsRedoLabel.setMinWidth(17);
        movementsRedoLabel.setAlignment(Pos.CENTER);

        redoButton.setMinWidth(90);
        redoButton.setCursor(Cursor.HAND);

        undoButton.setMinWidth(90);
        undoButton.setCursor(Cursor.HAND);

        buttons.getChildren().add(undoLabel);
        buttons.getChildren().add(movementsUndoLabel);
        buttons.getChildren().add(undoButton);
        buttons.getChildren().add(redoButton);
        buttons.getChildren().add(movementsRedoLabel);
        buttons.getChildren().add(redoLabel);

        setCenter(buttons);
    }

    public void updateRedoLabel(String text) {
        redoLabel.setText(text);
    }

    public void updateRedoMovements(int amount) {
        movementsRedoLabel.setText(String.valueOf(amount));
    }

    public void updateUndoLabel(String text) {
        undoLabel.setText(text);
    }

    public void updateUndoMovements(int amount) {
        movementsUndoLabel.setText(String.valueOf(amount));
    }

    public void undoButtonSetOnAction(EventHandler<? super MouseEvent> event) {
        undoButton.setOnMouseClicked(event);
    }

    public void redoButtonSetOnAction(EventHandler<? super MouseEvent> event) {
        redoButton.setOnMouseClicked(event);
    }

}
