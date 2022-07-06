package backend.Operations;

import backend.model.Figure;

import java.util.List;

public abstract class Operation {
    protected Figure figure;

    public Operation(Figure figure) {
        this.figure = figure;
    }

    public abstract void undoOperation();

    public abstract void redoOperation();
}
