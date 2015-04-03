package cci.chapter4;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;

import org.junit.Test;

public class TreeNodeTests {

	@Test
	public void testNullReturnedForEmptyArray() {
		TreeNode<Integer> tree = TreeNode.fromArray(new Integer[]{ });
		assertNull("Tree should be null", tree);
	}
	
	@Test
	public void testOneElementArrayConvertedToTreeWithDepthOne() {
		TreeNode<Integer> tree = TreeNode.fromArray(new Integer[]{ 1 });
		assertThat("Tree depth is wrong", getTreeMaxDepth(tree), equalTo(1));
	}
	
	@Test
	public void testTwoElementArrayConvertedToTreeWithDepthTwo() {
		TreeNode<Integer> tree = TreeNode.fromArray(new Integer[]{ 1, 2 });
		assertThat("Tree depth is wrong", getTreeMaxDepth(tree), equalTo(2));
	}
	
	@Test
	public void testTreeElementArrayConvertedToTreeWithDepthTwo() {
		TreeNode<Integer> tree = TreeNode.fromArray(new Integer[]{ 1, 2, 3 });
		assertThat("Tree depth is wrong", getTreeMaxDepth(tree), equalTo(2));
	}
	
	@Test
	public void testFourElementArrayConvertedToTreeWithDepthTree() {
		TreeNode<Integer> tree = TreeNode.fromArray(new Integer[]{ 1, 2, 3, 4 });
		assertThat("Tree depth is wrong", getTreeMaxDepth(tree), equalTo(3));
	}
	
	@Test
	public void testFiveElementArrayConvertedToTreeWithDepthTree() {
		TreeNode<Integer> tree = TreeNode.fromArray(new Integer[]{ 1, 2, 3, 4, 5 });
		assertThat("Tree depth is wrong", getTreeMaxDepth(tree), equalTo(3));
	}
	
	@Test
	public void testSixElementArrayConvertedToTreeWithDepthTree() {
		TreeNode<Integer> tree = TreeNode.fromArray(new Integer[]{ 1, 2, 3, 4, 5, 6 });
		assertThat("Tree depth is wrong", getTreeMaxDepth(tree), equalTo(3));
	}
	
	@Test
	public void testSevenElementArrayConvertedToTreeWithDepthTree() {
		TreeNode<Integer> tree = TreeNode.fromArray(new Integer[]{ 1, 2, 3, 4, 5, 6, 7 });
		assertThat("Tree depth is wrong", getTreeMaxDepth(tree), equalTo(3));
	}
	
	@Test
	public void testEightElementArrayConvertedToTreeWithDepthFour() {
		TreeNode<Integer> tree = TreeNode.fromArray(new Integer[]{ 1, 2, 3, 4, 5, 6, 7, 8 });
		assertThat("Tree depth is wrong", getTreeMaxDepth(tree), equalTo(4));
	}
	
	private <T> int getTreeMaxDepth(TreeNode<T> root) {
		if(root == null) {
			return 0;
		}
		
		return 1 + Math.max(getTreeMaxDepth(root.left), getTreeMaxDepth(root.right));
	}

}
