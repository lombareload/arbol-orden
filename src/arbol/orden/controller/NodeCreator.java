package arbol.orden.controller;

import java.text.CharacterIterator;
import java.text.StringCharacterIterator;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.EllipseBuilder;
import javafx.scene.shape.Ellipse;
import javafx.scene.text.Text;

public class NodeCreator {
	public static final int RADIO = 20;

	private final static CharacterIterator iterator = new StringCharacterIterator("ABCDEFGHIJKLMNOPQRSTUVWXYZ");
	private NodeCreator() {
		throw new AssertionError("No debería instanciarse");
	}
	public static StackPane createElipse() {
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
	
	static public HBox fakeElipse() {
		Ellipse e = EllipseBuilder
				.create()
				.fill(Color.WHITE)
				.strokeWidth(3)
				.radiusX(RADIO)
				.radiusY(RADIO)
				.build();
		e.setOnMouseEntered(new PrettyHover(e));
		e.setOnMouseExited(new UnprettyHover(e));
		HBox box = new HBox();
		box.setStyle("-fx-border-style: dashed;"
				+ "-fx-border-color: black;"
				+ "-fx-border-width: 3;"
				+ "-fx-border-radius: " + RADIO + ";");
		box.getChildren().add(e);
		return box;
	}
}
