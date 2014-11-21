package arbol.orden;

import arbol.orden.model.Nodo;
import java.text.CharacterIterator;
import java.text.StringCharacterIterator;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
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
	
	@Override
	public void start(Stage primaryStage) {
		Node inicial = createElipse();
		inicial.setOnMouseClicked(new EventHandler<MouseEvent>() {
			
			@Override
			public void handle(MouseEvent event) {
				System.out.println("Hello World!");
			}
		});
		
		Group root = new Group();
		root.getChildren().add(inicial);
		
		Scene scene = new Scene(root, WIDTH, HEIGHT);
		
		primaryStage.setTitle("Orden Arbol");
		primaryStage.setScene(scene);
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
		stack.setLayoutX((WIDTH - RADIO)/2);
		stack.setLayoutY(RADIO + V_OFFSET);
		
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
		box.setLayoutX((WIDTH - RADIO)/2);
		box.setLayoutY(RADIO + V_OFFSET);
		return box;
	}
}
