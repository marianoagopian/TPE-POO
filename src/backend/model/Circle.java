package backend.model;

import javafx.scene.paint.Color;

public class Circle extends Ellipse {

    public Circle(Point centerPoint, Point endPoint, double border, Color borderColor, Color fillColor) {
        super(centerPoint, endPoint,border,borderColor, fillColor);
        setRadius(endPoint);
    }

    @Override
    public String toString() {
        return String.format("Círculo [Centro: %s, Radio: %.2f]", centerPoint, getRadius());
    }


    private void setRadius(Point endPoint) {
       sMayorAxis= endPoint.getX() - centerPoint.getX();
       sMinorAxis = sMayorAxis;
    }

    public double getRadius() {
        return getsMayorAxis()/2;
    }

    public String getName() {
        return "Circle";
    }

}