package backend.model;

import javafx.scene.paint.Color;

public class Circle extends Ellipse {

    public Circle(Point centerPoint, double radius, double border, Color borderColor, Color fillColor) {
        super(centerPoint, 2*radius, 2*radius,border,borderColor,fillColor);
    }

    @Override
    public String toString() {
        return String.format("Círculo [Centro: %s, Radio: %.2f]", centerPoint, getRadius());
    }

    public double getRadius() {
        return getsMayorAxis()/2;
    }

    public String getName() {
        return "Circle";
    }

}