package backend.Operations;

import backend.model.Figure;

public class ReduceOperation extends Operation {
    public ReduceOperation(Figure figure) {
        super(figure);
    }

    public void undoOperation() {
        getFigure().undoReduce();
    }

    public void redoOperation() {
        getFigure().reduce();
    }
    @Override
    public String toString() {
        return String.format("Achicar %s", super.toString());
    }
}

