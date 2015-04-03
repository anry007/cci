package cci.chapter4;

import java.util.HashSet;
import java.util.Set;

import cci.chapter3.IStack;
import cci.chapter3.Stack;

public class GraphNode<T> {
	public T data;
	public Set<GraphNode<T>> childs = new HashSet<>();

	public static <T> boolean isRoteBeetweenTwoGraphNodes(GraphNode<T> start,
			GraphNode<T> end) {
		
		Set<GraphNode<T>> visited = new HashSet<>();
		IStack<GraphNode<T>> stack = new Stack<>();
		stack.push(start);
		visited.add(start);
		
		while(!stack.isEmpty()) {
			GraphNode<T> node = stack.pop();
			if(node == end) {
				return true;
			}
			
			for(GraphNode<T> child : node.childs) {
				if(!visited.contains(child)) {
					stack.push(child);
					visited.add(child);
				}
			}
		}
		
		return false;
	}
}
