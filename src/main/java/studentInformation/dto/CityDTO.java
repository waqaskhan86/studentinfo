package studentInformation.dto;

public class CityDTO {
	
	private int cityId;
	private String cityName;
	private String cityCountryCode;
	private int cityPopulation;
	
	public int getCityId() {
		return cityId;
	}
	public void setCityId(int cityId) {
		this.cityId = cityId;
	}
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	public String getCityCountryCode() {
		return cityCountryCode;
	}
	public void setCityCountryCode(String cityCountryCode) {
		this.cityCountryCode = cityCountryCode;
	}
	public int getCityPopulation() {
		return cityPopulation;
	}
	public void setCityPopulation(int cityPopulation) {
		this.cityPopulation = cityPopulation;
	}

}
