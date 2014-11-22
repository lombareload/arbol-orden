/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arbol.orden.controller;

import arbol.orden.ArbolOrden;
import static arbol.orden.controller.NodeCreator.createElipse;
import arbol.orden.model.Nodo;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;

/**
 *
 * @author fabian
 */
public class LeftHandler implements EventHandler<MouseEvent> {

	private final Nodo<Node> nodo;
	private final ArbolOrden app;

	public LeftHandler(Nodo<Node> nodo, ArbolOrden app) {
		this.app = app;
		this.nodo = nodo;
	}

	@Override
	public void handle(MouseEvent t) {
		Nodo<Node> newNode = new Nodo<Node>(createElipse());
		nodo.setLeft(newNode);
		app.crearTablero();
	}
}
