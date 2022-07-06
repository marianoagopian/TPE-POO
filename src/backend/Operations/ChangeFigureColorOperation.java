package backend.Operations;

import backend.model.Figure;

import java.util.List;
import javafx.scene.paint.Color;


public abstract class ChangeFigureColorOperation extends  Operation {
    protected Color pastColor, newColor;

    public ChangeFigureColorOperation(Figure figure, Color color) {
        super(figure);
        pastColor = color;
    }

    protected void setNewColor(Color color) {
        newColor = color;
    }
}