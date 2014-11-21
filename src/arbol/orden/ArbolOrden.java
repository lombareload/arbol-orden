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
	private static final int WIDTH = 600;
	private static final int HEIGHT = 500;
	private static final int RADIO = 20;
	private static final int V_OFFSET = 10;
	private static final int BASE_X = (WIDTH - RADIO)/2;
	private static final int BASE_Y = RADIO + V_OFFSET;
	
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
		agregarNodos(root, arbol);
		return root;
	}

	private void agregarNodos(Group root, Nodo<Node> arbol) {
		int y = BASE_Y * (arbol.calcularAltura() + 1);
		int x = BASE_X + (arbol.calcularRightLongitud());
		arbol.getValue().setLayoutX(x);
		arbol.getValue().setLayoutY(y);
		root.getChildren().add(arbol.getValue());
	}
}
