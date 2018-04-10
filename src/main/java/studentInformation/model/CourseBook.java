package studentInformation.model;

import javax.persistence.Entity;

@Entity
public class CourseBook extends Book {

	private boolean isCourseBook;

	public boolean isCourseBook() {
		return isCourseBook;
	}

	public void setCourseBook(boolean isCourseBook) {
		this.isCourseBook = isCourseBook;
	}
	
	
}
