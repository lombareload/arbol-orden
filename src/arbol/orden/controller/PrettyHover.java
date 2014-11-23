package arbol.orden.controller;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.text.Text;

public class PrettyHover implements EventHandler<MouseEvent>{

	final private Ellipse n;
	private static final Text plus = new Text("+");
	public PrettyHover(Ellipse n) {
		this.n = n;
	}

	@Override
	public void handle(MouseEvent t) {
		n.setFill(Color.ANTIQUEWHITE);
	}
	
}
