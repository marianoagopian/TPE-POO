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
        getFigure().setFillColor(getPastColor());
    }

    @Override
    public void redoOperation() {
        getFigure().setFillColor(getNewColor());
    }

    @Override
    public String toString() {
        return String.format("Cambiar color de relleno de %s", super.toString());
    }
}
