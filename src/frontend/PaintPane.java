package frontend;

import backend.CanvasState;
import backend.model.*;
import frontend.Creators.*;
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
	private final CanvasState canvasState;

	// Canvas y relacionados
	private final Canvas canvas = new Canvas(800, 600);
	private final GraphicsContext gc = canvas.getGraphicsContext2D();

	// Botones Barra Izquierda
	private final ToggleButton selectionButton = new ToggleButton("Seleccionar");
	private final ToggleButton rectangleButton = new ToggleButton("Rectángulo");
	private final ToggleButton circleButton = new ToggleButton("Círculo");
	private final ToggleButton squareButton = new ToggleButton("Cuadrado");
	private final ToggleButton ellipseButton = new ToggleButton("Elipse");
	private final ToggleButton deleteButton = new ToggleButton("Borrar");
	private final Button enlargeButton = new Button("Agrandar");
	private final Button reduceButton = new Button("Achicar");

	// Dibujar una figura
	private Point startPoint;

	// Seleccionar una figura
	private Figure selectedFigure;

	// Crear el slider para el borde
	private final Slider slider = new Slider(1, 50, 1);

	// Seleccionar un border
	private double border = 1;

	// Crear un ColorPicker para relleno
	private final ColorPicker insideColorPicker = new ColorPicker(Color.YELLOW);

	// Crear un ColorPicker para borde
	private final ColorPicker borderColorPicker = new ColorPicker(Color.BLACK);

	// StatusBar
	private final StatusPane statusPane;

	// HistoryPane
	private final HistoryPane historyPane;

	//Creator actual
	private Creator currentCreator;



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
		for(Button button : buttons) {
			button.setMinWidth(90);
			button.setCursor(Cursor.HAND);
		}
		buttonsBox.getChildren().addAll(buttons);

		deleteButton.setOnMouseClicked(e -> currentCreator = null);

		selectionButton.setOnMouseClicked(e -> currentCreator = null);

		rectangleButton.setOnMouseClicked(e -> {
			currentCreator = new RectangleCreator();
			selectedFigure = null;
		});

		squareButton.setOnMouseClicked(e -> {
			currentCreator=new SquareCreator();
			selectedFigure=null;
		});

		circleButton.setOnMouseClicked(e -> {
			currentCreator = new CircleCreator();
			selectedFigure = null;
		});

		ellipseButton.setOnMouseClicked(e -> {
			currentCreator = new EllipseCreator();
			selectedFigure = null;
		});

		enlargeButton.setOnAction(e -> {
			if (selectedFigure != null) {
				selectedFigure.enlarge();
				canvasState.enlarge(selectedFigure);
				redrawCanvas();
			}
			else statusPane.updateStatus("Una figura debe estar seleccionada para agrandar");
		});


		reduceButton.setOnAction(e -> {
			if (selectedFigure != null) {
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

		canvas.setOnMousePressed(event -> startPoint = new Point(event.getX(), event.getY()));

		canvas.setOnMouseReleased(event -> {
			Point endPoint = new Point(event.getX(), event.getY());
			if (startPoint == null || currentCreator == null) {
				return;
			}
			try {
				Figure newFigure = currentCreator.createInstance(startPoint, endPoint, border, borderColorPicker.getValue(), insideColorPicker.getValue());
				canvasState.addFigure(newFigure);
				redrawCanvas();
			}
			catch (Exception e) {
				statusPane.updateStatus(e.getMessage());
			}
			startPoint=null;
		});

		canvas.setOnMouseMoved(event -> {
			Point eventPoint = new Point(event.getX(), event.getY());
			boolean found = false;
			StringBuilder label = new StringBuilder();
			for (Figure figure : canvasState) {
				if (figure.figureBelongs(eventPoint)) {
					found = true;
					label.append(figure);
				}
			}
			if (found) {
				statusPane.updateStatus(label.toString());
			} else {
				statusPane.updateStatus(eventPoint.toString());
			}
		});

		canvas.setOnMouseClicked(event -> {
			if (selectionButton.isSelected()) {
				Point eventPoint = new Point(event.getX(), event.getY());
				boolean found = false;
				StringBuilder label = new StringBuilder("Se seleccionó: ");
				for (Figure figure : canvasState) {
					if (figure.figureBelongs(eventPoint)) {
						found = true;
						selectedFigure = figure;
						label.append(figure);
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
			if (selectionButton.isSelected()) {
				if (selectedFigure != null) {
					Point eventPoint = new Point(event.getX(), event.getY());
					double diffX = (eventPoint.getX() - startPoint.getX()) / 100;
					double diffY = (eventPoint.getY() - startPoint.getY()) / 100;
					selectedFigure.move(diffX, diffY);
					redrawCanvas();
				}
			}
		});

		deleteButton.setOnAction(event -> {
			currentCreator=null;
			if (selectedFigure != null) {
				canvasState.deleteFigure(selectedFigure);
				selectedFigure = null;
				redrawCanvas();
			}
		});

		historyPane.redoButtonSetOnAction(e -> {
			try {
				canvasState.redoOperation();
				redrawCanvas();
			} catch (Exception redoException) {
				Alert alert = new Alert(Alert.AlertType.WARNING);
				alert.setHeaderText("No quedan operaciones para rehacer");
				alert.setContentText("Por favor verifique que queden operaciones pendientes por rehacer");
				alert.showAndWait()
						.filter(response -> response == ButtonType.OK)
						.ifPresent(response -> alert.close());
			}
		});

		historyPane.undoButtonSetOnAction(e -> {
			try {
				selectedFigure=null;
				canvasState.undoOperation();
				redrawCanvas();
			} catch (Exception undoException) {
				Alert alert = new Alert(Alert.AlertType.WARNING);
				alert.setHeaderText("No quedan operaciones por deshacer");
				alert.setContentText("Por favor verifique que queden operaciones pendientes por deshacer");
				alert.showAndWait()
						.filter(response -> response == ButtonType.OK)
						.ifPresent(response -> alert.close());
			}
		});
		setLeft(buttonsBox);
		setRight(canvas);
	}

	public void redrawCanvas() {
		gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
		border = slider.getValue();
		for (Figure figure : canvasState) {
			if (figure == selectedFigure) {
				gc.setStroke(Color.RED);
				gc.setLineWidth(border);
				figure.setBorder(border);
				if (selectedFigure.getBorderColor() != borderColorPicker.getValue()) {
					Color aux = selectedFigure.getBorderColor();
					figure.setBorderColor(borderColorPicker.getValue());
					canvasState.changeBorderColor(figure, aux);
				}
				if (selectedFigure.getFillColor() != insideColorPicker.getValue()) {
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
}