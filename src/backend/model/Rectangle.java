package backend.model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Rectangle extends Figure {

    private final Point topLeft, bottomRight;

    private double sideX, sideY;

    public Rectangle(Point topLeft, Point bottomRight, double border, Color borderColor, Color fillColor) {
        super(topLeft,bottomRight,border, borderColor, fillColor);
        this.topLeft = topLeft;
        this.bottomRight = bottomRight;
    }

    @Override
    public String toString() {
        return String.format("Rectángulo [ %s , %s ]", topLeft, bottomRight);
    }

    protected void setSides() {
        setSideX(bottomRight.getX() - topLeft.getX());
        setSideY(bottomRight.getY() - topLeft.getY());
    }

    protected Point getTopLeft() {
        return topLeft;
    }

    protected Point getBottomRight() {
        return bottomRight;
    }

    protected void setSideX(double sideX) {
        this.sideX = sideX;
    }

    protected double getSideY() {
        return sideY;
    }

    protected void setSideY(double sideY) {
        this.sideY = sideY;
    }

    @Override
    public void reduce() {
        setSides();
        bottomRight.move((sideX * (-0.05)), (sideY * (-0.05)));
        topLeft.move((sideX * 0.05), (sideY * 0.05));
    }

    @Override
    public void enlarge() {
        setSides();
        bottomRight.move((sideX * 0.05), (sideY * 0.05));
        topLeft.move((sideX * (-0.05)), (sideY * (-0.05)));
    }

    public String getName() {
        return "Rectángulo";
    }

    @Override
    public void move(double deltaX, double deltaY) {
        topLeft.move(deltaX, deltaY);
        bottomRight.move(deltaX, deltaY);
    }

    @Override
    public void undoReduce() {
        setSides();
        bottomRight.move(sideX * (1.0/18), sideY * (1.0/18));
        topLeft.move(sideX * (-1.0/18), sideY * (-1.0/18));
    }

    @Override
    public void undoEnlarge() {
        setSides();
        bottomRight.move(sideX * (-1.0/22), sideY * (-1.0/22));
        topLeft.move(sideX * (1.0/22), sideY * (1.0/22));
    }

    @Override
    public void draw(GraphicsContext gc) {
        setSides();
        gc.fillRect(topLeft.getX(), topLeft.getY(), sideX, sideY);
        gc.strokeRect(topLeft.getX(), topLeft.getY(), sideX, sideY);
    }

    @Override
    public boolean figureBelongs(Point point) {
        return point.getX() > topLeft.getX() && point.getX() < bottomRight.getX() &&
                point.getY() > topLeft.getY() && point.getY() < bottomRight.getY();
    }
}