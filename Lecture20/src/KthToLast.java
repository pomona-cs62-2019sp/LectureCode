import structure5.Node;
import structure5.SinglyLinkedList;

public class KthToLast<E> extends SinglyLinkedList<E> {

	public int printKthToLast(Node<E> head, int k) {
		if (head == null) {
			return 0;
		}
		int index = printKthToLast(head.next(), k) + 1;
		System.out.println(index);
		if (index == k) {
			System.out.println(k + "th to last node is " + head.value());
		}
		return index;
	}

	public static void main(String args[]) {
		KthToLast<Integer> mylist = new KthToLast<Integer>();
		for (int i = 0; i < 8; i++) {
			mylist.add(i);
		}

		System.out.println(mylist); // <SinglyLinkedList: 0 1 2 3 4 5 6 7>
		mylist.printKthToLast(mylist.head, 3);
		System.out.println(mylist); // 5
	}

}
