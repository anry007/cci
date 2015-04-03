package cci.chapter3;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;
import cci.chapter3.Queue.QueueIsEmptyException;

import org.junit.Test;

public abstract class QueueTestsBase {

	public abstract IQueue<Integer> getEmptyQueue();
	
	@Test
	public void testDequeueThrowsWhenQueueEmpty() {
		IQueue<Integer> queue = getEmptyQueue();
		try {
			queue.dequeue();
			fail("dequeue call on empty queue did not throw, but expected"); 
		} catch (QueueIsEmptyException e) {
			// we expect this exception
		}
	}
	
	@Test
	public void testNewQueueIsEmpty() {
		IQueue<Integer> queue = getEmptyQueue();
		assertTrue("Newly created Q should be empty", queue.isEmpty());
	}

	@Test
	public void testQueueIsEmptyWhenAllElementDequeued() {
		IQueue<Integer> queue = getEmptyQueue();
		queue.enqueue(0);
		queue.enqueue(1);
		queue.dequeue();
		queue.dequeue();
		assertTrue("Q with all elements dequeued should be empty", queue.isEmpty());
	}

	@Test
	public void testQueueWithElementsShouldNotBeEmpty() {
		IQueue<Integer> queue = getEmptyQueue();
		queue.enqueue(0);
		assertFalse("Q with some elements should not be empty", queue.isEmpty());
	}

	@Test
	public void testQueueWithNotAllElementsDequeuedShouldNotBeEmpty() {
		IQueue<Integer> queue = getEmptyQueue();
		queue.enqueue(0);
		queue.enqueue(1);
		queue.dequeue();
		assertFalse("Q with not all elements dequeued should not be empty", queue.isEmpty());
	}

	@Test
	public void testQueueHonorItsOrderWhenDequeueing() {
		int[] input = new int[] {0,1,4,2,5,8,9};
		IQueue<Integer> queue = getEmptyQueue();
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
