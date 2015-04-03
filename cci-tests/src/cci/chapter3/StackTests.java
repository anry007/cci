package cci.chapter3;

public class StackTests extends StackTestsBase {

	@Override
	public IStack<Integer> getEmptyStack() {
		return new Stack<>();
	}

}
