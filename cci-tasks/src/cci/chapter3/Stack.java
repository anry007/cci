package cci.chapter3;

import cci.chapter3.Exceptions.*;

public class Stack<T> {
	private Node<T> top;
	
	public T pop() {
		if(top != null) {
			Node<T> tmp = top;
			top = top.next;
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
	}
	
	public boolean isEmpty() {
		return top == null;
	}
}
