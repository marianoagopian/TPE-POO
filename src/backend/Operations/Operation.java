package backend.Operations;

import backend.model.Figure;

public abstract class Operation {
    private final Figure figure;

    public Operation(Figure figure) {
        this.figure = figure;
    }

    public abstract void undoOperation();

    public abstract void redoOperation();

    protected Figure getFigure() {
        return figure;
    }

    @Override
    public String toString() {
        return figure.getName();
    }
}
