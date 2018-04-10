package studentInformation.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import studentInformation.dao.BookRepository;
import studentInformation.model.Book;
import studentInformation.service.BookService;

@Service(value = "bookService")
public class BookServiceImpl implements BookService {

	@Autowired
	public BookRepository bookRepository;

	@Override
	public List<Book> getAll() {
		List<Book> books = new ArrayList<>();
		try {
			for (Book book : bookRepository.findAll()) {
				books.add(book);
			}
		} catch (Exception e) {
			throw e;
		}
		return books;
	}

	@Override
	public Book save(Book book) throws Exception {
		return bookRepository.save(book);
	}

}
