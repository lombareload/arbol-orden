package arbol.orden.controller;

import arbol.orden.ArbolOrden;
import arbol.orden.model.Nodo;
import java.util.Objects;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import static arbol.orden.controller.NodeCreator.createElipse;

public class InsertHandler implements EventHandler<ActionEvent>{

	private final Nodo<Node> node;
	private final Nodo<Integer> shadowTree = new Nodo<Integer>(null);
	private final ArbolOrden app;
	
	public InsertHandler(Nodo<Node> node, ArbolOrden app) {
		Objects.requireNonNull(node, "No se puede usar un nodo nulo como argumento");
		this.node = node;
		this.app = app;
	}
	
	@Override
	public void handle(ActionEvent t) {
		TextField source = (TextField) t.getSource();
		String texto = source.getText();
		int intValue = Integer.parseInt(texto);
		if(shadowTree.getValue() == null){
			shadowTree.setValue(intValue);
		} else{
			addInPlace(shadowTree, intValue);
		}
		shadowTreeToNodeTree(shadowTree, node);
		app.crearTablero();
	}

	private void addInPlace(Nodo<Integer> currentNodo, int intValue) {
		if (intValue > currentNodo.getValue()){
			if(currentNodo.getRight() == null){
				currentNodo.setRight(new Nodo<Integer>(intValue));
			} else{
				addInPlace(currentNodo.getRight(), intValue);
			}
		} else if (intValue < currentNodo.getValue()){
			if(currentNodo.getLeft() == null){
				currentNodo.setLeft(new Nodo<Integer>(intValue));
			} else{
				addInPlace(currentNodo.getLeft(), intValue);
			}
		}
	}
	
	private void shadowTreeToNodeTree(Nodo<Integer> shadowTree, Nodo<Node> visualTree){
		visualTree.setValue(createElipse(shadowTree.getValue().toString()));
		if(shadowTree.getLeft() != null){
			Nodo<Node> left = new Nodo<Node>(null);
			visualTree.setLeft(left);
			shadowTreeToNodeTree(shadowTree.getLeft(), left);
		}
		if(shadowTree.getRight() != null){
			Nodo<Node> right = new Nodo<Node>(null);
			visualTree.setRight(right);
			shadowTreeToNodeTree(shadowTree.getRight(), right);
		}
	}
}
