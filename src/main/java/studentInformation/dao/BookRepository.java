package studentInformation.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import studentInformation.model.Book;

@Repository
public interface BookRepository extends CrudRepository<Book,Integer>{
	

}
