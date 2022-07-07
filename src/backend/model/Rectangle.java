package backend.model;

import javafx.scene.paint.Color;

public class Rectangle extends Figure {

    private final Point topLeft, bottomRight;

    private double sideX, sideY;

    public Rectangle(Point topLeft, Point bottomRight, double border, Color borderColor, Color fillColor) {
        super(border, borderColor, fillColor);
        this.topLeft = topLeft;
        this.bottomRight = bottomRight;
    }

    public Point getTopLeft() {
        return topLeft;
    }

    public Point getBottomRight() {
        return bottomRight;
    }

    @Override
    public String toString() {
        return String.format("Rectángulo [ %s , %s ]", topLeft, bottomRight);
    }

    private void setSides(){
        sideX = bottomRight.getX() - topLeft.getX();
        sideY = bottomRight.getY() - topLeft.getY();
    }

    @Override
    public void reduce() {
        setSides();
        bottomRight.move(sideX * -0.05, sideY * -0.05);
        topLeft.move(sideX * 0.05, sideY * 0.05);
    }

    @Override
    public void enlarge() {
        setSides();
        bottomRight.move(sideX * 0.05, sideY * 0.05);
        topLeft.move(sideX * -0.05, sideY * -0.05);
    }

    public String getName() {
        return "Rectángulo";
    }

    @Override
    public void move(double deltaX, double deltaY) {
        topLeft.move(deltaX, deltaY);
        bottomRight.move(deltaX, deltaY);
    }
}