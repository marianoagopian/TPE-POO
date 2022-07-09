package backend.Operations;

import backend.model.Figure;
import javafx.scene.paint.Color;


public abstract class ChangeFigureColorOperation extends Operation {
    private final Color pastColor;
    private Color newColor;

    public ChangeFigureColorOperation(Figure figure, Color color) {
        super(figure);
        pastColor = color;
    }

    protected Color getPastColor() {
        return pastColor;
    }

    protected Color getNewColor() {
        return newColor;
    }

    protected void setNewColor(Color color) {
        newColor = color;
    }
}
