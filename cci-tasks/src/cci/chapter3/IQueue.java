package cci.chapter3;

public interface IQueue<T> {

	T dequeue();
	void enqueue(T data);
	boolean isEmpty();

}