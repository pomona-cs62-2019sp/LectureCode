import structure5.Node;
import structure5.SinglyLinkedList;

public class UniqueSinglyLinkedList<E> extends SinglyLinkedList<E> {

	public void removeDuplicates() {
		Node<E> current = head;

		while (current != null) {
			Node<E> runner = current;

			while (runner.next() != null) {
				if (runner.next().value().equals(current.value())) {
					runner.setNext(runner.next().next());
					count--;
				} else {
					runner = runner.next();
				}
			}

			current = current.next();
		}

	}

	public static void main(String args[]) {
		UniqueSinglyLinkedList<Integer> mylist = new UniqueSinglyLinkedList<Integer>();
		for (int i = 0; i < 5; i++) {
			mylist.add(0);
			mylist.add(1);
		}

		System.out.println(mylist); // <SinglyLinkedList: 0 1 0 1 0 1 0 1 0 1>
		mylist.removeDuplicates();
		System.out.println(mylist); // <SinglyLinkedList: 0 1>
	}

}
