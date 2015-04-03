package cci.chapter4;

import static org.junit.Assert.*;

import org.junit.Test;

import cci.chapter4.BalancableTree;

public class BalancableTreeTests {

	@Test
	public void testEmptyTreeIsBalanced() {
		BalancableTree<Integer> tree = new BalancableTree<Integer>(null);
		assertTrue("Empty tree is no ballanced but should be", tree.isBallanced());
	}

	@Test
	public void testTreeWithOneLeafIsBalanced() {
		BalancableTree<Integer> treeLeftLeaf = new BalancableTree<Integer>(null);
		BalancableTree<Integer> tree = new BalancableTree<Integer>(null, treeLeftLeaf, null);
		assertTrue("Tree with only one leaf is not ballanced but should be", tree.isBallanced());
	}

	@Test
	public void testTreeWithTwoLeafsIsBalanced() {
		BalancableTree<Integer> treeLeftLeaf = new BalancableTree<Integer>(null);
		BalancableTree<Integer> treeRightLeaf = new BalancableTree<Integer>(null);
		BalancableTree<Integer> tree = new BalancableTree<Integer>(null, treeLeftLeaf, treeRightLeaf);
		assertTrue("Tree with only one leaf is not ballanced but should be", tree.isBallanced());
	}

	@Test
	public void testLadderLikeTreeIsBalanced() {
		BalancableTree<Integer> treeLeftLeaf = new BalancableTree<Integer>(1);
		BalancableTree<Integer> treeRightChildLeftLeaf = new BalancableTree<Integer>(3);
		BalancableTree<Integer> treeRightChildRightChild = new BalancableTree<Integer>(4, null, null);
		BalancableTree<Integer> treeRightChild = new BalancableTree<Integer>(2, treeRightChildLeftLeaf, treeRightChildRightChild);
		BalancableTree<Integer> tree = new BalancableTree<Integer>(0, treeLeftLeaf, treeRightChild);
		assertTrue("Ladder like tree is not ballanced but should be", tree.isBallanced());
	}

	@Test
	public void testUnbalancedTreeIsBalanced() {
		BalancableTree<Integer> treeLeftLeaf = new BalancableTree<Integer>(null);
		BalancableTree<Integer> treeRightChildRightChildRightLeaf = new BalancableTree<Integer>(null);
		BalancableTree<Integer> treeRightChildRightChild = new BalancableTree<Integer>(null, null, treeRightChildRightChildRightLeaf);
		BalancableTree<Integer> treeRightChild = new BalancableTree<Integer>(null, null, treeRightChildRightChild);
		BalancableTree<Integer> tree = new BalancableTree<Integer>(null, treeLeftLeaf, treeRightChild);
		assertFalse("Ladder like tree is not ballanced but should be", tree.isBallanced());
	}
}
