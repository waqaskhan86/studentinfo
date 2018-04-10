package studentInformation.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import studentInformation.dao.CityRepository;
import studentInformation.dto.CityDTO;
import studentInformation.model.City;
import studentInformation.service.CityService;

@Service(value = "cityService")
public class CityServiceImpl implements CityService {

	@Autowired
	private CityRepository cityRepository;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<CityDTO> findAll() throws Exception {
		List<CityDTO> lsCities = new ArrayList<>();

		try {
			for (City city : cityRepository.findAll()) {
				CityDTO cityObj = new CityDTO();
				cityObj.setCityId(city.getCity_id());
				cityObj.setCityCountryCode(city.getCity_country_code());
				cityObj.setCityName(city.getCity_name());
				cityObj.setCityPopulation(city.getCity_population());
				lsCities.add(cityObj);				
			}
		} catch (Exception e) {
			throw e;
		}

		return lsCities;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<City> getAll() throws Exception {
		List<City> lsCities = new ArrayList<>();

		try {
			for (City city : cityRepository.findAll()) {
				lsCities.add(city);
			}
		} catch (Exception e) {
			throw e;
		}

		return lsCities;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public City findById(int id) throws Exception {
		City city = new City();
		try {
			city = cityRepository.findOne(id);
		} catch (Exception e) {
			throw e;
		}
		return city;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void save(City city) {
		try {
			cityRepository.save(city);
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void delete(int id) {
		try {
			cityRepository.delete(id);
		} catch (Exception e) {
			throw e;
		}

	}

}
