package edu.uprm.cse.datastructures.cardealer;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import edu.uprm.cse.datastructures.cardealer.model.Car;
import edu.uprm.cse.datastructures.cardealer.model.CarComparator;
import edu.uprm.cse.datastructures.cardealer.model.CarList;

@Path("/cars")
public class CarManager {

	// call a new instance of the list
	private static final SortedCircularDoublyLinkedList<Car> carList = CarList.getInstance(); //helper class instance

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Car[] getCarList(){ // returns all the elements available in the list
		if(carList.isEmpty())return null;
		Car[] carArray = new Car[carList.size()];
		for(int i=0;i<carArray.length; i++)carArray[i]=(Car)carList.get(i);
		return carArray;
	}

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Car getCar(@PathParam("id") long id){ // returns the element with the specified id value
		for(int i=0; i<carList.size(); i++){
			if(((Car)carList.get(i)).getCarId()==id){ return (Car)carList.get(i);}  
		}  throw new NotFoundException();
	}

	@POST
	@Path("/add")
	@Produces(MediaType.APPLICATION_JSON)
	public Response addCar(Car car){ // adds a new element to the list
		carList.add(car);
		return Response.status(201).build();
	}

	@PUT
	@Path("/{id}/update")
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateCar(Car car){ // updates an existing element with the specified id
		for(int i=0; i<carList.size(); i++){
			if(((Car)carList.get(i)).getCarId()==car.getCarId()){ // tests for same element id
				carList.remove(i);
				carList.add(car);
				return Response.status(Response.Status.OK).build();
			}
		}
		return Response.status(404).build(); // if the id does not exist, return 404 (Error)
	}

	@DELETE
	@Path("/{id}/delete")
	public Response deleteCar(@PathParam("id") long id){ // deletes an existing element with the specified id
		for(int i=0; i<carList.size(); i++){
			if(id==((Car)carList.get(i)).getCarId()){
				carList.remove(i);
				return Response.status(Response.Status.OK).build();
			}
		}
		return Response.status(404).build(); // if the id does not exist, return 404 (Error)

	}
}