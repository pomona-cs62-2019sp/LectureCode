import structure5.*;

import java.util.function.Consumer;
import java.lang.Math;

/**
* This class implements a single node of a binary tree.  It is a
* recursive structure.  Relationships between nodes are 
* doubly linked, with parent and child references.  Many characteristics
* of trees may be detected with static methods.
*
* @version $Id: BinaryTree.java 34 2007-08-09 14:43:44Z bailey $
* @author, 2001 duane a. bailey
* @see structure.BinaryTree
* @see structure.BinarySearchTree
*/
public class BinaryTree8<E>
{
 /**
  * The value associated with this node
  */
 protected E val; // value associated with node
 /**
  * The parent of this node
  */
 protected BinaryTree8<E> parent; // parent of node
 /**
  * The left child of this node, or an "empty" node
  */
 protected BinaryTree8<E> left, right; // children of node

 /**
  * A one-time constructor, for constructing empty trees.
  * Space efficiencies are possible if empty trees are reused.
  *
  * @post Constructor that generates an empty node
  * @return an empty node
  */
 public BinaryTree8()
 {
     val = null;
     parent = null; left = right = this;
 }

 /**
  * Constructs a tree node with no children.  Value of the node
  * and subtrees are provided by the user
  *
  * @post Returns a tree referencing value and two empty subtrees
  * @param value A non-null value to be referenced by node
  */
 public BinaryTree8(E value)
 {
     Assert.pre(value != null, "Tree values must be non-null.");
     val = value;
     right = left = new BinaryTree8<E>();
     setLeft(left);
     setRight(right);
 }

 /**
  * Constructs a tree node with two children.  Value of the node
  * and subtrees are provided by the user.
  *
  * @post Returns a tree referencing value and two subtrees
  * @param value A non-null value to be referenced by node
  * @param left The subtree to be left subtree of node
  * @param right The subtree to be right subtree of node
  */
 public BinaryTree8(E value, BinaryTree8<E> left, BinaryTree8<E> right)
 {
     Assert.pre(value != null, "Tree values must be non-null.");
     val = value;
     if (left == null) { left = new BinaryTree8<E>(); }
     setLeft(left);
     if (right == null) { right = new BinaryTree8<E>(); }
     setRight(right);
 }

 /**
  * Get left subtree of current node
  *
  * @post Returns reference to (possibly empty) left subtree
  *
  * @return The left subtree of this node
  */
 public BinaryTree8<E> left()
 {
     return left;
 }

 /**
  * Get right subtree of current node
  *
  * @post Returns reference to (possibly empty) right subtree
  * 
  * @return The right subtree of this node
  */
 public BinaryTree8<E> right()
 {
     return right;
 }

 /**
  * Get reference to parent of this node
  *
  * @post Returns reference to parent node, or null
  * 
  * @return Reference to parent of this node
  */
 public BinaryTree8<E> parent()
 {
     return parent;
 }
 
 /**
  * Update the left subtree of this node.  Parent of the left subtree
  * is updated consistently.  Existing subtree is detached
  *
  * @post Sets left subtree to newLeft
  *       re-parents newLeft if not null
  * 
  * @param newLeft The root of the new left subtree
  */
 public void setLeft(BinaryTree8<E> newLeft)
 {
     if (isEmpty()) return;
     if (left != null && left.parent() == this) {
     		left.setParent(null);
     }
     left = newLeft;
     left.setParent(this);
 }

 /**
  * Update the right subtree of this node.  Parent of the right subtree
  * is updated consistently.  Existing subtree is detached
  *
  * @post Sets left subtree to newRight
  *       re-parents newRight if not null
  * 
  * @param newRight A reference to the new right subtree of this node
  */
 public void setRight(BinaryTree8<E> newRight)
 {
     if (isEmpty()) return;
     if (right != null && right.parent() == this) right.setParent(null);
     right = newRight;
     right.setParent(this);
 }

 /**
  * Update the parent of this node
  *
  * @post Re-parents this node to parent reference, or null
  *
  * @param newParent A reference to the new parent of this node
  */
 protected void setParent(BinaryTree8<E> newParent)
 {
     if (!isEmpty()) {
         parent = newParent;
     }
 }

 /**
  * Returns the number of descendants of node
  *
  * @post Returns the size of the subtree
  * @return Size of subtree
  */
 public int size()
 {
     if (isEmpty()) return 0;
     return left().size() + right().size() + 1;
 }

 /**
  * Returns reference to root of tree containing n
  *
  * @post Returns the root of the tree node n
  * @return Root of tree
  */
 public BinaryTree8<E> root()
 {
     if (parent() == null) return this;
     else return parent().root();
 }

 /**
  * Returns height of node in tree.  Height is maximum path
  * length to descendant
  *
  * @post Returns the height of a node in its tree
  * @return The height of the node in the tree
  */
 public int height()
 {
     if (isEmpty()) { return -1; }
     return 1 + Math.max(left.height(),right.height());
 }

 /**
  * Compute the depth of a node.  The depth is the path length
  * from node to root
  *
  * @post Returns the depth of a node in the tree
  * @return The path length to root of tree
  */
 public int depth()
 {
     if (parent() == null) return 0;
     return 1 + parent.depth();
 }

 /**
  * Returns true if tree is full.  A tree is full if adding a node
  * to tree would necessarily increase its height
  *
  * @post Returns true iff the tree rooted at node is full
  * @return True iff tree is full
  */
 public boolean isFull()
 {
     if (isEmpty()) {return true;}
     if (left().height() != right().height()) {return false;}
     return left().isFull() && right().isFull();
 }

 /**
  * Returns true if tree is empty.
  * @post Returns true iff the tree rooted at node is empty
  * @return True iff tree is empty
  */
 public boolean isEmpty()
 {
     return val == null;
 }
 
 /**
  * Return whether tree is complete.  A complete tree has minimal height
  * and any holes in tree would appear in last level to right.       
  *
  * @post Returns true iff the tree rooted at node is complete
  * @return True iff the subtree is complete
  */
 public boolean isComplete()
 {
     int leftHeight, rightHeight;
     boolean leftIsFull, rightIsFull;
     boolean leftIsComplete, rightIsComplete;
     if (isEmpty()) return true;
     leftHeight = left().height();
     rightHeight = right().height();
     leftIsFull = left().isFull();
     rightIsFull = right().isFull();
     leftIsComplete = left().isComplete();
     rightIsComplete = right().isComplete();

     // case 1: left is full, right is complete, heights same
     if (leftIsFull && rightIsComplete &&
         (leftHeight == rightHeight)) return true;
     // case 2: left is complete, right is full, heights differ
     if (leftIsComplete && rightIsFull &&
         (leftHeight == (rightHeight + 1))) return true;
     return false;
 }

 /**
  * Return true iff the tree is height balanced.  A tree is height
  * balanced iff at every node the difference in heights of subtrees is
  * no greater than one
  *
  * @post Returns true iff the tree rooted at node is balanced
  * @return True if tree is height balanced
  */
 public boolean isBalanced()
 {
     if (isEmpty()) return true;
     return (Math.abs(left().height()-right().height()) <= 1) &&
            left().isBalanced() && right().isBalanced();
 }


 /**
  * Method to perform a right rotation of tree about this node
  * Node must have a left child.  Relation between left child and node
  * are reversed
  *
  * @pre This node has a left subtree
  * @post Rotates local portion of tree so left child is root
  */
 protected void rotateRight()
 {
     BinaryTree8<E> parentHolder = parent();
     BinaryTree8<E> newRoot = left();
     boolean wasChild = parentHolder != null;
     boolean wasLeftChild = isLeftChild();

     // hook in new root (sets newRoot as parent, as well)
     setLeft(newRoot.right());

     // puts pivot below it (sets this's parent, as well)
     newRoot.setRight(this);

     if (wasChild) {
         if (wasLeftChild) {
         		parentHolder.setLeft(newRoot);
         } else {
         		parentHolder.setRight(newRoot);
         }
     }
 }

 /**
  * Method to perform a left rotation of tree about this node
  * Node must have a right child.  Relation between right child and node
  * are reversed
  *
  * @pre This node has a right subtree
  * @post Rotates local portion of tree so right child is root
  */
 protected void rotateLeft()
 {
     // all of this information must be grabbed before
     // any of the references are set.  Draw a diagram for help
     BinaryTree8<E> parentHolder = parent();
     BinaryTree8<E> newRoot = right();
     // is the this node a child; if so, a left child?
     boolean wasChild = parentHolder != null;
     boolean wasRightChild = isRightChild();

     // hook in new root (sets newRoot's parent, as well)
     setRight(newRoot.left());

     // put pivot below it (sets this's parent, as well)
     newRoot.setLeft(this);

     if (wasChild) {
         if (wasRightChild) {
         		parentHolder.setRight(newRoot);
         } else {
         		parentHolder.setLeft(newRoot);
         }
     }
 }

 /**
  * Determine if this node is a left child
  *
  * @post Returns true if this is a left child of parent
  * 
  * @return True iff this node is a left child of parent
  */
 public boolean isLeftChild()
 {
     if (parent() == null) return false;
     return this == parent().left();
 }

 /**
  * Determine if this node is a right child
  *
  * @post Returns true if this is a right child of parent
  * 
  * @return True iff this node is a right child of parent
  */
 public boolean isRightChild()
 {
     if (parent() == null) return false;
     return this == parent().right();
 }

 /**
  * Returns value associated with this node
  *
  * @post Returns value associated with this node
  * 
  * @return The node's value
  */
 public E value()
 {
     return val;
 }

 /**
  * Sets value associated with this node
  *
  * @post Sets the value associated with this node
  * @param value The new value of this node
  */
 public void setValue(E value)
 {
     val = value;
 }
 
 public void doPreorder(Consumer<? super E> action) {
	if(!isEmpty()) {
		action.accept(val);
		left.doPreorder(action);
		right.doPreorder(action);
	}
 }

 public void doInorder(Consumer<? super E> action) {
		if(!isEmpty()) {
			left.doInorder(action);
			action.accept(val);
			right.doInorder(action);
		}
	 }

 public void doPostorder(Consumer<? super E> action) {
		if(!isEmpty()) {
			left.doPostorder(action);
			right.doPostorder(action);
			action.accept(val);
		}
 }
 
 public E calcPostorder(TrinaryFunction<E> operation, E id) {
		if(!isEmpty()) {
			return operation.apply(left.calcPostorder(operation,id),
								   right.calcPostorder(operation,id), val);
		}
		return id;
}


 /**
  * @post return sum of hashcodes of the contained values
  */
 public int hashCode()
 {
     if (isEmpty()) return 0;
     int result = left().hashCode() + right().hashCode();
     if (value() != null) result += value().hashCode();
     return result;
 }
 
 /**
  * Returns a string representing the tree rooted at this node.
  * <font color="#FF0000">WARNING</font> this can be a very long string.
  * 
  * @return A string representing the tree rooted at this node.
  */
 public String treeString(){
     String s = "";
     for (int i=0; i < this.depth(); i++){
         s += "\t|";
     }
     
     s += ("<" + val + " : " + getHand() + ">\n");
     
     if (!left.isEmpty()) s += left.treeString();
     if (!right.isEmpty()) s += right.treeString();

     return s;
 }
 
 /**
  * Support method for {@link #toString}. Returns R if this is node 
  * is a right child, L if this node is a left child and Root if this
  * node is the root.
  * 
  * @return R if this is node 
  * is a right child, L if this node is a left child and Root if this
  * node is the root.
  */
 private String getHand(){
     if (isRightChild()) return "R";
     if (isLeftChild()) return "L";
     return "Root";  
 }
 
 
 /**
  * Returns a representation the subtree rooted at this node
  *
  * @post Returns string representation
  * 
  * @return String representing this subtree
  */
 public String toString()
 {
     if (isEmpty()) return "<BinaryTree8: empty>";
     StringBuffer s = new StringBuffer();
     s.append("<BinaryTree8 "+value());
     if (!left().isEmpty()) {
     		s.append("L: "+left());
     } else {
     		s.append(" -");
     }
     if (!right().isEmpty()) {
     		s.append("R: "+right());
     }
     else {
     		s.append(" -");
     }
     s.append('>');
     return s.toString();
 }
 
 public static void main(String[] args) {
	 BinaryTree8<Integer> leftleft = new BinaryTree8<Integer>(12);
	 BinaryTree8<Integer> leftright = new BinaryTree8<Integer>(9);
	 BinaryTree8<Integer> left = 
			 new BinaryTree8<Integer>(47,leftleft,leftright);
	 BinaryTree8<Integer> right = new BinaryTree8<Integer>(5);
	 BinaryTree8<Integer> full = new BinaryTree8<Integer>(10,left,right);
//	 System.out.println("Preorder");
//	 full.doPreorder(s -> System.out.println(s));
//	 System.out.println();
//	 System.out.println("Inorder");
//	 full.doInorder(s -> System.out.println(s));
//	 System.out.println();
//	 System.out.println("Postorder");
//	 full.doPostorder(s -> System.out.println(s));
	 
	 // The following is illegal in Java 8 because the function in 
	 // doInorder accesses a variable sum that is not effectively final!
	 //	 int sum = 0;
	 //	 full.doInorder(s -> sum = sum + s);
	 
	 // But the following is fine!
	 System.out.println("The sum is "+ full.calcPostorder((s,t,u) -> s+t-u, 0));
 }
}
