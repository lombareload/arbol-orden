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
    public static <T> Nodo<T> rotate(Nodo<T> shadowTree) {
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
    private static <T> Nodo<T> doRotate(Nodo<T> nodo) {
        Nodo<T> parent = nodo.getParent();
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

    private static <T> Nodo<T> rotateLeft(Nodo<T> nodo) {
        Nodo<T> newNodo = new Nodo<T>(nodo.getRight().getValue());
        Nodo<T> right = nodo.getRight();
        Nodo<T> newRight = right.getLeft();
        newNodo.setLeft(nodo);
        newNodo.setRight(right.getRight());
        nodo.setRight(newRight);
        return newNodo;
    }

    private static <T> Nodo<T> rotateRight(Nodo<T> nodo) {
        Nodo<T> newNodo = new Nodo<T>(nodo.getLeft().getValue());
        Nodo<T> left = nodo.getLeft();
        Nodo<T> newLeft = left.getRight();
        newNodo.setRight(nodo);
        newNodo.setLeft(left.getLeft());
        nodo.setLeft(newLeft);
        return newNodo;
    }
}
