package cci.chapter3;

public class StackBasedQueue<T> implements IQueue<T>{
	private Stack<T> inStack = new Stack<T>();
	private Stack<T> outStack = new Stack<T>();
	
	@Override
	public T dequeue() {
		if(outStack.isEmpty())
		{
			while(!inStack.isEmpty()) {
				T data = inStack.pop();
				outStack.push(data);
			}
		}
		
		try {
			return outStack.pop();
		} catch (Stack.StackIsEmptyException e) {
			throw new Queue.QueueIsEmptyException(e);
		}
	}

	@Override
	public void enqueue(T data) {
		inStack.push(data);
	}

	@Override
	public boolean isEmpty() {
		return inStack.isEmpty() && outStack.isEmpty();
	}

}
