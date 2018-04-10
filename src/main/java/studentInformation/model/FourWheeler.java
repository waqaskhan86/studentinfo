/**
 * 
 */
package studentInformation.model;

import javax.persistence.Entity;

/**
 * @author mwaqas
 *
 */
@Entity
public class FourWheeler extends Vehicle {
	
	private String SteeringType;

	public String getSteeringType() {
		return SteeringType;
	}

	public void setSteeringType(String steeringType) {
		SteeringType = steeringType;
	}

}
