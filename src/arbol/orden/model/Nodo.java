package arbol.orden.model;

public class Nodo<T> {

    private int height = 0;
    private T value;
    private Nodo<T> parent;
    private Nodo<T> left = EMPTY;
    private Nodo<T> right = EMPTY;

    public Nodo(T value) {
        this.value = value;
    }

    public void setLeft(Nodo<T> left) {
        left.parent = this;
        this.left = left;
    }

    public void setRight(Nodo<T> right) {
        right.parent = this;
        this.right = right;
    }

    public T getValue() {
        return value;
    }

    public Nodo<T> getLeft() {
        return left;
    }

    public Nodo<T> getRight() {
        return right;
    }

    public int calcularLeftLongitud() {
        return (left == null ? 0 : 1 + left.calcularLeftLongitud()) + (left == null ? 0 : left.calcularRightLongitud());
    }

    public int calcularRightLongitud() {
        return (right == null ? 0 : 1 + right.calcularRightLongitud()) + (right == null ? 0 : right.calcularLeftLongitud());
    }

    public Nodo<T> getParent() {
        return parent;
    }

    public int calcularAltura() {
        return parent == null ? 0 : 1 + parent.calcularAltura();
    }

    public void setValue(T value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return String.format("{value:%s, nodes:[%s, %s]}",
                getValue(),
                left == null ? "null" : left.toString(),
                right == null ? "null" : right.toString());
    }

    public static final Nodo EMPTY = new Nodo(null) {

        @Override
        public int calcularAltura() {
            return -1;
        }
    };
}
