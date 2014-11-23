package arbol.orden.controller;

import arbol.orden.ArbolOrden;
import static arbol.orden.controller.NodeCreator.createElipse;
import arbol.orden.model.Nodo;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;

public class RightHandler implements EventHandler<MouseEvent> {

	private final Nodo<Node> nodo;
	private final ArbolOrden app;

	public RightHandler(Nodo<Node> nodo, ArbolOrden app) {
		this.nodo = nodo;
		this.app = app;
	}

	@Override
	public void handle(MouseEvent t) {
		nodo.setRight(new Nodo<Node>(createElipse()));
		app.crearTablero();
	}
}
