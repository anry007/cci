package cci.chapter3;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.*;
import cci.chapter3.Stack.StackIsEmptyException;

import org.junit.Test;

public abstract class StackTestsBase {

	public abstract IStack<Integer> getEmptyStack();
	@Test
	public void testEmptyStackHas0Size() {
		IStack<Integer> stack = getEmptyStack();
		assertEquals("Stack size is wrong", 0, stack.size());
	}

	@Test
	public void testStackSizeIncreasedWhenElementPushed() {
		IStack<Integer> stack = getEmptyStack();
		stack.push(1);
		assertEquals("Stack size is wrong after first push", 1, stack.size());
		stack.push(1);
		assertEquals("Stack size is wrong after second push", 2, stack.size());
		stack.push(1);
		assertEquals("Stack size is wrong after third push", 3, stack.size());
	}

	@Test
	public void testStackSizeDecreasedWhenElementPoped() {
		IStack<Integer> stack = getEmptyStack();
		stack.push(1);
		stack.push(1);
		stack.push(1);
		
		stack.pop();
		assertEquals("Stack size is wrong after first pop", 2, stack.size());
		stack.pop();
		assertEquals("Stack size is wrong after second push", 1, stack.size());
		stack.pop();
		assertEquals("Stack size is wrong after third push", 0, stack.size());
	}

	@Test
	public void testNewStackIsEmpty() {
		IStack<Integer> stack = getEmptyStack();
		assertTrue("New stack is not empty", stack.isEmpty());
	}

	@Test
	public void testStackIsNotEmptyWhneElementIsPushedIn() {
		IStack<Integer> stack = getEmptyStack();
		stack.push(1);
		assertFalse("Stack with element is empty", stack.isEmpty());
	}

	@Test
	public void testStackWithOneElementBecomesEmptyAfterPop() {
		IStack<Integer> stack = getEmptyStack();
		stack.push(1);
		stack.pop();
		assertTrue("Stack is not empty afted pop of a single element", stack.isEmpty());
	}

	@Test
	public void testStackWithTwoElementDoesNotBecomeEmptyAfterOnePop() {
		IStack<Integer> stack = getEmptyStack();
		stack.push(1);
		stack.push(2);
		stack.pop();
		assertFalse("Stack is empty afted pop of a one from 2 elements", stack.isEmpty());
	}

	@Test
	public void testStackWithOneElementDoesNotBecomesEmptyAfterPeek() {
		IStack<Integer> stack = getEmptyStack();
		stack.push(1);
		stack.peek();
		assertFalse("Stack is empty afted peek of a single element", stack.isEmpty());
	}

	@Test
	public void testStackHonorsTheRightOrder() {
		int[] input = new int[] {0,1,4,2,5,8,9};
		IStack<Integer> stack = getEmptyStack();
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
		IStack<Integer> stack = getEmptyStack();
		try {
			stack.pop();
			fail("pop call on empty stack did not throw, but expected"); 
		} catch (StackIsEmptyException e) {
			// we expect this exception
		}
	}

	@Test
	public void testPeekThrowsWhenStackIsEmpty() {
		IStack<Integer> stack = getEmptyStack();
		try {
			stack.peek();
			fail("peek call on empty stack did not throw, but expected"); 
		} catch (StackIsEmptyException e) {
			// we expect this exception
		}
	}
}
