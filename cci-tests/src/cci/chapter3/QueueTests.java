package cci.chapter3;

import cci.chapter3.Queue;

public class QueueTests extends QueueTestsBase {

	@Override
	public IQueue<Integer> getEmptyQueue() {
		return new Queue<>();
	}

}
