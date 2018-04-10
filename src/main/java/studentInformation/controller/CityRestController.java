package studentInformation.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import studentInformation.dto.CityDTO;
import studentInformation.model.City;
import studentInformation.service.impl.*;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequestMapping("/city")
public class CityRestController {

	@Autowired
	private CityServiceImpl cityService;

	@Autowired
	public CityRestController() {
	}
	
//	@GetMapping("/")
//	public ResponseEntity<List<City>> listAllCities() throws Exception {
//		List<City> cities = cityService.findAll();
//		if (cities.isEmpty())
//			return new ResponseEntity(HttpStatus.NO_CONTENT);
//		try {
//			return new ResponseEntity<List<City>>(cities, HttpStatus.OK);
//		} catch (Exception e) {
//			throw e;
//		}
//	}
	
	@GetMapping("/")
	public ResponseEntity<List<CityDTO>> listAllCities() throws Exception {
		List<CityDTO> cities = cityService.findAll();
		if (cities.isEmpty())
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		try {
			return new ResponseEntity<List<CityDTO>>(cities, HttpStatus.OK);
		} catch (Exception e) {
			throw e;
		}
	}
	

	/*@GetMapping("/")
	public String allCities() throws Exception {
		try {
			return cityService.findAll().toString();
		} catch (Exception e) {
			throw new Exception("Did not find any city.");
		}
	}*/

	@GetMapping("/{id}")
	public String GetCityById(@PathVariable int id) throws Exception {
		try {
			return cityService.findById(id).toString();
		} catch (Exception e) {
			throw new Exception("Did not find the city.");
		}
	}

	@PostMapping("/")
	public String createCity(@RequestBody City city) throws Exception {
		try {
			cityService.save(city);
			return "Record added successfully!!!";
		} catch (Exception e) {
			throw new Exception("Record could not be added.");
		}
	}

	@PostMapping("/{id}")
	public String updateCity(@PathVariable int id, @RequestBody City city) throws Exception {
		City currCity = new City();
		try {
			currCity = cityService.findById(id);
		} catch (Exception e) {
			throw new Exception("Did not find the city.");
		}
		/*
		 * if (currStudent == null) {
		 * logger.error("Unable to update. User with id {} not found.", id); return new
		 * ResponseEntity(new CustomErrorType("Unable to upate. User with id " + id +
		 * " not found."), HttpStatus.NOT_FOUND); }
		 */
		currCity.setCity_country_code(city.getCity_country_code());
		currCity.setCity_name(city.getCity_name());
		currCity.setCity_population(city.getCity_population());
		try {
			cityService.save(currCity);
		} catch (Exception e) {
			throw new Exception("Record could not be updated.");
		}
		return "Record updated successfully!!!";
	}

	@DeleteMapping("/delete/{id}")
	public String deleteCity(@PathVariable int id) throws Exception {
		try {
			cityService.delete(id);
			return "Record deleted successfully!!!";
		} catch (Exception e) {
			throw new Exception("Record could not be deleted.");
		}
	}

}
