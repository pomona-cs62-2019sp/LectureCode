import structure5.QueueList;
import structure5.StackList;

public class ReverseQueue<E> {

	QueueList<E> queue = new QueueList<E>();

	public void reversequeue() {
		StackList<E> stack = new StackList<E>();
		while (!queue.isEmpty()) {
			stack.push(queue.peek());
			queue.dequeue();
		}
		while (!stack.isEmpty()) {
			queue.enqueue(stack.peek());
			stack.pop();
		}
	}

	public String toString() {
		String queueC = "";
		while (!queue.isEmpty()) {
			queueC += queue.remove();
		}
		return queueC;
	}

	public static void main(String args[]) {
		ReverseQueue<Integer> rq = new ReverseQueue<Integer>();
		for (int i = 0; i < 10; i++) {
			rq.queue.add(i);
		}
		System.out.println(rq);

		for (int i = 0; i < 10; i++) {
			rq.queue.add(i);
		}
		rq.reversequeue();
		System.out.println(rq);

	}

}
