package edu.uprm.cse.datastructures.cardealer.model;

import java.util.Comparator;

public class CarComparator implements Comparator<Car> {

	@Override
	public int compare(Car car1, Car car2) {
		String car1Str = car1.getCarBrand() + car1.getCarModel() + car1.getCarModelOption(); 
		String car2Str = car2.getCarBrand() + car2.getCarModel() + car2.getCarModelOption();
		
	    if (car1Str == car2Str) 
	        return 0;
	    
	    if (car1Str == null) 
	    	return -1;
	    
	    if (car2Str == null) 
	        return 1;
	    
	    return car1Str.compareTo(car2Str);
	    
//		String car1Str = car1.getCarBrand(); 
//		String car2Str = car2.getCarBrand();
//		int i = 0;
//
//		while (i < 2) {
//			if (car1Str.compareTo(car2Str) > 0) { // car1 > car 2
//				return 1;
//			}
//			else if (car1Str.compareTo(car2Str) < 0) { // car1 < car 2
//				return -1;
//			}
//			else { // if the labeling was the same, swap to the next label and repeat
//				car1Str = car1.getCarModel();
//				car2Str = car2.getCarModel();
//				i++;
//			}
//		}
//		return 0; // Both cars are the same
	}

}
