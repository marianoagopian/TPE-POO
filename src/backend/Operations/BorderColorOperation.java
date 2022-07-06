package backend.Operations;

import backend.model.Figure;
import javafx.scene.paint.Color;

import java.util.List;

public class BorderColorOperation extends ChangeFigureColorOperation {

    public BorderColorOperation(Figure figure, Color color) {
        super(figure, color);
        setNewColor(figure.getBorderColor());
    }


    @Override
    public void undoOperation() {
        figure.setBorderColor(pastColor);
    }

    @Override
    public void redoOperation() {
        figure.setBorderColor(newColor);
    }

    @Override
    public String toString() {
        return String.format("Cambiar color de borde de " + super.toString());
    }
}
