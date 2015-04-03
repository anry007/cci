package cci.chapter3;

public class Stack<T> implements IStack<T> {
	private Node<T> top;
	private int size = 0;
	
	public T pop() {
		if(top != null) {
			Node<T> tmp = top;
			top = top.next;
			size--;
			return tmp.data;
		}
		throw new StackIsEmptyException();
	}
	
	public T peek() {
		if(top != null) {
			return top.data;
		}
		throw new StackIsEmptyException();
	}
	
	public void push(T data) {
		Node<T> node = new Node<T>();
		node.data = data;
		node.next = top;
		top = node;
		size++;
	}
	
	public boolean isEmpty() {
		return top == null;
	}
	
	public int size() {
		return size;
	}

	public static class StackIsEmptyException extends UnsupportedOperationException {
		private static final long serialVersionUID = -8079678322725822355L;

		public StackIsEmptyException() {
			super("Stack is empty");
		}
	}
}
