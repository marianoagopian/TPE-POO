package backend.Creators;
import backend.model.Figure;
import backend.model.Point;
import javafx.scene.paint.Color;
public interface Creator {
    Figure createInstance(Point startPoint, Point endPoint, double border, Color borderColor, Color fillColor);
}
