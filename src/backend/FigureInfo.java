package backend;

import backend.model.Figure;

public class FigureInfo {

    private Figure figure;
    private Operations operations;

    public FigureInfo(Figure figure, Operations operations) {
        this.figure = figure;
        this.operations = operations;
    }

    public Figure getFigure() {
        return figure;
    }
}
