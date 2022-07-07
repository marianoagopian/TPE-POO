package backend.model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Ellipse extends Figure {

    protected final Point centerPoint;
    protected double sMayorAxis, sMinorAxis;

    public Ellipse(Point centerPoint, double sMayorAxis, double sMinorAxis, double border, Color borderColor, Color fillColor) {
        super(border, borderColor, fillColor);
        this.centerPoint = centerPoint;
        this.sMayorAxis = sMayorAxis;
        this.sMinorAxis = sMinorAxis;
    }

    @Override
    public String toString() {
        return String.format("Elipse [Centro: %s, DMayor: %.2f, DMenor: %.2f]", centerPoint, sMayorAxis, sMinorAxis);
    }

    public Point getCenterPoint() {
        return centerPoint;
    }

    public double getsMayorAxis() {
        return sMayorAxis;
    }

    public double getsMinorAxis() {
        return sMinorAxis;
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
        gc.fillOval(centerPoint.getX()-sMayorAxis/2, centerPoint.getY()-sMinorAxis/2, sMayorAxis ,sMinorAxis);
        gc.strokeOval(centerPoint.getX()-sMayorAxis/2, centerPoint.getY()-sMinorAxis/2, sMayorAxis ,sMinorAxis);
    }

    @Override
    public void undoReduce() {
        sMayorAxis/=0.9;
        sMinorAxis/=0.9;
    }

    @Override
    public void undoEnlarge(){
        sMayorAxis/=1.1;
        sMinorAxis/=1.1;
    }

    @Override
    public boolean figureBelongs(Point point) {
        return ((Math.pow(point.getX() - centerPoint.getX(), 2) / Math.pow(sMayorAxis, 2)) +
                (Math.pow(point.getY() - centerPoint.getY(), 2) / Math.pow(sMinorAxis, 2))) <= 0.30;
    }
}