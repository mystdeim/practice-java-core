package collection;

import java.util.ArrayDeque;
import java.util.Deque;

public class DequeTest {

	public static void main(String[] args) {
		
		Deque<Long> deque = new ArrayDeque<>();
		System.out.println(deque.peekFirst());
		try {
			System.out.println(deque.getFirst());
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

}
