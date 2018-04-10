package studentInformation.service;

import java.util.List;

import studentInformation.model.Book;

public interface BookService {

	/*
	 * Find all the books.
	 * @param None
	 * @Return List of all the book's objects.
	 */
	public List<Book> getAll();
	
	/*
	 * Save the Book object
	 * @param Book Object
	 * @Return Book's object.
	 */
	public Book save(Book book) throws Exception;
}
