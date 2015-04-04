package cci.chapter4;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
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
	
	@Test
	public void testNullTreeConvertedToNullMap() {
		assertNull("Null tree converted wrong", TreeNode.toLevelLists(null));
	}
	
	@Test
	public void testOneElementTreeConvertedToOneLevelList() {
		TreeNode<Integer> root = new TreeNode<>(0);
		Map<Integer, List<TreeNode<Integer>>> lists = getMapWithRootLevelList(root);
		assertThat("Tree converted wrong", TreeNode.toLevelLists(root), equalTo(lists));
	}
	
	@Test
	public void testTreeWithOnlyLeftChildConvertedToTwoLevelLists() {
		testTreeWithOnlyOneChildConvertedToTwoLevelsListMap(NodeDescriptor.LEFT);
	}

	@Test
	public void testTreeWithOnlyRightChildConvertedToTwoLevelLists() {
		testTreeWithOnlyOneChildConvertedToTwoLevelsListMap(NodeDescriptor.RIGHT);
	}

	@Test
	public void testTreeWithTwoChildrenConvertedToTwoLevelLists() {
		TreeNode<Integer> root = new TreeNode<>(0);
		
		Map<Integer, List<TreeNode<Integer>>> lists = getMapWithRootLevelList(root);
		addChildToParentAndNumberedList(root, 1, lists, NodeDescriptor.LEFT);
		addChildToParentAndNumberedList(root, 1, lists, NodeDescriptor.RIGHT);
		assertThat("Tree converted wrong", TreeNode.toLevelLists(root), equalTo(lists));
	}

	@Test
	public void testTreeWithFullThirdLevelConvertedToThreeLevelLists() {
		TreeNode<Integer> root = new TreeNode<>(0);
		
		Map<Integer, List<TreeNode<Integer>>> lists = getMapWithRootLevelList(root);
		
		TreeNode<Integer> leftChild = addChildToParentAndNumberedList(root, 1, lists, NodeDescriptor.LEFT);
		TreeNode<Integer> rightChild = addChildToParentAndNumberedList(root, 1, lists, NodeDescriptor.RIGHT);

		addChildToParentAndNumberedList(leftChild, 2, lists, NodeDescriptor.LEFT);
		addChildToParentAndNumberedList(leftChild, 2, lists, NodeDescriptor.RIGHT);
		
		addChildToParentAndNumberedList(rightChild, 2, lists, NodeDescriptor.LEFT);
		addChildToParentAndNumberedList(rightChild, 2, lists, NodeDescriptor.RIGHT);
		
		assertThat("Tree converted wrong", TreeNode.toLevelLists(root), equalTo(lists));
	}

	@Test
	public void testTreeWithFullThirdLevelAndOneElementConvertedToFourLevelLists() {
		TreeNode<Integer> root = new TreeNode<>(0);
		
		Map<Integer, List<TreeNode<Integer>>> lists = getMapWithRootLevelList(root);
		
		TreeNode<Integer> leftChild = addChildToParentAndNumberedList(root, 1, lists, NodeDescriptor.LEFT);
		TreeNode<Integer> rightChild = addChildToParentAndNumberedList(root, 1, lists, NodeDescriptor.RIGHT);

		addChildToParentAndNumberedList(leftChild, 2, lists, NodeDescriptor.LEFT);
		TreeNode<Integer> leftChildrightChild = addChildToParentAndNumberedList(leftChild, 2, lists, NodeDescriptor.RIGHT);
		
		addChildToParentAndNumberedList(rightChild, 2, lists, NodeDescriptor.LEFT);
		addChildToParentAndNumberedList(rightChild, 2, lists, NodeDescriptor.RIGHT);
		
		addChildToParentAndNumberedList(leftChildrightChild, 3, lists, NodeDescriptor.RIGHT);
		
		assertThat("Tree converted wrong", TreeNode.toLevelLists(root), equalTo(lists));
	}

	@Test
	public void testNullTreeConvertedToNullCollection() {
		assertNull("Null tree converted wrong", TreeNode.toBranchLists(null));
	}
	
	@Test
	public void testOneElementTreeConvertedToOneBranchList() {
		TreeNode<Integer> root = new TreeNode<>(0);
		Collection<List<TreeNode<Integer>>> lists = new HashSet<>();
		lists.add(Arrays.asList(root));
		assertThat("Tree converted wrong", TreeNode.toBranchLists(root), equalTo(lists));
	}
	
	@Test
	public void testTreeWithOnlyLeftChildrenConvertedToOneBranchList() {
		testTreeWithOnlyOneKinfOfChildrenConvertedToOneBranchList(NodeDescriptor.LEFT);
	}

	@Test
	public void testTreeWithOnlyRightChildrenConvertedToOneBranchList() {
		testTreeWithOnlyOneKinfOfChildrenConvertedToOneBranchList(NodeDescriptor.RIGHT);
	}

	@Test
	public void testTreeWithTwoChildrenConvertedToTwoBranchLists() {
		TreeNode<Integer> root = new TreeNode<>(0);
		
		Map<Integer, List<TreeNode<Integer>>> lists = getMapWithRootLevelList(root);
		addChildToParentAndNumberedList(root, 1, 0, lists, NodeDescriptor.RIGHT);
		addChildToParentAndNumberedList(root, 0, lists, NodeDescriptor.LEFT);
		assertThat("Tree converted wrong", TreeNode.toBranchLists(root), containsInAnyOrder(lists));
	}

	private static Matcher<Iterable<? extends Object>> containsInAnyOrder(
			Map<Integer, List<TreeNode<Integer>>> lists) {
		return Matchers.containsInAnyOrder(lists.values().toArray());
	}

	@Test
	public void testTreeWithFullThirdLevelConvertedToFourBranchLists() {
		TreeNode<Integer> root = new TreeNode<>(0);
		
		Map<Integer, List<TreeNode<Integer>>> lists = getMapWithRootLevelList(root);
		
		TreeNode<Integer> rightChild = addChildToParentAndNumberedList(root, 1, 0, lists, NodeDescriptor.RIGHT);

		addChildToParentAndNumberedList(rightChild, 2, 1, lists, NodeDescriptor.LEFT);
		addChildToParentAndNumberedList(rightChild, 3, 1, lists, NodeDescriptor.RIGHT);
		
		TreeNode<Integer> leftChild = addChildToParentAndNumberedList(root, 0, lists, NodeDescriptor.LEFT);

		addChildToParentAndNumberedList(leftChild, 4, 0, lists, NodeDescriptor.LEFT);
		addChildToParentAndNumberedList(leftChild, 0, lists, NodeDescriptor.RIGHT);
		
		// list #1 is technical list that have only rot ant right child. It's not full branch
		lists.remove(1);
		
		assertThat("Tree converted wrong", TreeNode.toBranchLists(root), containsInAnyOrder(lists));
	}

	@Test
	public void testTreeWithFullThirdLevelAndOneElementConvertedToFourBranchLists() {
		TreeNode<Integer> root = new TreeNode<>(0);
		
		Map<Integer, List<TreeNode<Integer>>> lists = getMapWithRootLevelList(root);
		
		TreeNode<Integer> rightChild = addChildToParentAndNumberedList(root, 1, 0, lists, NodeDescriptor.RIGHT);

		addChildToParentAndNumberedList(rightChild, 2, 1, lists, NodeDescriptor.LEFT);
		addChildToParentAndNumberedList(rightChild, 3, 1, lists, NodeDescriptor.RIGHT);
		
		TreeNode<Integer> leftChild = addChildToParentAndNumberedList(root, 0, lists, NodeDescriptor.LEFT);

		addChildToParentAndNumberedList(leftChild, 4, 0, lists, NodeDescriptor.LEFT);
		TreeNode<Integer> leftChildRightChild = addChildToParentAndNumberedList(leftChild, 0, lists, NodeDescriptor.RIGHT);
		
		addChildToParentAndNumberedList(leftChildRightChild, 0, lists, NodeDescriptor.RIGHT);
		
		// list #1 is technical list that have only rot ant right child. It's not full branch
		lists.remove(1);

		assertThat("Tree converted wrong", TreeNode.toBranchLists(root), containsInAnyOrder(lists));
	}

	private void testTreeWithOnlyOneChildConvertedToTwoLevelsListMap(NodeDescriptor nodeDescriptor) {
		TreeNode<Integer> root = new TreeNode<>(0);
		Map<Integer, List<TreeNode<Integer>>> lists = getMapWithRootLevelList(root);
		addChildToParentAndNumberedList(root, 1, lists, nodeDescriptor);
		assertThat("Tree converted wrong", TreeNode.toLevelLists(root), equalTo(lists));
	}

	private void testTreeWithOnlyOneKinfOfChildrenConvertedToOneBranchList(NodeDescriptor nodeDescriptor) {
		TreeNode<Integer> root = new TreeNode<>(0);
		Map<Integer, List<TreeNode<Integer>>> lists = getMapWithRootLevelList(root);
		addChildToParentAndNumberedList(root, 0, lists, nodeDescriptor);
		assertThat("Tree converted wrong", TreeNode.toBranchLists(root), containsInAnyOrder(lists));
	}

	@SuppressWarnings("serial")
	private HashMap<Integer, List<TreeNode<Integer>>> getMapWithRootLevelList(final TreeNode<Integer> root) {
		return new HashMap<Integer, List<TreeNode<Integer>>>() {{
			put(0, new LinkedList<TreeNode<Integer>>(Arrays.asList(root)));
		}};
	}
	
	private TreeNode<Integer> addChildToParentAndNumberedList(TreeNode<Integer> parent, 
			int listNumber, Integer listNumberToCopyFrom, Map<Integer, List<TreeNode<Integer>>> lists,
			NodeDescriptor nodeDescriptor) {
		
		TreeNode<Integer> child = addChildToParent(parent, nodeDescriptor);
		
		if(!lists.containsKey(listNumber)){
			LinkedList<TreeNode<Integer>> list;
			if(listNumberToCopyFrom != null) {
				if(lists.containsKey(listNumberToCopyFrom)) {
					list = new LinkedList<>(lists.get(listNumberToCopyFrom));
				} else {
					throw new IllegalArgumentException("listNumberToCopyFrom");
				}
			} else {
				list = new LinkedList<>();
			}
			lists.put(listNumber, list);
		}
		
		lists.get(listNumber).add(child);
		return child;
	}
	
	private TreeNode<Integer> addChildToParentAndNumberedList(TreeNode<Integer> parent, 
			int listNumber, Map<Integer, List<TreeNode<Integer>>> lists, NodeDescriptor nodeDescriptor) {
		return addChildToParentAndNumberedList(parent, listNumber, null, lists, nodeDescriptor);
	}

	private TreeNode<Integer> addChildToParent(TreeNode<Integer> parent, NodeDescriptor nodeDescriptor) {
		TreeNode<Integer> child = new TreeNode<>(0);
		
		switch(nodeDescriptor) {
			case LEFT: {
				parent.left = child;
				break;
			}
			case RIGHT: {			
				parent.right = child;
				break;
			}
			default: { 
				throw new IllegalArgumentException("nodeDescriptor");
			}
		}
		return child;
	}

	public enum NodeDescriptor { LEFT, RIGHT }
	
	private <T> int getTreeMaxDepth(TreeNode<T> root) {
		if(root == null) {
			return 0;
		}
		
		return 1 + Math.max(getTreeMaxDepth(root.left), getTreeMaxDepth(root.right));
	}

}
