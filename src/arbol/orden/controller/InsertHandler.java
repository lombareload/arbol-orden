package arbol.orden.controller;

import arbol.orden.ArbolOrden;
import arbol.orden.model.Nodo;
import java.util.Objects;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import static arbol.orden.controller.NodeCreator.createElipse;
import java.util.ArrayList;
import java.util.List;

public class InsertHandler implements EventHandler<ActionEvent> {

    private final Nodo<Node> node;
    private final Nodo<Integer> shadowTree = new Nodo<>(null);
    private final ArbolOrden app;
    private final List<Node> inorden;
    private final List<Node> postorden;
    private final List<Node> preorden;

    public InsertHandler(Nodo<Node> node, ArbolOrden app, List<Node> inorden, List<Node> postorden, List<Node> preorden) {
        Objects.requireNonNull(node, "No se puede usar un nodo nulo como argumento");
        this.node = node;
        this.app = app;
        this.inorden = inorden;
        this.postorden = postorden;
        this.preorden = preorden;
    }

    @Override
    public void handle(ActionEvent t) {
        TextField source = (TextField) t.getSource();
        String texto = source.getText();
        int intValue = Integer.parseInt(texto);
        if (shadowTree.getValue() == null) {
            shadowTree.setValue(intValue);
        } else {
            addInPlace(shadowTree, intValue);
        }
        List<Integer> preorder = preorderTree(shadowTree, new ArrayList<Integer>());
        List<Integer> inorder = inorderTree(shadowTree, new ArrayList<Integer>());
        List<Integer> postorder = postorderTree(shadowTree, new ArrayList<Integer>());
        this.preorden.clear();
        this.preorden.addAll(intListToNodeList(preorder));
        this.postorden.clear();
        this.postorden.addAll(intListToNodeList(postorder));
        this.inorden.clear();
        this.inorden.addAll(intListToNodeList(inorder));
        shadowTreeToNodeTree(shadowTree, node);
        app.crearTablero();
    }

    private void addInPlace(Nodo<Integer> currentNodo, int intValue) {
        if (intValue > currentNodo.getValue()) {
            if (currentNodo.getRight() == null) {
                currentNodo.setRight(new Nodo<>(intValue));
            } else {
                addInPlace(currentNodo.getRight(), intValue);
            }
        } else if (intValue < currentNodo.getValue()) {
            if (currentNodo.getLeft() == null) {
                currentNodo.setLeft(new Nodo<>(intValue));
            } else {
                addInPlace(currentNodo.getLeft(), intValue);
            }
        }
    }

    private void shadowTreeToNodeTree(Nodo<Integer> shadowTree, Nodo<Node> visualTree) {
        visualTree.setValue(createElipse(shadowTree.getValue().toString()));
        if (shadowTree.getLeft() != null) {
            Nodo<Node> left = new Nodo<>(null);
            visualTree.setLeft(left);
            shadowTreeToNodeTree(shadowTree.getLeft(), left);
        }
        if (shadowTree.getRight() != null) {
            Nodo<Node> right = new Nodo<>(null);
            visualTree.setRight(right);
            shadowTreeToNodeTree(shadowTree.getRight(), right);
        }
    }

    private List<Integer> preorderTree(Nodo<Integer> currentNodo, List<Integer> lista) {
        if (currentNodo == null) {
            return lista;
        }
        lista.add(currentNodo.getValue());
        preorderTree(currentNodo.getLeft(), lista);
        preorderTree(currentNodo.getRight(), lista);
        return lista;
    }

    private List<Integer> postorderTree(Nodo<Integer> currentNodo, List<Integer> lista) {
        if (currentNodo == null) {
            return lista;
        }
        postorderTree(currentNodo.getLeft(), lista);
        postorderTree(currentNodo.getRight(), lista);
        lista.add(currentNodo.getValue());
        return lista;
    }

    private List<Integer> inorderTree(Nodo<Integer> currentNodo, List<Integer> lista) {
        if (currentNodo == null) {
            return lista;
        }
        inorderTree(currentNodo.getLeft(), lista);
        lista.add(currentNodo.getValue());
        inorderTree(currentNodo.getRight(), lista);
        return lista;
    }

    private List<Node> intListToNodeList(List<Integer> source) {
        List<Node> nodes = new ArrayList<>(source.size());
        for (Integer i : source) {
            nodes.add(createElipse(i.toString()));
        }
        return nodes;
    }
}
