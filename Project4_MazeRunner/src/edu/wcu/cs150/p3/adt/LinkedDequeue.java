package edu.wcu.cs150.p3.adt;

import java.awt.Point;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * This class implements a dequeue (double-ended queue) using a doubly linked
 * list. A dequeue allows for efficient insertion and removal of elements at
 * both the front and the back of the queue.
 * @Author Dagmawi Negatu
 * @version May 6 2023
 * @param <E> the type of elements stored in the dequeue
 */

public class LinkedDequeue<E> implements Dequeue<E>, Iterable<E> {

	/**
	 * A reference to the first node in the linked list.
	 */
	Node<E> head;
	/**
	 * Front element of dequeue head points to
	 */
	int front;
	/**
	 * End element of dequeue
	 */
	int end;

	/**
	 * The number of values in this dequeue.
	 */
	int numElements;

	/**
	 * Initializes a new empty dequeue.
	 */
	public LinkedDequeue() {
		// Empty dequeue has head element null
		head = null;
		// front -1 for logical access in dequeue
		front = -1;
		// end -1 for logical access in dequeue
		end = -1;
		// empty dequeue contains no elements
		numElements = 0;
	}

	/**
	 * Initializes a new empty dequeue.
	 * 
	 * @param head first contained data in dequeue
	 */

	public LinkedDequeue(Node<E> head) {
		// Assign new head element data
		this.head = head;

		// Front 0 for first element access
		front = 0;

		// End 0 for last element access
		end = 0;
		// The number of values in dequeue 1
		numElements = 1;
	}

	/**
	 * Initializes a new by dequeue using the items from the other dequeue
	 * PostCondition: The other dequeue should not have any items removed from it.
	 * This node should have the data items appearing in the same order.
	 * 
	 * @param d another LinkedDeque from which the values should be copied.
	 */
	public LinkedDequeue(LinkedDequeue<E> d) {

		for (E object : d) {
			// Add each node at the end of the linkedDequeue
			this.addLast(object);
		}
	}

	/**
	 * Appends the specified value on the "right end" of this dequeue.
	 * postcondition: If the size of this dequeue before the append operation was
	 * zero (i.e., if there were previously no elements in this dequeue), the newly
	 * appended element becomes both the first and last element in this dequeue.
	 * Otherwise, the newly appended element becomes the last element in this
	 * dequeue and all previous elements in the dequeue remain unchanged.
	 */
	@Override
	public void addLast(E value) {
		// Exception handling passed in node does not contain real data
		if (value == null)
			throw new IllegalArgumentException("Parameter is null");

		// Initialize new node object
		Node<E> userEntry = new Node<E>(value);
		// Dequeue is empty, add the node data in the head
		if (this.numElements == 0) {
			head = userEntry;
			front = 0;
			end++;
		}

		// Dequeue is not empty
		else {
			Node<E> current = head;

			// Find insertion point in the end of the linkedDequeue
			for (int i = 0; i < this.numElements - 1; i++) {
				if (current.next != null)
					current = current.next;
			}

			// Add passed in object
			current.next = userEntry;
		}

		// Update number of elements
		this.numElements++;

		// Update the end point for the LinkedDequeue
		end++;

	}

	/**
	 * Prepends the specified value on the "left end" of this dequeue.
	 * postcondition: If the size of this dequeue before the append operation was
	 * zero (i.e., if there were previously no elements in this dequeue), the newly
	 * prepended element becomes both the first and last element in this dequeue.
	 * Otherwise, the newly appended element becomes the first element in this
	 * dequeue and all previous elements in the dequeue remain unchanged.
	 */
	@Override
	public void addFirst(E value) {
		// Handling exception passed in node object does not contain real data
		if (value == null)
			throw new IllegalArgumentException("Parameter is null");

		// Initialize new node object
		head = new Node<E>(value, head);
		// Front becomes zero
		front = 0;
		// Update the number of elements in linkedDequeue
		this.numElements++;
		// Update the end point for linkedDequeue
		end++;
	}

	/**
	 * Removes the first element from this dequeue. postcondition: After
	 * removeFirst() returns, this dequeue contains all of the elements it
	 * previously had, with the exception of the first element. If there was
	 * previously only one element in the dequeue, then this dequeue is empty when
	 * this method returns. precondition: the size of this dequeue must be greater
	 * than zero.
	 * 
	 * @throws IllegalStateException - if this dequeue is empty.
	 */
	@Override
	public E removeFirst() {

		// Handling excpetion if trying to remove element when no element in
		// linkedDequeue
		if (this.numElements < 1)
			throw new IllegalStateException("No elements to access");

		// Generic data for the head node
		E result = head.getData();

		// Remove object from linkedDequeue
		head = head.next;

		// Update the end point of the LinkedDequeue
		end--;
		// Update the number of values in LinkedDequeue
		this.numElements--;

		// Have access to the removed element
		return result;
	}

	/**
	 * Removes the first element from this dequeue. precondition: the size of this
	 * dequeue must be greater than zero. postcondition: After removeLast() returns,
	 * this dequeue contains all of the elements it previously had, with the
	 * exception of the last element. If there was previously only one element in
	 * the dequeue, then this dequeue is empty when this method returns
	 * 
	 * @throws IllegalStateException - if this dequeue is empty.
	 */

	@Override
	public E removeLast() {
		// Handling Exception if no element to remove from LinkedDequeue
		if (this.numElements < 1) {
			throw new IllegalStateException("Dequeue is empty");
		}

		// Current node starts from head for traversing elements
		Node<E> current = head;

		// Node to hold the removed object
		Node<E> result = null;
		// 1 number of element, add the object next to the head
		if (this.numElements == 1) {
			result = head;
			head = null;
		}

		// Traverse through all elements until 2 last object
		for (int i = 1; i < this.numElements - 1; i++) {
			current = current.next;
		}

		// Save element to be removed
		result = current.next;

		// Remove the element from our linkedList
		current.next = null;
		end--;

		// Update number of elements
		this.numElements--;

		// Return the data of the removed object
		return result.getData();
	}

	/**
	 * Returns a copy of the first element in this dequeue. precondition: the size
	 * of this dequeue must be greater than zero. postcondition: This method does
	 * not modify the abstract state of this dequeue.
	 * 
	 * @throws IllegalStateException - if this dequeue is empty.
	 */

	@Override
	public E peekFirst() {

		// Exception handling the Dequeue does not contain any data
		if (this.numElements < 1)
			throw new IllegalStateException("Dequeue is empty");

		// Return the head's contained data
		return (head.getData());
	}

	/**
	 * Returns a copy of the last element in this dequeue. precondition: the size of
	 * this dequeue must be greater than zero. postcondition: This method does not
	 * modify the abstract state of this dequeue.
	 * 
	 * @throws IllegalStateException - if this dequeue is empty.
	 */
	@Override
	public E peekLast() {

		// Linked Dequeue does not contain any data
		if (this.numElements < 1)
			throw new IllegalStateException("Dequeue is empty");

		// Initialize new node object
		Node<E> current = head;

		// Traverse through all elements in LinkedDequeue
		for (int i = 0; i < this.numElements; i++) {
			if (current.next != null)
				current = current.next;// Save last element
		}

		// Return Last element data
		return (current.getData());
	}

	/**
	 * Return true if this dequeue is empty.
	 * 
	 * @return True if empty, false if not.
	 * 
	 */

	@Override
	public boolean isEmpty() {
		return (head == null && this.numElements < 1);
	}

	/**
	 * Determines if this dequeue is equal to the given object. precondition: data
	 * types for <E> have a working equals method. postcondition: This method does
	 * not modify the abstract state of this dequeue.
	 * 
	 * @Parameter other - The object with which to compare this dequeue.
	 * @return true if other object is a GenericLinkedDeque with the same abstract
	 *         state as this object; false otherwise
	 */

	@SuppressWarnings("unused")
	public boolean equals(Object other) {
		boolean result = false;

		// Two objects considered to be equal
		// if they contain same memory locations
		if (this == other)
			return (true);

		// Both object null, they are equal
		if (this == null && other == null)
			return (true);

		// Go through properties of objects if both have same instances
		if (other instanceof LinkedDequeue) {
			@SuppressWarnings("unchecked")
			// Other object as an instance of LinkedDequeue
			LinkedDequeue<E> tmp = (LinkedDequeue<E>) other;
			LinkedDequeue<E> tmpDeq = tmp;

			if (tmp.size() != this.size()) {
				return (false);
			}
			// Start traversing from the head
			Node<E> current = head;

			// Traverse through all object in other object
			for (E thisObject : tmp) {
				for (int i = 0; i < this.numElements; i++) {
					// Compare the two object's field
					if (thisObject != current) {
						result = false;
						break;
					}
					// Move to the next element in this LinkedDequeue
					current = current.next;
				}
			}
		}

		// Boolean check if two objects are equal
		return (result);

	}

	/**
	 * Returns a String representation of this dequeue. The format of the String
	 * representation is: <first, next, next, last>. postcondition: This method does
	 * not modify the abstract state of this dequeue.
	 */
	public String toString() {

		// Initialize stringBuilder object
		StringBuilder sb = new StringBuilder();

		// Start traversing from the head
		Node<E> current = head;

		// Set marker for the content of LinkedDequeue
		sb.append("<");

		// Traverse through all the elements in this LinkedDequeue
		while (current != null) {
			// Add each node object in stringBuilder
			sb.append(current.data);

			// Marker for each node object's data in stringbuilder
			if (current.next != null) {
				sb.append(", ");
			}

			// Move to the next element in this LinkedDequeue
			current = current.next;
		}

		sb.append(">");// End of LinkedDequeue data
		// Return string representation of this Linked Dequeue
		return (sb.toString());
	}

	/**
	 * Returns the number of elements in this dequeue. postcondition: This method
	 * does not modify the abstract state of this dequeue.
	 * 
	 * @return the number of elements in this dequeue.
	 */

	public int size() {
		return (this.numElements);
	}

	/**
	 * This class represents a node in the linked dequeue implementation.
	 * 
	 * @author Dagmawi Negatu
	 *
	 * @param <T>
	 */

	public class Node<T> extends Object {

		// The value at this node's location.
		private T data;

		// A reference to the next node in the linked list
		private Node<T> next;

		/**
		 * Constructs a new Node give a value in the linked list.
		 * 
		 * @param data - The value this node should store
		 */
		public Node(T data) {
			this.data = data;
			this.next = null;
		}

		/**
		 * Constructs a new Node given a value and a pointer to the next node in the
		 * linked list.
		 * 
		 * @param data     value - The value this node should store.
		 * @param nextNode A reference to the next node in the list, or null if this
		 *                 node is the last node.
		 */
		public Node(T data, LinkedDequeue<E>.Node<T> nextNode) {
			this.data = data;
			this.next = nextNode;
		}

		/**
		 * Get the node object data
		 * 
		 * @return generic
		 */
		private T getData() {
			return this.data;
		}

		/**
		 * Set the value of the node object
		 * 
		 * @param data value to set in this Linked list
		 */
		@SuppressWarnings("unused")
		private void setData(T data) {
			this.data = data;
		}

		/**
		 * Get the next node in this linked list.
		 * 
		 * @return return node
		 */
		public LinkedDequeue<E>.Node<T> getNextNode() {
			return next;
		}

		/**
		 * Set the next node
		 * 
		 * @param nextNode
		 */
		public void setNextNode(LinkedDequeue<E>.Node<T> nextNode) {
			next = nextNode;
		}

	}

	/**
	 * 
	 */
	@Override
	public Iterator<E> iterator() {
		return new DIterator();
	}

	/**
	 * 
	 * A private inner class that implements the Iterator interface for a
	 * LinkedDequeue.
	 * 
	 * It allows iteration over the elements in the dequeue in a forward direction.
	 */
	private class DIterator implements Iterator<E> {

		// The current node being pointed to by the iterator.
		private LinkedDequeue<E>.Node<E> current;

		// The tail node of the LinkedDequeue.
		private E tail;

		// The data element of the current node.
		private E data = null;

		/**
		 * 
		 * Constructs a new DIterator object with the current node set to the head of
		 * the LinkedDequeue, and the tail node set to the tail of the LinkedDequeue.
		 */
		private DIterator() {
			current = head;
			tail = LinkedDequeue.this.getTail();
		}

		/**
		 * 
		 * Returns true if there are more elements to be iterated over in the
		 * LinkedDequeue.
		 * 
		 * @return true if there are more elements to be iterated over, false otherwise.
		 */
		public boolean hasNext() {
			return (current != null || data != tail);
		}

		/**
		 * 
		 * Returns the next element in the iteration of the LinkedDequeue.
		 * 
		 * @return the next element in the iteration.
		 * 
		 * @throws NoSuchElementException if there are no more elements to be iterated
		 *                                over.
		 */
		@Override
		public E next() {
			// If the current node is null, set data to null.
			if (current == null) {
				data = null;
			} else {
				data = current.getData();
			}

			// If there are no more elements to be iterated over, throw a
			// NoSuchElementException.
			if (!hasNext()) {
				throw new NoSuchElementException("No new node next");
			}

			// If data is null, update data to the tail node. Otherwise, update current to
			// the next node.
			if (data == null) {
				data = tail; // update data to tail node
			} else {
				current = current.next;
			}

			// Return the next element in the iteration.
			return data;
		}
	}

	/**
	 * 
	 * Returns the tail element of the LinkedDequeue.
	 * 
	 * @return the tail element of the LinkedDequeue, or null if the LinkedDequeue
	 *         is empty.
	 */
	public E getTail() {
		// If the LinkedDequeue is empty, return null.
		if (head == null) {
			return null;
		}

		// Otherwise, iterate through the LinkedDequeue until the tail element is found.
		Node<E> current = head;
		while (current.next != null) {
			current = current.next;
		}

		// Return the tail element.
		return current.getData();
	}

	/**
	 * 
	 * Creates and returns a new LinkedDequeue<Point> object that is a clone of the
	 * specified LinkedDequeue<Point> object.
	 * 
	 * @param stack the LinkedDequeue<Point> object to clone.
	 * 
	 * @return a new LinkedDequeue<Point> object that is a clone of the specified
	 *         LinkedDequeue<Point> object.
	 */
	public LinkedDequeue<Point> stackCloner(LinkedDequeue<Point> stack) {
		// Create a new LinkedDequeue<Point> object to hold the cloned elements.
		LinkedDequeue<Point> cloned = new LinkedDequeue<Point>();

		// Iterate through the elements in the original LinkedDequeue<Point>.
		Point point = null;
		for (Point pointInOriginal : stack) {
			// Get the x and y coordinates of the current Point element.
			int x = (int) pointInOriginal.getX();
			int y = (int) pointInOriginal.getY();
			// Create a new Point element with the same x and y coordinates.
			point = new Point(x, y);

			// Add the new Point element to the cloned LinkedDequeue<Point>.
			cloned.addLast(point);

		} // Return the cloned LinkedDequeue<Point> object.
		return cloned;
	}

}
