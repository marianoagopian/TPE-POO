package backend.Operations;

import backend.model.Figure;

import java.util.List;

public class EraseOperation extends SomethingOperation {
    public EraseOperation(Figure figure, List<Figure> list) {
        super(figure, list);
    }

    public void undoOperation() {
        list.add(figure);
    }

    public void redoOperation() {
        list.remove(figure);
    }

    @Override
    public String toString() {
        return String.format("Borrar " + figure.getName());
    }
}
