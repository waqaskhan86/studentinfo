package studentInformation.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

@Entity(name = "students")
//@NamedQuery(name = "student.findByLastName", query = "SELECT s FROM students s WHERE lower(s.student_last_name) = lower(?1)")
public  class Student implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="student_id")
	private int studentId;
	@NotNull
	@Column(name="student_first_name")
	private String firstName;
	@NotNull
	@Column(name="student_last_name")
	private String lastName;
	@NotNull
	@Column(name="student_father_name")
	private String fatherName;
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name="student_dob")
	private Date studentDOB;

	@ManyToOne
	@JoinColumn(name = "student_city_id")
	private City cities;

	public Student() {
	}

	public Student(String student_first_name, String student_last_name, String student_father_name, Date student_dob) {
		super();
		this.firstName = student_first_name;
		this.lastName = student_last_name;
		this.fatherName = student_father_name;
		this.studentDOB = student_dob;
	}

	public int getStudent_id() {
		return studentId;
	}

	public void setStudent_id(int student_id) {
		this.studentId = student_id;
	}

	public String getStudent_first_name() {
		return firstName;
	}

	public void setStudent_first_name(String student_first_name) {
		this.firstName = student_first_name;
	}

	public String getStudent_last_name() {
		return lastName;
	}

	public void setStudent_last_name(String student_last_name) {
		this.lastName = student_last_name;
	}

	public String getStudent_father_name() {
		return fatherName;
	}

	public void setStudent_father_name(String student_father_name) {
		this.fatherName = student_father_name;
	}

	public Date getStudent_dob() {
		return studentDOB;
	}

	public void setStudent_dob(Date student_dob) {
		this.studentDOB = student_dob;
	}

	public City getCity() {
		return cities;
	}

	public void setCity(City city) {
		this.cities = city;
	}

	@Override
	public String toString() {
		return "Student [student_id=" + studentId + ", student_first_name=" + firstName
				+ ", student_last_name=" + lastName + ", student_father_name=" + fatherName
				+ ", student_dob=" + studentDOB + "]";
	}

}
