package frontend.Creators;

import backend.model.Circle;
import backend.model.Figure;
import backend.model.Point;
import backend.model.Rectangle;
import javafx.scene.paint.Color;

public class CircleCreator implements Creator{

    @Override
    public Figure createInstance(Point startPoint, Point endPoint, double border, Color borderColor, Color fillColor) {
        return new Circle(startPoint,endPoint,border,borderColor,fillColor);
    }
}
