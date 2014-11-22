package arbol.orden.model;

public class Nodo<T> {
	private final T value;
	private Nodo<T> parent;
	private Nodo<T> left;
	private Nodo<T> right;

	public Nodo(T value) {
		this.value = value;
	}
	
	public void setLeft(Nodo<T> left){
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
	
	public int calcularLeftLongitud(){
		return (left == null ? 0 : 1 + left.calcularLeftLongitud());
	}
	
	public int calcularRightLongitud(){
		return (right == null ? 0 : 1 + right.calcularRightLongitud());
	}
	
	public Nodo<T> getParent(){
		return parent;
	}
	
	public int calcularAltura(){
		return parent == null ? 0 : 1 + parent.calcularAltura();
	}
}
