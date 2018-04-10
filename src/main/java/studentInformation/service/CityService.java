package studentInformation.service;

import java.util.List;

import studentInformation.dto.CityDTO;
import studentInformation.model.City;

public interface CityService {

	/*
	 * Find all the cities.
	 * @param None
	 * @Return List of all the city's DTOs.
	 */
	public List<CityDTO> findAll() throws Exception;
	
	/*
	 * Find all the cities.
	 * @param None
	 * @Return List of all the city's Objects.
	 */
	public List<City> getAll() throws Exception;

	/*
	 * Find the city that matches the provided id.
	 * @param city Id
	 * @Return city's object.
	 */
	public City findById(int id) throws Exception;

	/*
	 * Save the city object
	 * @param city Object
	 * @Return city's object.
	 */
	public void save(City city);

	/*
	 * Delete the city that matches the provided id.
	 * @param city Id
	 * @Return None.
	 */
	public void delete(int id);

}
