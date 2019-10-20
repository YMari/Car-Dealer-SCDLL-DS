package edu.uprm.cse.datastructures.cardealer.model;

import edu.uprm.cse.datastructures.cardealer.SortedCircularDoublyLinkedList;
import edu.uprm.cse.datastructures.cardealer.util.SortedList;

public class CarList {
	
	public static void resetCars() {
		SortedList<Car> carList = new SortedCircularDoublyLinkedList<Car>(new CarComparator());
	}

}
