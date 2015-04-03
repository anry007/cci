package cci.chapter4;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import cci.chapter3.IQueue;
import cci.chapter3.Queue;
import cci.chapter3.Stack;

public class TreeNode<T> {
	public T data;
	public TreeNode<T> left;
	public TreeNode<T> right;
	
	public TreeNode(T data) {
		this.data = data;
	}
	
	public TreeNode(T data, TreeNode<T> left, TreeNode<T> right) {
		this(data);
		this.left = left;
		this.right = right;
	}
	
	public boolean isLeafNode() {
		return left == null && right == null;
	}
	
	/**
	 * Given a sorted (increasing order) array, write an algorithm to create a binary tree with
	 * minimal height.
	 * 
	 * @param array array to create tree from
	 * @return binary tree with the smallest depth
	 */
	public static <Type> TreeNode<Type> fromArray(Type[] array) {
		if(array.length > 0 ) {
			TreeNode<Type> root = new TreeNode<Type>(array[0]);
			if(array.length > 1) {
				IQueue<TreeNode<Type>> queue = new Queue<>();
				queue.enqueue(root);
				for(int i = 1; i < array.length; i += 2) {
					TreeNode<Type> currentElement = queue.dequeue();
					
					TreeNode<Type> leftChild = new TreeNode<Type>(array[i]);
					currentElement.left = leftChild;
					queue.enqueue(leftChild);
					
					if(i + 1 < array.length) {
						TreeNode<Type> rightChild = new TreeNode<Type>(array[i + 1]);
						currentElement.right = rightChild;
						queue.enqueue(rightChild);
					}
				}
			}
			
			return root;
		}
		
		return null;
	}
	
	public static <T> Set<List<T>> toLists(TreeNode<T> root) {
		if(root == null)
			return null;
		
		Set<List<T>> lists = new HashSet<>();
		Stack<TreeNode<T>> stack = new Stack<TreeNode<T>>();
		stack.push(root);
		while(!stack.isEmpty()) {
			TreeNode<T> element = stack.pop();
		}
		
		return null;
	}
}
