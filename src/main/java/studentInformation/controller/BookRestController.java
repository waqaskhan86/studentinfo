package studentInformation.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import studentInformation.model.Book;
import studentInformation.model.CourseBook;
import studentInformation.model.ReferenceBook;
import studentInformation.service.impl.BookServiceImpl;

@RestController
@RequestMapping("/books")
public class BookRestController {
	
	@Autowired
	private BookServiceImpl bookService;

	@GetMapping("/")
	public List<Book> allBooks() throws Exception {
		try {
			return bookService.getAll();
		} catch (Exception e) {
			throw new Exception("Did not find any books.");
		}
	}

	@PostMapping("/add")
	public Book createBook(@RequestBody Book book) throws Exception {
		Book bookObject = new Book();
		bookObject.setBookName(book.getBookName());
		bookObject.setBookTitle(book.getBookTitle());
		
		CourseBook cBook = new CourseBook();
		cBook.setBookName(book.getBookName());
		cBook.setBookTitle(book.getBookTitle());
		cBook.setCourseBook(true);
		
		ReferenceBook rBook = new ReferenceBook();
		rBook.setBookName(book.getBookName());
		rBook.setBookTitle(book.getBookTitle());
		rBook.setReferenceBook(true);
		
		try {
			bookService.save(rBook);
		} catch (Exception ex) {
			throw ex;
		}
		return bookObject;
	}

}
