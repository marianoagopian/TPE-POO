package backend.Operations;

import backend.model.Figure;
import java.util.List;

public class DrawOperation extends ListNeededOperations {

    public DrawOperation(Figure figure, List<Figure> list) {
        super(figure, list);
    }

    public void undoOperation() {
        list.remove(figure);
    }

    public void redoOperation() {
        list.add(figure);
    }

    @Override
    public String toString() {
        return String.format("Dibujar " + super.toString());
    }
}
