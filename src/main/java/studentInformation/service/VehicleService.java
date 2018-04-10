package studentInformation.service;

import java.util.List;

import studentInformation.model.Vehicle;

public interface VehicleService {
	
	/*
	 * Find all the Vehicles.
	 * @param None
	 * @Return List of all the Vehicle's objects.
	 */
	public List<Vehicle> getAll();
	
	/*
	 * Save the Vehicle object
	 * @param Vehicle Object
	 * @Return Vehicle's object.
	 */
	public Vehicle save(Vehicle vehicle) throws Exception;

}
