package cci.chapter3;

public class StackBasedQueueTests extends QueueTestsBase {

	@Override
	public IQueue<Integer> getEmptyQueue() {
		return new StackBasedQueue<>();
	}

}
