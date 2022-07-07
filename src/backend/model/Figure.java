package backend.model;

import backend.Properties.Movable;
import backend.Properties.Drawable;
import javafx.scene.paint.Color;

public abstract class Figure implements Movable, Drawable {
    private double border;
    private Color borderColor, fillColor;

    public Figure(double border, Color borderColor, Color fillColor){
        setBorder(border);
        setBorderColor(borderColor);
        setFillColor(fillColor);
    }

    public double getBorder() {
        return border;
    }

    public void setBorder(double border) {
        this.border = border;
    }

    public Color getBorderColor() {
        return borderColor;
    }

    public void setBorderColor(Color borderColor) {
        this.borderColor = borderColor;
    }

    public Color getFillColor() {
        return fillColor;
    }

    public void setFillColor(Color fillColor) {
        this.fillColor = fillColor;
    }

    public abstract void reduce();

    public abstract void enlarge();

    public abstract String getName();

    public abstract void undoReduce();

    public abstract void undoEnlarge();

    public abstract boolean figureBelongs(Point point);
}