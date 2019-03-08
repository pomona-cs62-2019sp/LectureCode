import structure5.StackList;

public class MyQueue<E> {

	StackList<E> stackNewElts, stackOldElts;

	public MyQueue() {
		stackNewElts = new StackList<E>();
		stackOldElts = new StackList<E>();
	}

	public int size() {
		return stackNewElts.size() + stackOldElts.size();
	}

	public void enqueue(E value) {
		stackNewElts.push(value);
	}

	private void shiftStacks() {
		if (stackOldElts.isEmpty()) {
			while (!stackNewElts.isEmpty()) {
				stackOldElts.push(stackNewElts.pop());
			}
		}
	}

	public E peek() {
		shiftStacks();
		return stackOldElts.peek();
	}

	public E dequeue() {
		shiftStacks();
		return stackOldElts.pop();
	}

	public static void main(String args[]) {
		MyQueue<Integer> mq = new MyQueue<Integer>();
		for (int i = 0; i < 8; i++)
			mq.enqueue(i);
		System.out.println(mq.size());
		for (int i = 0; i < 8; i++) {
			System.out.println(mq.dequeue()); // 0 1 2 3 4 5 6 7
		}

	}

}
