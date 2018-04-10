package studentInformation.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import studentInformation.dao.VehicleRepository;
import studentInformation.model.Vehicle;
import studentInformation.service.VehicleService;

@Service(value="vehicleService")
public class VehicleServiceImpl implements VehicleService {

	@Autowired
	private VehicleRepository vehicleRepository;

	@Override
	public List<Vehicle> getAll() {
		List<Vehicle> vehicles = new ArrayList<>();
		try {
			for (Vehicle vehicle : vehicleRepository.findAll()) {
				vehicles.add(vehicle);
			}
		} catch (Exception e) {
			throw e;
		}
		return vehicles;
	}

	@Override
	public Vehicle save(Vehicle vehicle) throws Exception {
		return vehicleRepository.save(vehicle);
	}

}
