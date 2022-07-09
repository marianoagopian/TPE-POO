package backend.Operations;

import backend.model.Figure;


public class EnlargeOperation extends Operation {
    public EnlargeOperation(Figure figure) {
        super(figure);
    }

    public void undoOperation() {
        getFigure().undoEnlarge();
    }

    public void redoOperation() {
        getFigure().enlarge();
    }

    @Override
    public String toString() {
        return String.format("Agrandar %s", super.toString());
    }
}
