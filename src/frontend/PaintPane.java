package frontend;

import backend.CanvasState;
import backend.model.*;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class PaintPane extends BorderPane {

	// BackEnd
	CanvasState canvasState;

	// Canvas y relacionados
	Canvas canvas = new Canvas(800, 600);
	GraphicsContext gc = canvas.getGraphicsContext2D();

	// Botones Barra Izquierda
	ToggleButton selectionButton = new ToggleButton("Seleccionar");
	ToggleButton rectangleButton = new ToggleButton("Rectángulo");
	ToggleButton circleButton = new ToggleButton("Círculo");
	ToggleButton squareButton = new ToggleButton("Cuadrado");
	ToggleButton ellipseButton = new ToggleButton("Elipse");
	ToggleButton deleteButton = new ToggleButton("Borrar");
	Button enlargeButton = new Button("Agrandar");
	Button reduceButton = new Button("Achicar");

	// Dibujar una figura
	Point startPoint;

	// Seleccionar una figura
	Figure selectedFigure;

	// Crear el slider
	Slider slider = new Slider(1, 50, 1);

	// Seleccionar un border
	double border = 1;

	// Crear un ColorPicker para relleno
	ColorPicker insideColorPicker = new ColorPicker(Color.YELLOW);

	// Crear un ColorPicker para borde
	ColorPicker borderColorPicker = new ColorPicker(Color.BLACK);

	// StatusBar
	StatusPane statusPane;

	// HistoryPane
	HistoryPane historyPane;



	public PaintPane(CanvasState canvasState, StatusPane statusPane, HistoryPane historyPane) {
		this.canvasState = canvasState;
		this.statusPane = statusPane;
		this.historyPane = historyPane;
		ToggleButton[] toolsArr = {selectionButton, rectangleButton, circleButton, squareButton, ellipseButton, deleteButton};
		ToggleGroup tools = new ToggleGroup();
		for (ToggleButton tool : toolsArr) {
			tool.setMinWidth(90);
			tool.setToggleGroup(tools);
			tool.setCursor(Cursor.HAND);
		}
		VBox buttonsBox = new VBox(10);
		buttonsBox.getChildren().addAll(toolsArr);
		buttonsBox.setPadding(new Insets(5));
		buttonsBox.setStyle("-fx-background-color: #999");
		buttonsBox.setPrefWidth(100);
		gc.setLineWidth(1);
		buttonsBox.getChildren().add(new Text("Border"));
		slider.setShowTickMarks(true);
		slider.setShowTickLabels(true);
		buttonsBox.getChildren().add(slider);
		buttonsBox.getChildren().add(borderColorPicker);
		buttonsBox.getChildren().add(new Text("Relleno"));
		buttonsBox.getChildren().add(insideColorPicker);
		Button[] buttons = {enlargeButton, reduceButton};
		for(Button button : buttons){
			button.setMinWidth(90);
			button.setCursor(Cursor.HAND);
		}
		buttonsBox.getChildren().addAll(buttons);

		enlargeButton.setOnAction(e -> {
			if(selectedFigure!=null) {
				selectedFigure.enlarge();
				canvasState.enlarge(selectedFigure);
				redrawCanvas();
			}
			else statusPane.updateStatus("Una figura debe estar seleccionada para agrandar");
		});

		reduceButton.setOnAction(e -> {
			if(selectedFigure!=null) {
				selectedFigure.reduce();
				canvasState.reduce(selectedFigure);
				redrawCanvas();
			}
			else statusPane.updateStatus("Una figura debe estar seleccionada para achicar");
		});


		slider.setOnMouseDragged(e -> redrawCanvas());
		slider.setCursor(Cursor.HAND);

		borderColorPicker.setOnAction(e -> redrawCanvas());
		borderColorPicker.setCursor(Cursor.HAND);

		insideColorPicker.setOnAction(e -> redrawCanvas());
		insideColorPicker.setCursor(Cursor.HAND);

		canvas.setOnMousePressed(event -> {
			startPoint = new Point(event.getX(), event.getY());
		});

		canvas.setOnMouseReleased(event -> {
			Point endPoint = new Point(event.getX(), event.getY());
			if(startPoint == null) {
				return ;
			}
			if(endPoint.getX() <= startPoint.getX() || endPoint.getY() <= startPoint.getY()) {
				return ;
			}
			Figure newFigure = null;
			if(rectangleButton.isSelected()) {
				newFigure = new Rectangle(startPoint, endPoint,border,borderColorPicker.getValue(),insideColorPicker.getValue());
			}
			else if(circleButton.isSelected()) {
				double circleRadius = Math.abs(endPoint.getX() - startPoint.getX());
				newFigure = new Circle(startPoint, circleRadius,border,borderColorPicker.getValue(),insideColorPicker.getValue());
			} else if(squareButton.isSelected()) {
				double size = Math.abs(endPoint.getX() - startPoint.getX());
				newFigure = new Square(startPoint, size,border,borderColorPicker.getValue(),insideColorPicker.getValue());
			} else if(ellipseButton.isSelected()) {
				Point centerPoint = new Point(Math.abs(endPoint.getX() + startPoint.getX()) / 2, (Math.abs((endPoint.getY() + startPoint.getY())) / 2));
				double sMayorAxis = Math.abs(endPoint.getX() - startPoint.getX());
				double sMinorAxis = Math.abs(endPoint.getY() - startPoint.getY());
				newFigure = new Ellipse(centerPoint, sMayorAxis, sMinorAxis,border,borderColorPicker.getValue(),insideColorPicker.getValue());
			} else {
				return ;
			}
			canvasState.addFigure(newFigure);
			startPoint = null;
			redrawCanvas();
		});

		canvas.setOnMouseMoved(event -> {
			Point eventPoint = new Point(event.getX(), event.getY());
			boolean found = false;
			StringBuilder label = new StringBuilder();
			for(Figure figure : canvasState) {
				if(figureBelongs(figure, eventPoint)) {
					found = true;
					label.append(figure.toString());
				}
			}
			if(found) {
				statusPane.updateStatus(label.toString());
			} else {
				statusPane.updateStatus(eventPoint.toString());
			}
		});

		canvas.setOnMouseClicked(event -> {
			if(selectionButton.isSelected()) {
				Point eventPoint = new Point(event.getX(), event.getY());
				boolean found = false;
				StringBuilder label = new StringBuilder("Se seleccionó: ");
				for (Figure figure : canvasState) {
					if(figureBelongs(figure, eventPoint)) {
						found = true;
						selectedFigure = figure;
						label.append(figure.toString());
					}
				}
				if (found) {
					statusPane.updateStatus(label.toString());
				} else {
					selectedFigure = null;
					statusPane.updateStatus("Ninguna figura encontrada");
				}
				redrawCanvas();
			}
		});

		canvas.setOnMouseDragged(event -> {
			if(selectionButton.isSelected()) {
				if(selectedFigure != null) {
					Point eventPoint = new Point(event.getX(), event.getY());
					double diffX = (eventPoint.getX() - startPoint.getX()) / 100;
					double diffY = (eventPoint.getY() - startPoint.getY()) / 100;
					selectedFigure.move(diffX, diffY);
					redrawCanvas();
				}
			}
		});

		deleteButton.setOnAction(event -> {
			if (selectedFigure != null) {
				canvasState.deleteFigure(selectedFigure);
				selectedFigure = null;
				redrawCanvas();
			}
		});

		historyPane.redoButton.setOnMouseClicked(e -> {
			if(!(canvasState.redoAvailable() > 0)) {
				statusPane.updateStatus("No quedan operaciones por rehacer");
			} else {
				canvasState.redoOperation();
				redrawCanvas();
			};
		});

		historyPane.undoButton.setOnMouseClicked(e -> {
			if(!(canvasState.undoAvailable() > 0)) {
				statusPane.updateStatus("No quedan hay operaciones por deshacer");
			} else {
				canvasState.undoOperation();
				redrawCanvas();
			};
		});


		setLeft(buttonsBox);
		setRight(canvas);
	}

	void redrawCanvas() {
		gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
		border = slider.getValue();
		for(Figure figure : canvasState) {
			if(figure == selectedFigure) {
				gc.setStroke(Color.RED);
				gc.setLineWidth(border);
				if (slider.getValue() != selectedFigure.getBorder()) {
					canvasState.cleanRedos();
				}
				figure.setBorder(border);
				if(selectedFigure.getBorderColor() != borderColorPicker.getValue()) {
					Color aux = selectedFigure.getBorderColor();
					figure.setBorderColor(borderColorPicker.getValue());
					canvasState.changeBorderColor(figure, aux);
				}
				if(selectedFigure.getFillColor() != insideColorPicker.getValue()) {
					Color aux = selectedFigure.getFillColor();
					figure.setFillColor(insideColorPicker.getValue());
					canvasState.changeFillColor(figure, aux);
				}
			} else {
				gc.setLineWidth(figure.getBorder());
				gc.setStroke(figure.getBorderColor());
			}
			gc.setFill(figure.getFillColor());
			figure.draw(gc);
			gc.setLineWidth(1);
		}
		historyPane.updateRedoLabel(canvasState.redoLastOperationTitle());
		historyPane.updateUndoLabel(canvasState.undoLastOperationTitle());
		historyPane.updateRedoMovements(canvasState.redoAvailable());
		historyPane.updateUndoMovements(canvasState.undoAvailable());
	}

	boolean figureBelongs(Figure figure, Point eventPoint) {
		boolean found = false;
		if(figure instanceof Rectangle) {
			Rectangle rectangle = (Rectangle) figure;
			found = eventPoint.getX() > rectangle.getTopLeft().getX() && eventPoint.getX() < rectangle.getBottomRight().getX() &&
					eventPoint.getY() > rectangle.getTopLeft().getY() && eventPoint.getY() < rectangle.getBottomRight().getY();
		} else if(figure instanceof Circle) {
			Circle circle = (Circle) figure;
			found = Math.sqrt(Math.pow(circle.getCenterPoint().getX() - eventPoint.getX(), 2) +
					Math.pow(circle.getCenterPoint().getY() - eventPoint.getY(), 2)) < circle.getRadius();
		} else if(figure instanceof Square) {
			Square square = (Square) figure;
			found = eventPoint.getX() > square.getTopLeft().getX() && eventPoint.getX() < square.getBottomRight().getX() &&
					eventPoint.getY() > square.getTopLeft().getY() && eventPoint.getY() < square.getBottomRight().getY();
		} else if(figure instanceof Ellipse) {
			Ellipse ellipse = (Ellipse) figure;
			// Nota: Fórmula aproximada. No es necesario corregirla.
			found = ((Math.pow(eventPoint.getX() - ellipse.getCenterPoint().getX(), 2) / Math.pow(ellipse.getsMayorAxis(), 2)) +
					(Math.pow(eventPoint.getY() - ellipse.getCenterPoint().getY(), 2) / Math.pow(ellipse.getsMinorAxis(), 2))) <= 0.30;
		}
		return found;
	}

}