package cci.chapter4;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.HashSet;

import org.junit.Test;

public class GraphTests {

	@Test
	public void testNonConnectedGraphNodesDoesNotHaveRoute() {
		GraphNode<Integer> graph1 = new GraphNode<Integer>();
		GraphNode<Integer> graph2 = new GraphNode<Integer>();
		assertFalse("Not connected graphs should not have route", GraphNode.isRoteBeetweenTwoGraphNodes(graph1, graph2));
	}

	@Test
	public void testSameNodeHasRoute() {
		GraphNode<Integer> graph1 = new GraphNode<Integer>();
		assertTrue("Same node has route", GraphNode.isRoteBeetweenTwoGraphNodes(graph1, graph1));
	}
	
	@Test
	public void testChildrenOfTheParentDontHaveRoute() {
		final GraphNode<Integer> graphChild1 = new GraphNode<Integer>();
		final GraphNode<Integer> graphChild2 = new GraphNode<Integer>();
		GraphNode<Integer> graph = new GraphNode<Integer>();
		graph.childs = new HashSet<GraphNode<Integer>>();
		graph.childs.add(graphChild1);
		graph.childs.add(graphChild2);
		assertFalse("GraphNode children should not have route", GraphNode.isRoteBeetweenTwoGraphNodes(graphChild1, graphChild2));
	}

	@Test
	public void testParentHasRouteToChild() {
		final GraphNode<Integer> graphChild = new GraphNode<Integer>();
		GraphNode<Integer> graph = new GraphNode<Integer>();
		graph.childs = new HashSet<>(Arrays.asList(graphChild));
		assertTrue("Parent should have route to child", GraphNode.isRoteBeetweenTwoGraphNodes(graph, graphChild));
	}
	
	@Test
	public void testParentHasRouteToChildOfTheChild() {
		final GraphNode<Integer> graphChildChild = new GraphNode<Integer>();
		final GraphNode<Integer> graphChild = new GraphNode<Integer>();
		graphChild.childs = new HashSet<>(Arrays.asList(graphChildChild));
		GraphNode<Integer> graph = new GraphNode<Integer>();
		graph.childs = new HashSet<>(Arrays.asList(graphChild));
		assertTrue("Parent should have route to child's child", GraphNode.isRoteBeetweenTwoGraphNodes(graph, graphChildChild));
	}
	
	@Test
	public void testCycledGraphCanBetraversed() {
		final GraphNode<Integer> graph = new GraphNode<Integer>();
		final GraphNode<Integer> otherGraph = new GraphNode<Integer>();
		graph.childs.add(graph);
		assertFalse("Circled graph has connection with other graph", GraphNode.isRoteBeetweenTwoGraphNodes(graph, otherGraph));
	}
	
	@Test
	public void testCycledOnChildGraphCanBetraversed() {
		final GraphNode<Integer> graph = new GraphNode<Integer>();
		final GraphNode<Integer> graphChild = new GraphNode<Integer>();
		final GraphNode<Integer> graphChildChild = new GraphNode<Integer>();
		graph.childs.add(graphChild);
		graphChild.childs.add(graph);
		graphChild.childs.add(graphChildChild);
		assertTrue("Circled graph has no connection with child's child", GraphNode.isRoteBeetweenTwoGraphNodes(graph, graphChildChild));
	}
}
