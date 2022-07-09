package backend.Operations;

import backend.model.Figure;
import javafx.scene.paint.Color;

public class BorderColorOperation extends ChangeFigureColorOperation {

    public BorderColorOperation(Figure figure, Color color) {
        super(figure, color);
        setNewColor(figure.getBorderColor());
    }

    @Override
    public void undoOperation() {
        getFigure().setBorderColor(getPastColor());
    }

    @Override
    public void redoOperation() {
        getFigure().setBorderColor(getNewColor());
    }

    @Override
    public String toString() {
        return String.format("Cambiar color de borde de %s", super.toString());
    }
}
