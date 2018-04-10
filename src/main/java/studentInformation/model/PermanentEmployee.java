package studentInformation.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Permanent")
public class PermanentEmployee extends Employee {
	
	private Boolean IsPermanentEmployee;

	public Boolean getIsPermanentEmployee() {
		return IsPermanentEmployee;
	}

	public void setIsPermanentEmployee(Boolean isPermanentEmployee) {
		IsPermanentEmployee = isPermanentEmployee;
	}
	
	

}
