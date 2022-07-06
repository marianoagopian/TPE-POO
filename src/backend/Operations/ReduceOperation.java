package backend.Operations;

import backend.model.Figure;

import java.util.List;

public class ReduceOperation extends Operation {
    public ReduceOperation(Figure figure) {
        super(figure);
    }

    public void undoOperation() {
        figure.enlarge();
    }

    public void redoOperation() {
        figure.reduce();
    }
    @Override
    public String toString() {
        return String.format("Achicar " + super.toString());
    }
}

