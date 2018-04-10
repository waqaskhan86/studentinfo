package studentInformation.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import studentInformation.model.City;

@Repository
public interface CityRepository extends CrudRepository<City, Integer> {

}
