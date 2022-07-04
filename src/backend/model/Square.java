package backend.model;

import javafx.scene.paint.Color;

public class Square extends Rectangle {


    public Square(Point topLeft, double size, double border, Color borderColor, Color fillColor) {
        super(topLeft, new Point(topLeft.getX() + size, topLeft.getY() + size),border,borderColor,fillColor);
    }

    @Override
    public String toString() {
        return String.format("Cuadrado [ %s , %s ]", super.getTopLeft(), super.getBottomRight());
    }

}