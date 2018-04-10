package studentInformation.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Contractual")
public class ContractualEmployee extends Employee {

	private Boolean IsContractualEmployee;

	public Boolean getIsContractualEmployee() {
		return IsContractualEmployee;
	}

	public void setIsContractualEmployee(Boolean isContractualEmployee) {
		IsContractualEmployee = isContractualEmployee;
	}
	
	
}
