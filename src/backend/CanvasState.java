package backend;

import backend.Operations.*;
import backend.model.Figure;
import javafx.scene.paint.Color;

import java.util.*;

public class CanvasState implements Iterable<Figure> {

    private final List<Figure> list = new ArrayList<>();
    private final Stack<Operation> redoList = new Stack<>();
    private final Stack<Operation> undoList = new Stack<>();

    public void addFigure(Figure figure) {
        list.add(figure);
        undoList.add(new DrawOperation(figure, list));
    }

    public void deleteFigure(Figure figure) {
        list.remove(figure);
        undoList.add(new EraseOperation(figure, list));
    }

    public void changeFillColor(Figure figure, Color color) {
        undoList.add(new FillColorOperation(figure, color));
    }

    public void changeBorderColor(Figure figure, Color color) {
        undoList.add(new BorderColorOperation(figure, color));
    }

    public void enlarge(Figure figure) {
        undoList.add(new EnlargeOperation(figure));
    }

    public void reduce(Figure figure) {
        undoList.add(new ReduceOperation(figure));
    }

    @Override
    public Iterator<Figure> iterator() {
        return list.iterator();
    }

    public void redoOperation() {
        if(!redoList.isEmpty()) {
            Operation aux = redoList.pop();
            aux.redoOperation();
            undoList.push(aux);
        }
    }

    public void undoOperation(){
        if(!undoList.isEmpty()) {
            Operation aux = undoList.pop();
            aux.undoOperation();
            redoList.push(aux);
        }
    }
}
