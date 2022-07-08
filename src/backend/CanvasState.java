package backend;

import backend.Operations.*;
import backend.model.Figure;
import javafx.scene.paint.Color;

import java.util.*;

public class CanvasState implements Iterable<Figure> {

    private final List<Figure> figuresList = new ArrayList<>();
    private final Stack<Operation> redoStack = new Stack<>();
    private final Stack<Operation> undoStack = new Stack<>();

    public String undoLastOperationTitle() {
        return undoStack.isEmpty() ? "" : undoStack.peek().toString();
    }

    public int undoAvailable() {
        return undoStack.size();
    }

    public String redoLastOperationTitle() {
        return redoStack.isEmpty() ? "" : redoStack.peek().toString();
    }

    public int redoAvailable() {
        return redoStack.size();
    }

    public void addFigure(Figure figure) {
        figuresList.add(figure);
        cleanRedos();
        undoStack.add(new DrawOperation(figure, figuresList));
    }

    public void deleteFigure(Figure figure) {
        figuresList.remove(figure);
        cleanRedos();
        undoStack.add(new EraseOperation(figure, figuresList));
    }

    public void changeFillColor(Figure figure, Color color) {
        undoStack.add(new FillColorOperation(figure, color));
        cleanRedos();
    }

    public void changeBorderColor(Figure figure, Color color) {
        undoStack.add(new BorderColorOperation(figure, color));
        cleanRedos();
    }

    public void enlarge(Figure figure) {
        undoStack.add(new EnlargeOperation(figure));
        cleanRedos();
    }

    public void reduce(Figure figure) {
        undoStack.add(new ReduceOperation(figure));
        cleanRedos();
    }

    @Override
    public Iterator<Figure> iterator() {
        return figuresList.iterator();
    }

    public void redoOperation() {
        Operation aux = redoStack.pop();
        aux.redoOperation();
        undoStack.push(aux);
    }

    public void undoOperation(){
        Operation aux = undoStack.pop();
        aux.undoOperation();
        redoStack.push(aux);
    }

    public void cleanRedos() {
        redoStack.clear();
    }
}
