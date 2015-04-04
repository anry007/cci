package cci.chapter4;

import java.util.AbstractMap;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
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
	
	/**
	 * Given a binary search tree, design an algorithm which creates a linked list of all the
	 * nodes at each depth (i.e., if you have a tree with depth D, you’ll have D linked lists).
	 * 
	 * @param root root of the tree to process
	 * @return {@link Set} of {@link List} that contains on the nodes on each depth in the tree
	 */
	public static <T> Map<Integer, List<TreeNode<T>>> toLevelLists(TreeNode<T> root) {
		if(root == null)
			return null;
		
		Map<Integer, List<TreeNode<T>>> lists = new HashMap<>();
		
		IQueue<Map.Entry<TreeNode<T>, Integer>> queue = new Queue<Map.Entry<TreeNode<T>, Integer>>();
		queue.enqueue(new AbstractMap.SimpleEntry<>(root, 0));
		
		while(!queue.isEmpty()) {
			Map.Entry<TreeNode<T>, Integer> currentNodeDescriptor = queue.dequeue();
			TreeNode<T> node = currentNodeDescriptor.getKey();
			int level = currentNodeDescriptor.getValue();
			
			if(!lists.containsKey(level)) {
				lists.put(level, new LinkedList<TreeNode<T>>());
			}
			lists.get(level).add(node);
			
			pushNotNullNodeToQueue(queue, level, node.left);
			pushNotNullNodeToQueue(queue, level, node.right);
		}
		
		return lists;
	}
	
	/**
	 * Given a binary search tree, design an algorithm which creates a linked list of all the
	 * nodes at each branch.
	 * 
	 * @param root root of the tree to process
	 * @return {@link Set} of {@link List} for each depth in the tree
	 */
	public static <T> Collection<List<TreeNode<T>>> toBranchLists(TreeNode<T> root) {
		if(root == null)
			return null;
		
		Set<List<TreeNode<T>>> lists = new HashSet<>();
		
		Stack<Map.Entry<TreeNode<T>, List<TreeNode<T>>>> stack = new Stack<Map.Entry<TreeNode<T>, List<TreeNode<T>>>>();
		List<TreeNode<T>> initialList = new LinkedList<>();
		initialList.add(root);
		stack.push(new AbstractMap.SimpleEntry<>(root, initialList));
		lists.add(initialList);
		
		while(!stack.isEmpty()) {
			Map.Entry<TreeNode<T>, List<TreeNode<T>>> elementWithList = stack.pop();
			TreeNode<T> element = elementWithList.getKey();
			List<TreeNode<T>> list = elementWithList.getValue();
			
			List<TreeNode<T>> listBeforeLeftAdded = list;
			boolean wasBranched = false;
			TreeNode<T> left = element.left;
			if(left != null) {
				listBeforeLeftAdded = new LinkedList<>(list);
				wasBranched = true;
				list.add(left);
				stack.push(new AbstractMap.SimpleEntry<>(left, list));
			}

			TreeNode<T> right = element.right;
			if(right != null) {
				// need to add list only if one was branched
				if(wasBranched) {
					lists.add(listBeforeLeftAdded);
				}
				
				listBeforeLeftAdded.add(right);
				stack.push(new AbstractMap.SimpleEntry<>(right, listBeforeLeftAdded));
			}
		}
		
		return lists;
	}

	static <T> void pushNotNullNodeToQueue(IQueue<Map.Entry<TreeNode<T>, Integer>> queue, int level, TreeNode<T> childNode) {
		if(childNode != null) {
			Map.Entry<TreeNode<T>, Integer> childNodeDescriptor = new AbstractMap.SimpleEntry<>(childNode, level + 1);
			queue.enqueue(childNodeDescriptor);
		}
	}
}
