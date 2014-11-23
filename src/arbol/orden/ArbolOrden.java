package arbol.orden;

import arbol.orden.controller.LeftHandler;
import arbol.orden.controller.RightHandler;
import arbol.orden.model.Nodo;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;
import static arbol.orden.controller.NodeCreator.createElipse;
import static arbol.orden.controller.NodeCreator.fakeElipse;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;

public class ArbolOrden extends Application {

	private final Nodo<Node> arbol = new Nodo<Node>(createElipse());
	private static final int WIDTH = 900;
	private static final int HEIGHT = 600;
	private static final int RADIO = 20;
	private static final int V_OFFSET = 10;
	private static final int BASE_X = (WIDTH - RADIO) / 2;
	private static final int BASE_Y = RADIO + RADIO + V_OFFSET;
	private static final int DELTA = 60;
	private Stage primaryStage;

	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		primaryStage.setTitle("Orden Arbol");
		crearTablero();
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}

	public void crearTablero() {
		Group root = pintarInterfaz();
		Scene scene = new Scene(root, WIDTH, HEIGHT);
		primaryStage.setScene(scene);
	}

	private Group pintarInterfaz() {
		Group root = new Group();
		paintNodos(root, arbol, BASE_X);
		return root;
	}

	private void paintNodos(
			final Group root,
			final Nodo<Node> nodo,
			final int x) {

		int nodeY = calcularLevel(nodo);
		int nodeX = x;
		nodo.getValue().setLayoutX(nodeX);
		nodo.getValue().setLayoutY(nodeY);

		MoveTo moveTo = new MoveTo(nodeX + RADIO, nodeY + RADIO);
		Nodo<Node> left = nodo.getLeft();
		Nodo<Node> right = nodo.getRight();

		if (left != null) {
			root.getChildren().add(
				paintLine(
						moveTo,
						RADIO + x - (1 + left.calcularRightLongitud()) * DELTA,
						RADIO + calcularLevel(nodo) + BASE_Y));
			paintNodos(root, left, x - (1 + left.calcularRightLongitud()) * DELTA);
		} else {
			paintFakeNodo(root, nodo, x - DELTA / 2, new LeftHandler(nodo, this));
		}

		if (right != null) {
			root.getChildren().add(
				paintLine(
						moveTo,
						RADIO + x + (1 + right.calcularLeftLongitud()) * DELTA,
						RADIO + calcularLevel(nodo) + BASE_Y));
			paintNodos(root, right, x + (1 + right.calcularLeftLongitud()) * DELTA);
		} else {
			paintFakeNodo(root, nodo, x + DELTA / 2, new RightHandler(nodo, this));
		}
		root.getChildren().add(nodo.getValue());
	}

	private void paintFakeNodo(final Group root, final Nodo<Node> parent, int x, EventHandler handler) {
		Node fakeNode = fakeElipse();
		fakeNode.setLayoutY(calcularLevel(parent) + BASE_Y);
		fakeNode.setLayoutX(x);
		fakeNode.setOnMouseClicked(handler);
		root.getChildren().add(fakeNode);
	}

	private int calcularLevel(Nodo<Node> nodo) {
		return BASE_Y * (nodo.calcularAltura() + 1);
	}
	
	private Path paintLine(MoveTo moveTo, double x, double y){
		Path path = new Path();
		path.getElements().add(moveTo);
		path.getElements().add(new LineTo(x, y));
		path.setStrokeWidth(3);
		return path;
	}
}
