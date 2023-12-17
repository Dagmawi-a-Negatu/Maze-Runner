package edu.wcu.cs150.p3.adt;

import java.awt.Point;

/**
 * Defines a queue ADT. A dequeue compines a stack and a queue in to a single data structure.
 * <br>
 * <br>
 * Stack Method [Equivalent Deque Method]<br>
 * =========== =====================<br>
 * push(e) [addFirst(e)]<br>
 * pop() 	[removeFirst()]<br>
 * peek() 	[peekFirst()]<br>
<br>
 *Queue Method [Equivalent Deque Method]<br>
 *===========   ===================<br>
 * add(e)	[ addLast(e)]<br>
 * offer(e) [offerLast(e)]<br>
 * remove() [removeFirst()]<br>
 * poll() [pollFirst()]<br>
 * element() 	[getFirst()]<br>
 * peek() 	[peekFirst()]<br>
 *
 * @author Dagmawi Negatu
 * @version May 6 2023
 * @param <E> The type to be stored by this dequeue.
 */
public interface Dequeue<E> {
	
    /**
     * Appends the specified value on the &quot;right end&quot; of this deque.
     *
     * postcondition: If the size of this deque before the append operation was
     *                zero (i.e., if there were previously no elements in this
     *                deque), the newly appended element becomes both the first
     *                and last element in this deque. Otherwise, the newly
     *                appended element becomes the last element in this deque
     *                and all previous elements in the deque remain unchanged.
     *                
     * @param value The value to add to the &quot;right end&quot; of this deque.
     */
    public void addLast(E value);
    
    
    /**
     * Prepends the specified value on the &quot;left end&quot; of this deque.
     *
     *postcondition: If the size of this deque before the append operation was
     *                zero (i.e., if there were previously no elements in this
     *                deque), the newly prepended element becomes both the first
     *                and last element in this deque. Otherwise, the newly
     *                appended element becomes the first element in this deque
     *                and all previous elements in the deque remain unchanged.
     * @param value The value to add to the &quot;left end&quot; of this deque.
     *
     */
    public void addFirst(E value);
    
    
    /**
     * Removes the first element from this deque.
     *  postcondition: After removeFirst() returns, this deque contains all of
     *                the elements it previously had, with the exception of
     *                the first element.  If there was previously only one
     *                element in the deque, then this deque is empty when this
     *                method returns.
     *                
     * precondition: the size of this deque must be greater than zero.
     *
     * @throws IllegalStateException if this deque is empty.
     *
     */
    public E removeFirst();
    
    /**
     * Removes the first element from this dequeue.
     *
     * precondition: the size of this dequeue must be greater than zero.
     * postcondition: After removeLast() returns, this dequeue contains all of the
     *                elements it previously had, with the exception of the last
     *                element.  If there was previously only one element in the
     *                dequeue, then this dequeue is empty when this method returns
     *
     * @throws IllegalStateException if this dequeue is empty.
     *
     */
    public E removeLast();
    
    /**
     * Returns a copy of the first element in this dequeue.
     *
     * precondition: the size of this dequeue must be greater than zero.
     * postcondition: This method does not modify the abstract state of this deque.
     *
     * @throws IllegalStateException if this dequeue is empty.
     * @return a copy of the first element in this dequeue.
     *
     */
    public E peekFirst();
    
    /**
     * If the dequeue is empty this function returns true.
     * @return True if empty, false if not.
     */
    public boolean isEmpty();
    
    /**
     * Returns a copy of the last element in this dequeue.
     *
     * precondition: the size of this dequeue must be greater than zero.
     *  postcondition: This method does not modify the abstract state of this dequeue.
     *
     * @throws IllegalStateException if this dequeue is empty.
     *
     * @return a copy of the last element in this dequeue.
     */
    public E peekLast();

}
