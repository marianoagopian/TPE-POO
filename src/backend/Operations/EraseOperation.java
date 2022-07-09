package backend.Operations;

import backend.model.Figure;

import java.util.List;

public class EraseOperation extends ListNeededOperations {
    public EraseOperation(Figure figure, List<Figure> list) {
        super(figure, list);
    }

    public void undoOperation() {
        getList().add(getFigure());
    }

    public void redoOperation() {
        getList().remove(getFigure());
    }

    @Override
    public String toString() {
        return String.format("Borrar %s", super.toString());
    }
}
