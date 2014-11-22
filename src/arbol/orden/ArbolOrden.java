package arbol.orden;

import arbol.orden.model.Nodo;
import java.text.CharacterIterator;
import java.text.StringCharacterIterator;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.EllipseBuilder;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class ArbolOrden extends Application {
	
	private final CharacterIterator iterator = new StringCharacterIterator("ABCDEFGHIJKLMNOPQRST");
	private final Nodo<Node> arbol = new Nodo<Node>(createElipse(), null);
	private static final int WIDTH = 800;
	private static final int HEIGHT = 600;
	private static final int RADIO = 20;
	private static final int V_OFFSET = 10;
	private static final int BASE_X = (WIDTH - RADIO)/2;
	private static final int BASE_Y = RADIO+RADIO + V_OFFSET;
	private static final int DELTA = 50;

	public ArbolOrden() {
		arbol.setLeft(new Nodo<Node>(createElipse(), arbol));
		arbol.getLeft().setLeft(new Nodo<Node>(createElipse(), arbol.getLeft()));
		arbol.getLeft().setRight(new Nodo<Node>(createElipse(), arbol.getLeft()));
	}
	
	@Override
	public void start(Stage primaryStage) {
//		Node inicial = createElipse();
//		inicial.setOnMouseClicked(new EventHandler<MouseEvent>() {
//			
//			@Override
//			public void handle(MouseEvent event) {
//				System.out.println("Hello World!");
//			}
//		});
		primaryStage.setTitle("Orden Arbol");
		crearTablero(primaryStage);
		primaryStage.show();
	}

	
	public static void main(String[] args) {
		launch(args);
	}
	
	private StackPane createElipse(){
		Ellipse e = EllipseBuilder
				.create()
				.fill(Color.WHITESMOKE)
				.strokeWidth(3)
				.stroke(Color.BLACK)
				.radiusX(RADIO)
				.radiusY(RADIO)
				.build();
		
		Text text = new Text(Character.toString(iterator.current()));
		iterator.next();
		StackPane stack = new StackPane();
		stack.getChildren().add(e);
		stack.getChildren().add(text);
		return stack;
	}
	
	private HBox fakeElipse(){
		Ellipse e = EllipseBuilder
				.create()
				.fill(Color.WHITESMOKE)
				.strokeWidth(3)
				.radiusX(RADIO)
				.radiusY(RADIO)
				.build();
		HBox box =  new HBox();
		box.setStyle("-fx-border-style: dashed;"
				+ "-fx-border-color: black;"
				+ "-fx-border-width: 3;"
				+ "-fx-border-radius: "+RADIO+";");
		box.getChildren().add(e);
		return box;
	}
	
	private void crearTablero(Stage primaryStage){
		Group root = poblarTablero();
		Scene scene = new Scene(root, WIDTH, HEIGHT);
		primaryStage.setScene(scene);
	}
	
	private Group poblarTablero(){
		Group root = new Group();
		paintNodos(root, arbol, BASE_X);
		return root;
	}

	private void paintNodos(
			final Group root,
			final Nodo<Node> arbol,
			final int x) {
		
		int nodeY = BASE_Y * (arbol.calcularAltura() + 1);
		int nodeX = x;
		arbol.getValue().setLayoutX(nodeX);
		arbol.getValue().setLayoutY(nodeY);
		root.getChildren().add(arbol.getValue());
		
		Nodo<Node> left = arbol.getLeft();
		if(left != null){
			paintNodos(root, left, x - (1 + left.calcularRightLongitud()) * DELTA);
		}
		Nodo<Node> right = arbol.getRight();
		if(right != null){
			paintNodos(root, right, x + (1 + right.calcularLeftLongitud()) * DELTA);
		}
	}
}
