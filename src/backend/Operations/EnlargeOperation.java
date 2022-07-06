package backend.Operations;

import backend.model.Figure;

import java.util.List;

public class EnlargeOperation extends Operation {
    public EnlargeOperation(Figure figure) {
        super(figure);
    }

    public void undoOperation() {
        figure.reduce();
    }

    public void redoOperation() {
        figure.enlarge();
    }

    @Override
    public String toString() {
        return String.format("Agrandar " + super.toString());
    }
}
