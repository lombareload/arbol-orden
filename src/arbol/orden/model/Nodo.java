package arbol.orden.model;

public class Nodo<T> {
	private final T value;
	private final Nodo<T> parent;
	private Nodo<T> left;
	private Nodo<T> right;

	public Nodo(T value, Nodo<T> parent) {
		this.value = value;
		this.parent =  parent;
	}
	
	public void setLeft(Nodo<T> left){
		this.left = left;
	}

	public void setRight(Nodo<T> right) {
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
	
	public int calculateLeftLongitud(){
		return left == null ? 0 : 1 + left.calculateLeftLongitud();
	}
	
	public int calculateRightLogitud(){
		return right == null ? 0 : 1 + right.calculateRightLogitud();
	}
}
