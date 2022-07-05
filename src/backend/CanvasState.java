package backend;

import backend.model.Figure;

import java.util.*;

public class CanvasState implements Iterable<Figure> {

    private final List<Figure> list = new ArrayList<>();
    private final Deque<FigureInfo> redoList = new LinkedList<>();
    private final Deque<FigureInfo> undoList = new LinkedList<>();

    public void addFigure(Figure figure) {
        list.add(figure);
    }

    public void deleteFigure(Figure figure) {
        list.remove(figure);
    }

    @Override
    public Iterator<Figure> iterator() {
        return list.iterator();
    }

    public void addRedoFigure(FigureInfo figure){
        redoList.add(figure);
    }

    public void addUndoFigure(FigureInfo figure){
        undoList.add(figure);
    }

    public void deleteRedoFigure(){
        addUndoFigure(redoList.pop());
    }

    public void deleteUndoFigure(){
        addRedoFigure(undoList.pop());
    }

    public void undo(){
        FigureInfo aux=undoList.pop();
        addRedoFigure(aux);
        list.add(aux.getFigure());
    }

    public void redo(){

    }
}
