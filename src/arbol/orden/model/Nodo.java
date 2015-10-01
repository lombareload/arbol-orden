package arbol.orden.model;

public class Nodo<T> {

    private int height = 0;
    private T value;
    private Nodo<T> parent = EMPTY;
    private Nodo<T> left = EMPTY;
    private Nodo<T> right = EMPTY;

    public Nodo(T value) {
        this.value = value;
    }

    public void setLeft(Nodo<T> left) {
        left.parent = this;
        this.left = left;
        updateHeight();
    }

    public void setRight(Nodo<T> right) {
        right.parent = this;
        this.right = right;
        updateHeight();
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

    public int getHeight() {
        return height;
    }
    
    public void updateHeight() {
        height = Integer.max(left.getHeight(), right.getHeight()) + 1;
        parent.updateHeight();
    }
    
    public boolean esAVL() {
        int rightHeight = right.getHeight();
        int leftHeight = left.getHeight();
        int abs = Math.abs(rightHeight - leftHeight);
        return abs == 1 || abs == 0;
    }

    @Override
    public String toString() {
        return String.format("{value:%s\n, nodes:[%s, %s]}",
                getValue() + "-" + esAVL(),
                left == null ? "null" : left.toString(),
                right == null ? "null" : right.toString());
    }

    public static final Nodo EMPTY = new Nodo(null) {
        
        @Override
        public int calcularAltura() {
            return -1;
        }
        
        @Override
        public int getHeight() {return -1;}
        
        @Override
        public void updateHeight(){}
        
        @Override
        public boolean esAVL(){return true;}
    };
}
