package backend;

import backend.Operations.*;
import backend.model.Figure;
import javafx.scene.paint.Color;

import java.util.*;

public class CanvasState implements Iterable<Figure> {

    private final List<Figure> list = new ArrayList<>();
    private final Stack<Operation> redoList = new Stack<>();
    private final Stack<Operation> undoList = new Stack<>();

    public String undoLastOperationTitle() {
        return undoList.isEmpty() ? "" : undoList.peek().toString();
    }

    public int undoAvailable() {
        return undoList.size();
    }

    public String redoLastOperationTitle() {
        return redoList.isEmpty() ? "" : redoList.peek().toString();
    }

    public int redoAvailable() {
        return redoList.size();
    }

    public void addFigure(Figure figure) {
        list.add(figure);
        cleanRedos();
        undoList.add(new DrawOperation(figure, list));
    }

    public void deleteFigure(Figure figure) {
        list.remove(figure);
        cleanRedos();
        undoList.add(new EraseOperation(figure, list));
    }

    public void changeFillColor(Figure figure, Color color) {
        undoList.add(new FillColorOperation(figure, color));
        cleanRedos();
    }

    public void changeBorderColor(Figure figure, Color color) {
        undoList.add(new BorderColorOperation(figure, color));
        cleanRedos();
    }

    public void enlarge(Figure figure) {
        undoList.add(new EnlargeOperation(figure));
        cleanRedos();
    }

    public void reduce(Figure figure) {
        undoList.add(new ReduceOperation(figure));
        cleanRedos();
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

    public void cleanRedos() {
        if(!redoList.isEmpty()) {
            redoList.clear();
        }
    }
}
