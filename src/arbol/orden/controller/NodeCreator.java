package arbol.orden.controller;

import java.util.Objects;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.EllipseBuilder;
import javafx.scene.shape.Ellipse;
import javafx.scene.text.Text;

public class NodeCreator {
	public static final int RADIO = 20;

	private NodeCreator() {
		throw new AssertionError("No debería instanciarse");
	}
	public static StackPane createElipse(String value) {
		Objects.requireNonNull(value, "no puede crear nodo con valores nulos");
		Ellipse e = EllipseBuilder
				.create()
				.fill(Color.WHITESMOKE)
				.strokeWidth(3)
				.stroke(Color.BLACK)
				.radiusX(RADIO)
				.radiusY(RADIO)
				.build();

		Text text = new Text(value);
		StackPane stack = new StackPane();
		stack.getChildren().add(e);
		stack.getChildren().add(text);
		return stack;
	}
}
