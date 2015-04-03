package cci.chapter3;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.*;

import cci.chapter3.Stack.StackIsEmptyException;
import org.junit.Test;

public class StackTests {

	@Test
	public void testNewStackIsEmpty() {
		Stack<Integer> stack = new Stack<>();
		assertTrue("New stack is not empty", stack.isEmpty());
	}

	@Test
	public void testStackIsNotEmptyWhneElementIsPushedIn() {
		Stack<Integer> stack = new Stack<>();
		stack.push(1);
		assertFalse("Stack with element is empty", stack.isEmpty());
	}

	@Test
	public void testStackWithOneElementBecomesEmptyAfterPop() {
		Stack<Integer> stack = new Stack<>();
		stack.push(1);
		stack.pop();
		assertTrue("Stack is not empty afted pop of a single element", stack.isEmpty());
	}

	@Test
	public void testStackWithTwoElementDoesNotBecomeEmptyAfterOnePop() {
		Stack<Integer> stack = new Stack<>();
		stack.push(1);
		stack.push(2);
		stack.pop();
		assertFalse("Stack is empty afted pop of a one from 2 elements", stack.isEmpty());
	}

	@Test
	public void testStackWithOneElementDoesNotBecomesEmptyAfterPeek() {
		Stack<Integer> stack = new Stack<>();
		stack.push(1);
		stack.peek();
		assertFalse("Stack is empty afted peek of a single element", stack.isEmpty());
	}

	@Test
	public void testStackHonorsTheRightOrder() {
		int[] input = new int[] {0,1,4,2,5,8,9};
		Stack<Integer> stack = new Stack<>();
		for(int i = input.length - 1; i >= 0; --i) {
			stack.push(input[i]);
		}
		
		int[] output = new int[input.length];
		int i = 0;
		while(!stack.isEmpty()) {
			output[i++] = stack.pop();
		}
		assertThat("Stack breaks the order", output, equalTo(input));
	}

	@Test
	public void testPopThrowsWhenStackIsEmpty() {
		Stack<Integer> stack = new Stack<>();
		try {
			stack.pop();
			fail("pop call on empty stack did not throw, but expected"); 
		} catch (StackIsEmptyException e) {
			// we expect this exception
		}
	}

	@Test
	public void testPeekThrowsWhenStackIsEmpty() {
		Stack<Integer> stack = new Stack<>();
		try {
			stack.peek();
			fail("peek call on empty stack did not throw, but expected"); 
		} catch (StackIsEmptyException e) {
			// we expect this exception
		}
	}
}
