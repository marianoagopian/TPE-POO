package backend.model;

import javafx.scene.paint.Color;

public class Square extends Rectangle {


    public Square(Point topLeft, Point bottomRight, double border, Color borderColor, Color fillColor) {
        super(topLeft, bottomRight, border, borderColor, fillColor);
    }

    @Override
    protected void setSides() {
        double auxSide = getBottomRight().getX() - getTopLeft().getX();
        setSideX(auxSide);
        setSideY(auxSide);
    }

    @Override
    public String toString() {
        return String.format("Cuadrado [ %s , %s ]", super.getTopLeft(), super.getBottomRight());
    }

    @Override
    public boolean figureBelongs(Point point) {
        return point.getX() > getTopLeft().getX() && point.getX() < getBottomRight().getX() &&
                point.getY() > getTopLeft().getY() && point.getY() < (getTopLeft().getY() + getSideY());
    }

    public String getName() {
        return "Cuadrado";
    }
}