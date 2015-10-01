package arbol.orden.controller;

import arbol.orden.model.Nodo;

public class RotationController {

    /**
     * asume que el nodo ingresado no es avl y recursivamente busca el nodo mas
     * bajo en el arbol que no es avl
     *
     * @param shadowTree
     * @return 
     */
    public static Nodo<Integer> rotate(Nodo<Integer> shadowTree) {
        boolean avlLeft = shadowTree.getLeft().esAVL();
        boolean avlRight = shadowTree.getRight().esAVL();
        if (avlLeft && avlRight) {
            System.out.println("rotar " + shadowTree.getValue());
            return doRotate(shadowTree);
        } else if (!avlLeft) {
            shadowTree.setLeft(rotate(shadowTree.getLeft()));
        } else if (!avlRight) {
            shadowTree.setRight(rotate(shadowTree.getRight()));
        } else {
            throw new AssertionError("El arbol ya era AVL");
        }
        return shadowTree;
    }

    /**
     * determina en que dirección debe rotar el nodo
     * @param nodo 
     */
    private static Nodo<Integer> doRotate(Nodo<Integer> nodo) {
        Nodo<Integer> parent = nodo.getParent();
        if (parent == Nodo.EMPTY) {
            if (nodo.getLeft().getHeight() < nodo.getRight().getHeight()) {
                return rotateLeft(nodo);
            } else {
                return rotateRight(nodo);
            }
        } else if (parent.getRight() == nodo) {
            return rotateLeft(nodo);
        } else {
            return rotateRight(nodo);
        }
    }

    private static Nodo<Integer> rotateLeft(Nodo<Integer> nodo) {
        Nodo<Integer> newNodo = new Nodo<>(nodo.getRight().getValue());
        Nodo<Integer> right = nodo.getRight();
        Nodo<Integer> newRight = right.getLeft();
        newNodo.setLeft(nodo);
        newNodo.setRight(right.getRight());
        nodo.setRight(newRight);
        return newNodo;
    }

    private static Nodo<Integer> rotateRight(Nodo<Integer> nodo) {
        Nodo<Integer> newNodo = new Nodo<>(nodo.getLeft().getValue());
        Nodo<Integer> left = nodo.getLeft();
        Nodo<Integer> newLeft = left.getRight();
        newNodo.setRight(nodo);
        newNodo.setLeft(left.getLeft());
        nodo.setLeft(newLeft);
        return newNodo;
    }
}
