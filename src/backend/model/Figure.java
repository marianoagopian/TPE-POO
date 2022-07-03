package backend.model;

import javafx.scene.paint.Color;

public abstract class Figure {
    private double border;
    private Color borderColor, fillColor;

    public Figure(double border,Color borderColor, Color fillColor){
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
}
