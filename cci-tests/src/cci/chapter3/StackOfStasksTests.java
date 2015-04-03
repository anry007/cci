package cci.chapter3;

import static org.junit.Assert.*;
import org.junit.Test;

public class StackOfStasksTests extends StackTestsBase{

	@Override
	public IStack<Integer> getEmptyStack() {
		return new StackOfStacks<>(5);
	}
	
	@Test
	public void testNumberOfIsZeroForEmptyStack() {
		StackOfStacks<Integer> stack = new StackOfStacks<>(5);
		assertEquals("Number of partition for empty stack is wrong", 0, stack.getNumberOfPartitions());
	}
	
	@Test
	public void testNumberOfPartitionsNotIncreasedBeforeCapacityIsReached() {
		StackOfStacks<Integer> stack = new StackOfStacks<>(5);
		for(int i = 0; i < 5; ++i) {
			stack.push(i);
		}
		assertEquals("Number of partition is wrong", 1, stack.getNumberOfPartitions());
	}
	
	@Test
	public void testNumberOfPartitionsIncreasedAfterCapacityIsReached() {
		StackOfStacks<Integer> stack = new StackOfStacks<>(5);
		for(int i = 0; i < 6; ++i) {
			stack.push(i);
		}
		assertEquals("Number of partition is wrong", 2, stack.getNumberOfPartitions());
	}

	@Test
	public void testNumberOfPartitionsNotDecreasedBeforeCurrentPartitionIsEmpty() {
		StackOfStacks<Integer> stack = new StackOfStacks<>(5);
		for(int i = 0; i < 10; ++i) {
			stack.push(i);
		}
		
		for(int i = 0; i < 4; ++i) {
			stack.pop();
		}
		assertEquals("Number of partition is wrong", 2, stack.getNumberOfPartitions());
	}
	
	@Test
	public void testNumberOfPartitionsDecreasedAfterCurrentPartitionIsEmpty() {
		StackOfStacks<Integer> stack = new StackOfStacks<>(5);
		for(int i = 0; i < 10; ++i) {
			stack.push(i);
		}

		for(int i = 0; i < 5; ++i) {
			stack.pop();
		}
		assertEquals("Number of partition is wrong", 1, stack.getNumberOfPartitions());
	}
}
