package edu.uprm.cse.datastructures.cardealer;

import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.uprm.cse.datastructures.cardealer.util.SortedList;

public class SortedCircularDoublyLinkedList<E> implements SortedList<E> {
	
	private Node<E> header;
	private int currentSize;
	private Comparator<E> comp;
	
	public SortedCircularDoublyLinkedList(Comparator cmp) {
		this.header = new Node<>();
		this.currentSize = 0;
		
		this.comp = cmp;
		this.header.setNext(this.header);
		this.header.setPrevious(this.header);
	}

	@Override
	public boolean add(E obj) {
		Node<E> temp = this.header;
		Node<E> newNode = new Node<E>((E) obj, null, null);
		
		if (this.isEmpty()) {
			temp.setNext(newNode);
			temp.setPrevious(newNode);
			newNode.setNext(temp);
			newNode.setPrevious(temp);
			this.currentSize++;
		}
		else {
			temp = temp.getNext();
			while (temp != this.header) {
				// compare object to keep the order
				if (this.comp.compare(newNode.getElement(), temp.getElement()) <= 0) {
					newNode.setNext(temp);
					newNode.setPrevious(temp.getPrevious());
					temp.getPrevious().setNext(newNode);
					temp.setPrevious(newNode);
					this.currentSize++;
					return true;
				}
				// move through the nodes
				else if (temp.getNext() != this.header) {
					temp = temp.getNext();
				}
				// if we made it to the end, place the new object at the end
				else {
					newNode.setPrevious(this.header.getPrevious());
					newNode.setNext(this.header);
					this.header.getPrevious().setNext(newNode);
					this.header.setPrevious(newNode);
					this.currentSize++;
					return true;
				}
			}
		}

		return false;  // if no condition is met, return false
	}
	
	@Override
	public int size() {
		return this.currentSize;
	}

	@Override
	public boolean remove(E obj) {
		int i = this.firstIndex(obj);
		if (i < 0) {
			return false;
		}else {
			this.remove(i);
			return true;
		}
	}

	@Override
	public boolean remove(int index) {
		if ((index < 0) || (index >= this.currentSize)){
			throw new IndexOutOfBoundsException();
		}

		Node<E> temp = this.header;
		for(int i = 0; i <= index; i++) { 
			temp = temp.getNext();
		}

		temp.getPrevious().setNext(temp.getNext());
		temp.getNext().setPrevious(temp.getPrevious()); 
		temp.setNext(null);
		temp.setPrevious(null);
		this.currentSize--;
		return true;
	}

	@Override
	public int removeAll(E obj) {
		int count = 0;
		while (this.remove(obj)) {
			count++;
		}
		return count;
	}

	@Override
	public E first() {  // return the first element
		return this.header.getNext().getElement();
	}

	@Override
	public E last() {  // return the last element
		return this.header.getPrevious().getElement();
	}

	@Override
	public E get(int index) {
		Node<E> temp = this.header;
        for(int i = 0; i <= index; i++) { 
            temp = temp.getNext();
        }
        return temp.getElement();
	}

	@Override
	public void clear() {
		while (!this.isEmpty()) {
			this.remove(0);
		}
	}

	@Override
	public boolean contains(E e) {
		return this.firstIndex(e) >= 0;
	}

	@Override
	public boolean isEmpty() {
		return this.size() == 0;
	}

	@Override
	public int firstIndex(E e) {
		int i = 0;
    	Node<E> temp = this.header.getNext(); 
    	
    	while(temp.getNext() != this.header) {  //loop until the first instance is found
    		if (temp.getElement().equals(e)) {
    			return i;
    		} 
    		temp = temp.getNext(); 
    		i++;
    	}
    	
    	if (temp.getElement().equals(e)) {  // return the last element's index
    		return i;
    	}
    	
    	return -1; // not found
	}

	@Override
	public int lastIndex(E e) {
		int i = this.currentSize - 1;
		Node<E> temp = this.header.getPrevious();
				
		while (temp.getPrevious() != this.header) {  // loop backwards until the last instance is found
			if (temp.getElement().equals(e)) {
				return i;
			}
			temp = temp.getPrevious();
			i--;
		}
		return -1;  // not found
	}
	
	@Override
	public Iterator<E> iterator() {
		return new SortedCircularDoublyLinkedListIterator<E>();
	}
	
	private static class Node<E> {
		private E element;
		private Node<E> next;
		private Node<E> previous;
		
		public Node(E element, Node<E> next, Node<E> previous) {
			super();
			this.element = element;
			this.next = next;
			this.previous = previous;
		}
		public Node() {
			super();
		}
		
		public E getElement() {
			return element;
		}
		public void setElement(E element) {
			this.element = element;
		}
		public Node<E> getNext() {
			return next;
		}
		public Node<E> getPrevious() {
			return previous;
		}
		public void setNext(Node<E> next) {
			this.next = next;
		}
		public void setPrevious(Node<E> previous) {
			this.previous = previous;
		}
	}
	
	private class SortedCircularDoublyLinkedListIterator<E> implements Iterator<E>{
		private Node<E> nextNode;
		private Node<E> previousNode;
		
		public SortedCircularDoublyLinkedListIterator() {
			this.nextNode = (Node<E>) header.getNext();
			this.previousNode = (Node<E>) header.getPrevious();
		}
		@Override
		public boolean hasNext() {
			return nextNode != null;
		}
		
		public boolean hasPrevious() {
			return previousNode != null;
		}

		@Override
		public E next() {
			if (this.hasNext()) {
				E result = this.nextNode.getElement();
				this.nextNode = this.nextNode.getNext();
				return result;
			}
			else {
				throw new NoSuchElementException();
			}
		}
		
		public E previous() {
			if (this.hasPrevious()) {
				E result = this.nextNode.getElement();
				this.previousNode = this.previousNode.getPrevious();
				return result;
			}
			else {
				throw new NoSuchElementException();
			}
		}
	}

}
