package backend.Operations;

import backend.model.Figure;

import java.util.List;

public abstract class SomethingOperation extends Operation {
    protected List<Figure> list;

    public SomethingOperation(Figure figure, List<Figure> list) {
        super(figure);
        this.list = list;
    }
}
