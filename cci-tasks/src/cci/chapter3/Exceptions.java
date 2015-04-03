package cci.chapter3;

public class Exceptions {
	public static class QueueIsEmptyException extends UnsupportedOperationException {
		private static final long serialVersionUID = 1777519214849154257L;

		public QueueIsEmptyException() {
			super("Queue is empty");
		}
	}

	public static class StackIsEmptyException extends UnsupportedOperationException {
		private static final long serialVersionUID = -8079678322725822355L;

		public StackIsEmptyException() {
			super("Stack is empty");
		}
	}
}
