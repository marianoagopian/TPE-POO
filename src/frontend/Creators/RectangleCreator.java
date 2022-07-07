package frontend.Creators;

import backend.model.Figure;
import backend.model.Point;
import backend.model.Rectangle;
import javafx.scene.paint.Color;

public class RectangleCreator implements Creator{

    @Override
    public Figure createInstance(Point startPoint, Point endPoint, double border, Color borderColor, Color fillColor) {
        return new Rectangle(startPoint,endPoint,border,borderColor,fillColor);
    }
}
