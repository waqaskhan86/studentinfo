package studentInformation.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import studentInformation.model.Vehicle;

@Repository
public interface VehicleRepository extends CrudRepository<Vehicle,Integer>{ 

}
