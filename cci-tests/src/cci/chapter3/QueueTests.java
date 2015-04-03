package cci.chapter3;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;

import cci.chapter3.Queue;
import cci.chapter3.Exceptions.*;
import org.junit.Test;

public class QueueTests {

	@Test
	public void testDequeueThrowsWhenQueueEmpty() {
		Queue<Integer> queue = new Queue<Integer>();
		try {
			queue.dequeue();
			
		} catch (QueueIsEmptyException e) {
			// we expect this exception
		}
	}
	
	@Test
	public void testNewQueueIsEmpty() {
		Queue<Integer> queue = new Queue<Integer>();
		assertTrue("Newly created Q should be empty", queue.isEmpty());
	}

	@Test
	public void testQueueIsEmptyWhenAllElementDequeued() {
		Queue<Integer> queue = new Queue<Integer>();
		queue.enqueue(0);
		queue.enqueue(1);
		queue.dequeue();
		queue.dequeue();
		assertTrue("Q with all elements dequeued should be empty", queue.isEmpty());
	}

	@Test
	public void testQueueWithElementsShouldNotBeEmpty() {
		Queue<Integer> queue = new Queue<Integer>();
		queue.enqueue(0);
		assertFalse("Q with some elements should not be empty", queue.isEmpty());
	}

	@Test
	public void testQueueWithNotAllElementsDequeuedShouldNotBeEmpty() {
		Queue<Integer> queue = new Queue<Integer>();
		queue.enqueue(0);
		queue.enqueue(1);
		queue.dequeue();
		assertFalse("Q with not all elements dequeued should not be empty", queue.isEmpty());
	}

	@Test
	public void testQueueHonorItsOrderWhenDequeueing() {
		int[] input = new int[] {0,1,4,2,5,8,9};
		Queue<Integer> queue = new Queue<Integer>();
		for(int i : input) {
			queue.enqueue(i);
		}
		
		int[] output = new int[input.length];
		int i = 0;
		while(!queue.isEmpty()) {
			output[i++] = queue.dequeue();
		}
		assertThat("Queue breaks the order", output, equalTo(input));
	}
}
