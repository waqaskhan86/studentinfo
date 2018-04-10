package studentInformation.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import studentInformation.model.FourWheeler;
import studentInformation.model.TwoWheeler;
import studentInformation.model.Vehicle;
import studentInformation.service.impl.VehicleServiceImpl;

@RestController
@RequestMapping("/vehicles")
public class VehicleRestController {

	@Autowired
	private VehicleServiceImpl vehicleService;

	@GetMapping("/")
	public List<Vehicle> allVehicles() throws Exception {
		try {
			return vehicleService.getAll();
		} catch (Exception e) {
			throw new Exception("Did not find any books.");
		}
	}

	@PostMapping("/add")
	public Vehicle createVehicle(@RequestBody Vehicle vehicle) throws Exception {
		Vehicle vehicleObject = new Vehicle();
		vehicleObject.setVehicleName(vehicle.getVehicleName());

		TwoWheeler tWheeler = new TwoWheeler();
		tWheeler.setVehicleName(vehicle.getVehicleName());
		tWheeler.setVehicleSteering("Bi");

		FourWheeler fWheeler = new FourWheeler();
		fWheeler.setVehicleName(vehicle.getVehicleName());
		fWheeler.setSteeringType("Power");

		try {
			// vehicleService.save(vehicleObject);
			// vehicleService.save(tWheeler);
			vehicleService.save(fWheeler);
		} catch (Exception ex) {
			throw ex;
		}
		return vehicleObject;
	}

}
