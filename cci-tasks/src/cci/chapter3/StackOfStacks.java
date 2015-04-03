package cci.chapter3;

public class StackOfStacks<T> implements IStack<T> {
	Stack<Stack<T>> stacks;
	int maxCapacity;
	int currentCapacity;
	
	public StackOfStacks(int maxCapacity) {
		this.maxCapacity = maxCapacity;
		currentCapacity = maxCapacity;
		stacks = new Stack<Stack<T>>();
	}
	
	public T peek() {
		return stacks.peek().peek();
	}
	
	public T pop() {
		Stack<T> currentStack = stacks.peek();
		T value = currentStack.pop();
		currentCapacity--;

		if(currentStack.isEmpty()) {
			stacks.pop();
			currentCapacity = maxCapacity;
		}

		return value;
	}
	
	public void push(T value) {
		if(currentCapacity == maxCapacity) {
			Stack<T> currentStack = new Stack<T>(); 
			stacks.push(currentStack);
			currentCapacity = 0;
		}
		
		stacks.peek().push(value);
		currentCapacity++;
	}
	
	public boolean isEmpty() {
		return stacks.isEmpty();
	}
	
	public int getNumberOfPartitions() {
		return stacks.size();
	}
	
	public int size() {
		if(isEmpty())
		{
			return 0;
		} else {
			return (getNumberOfPartitions() - 1) * maxCapacity + currentCapacity;
		}
	}
}