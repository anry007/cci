package cci.chapter3;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.*;

import cci.chapter3.Stack.StackIsEmptyException;
import org.junit.Test;

public class StackOfStasksTests {

	@Test
	public void testNewStackIsEmpty() {
		StackOfStacks<Integer> stack = new StackOfStacks<>(10);
		assertTrue("New stack is not empty", stack.isEmpty());
	}

	@Test
	public void testStackIsNotEmptyWhneElementIsPushedIn() {
		StackOfStacks<Integer> stack = new StackOfStacks<>(10);
		stack.push(1);
		assertFalse("StackOfStacks with element is empty", stack.isEmpty());
	}

	@Test
	public void testStackWithOneElementBecomesEmptyAfterPop() {
		StackOfStacks<Integer> stack = new StackOfStacks<>(10);
		stack.push(1);
		stack.pop();
		assertTrue("StackOfStacks is not empty afted pop of a single element", stack.isEmpty());
	}

	@Test
	public void testStackWithTwoElementDoesNotBecomeEmptyAfterOnePop() {
		StackOfStacks<Integer> stack = new StackOfStacks<>(10);
		stack.push(1);
		stack.push(2);
		stack.pop();
		assertFalse("StackOfStacks is empty afted pop of a one from 2 elements", stack.isEmpty());
	}

	@Test
	public void testStackWithOneElementDoesNotBecomesEmptyAfterPeek() {
		StackOfStacks<Integer> stack = new StackOfStacks<>(1);
		stack.push(1);
		stack.peek();
		assertFalse("StackOfStacks is empty afted peek of a single element", stack.isEmpty());
	}

	@Test
	public void testStackHonorsTheRightOrder() {
		int[] input = new int[] {0,1,4,2,5,8,9};
		StackOfStacks<Integer> stack = new StackOfStacks<>(2);
		for(int i = input.length - 1; i >= 0; --i) {
			stack.push(input[i]);
		}
		
		int[] output = new int[input.length];
		int i = 0;
		while(!stack.isEmpty()) {
			output[i++] = stack.pop();
		}
		assertThat("StackOfStacks breaks the order", output, equalTo(input));
	}

	@Test
	public void testPopThrowsWhenStackIsEmpty() {
		StackOfStacks<Integer> stack = new StackOfStacks<>(10);
		try {
			stack.pop();
			fail("pop call on empty stack did not throw, but expected"); 
		} catch (StackIsEmptyException e) {
			// we expect this exception
		}
	}

	@Test
	public void testPeekThrowsWhenStackIsEmpty() {
		StackOfStacks<Integer> stack = new StackOfStacks<>(10);
		try {
			stack.peek();
			fail("peek call on empty stack did not throw, but expected"); 
		} catch (StackIsEmptyException e) {
			// we expect this exception
		}
	}
}
