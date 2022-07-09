package backend.model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;


public class Ellipse extends Figure {

    protected Point centerPoint;
    protected double sMayorAxis, sMinorAxis;

    public Ellipse(Point startPoint, Point endPoint, double border, Color borderColor, Color fillColor) {
        super(startPoint, endPoint, border, borderColor, fillColor);
        this.centerPoint = new Point((endPoint.getX() + startPoint.getX()) / 2, ((endPoint.getY() + startPoint.getY())) / 2);
        setMayorAxis(endPoint);
        setMinorAxis(endPoint);
    }

    private void setMinorAxis(Point endPoint) {
        sMinorAxis = endPoint.getY() - centerPoint.getY();
    }

    private void setMayorAxis(Point endPoint) {
        sMayorAxis=endPoint.getX() - centerPoint.getX();
    }

    @Override
    public String toString() {
        return String.format("Elipse [Centro: %s, DMayor: %.2f, DMenor: %.2f]", centerPoint, sMayorAxis, sMinorAxis);
    }

    protected double getsMayorAxis() {
        return sMayorAxis;
    }

    @Override
    public void reduce() {
        sMayorAxis *= 0.9;
        sMinorAxis *= 0.9;
    }

    @Override
    public void enlarge() {
        sMayorAxis *= 1.1;
        sMinorAxis *= 1.1;
    }

    public String getName() {
        return "Elipse";
    }

    @Override
    public void move(double deltaX, double deltaY) {
        centerPoint.move(deltaX, deltaY);
    }

    @Override
    public void draw (GraphicsContext gc)  {
        gc.fillOval(centerPoint.getX() - sMayorAxis, centerPoint.getY() - sMinorAxis, 2 * sMayorAxis ,2 * sMinorAxis);
        gc.strokeOval(centerPoint.getX() - sMayorAxis, centerPoint.getY() - sMinorAxis, 2 * sMayorAxis ,2 * sMinorAxis);
    }

    @Override
    public void undoReduce() {
        sMayorAxis /= 0.9;
        sMinorAxis /= 0.9;
    }

    @Override
    public void undoEnlarge() {
        sMayorAxis /= 1.1;
        sMinorAxis /= 1.1;
    }

    @Override
    public boolean figureBelongs(Point point) {
        return ((Math.pow(point.getX() - centerPoint.getX(), 2) / Math.pow(sMayorAxis, 2)) +
                (Math.pow(point.getY() - centerPoint.getY(), 2) / Math.pow(sMinorAxis, 2))) <= 0.30;
    }
}
