package studentInformation.model;

import javax.persistence.Entity;

@Entity
public class TwoWheeler extends Vehicle {

	private String VehicleSteering;

	public String getVehicleSteering() {
		return VehicleSteering;
	}

	public void setVehicleSteering(String vehicleSteering) {
		VehicleSteering = vehicleSteering;
	}
	
	
	
	
}
