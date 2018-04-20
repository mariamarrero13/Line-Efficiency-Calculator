package Interfaces;

public interface Dequeue<E> {
	int size( );
	/** Tests whether the deque is empty. */
	boolean isEmpty( );
	/** Returns, but does not remove, the first element of the deque (null if empty). */
	E first( );
	/** Returns, but does not remove, the last element of the deque (null if empty). */
	E last( );
	/** Inserts an element at the front of the deque. */
	void addFirst(E e);
	/** Inserts an element at the back of the deque. */
	void addLast(E e);
	/** Removes and returns the first element of the deque (null if empty). */
	E removeFirst( );
	/** Removes and returns the last element of the deque (null if empty). */
	E removeLast( );
}