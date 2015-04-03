package cci.chapter4;

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
}
