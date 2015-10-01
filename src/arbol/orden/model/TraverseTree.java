package arbol.orden.model;

import static arbol.orden.controller.NodeCreator.createElipse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javafx.scene.Node;
import javafx.scene.layout.StackPane;

public class TraverseTree {

    private TraverseTree() {
    }

    public static List<String> preorderTree(Nodo<Map.Entry<StackPane, String>> currentNodo, List<String> lista) {
        if (currentNodo == Nodo.EMPTY) {
            return lista;
        }
        lista.add(currentNodo.getValue().getValue());
        preorderTree(currentNodo.getLeft(), lista);
        preorderTree(currentNodo.getRight(), lista);
        return lista;
    }

    public static List<String> postorderTree(Nodo<Map.Entry<StackPane, String>> currentNodo, List<String> lista) {
        if (currentNodo == Nodo.EMPTY) {
            return lista;
        }
        postorderTree(currentNodo.getLeft(), lista);
        postorderTree(currentNodo.getRight(), lista);
        lista.add(currentNodo.getValue().getValue());
        return lista;
    }

    public static List<String> inorderTree(Nodo<Map.Entry<StackPane, String>> currentNodo, List<String> lista) {
        if (currentNodo == Nodo.EMPTY) {
            return lista;
        }
        inorderTree(currentNodo.getLeft(), lista);
        lista.add(currentNodo.getValue().getValue());
        inorderTree(currentNodo.getRight(), lista);
        return lista;
    }

    public static List<Node> stringListToNodeList(List<String> source) {
        List<Node> nodes = new ArrayList<Node>(source.size());
        for (String i : source) {
            nodes.add(createElipse(i));
        }
        return nodes;
    }
}
