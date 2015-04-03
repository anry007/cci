package cci.chapter3;

public class SortableStack<T extends Comparable<T>> extends Stack<T> {
	public IStack<T> getSorted() {
		IStack<T> sortedStack = new Stack<T>();
		while(!this.isEmpty()) {
			T data =  this.pop();
			int numberOfMovedElements = 0;
			while(!sortedStack.isEmpty())
			{
				T currentElement = sortedStack.peek();
				if(currentElement.compareTo(data) < 0) {
					this.push(currentElement);
					sortedStack.pop();
					numberOfMovedElements++;
				} else {
					break;
				}						 
			}

			sortedStack.push(data);
			
			for(int i = 0; i < numberOfMovedElements; ++i) {
				sortedStack.push(this.pop());
			}
		}
		
		return sortedStack;
	}
}
