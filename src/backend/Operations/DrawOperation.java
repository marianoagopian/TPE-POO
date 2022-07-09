package backend.Operations;

import backend.model.Figure;
import java.util.List;

public class DrawOperation extends ListNeededOperations {

    public DrawOperation(Figure figure, List<Figure> list) {
        super(figure, list);
    }

    public void undoOperation() {
        getList().remove(getFigure());
    }

    public void redoOperation() {
        getList().add(getFigure());
    }

    @Override
    public String toString() {
        return String.format("Dibujar %s", super.toString());
    }
}
