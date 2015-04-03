package cci.chapter3;

import cci.chapter3.Stack.StackIsEmptyException;

public class Queue<T> implements IQueue<T> {
	private Node<T> start;
	private Node<T> end;
	
	@Override
	public T dequeue() {
		if(start != null) {
			Node<T> tmp = start;
			start = start.next;
			return tmp.data;
		}
		
		throw new QueueIsEmptyException();
	}
	
	@Override
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
	
	@Override
	public boolean isEmpty() {
		return start == null;
	}

	public static class QueueIsEmptyException extends UnsupportedOperationException {
		private static final long serialVersionUID = 1777519214849154257L;

		public QueueIsEmptyException() {
			super("Queue is empty");
		}

		public QueueIsEmptyException(StackIsEmptyException e) {
			super("Queue is empty", e);
		}
	}
}
