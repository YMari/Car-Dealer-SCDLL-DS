package edu.uprm.cse.datastructures.cardealer;

import java.util.Comparator;
import java.util.Iterator;

import edu.uprm.cse.datastructures.cardealer.util.SortedList;

public class SortedCircularDoublyLinkedList<E> implements SortedList<Integer> {
	
	private Node<E> header;
	private int currentSize;
	private Comparator Comp;
	
	public SortedCircularDoublyLinkedList(Comparator comparator) {
		this.header = new Node<>();
		this.currentSize = 0;
		
		this.header.setNext(this.header);
		this.header.setPrevious(this.header);
	}

	@Override
	public Iterator<Integer> iterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean add(Integer obj) {
		if (this.isEmpty()) {
			this.header.setNext(new Node<E>((E) obj, this.header, this.header));
			this.currentSize++;
		}
		else {
			Node<E>temp= this.header.getNext();
			while (temp.getNext() != this.header) {
				temp = temp.getNext();
			}
			Node<E> newNode = new Node<>((E) obj, this.header, this.header.getPrevious());
			temp.setNext(newNode);
			this.currentSize++;
		}
		return true;
	}

	@Override
	public int size() {
		return this.currentSize;
	}

	@Override
	public boolean remove(Integer obj) {
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
			return false;
		}
		else {
			Node<E> temp = this.header;
			Node<E> target = null;
			int currentPosition = 0;
			
			while (currentPosition != index) {
				temp = temp.getNext();
				currentPosition++;
			}
			target = temp.getNext();
			temp.setNext(target.getNext());
			target.setElement(null);
			target.setNext(null);
			target.setPrevious(null);
			this.currentSize--;
			return true;
		}
	}

	@Override
	public int removeAll(Integer obj) {
		int count = 0;
		while (this.remove(obj)) {
			count++;
		}
		return count;
	}

	@Override
	public Integer first() {
		// return the first element (casted to integer)
		return (this.isEmpty() ? null : (Integer) this.header.getNext().getElement());
	}

	@Override
	public Integer last() {
		// return the last element (casted to integer)
		return (this.isEmpty() ? null : (Integer) this.header.getPrevious().getElement());
	}

	@Override
	public Integer get(int index) {
		if ((index < 0) || index >= this.currentSize) {
			throw new IndexOutOfBoundsException();
		}
		
		Node<E> temp  = this.getPosition(index);
		return (Integer) temp.getElement();
	}
	
	private Node<E> getPosition(int index){
		int currentPosition = 0;
		Node<E> temp = this.header.getNext();
		
		while(currentPosition != index) {
			temp = temp.getNext();
			currentPosition++;
		}
		return temp;

	}

	@Override
	public void clear() {
		while (!this.isEmpty()) {
			this.remove(0);
		}
	}

	@Override
	public boolean contains(Integer e) {
		return this.firstIndex(e) >= 0;
	}

	@Override
	public boolean isEmpty() {
		return this.size() == 0;
	}

	@Override
	public int firstIndex(Integer e) {
		int i = 0;
		for (Node<E> temp = this.header.getNext(); temp != null; 
				temp = temp.getNext(), ++i) {
			if (temp.getElement().equals(e)) {
				return i;
			}
		}
		// not found
		return -1;
	}

	@Override
	public int lastIndex(Integer e) {
		int i = 0;
		for (Node<E> temp = this.header.getPrevious(); temp != null; 
				temp = temp.getPrevious(), ++i) {
			if (temp.getElement().equals(e)) {
				return i;
			}
		}
		// not found
		return -1;
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
	
}
