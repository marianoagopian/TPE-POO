package backend.Operations;

import backend.model.Figure;

import java.util.List;

public abstract class ListNeededOperations extends Operation {
    protected List<Figure> list;

    public ListNeededOperations(Figure figure, List<Figure> list) {
        super(figure);
        this.list = list;
    }
}
