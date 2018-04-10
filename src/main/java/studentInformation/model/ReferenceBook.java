package studentInformation.model;

import javax.persistence.Entity;

@Entity
public class ReferenceBook extends Book {

	private boolean isReferenceBook;

	public boolean isReferenceBook() {
		return isReferenceBook;
	}

	public void setReferenceBook(boolean isReferenceBook) {
		this.isReferenceBook = isReferenceBook;
	}
	
	
	
}
