package backend.Creators;

import backend.model.Ellipse;
import backend.model.Figure;
import backend.model.Point;
import javafx.scene.paint.Color;

public class EllipseCreator implements Creator {

    @Override
    public Figure createInstance(Point startPoint, Point endPoint, double border, Color borderColor, Color fillColor) {
        return new Ellipse(startPoint,endPoint,border,borderColor,fillColor);
    }
}
