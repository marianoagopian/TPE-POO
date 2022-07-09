package backend.model;

import javafx.scene.paint.Color;

public class Circle extends Ellipse {

    public Circle(Point startPoint, Point endPoint, double border, Color borderColor, Color fillColor) {
        super(new Point(2 * startPoint.getX() - (endPoint.getX()), 2 * startPoint.getY() - (endPoint.getY())), endPoint, border, borderColor, fillColor);
        setRadius(endPoint);
    }

    @Override
    public String toString() {
        return String.format("Círculo [Centro: %s, Radio: %.2f]", getCenterPoint(), getRadius());
    }


    private void setRadius(Point endPoint) {
       setsMayorAxis(endPoint.getX() - getCenterPoint().getX());
       setsMinorAxis(getsMayorAxis());
    }

    private double getRadius() {
        return getsMayorAxis()/2;
    }

    public String getName() {
        return "Circulo";
    }

}