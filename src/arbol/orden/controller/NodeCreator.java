package arbol.orden.controller;

import java.util.Map.Entry;
import java.util.AbstractMap;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.EllipseBuilder;
import javafx.scene.shape.Ellipse;
import javafx.scene.text.Text;
import javax.swing.JOptionPane;

public class NodeCreator {

    public static final int RADIO = 20;

    private NodeCreator() {
        throw new AssertionError("No debería instanciarse");
    }

    public static Entry<StackPane, String> createElipse() {
        Ellipse e = EllipseBuilder
                .create()
                .fill(Color.WHITESMOKE)
                .strokeWidth(3)
                .stroke(Color.BLACK)
                .radiusX(RADIO)
                .radiusY(RADIO)
                .build();

        String nodeText = JOptionPane.showInputDialog("Insert Node Value:");
        Text text = new Text(nodeText);
        StackPane stack = new StackPane();
        stack.getChildren().add(e);
        stack.getChildren().add(text);
        Entry<StackPane, String> entry = new AbstractMap.SimpleImmutableEntry<>(stack, nodeText);
        return entry;
    }
    
    public static StackPane createElipse(String s) {
        Ellipse e = EllipseBuilder
                .create()
                .fill(Color.WHITESMOKE)
                .strokeWidth(3)
                .stroke(Color.BLACK)
                .radiusX(RADIO)
                .radiusY(RADIO)
                .build();

        Text text = new Text(s);
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
