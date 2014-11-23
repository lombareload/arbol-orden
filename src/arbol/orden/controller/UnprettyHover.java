package arbol.orden.controller;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;

public class UnprettyHover implements EventHandler<MouseEvent> {

	private final Ellipse e;
	
	public UnprettyHover(Ellipse e) {
		this.e = e;
	}

	@Override
	public void handle(MouseEvent t) {
		e.setFill(Color.WHITE);
	}
	
}
