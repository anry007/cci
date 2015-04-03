package cci.chapter4;

import java.util.AbstractMap;
import java.util.Map;
import java.util.Map.Entry;

import cci.chapter3.IQueue;
import cci.chapter3.Queue;

public class BalancableTree<T> extends TreeNode<T>{

	public BalancableTree(T data) {
		super(data);
	}
	
	public BalancableTree(T data, TreeNode<T> left,
			TreeNode<T> right) {
		super(data, left, right);
	}

	/**
	 * Implement a function to check if a tree is balanced. For the purposes of this question,
	 * a balanced tree is defined to be a tree such that no two leaf nodes differ in distance
	 * from the root by more than one.
	 * @return true if tree is balanced
	 */
	public boolean isBallanced() {
		IQueue<Map.Entry<TreeNode<T>, Integer>> queue = new Queue<Map.Entry<TreeNode<T>, Integer>>();
		queue.enqueue(new AbstractMap.SimpleEntry<>((TreeNode<T>)this, 0));
		Integer currentMinDepth = null;
		while(!queue.isEmpty()) {
			Map.Entry<TreeNode<T>, Integer> currentNodeDescriptor = queue.dequeue();
			TreeNode<T> node = currentNodeDescriptor.getKey();
			int level = currentNodeDescriptor.getValue();
			
			if(node.isLeafNode()) {
				if(currentMinDepth == null) {
					currentMinDepth = level;
				}
				
				if(level > currentMinDepth + 1) {
					return false;
				}
			}
			
			pushNotNullNodeToQueue(queue, level, node.left);
			pushNotNullNodeToQueue(queue, level, node.right);
		}
		
		return true;
	}

	private void pushNotNullNodeToQueue(IQueue<Entry<TreeNode<T>, Integer>> queue, int level, TreeNode<T> childNode) {
		if(childNode != null) {
			Map.Entry<TreeNode<T>, Integer> childNodeDescriptor = new AbstractMap.SimpleEntry<>(childNode, level + 1);
			queue.enqueue(childNodeDescriptor);
		}
	}
}
