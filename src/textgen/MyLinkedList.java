package textgen;

import java.util.AbstractList;


/** A class that implements a doubly linked list
 * 
 * @author UC San Diego Intermediate Programming MOOC team
 *
 * @param <E> The type of the elements stored in the list
 */
public class MyLinkedList<E> extends AbstractList<E> {
	LLNode<E> head;
	LLNode<E> tail;
	int size;

	/** Create a new empty LinkedList */
	public MyLinkedList() {
		// TODO: Implement this method
		size=0;
		head=new LLNode<E>(null);
		tail=new LLNode<E>(null);
		head.next=tail;
		tail.prev=head;
	}

	/**
	 * Appends an element to the end of the list
	 * @param element The element to add
	 */
	public boolean add(E element ) 
	{
	   
		if (element == null) {
			throw new NullPointerException();
		}
        LLNode<E> n = new LLNode<E>(element);
        tail.prev.next = n;
        n.prev = tail.prev;
        n.next = tail;
        tail.prev = n;
        size++;
		return true;
	
	
	
		// TODO: Implement this method
	}

	/** Get the element at position index 
	 * @throws IndexOutOfBoundsException if the index is out of bounds. */
	public E get(int index) 
	{
		// TODO: Implement this method.
		 if (index < 0 || index > size - 1 || size == 0) {
		      throw new IndexOutOfBoundsException("Index out of bounds in get() method");
		    }

		    LLNode<E> node = head.next;
		    for (int i = 0; i < index; i++) {
		      node = node.next;
		    }

		    return node.data;

	}

	/**
	 * Add an element to the list at the specified index
	 * @param The index where the element should be added
	 * @param element The element to add
	 */
	public void add(int index, E element ) 
	{
		// TODO: Implement this method
		if (element == null) {
			throw new NullPointerException();
		}
		if ((index < 0 || index >= size)&& size != 0) {
			throw new IndexOutOfBoundsException();
		}
		LLNode<E> prev = head;
		for (int i = 0; i < index; i++) {
			prev = prev.next;
		}
		LLNode<E> node = new LLNode<E>(element);
	
		node.next = prev.next;
		prev.next = node;
		node.next.prev = node;
		node.prev = prev;
		size++;
	}


	/** Return the size of the list */
	public int size() 
	{
		return this.size;
	}

	/** Remove a node at the specified index and return its data element.
	 * @param index The index of the element to remove
	 * @return The data element removed
	 * @throws IndexOutOfBoundsException If index is outside the bounds of the list
	 * 
	 */
	public E remove(int index) 
	{
		 if (index < 0 || index > this.size() - 1) {
		      throw new IndexOutOfBoundsException("Illegal index in remove() method.");
		    }
		    
		    LLNode<E> nodeToRemove = head.next;
		    for (int i = 0; i < index; i++) {
		      nodeToRemove = nodeToRemove.next;
		    }
		    
		    E removedElement = nodeToRemove.data;
		    nodeToRemove.prev.next = nodeToRemove.next;
		    nodeToRemove.next.prev = nodeToRemove.prev;
		    nodeToRemove.prev = null;
		    nodeToRemove.next = null;
		    
		    this.size--;
		    
		    return removedElement;
		// TODO: Implement this method
		
	}

	/**
	 * Set an index position in the list to a new element
	 * @param index The index of the element to change
	 * @param element The new element
	 * @return The element that was replaced
	 * @throws IndexOutOfBoundsException if the index is out of bounds.
	 */
	public E set(int index, E element) 
	{
		// TODO: Implement this method
		if (element == null) {
		      throw new NullPointerException("'Data can not be null!");
		    }
		    
		    if (index < 0 || index > this.size() - 1) {
		      throw new IndexOutOfBoundsException("Index cannot be < 0 or > size minus 1");
		    }
		    
		    LLNode<E> nodeToSet = head.next;
		    for (int i = 0; i < index; i++) {
		      nodeToSet = nodeToSet.next;
		    }
		    
		    E old = nodeToSet.data;
		    nodeToSet.data = element;

		    return old;
		  
  }
}
class LLNode<E> 
{
	LLNode<E> prev;
	LLNode<E> next;
	E data;

	// TODO: Add any other methods you think are useful here
	// E.g. you might want to add another constructor

	public LLNode(E e) 
	{
		this.data = e;
		this.prev = null;
		this.next = null;
	}

}

