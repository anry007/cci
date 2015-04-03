package cci.chapter3;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.equalTo;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

public class SortableStackTests {

	@Test
	public void testEmptyStackReturnEmptySortedStack() {
		SortableStack<Integer> stack = getEmptySortableStack();
		assertTrue("Sorted stack is not empty", stack.getSorted().isEmpty());
	}

	@Test
	public void testSortingForStackWithOneElement() {
		List<Integer> input = Arrays.asList(0);
		validateStackSortsElemets(input);
	}

	@Test
	public void testSortingForStackWithMultipleElements() {
		List<Integer> input = Arrays.asList(0, -1, 3, 4, 2);
		validateStackSortsElemets(input);
	}

	private void validateStackSortsElemets(List<Integer> input) {
		SortableStack<Integer> stack = getEmptySortableStack();
		for(int i : input) {
			stack.push(i);
		}
		
		IStack<Integer> sorted = stack.getSorted();
		List<Integer> output = new LinkedList<Integer>();
		while(!sorted.isEmpty()) {
			output.add(sorted.pop());
		}
		
		Collections.sort(input);
		assertThat("Sort() does not sort it right", output, equalTo(input));
	}

	private SortableStack<Integer> getEmptySortableStack() {
		return new SortableStack<Integer>();
	}
}
