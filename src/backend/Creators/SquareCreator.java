package backend.Creators;

import backend.model.Figure;
import backend.model.Point;
import backend.model.Square;
import javafx.scene.paint.Color;

public class SquareCreator implements Creator {

    @Override
    public Figure createInstance(Point startPoint, Point endPoint, double border, Color borderColor, Color fillColor) {
        return new Square(startPoint,endPoint,border,borderColor,fillColor);
    }
}
