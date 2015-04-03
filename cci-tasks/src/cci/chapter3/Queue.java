package cci.chapter3;

import cci.chapter3.Exceptions.*;

public class Queue<T> {
	private Node<T> start;
	private Node<T> end;
	
	public T dequeue() {
		if(start != null) {
			Node<T> tmp = start;
			start = start.next;
			return tmp.data;
		}
		
		throw new QueueIsEmptyException();
	}
	
	public void enqueue(T data) {
		Node<T> node = new Node<T>();
		node.data = data;
		if(isEmpty()) {
			end = node;
			start = node;
		} else {
			end.next = node;
			end = node;
		}
	}
	
	public boolean isEmpty() {
		return start == null;
	}
}
