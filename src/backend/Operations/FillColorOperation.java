package backend.Operations;

import backend.model.Figure;
import javafx.scene.paint.Color;

public class FillColorOperation extends ChangeFigureColorOperation {
    public FillColorOperation(Figure figure, Color color) {
        super(figure, color);
        setNewColor(figure.getFillColor());
    }

    @Override
    public void undoOperation() {
        figure.setFillColor(pastColor);
    }

    @Override
    public void redoOperation() {
        figure.setFillColor(newColor);
    }

    @Override
    public String toString() {
        return String.format("Cambiar color de relleno de " + super.toString());
    }
}
