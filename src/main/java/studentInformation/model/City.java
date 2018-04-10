package studentInformation.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity(name = "cities")
public class City implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="city_id")
	private int cityId;
	
	@Column(name="city_name")
	private String cityName;
	
	@Column(name="city_country_code")
	private String countryCode;

	@Column(name="city_population")
	private int cityPopulation;

	

	public int getCity_id() {
		return cityId;
	}

	public void setCity_id(int city_id) {
		this.cityId = city_id;
	}

	public String getCity_name() {
		return cityName;
	}

	public void setCity_name(String city_name) {
		this.cityName = city_name;
	}

	public String getCity_country_code() {
		return countryCode;
	}

	public void setCity_country_code(String city_country_code) {
		this.countryCode = city_country_code;
	}

	public int getCity_population() {
		return cityPopulation;
	}

	public void setCity_population(int city_population) {
		this.cityPopulation = city_population;
	}

	public Set<Student> getStudents() {
		return students;
	}

	public void setStudents(Set<Student> students) {
		this.students = students;
	}

	@OneToMany(mappedBy = "cities")
	private Set<Student> students;

	@Override
	public String toString() {
		return "City [City_Id=" + cityId + ", Name =" + cityName + ", Country Code=" + countryCode + ", Population=" + cityPopulation + "]";
	}

}
