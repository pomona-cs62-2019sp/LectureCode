import structure5.DoublyLinkedList;
import structure5.DoublyLinkedNode;

public class MyDLL<E> extends DoublyLinkedList<E>{
	public E removeSecondToLast() {
		E  val = tail.previous().value();
		
		if (count == 2) {// head == tail.previous()
			head = tail;
			tail.setPrevious(null);
		} else {
			DoublyLinkedNode<E> n = tail.previous().previous();
			tail.setPrevious(n);
			n.setNext(tail);
		}
		
		count--;
		return val;
	}
	
	public static void main(String[] args) {
		MyDLL<Integer> list = new MyDLL<Integer>();
		list.add(0);
		list.add(1);
		list.add(2);
		System.out.println(list);
		System.out.println("Removing: " + list.removeSecondToLast());
		System.out.println(list);
		System.out.println("Removing: " + list.removeSecondToLast());
		System.out.println(list);
	}
}
