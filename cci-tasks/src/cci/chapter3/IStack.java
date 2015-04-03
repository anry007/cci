package cci.chapter3;

public interface IStack<T> {
	T pop();
	T peek();
	void push(T value);
	boolean isEmpty();
	int size();
}
