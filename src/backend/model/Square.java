package backend.model;

import javafx.scene.paint.Color;

public class Square extends Rectangle {


    public Square(Point topLeft, Point bottomRight, double border, Color borderColor, Color fillColor) {
        super(topLeft, bottomRight, border, borderColor, fillColor);
    }

    @Override
    protected void setSides(){
        double auxSide=bottomRight.getX() - topLeft.getX();
        sideX = auxSide;
        sideY = auxSide;
    }


    @Override
    public String toString() {
        return String.format("Cuadrado [ %s , %s ]", super.getTopLeft(), super.getBottomRight());
    }

    public String getName() {
        return "Cuadrado";
    }
}