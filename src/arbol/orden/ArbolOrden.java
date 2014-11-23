package arbol.orden;

import arbol.orden.controller.InsertHandler;
import arbol.orden.model.Nodo;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;
import static arbol.orden.controller.NodeCreator.RADIO;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;

public class ArbolOrden extends Application {

	private final Nodo<Node> arbol = new Nodo<Node>(null);
	private static final int WIDTH = 900;
	private static final int HEIGHT = 600;
	private static final int V_OFFSET = 10;
	private static final int BASE_X = (WIDTH - RADIO) / 2;
	private static final int BASE_Y = RADIO + RADIO + V_OFFSET;
	private static final int DELTA = 60;
	private Stage primaryStage;
	private final List<Node> inorden = new ArrayList<Node>();
	private final List<Node> postorden = new ArrayList<Node>();
	private final List<Node> preorden = new ArrayList<Node>();
	private final TextField input = createInputTextField();
	private final Label preLabel = new Label("Preorden:");
	private final Label postLabel = new Label("Postorden:");
	private final Label inLabel = new Label("Inorden:");
	
	private TextField createInputTextField(){
		TextField field = new TextField();
		field.setLayoutX(0);
		field.setLayoutY(0);
		field.setOnAction(new InsertHandler(arbol, this, inorden, postorden, preorden));
		return field;
	}

	public ArbolOrden() {
		inLabel.setLayoutX(20);
		preLabel.setLayoutX(20);
		postLabel.setLayoutX(20);
		inLabel.setLayoutY(450);
		preLabel.setLayoutY(500);
		postLabel.setLayoutY(550);
	}

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
		paintLabelsOrders(root);
		root.getChildren().add(input);
		if(arbol != null && arbol.getValue() != null){
			paintNodos(root, arbol, BASE_X);
		}
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
		}

		if (right != null) {
			root.getChildren().add(
				paintLine(
						moveTo,
						RADIO + x + (1 + right.calcularLeftLongitud()) * DELTA,
						RADIO + calcularLevel(nodo) + BASE_Y));
			paintNodos(root, right, x + (1 + right.calcularLeftLongitud()) * DELTA);
		}
		root.getChildren().add(nodo.getValue());
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
	
	private void paintLabelsOrders(final Group root){
		root.getChildren().add(inLabel);
		paintOrders(root, 450, inorden);
		root.getChildren().add(preLabel);
		paintOrders(root, 500, preorden);
		root.getChildren().add(postLabel);
		paintOrders(root, 550, postorden);
	}
	
	private void paintOrders(Group root, int y, List<Node> order){
		for(int i = 0; i < order.size(); i++){
			Node current = order.get(i);
			current.setLayoutY(y);
			int x = 100 + i * DELTA;
			current.setLayoutX(x);
			if(i + 1 < order.size()){
				MoveTo moveTo = new MoveTo(x + RADIO, y + RADIO);
				
				root.getChildren().add(paintLine(moveTo, x + DELTA, y + RADIO));
			}
			root.getChildren().add(current);
		}
	}
}
