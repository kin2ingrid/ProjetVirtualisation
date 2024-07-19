package bf.ibam.vagrantFront.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bf.ibam.vagrantFront.model.Book;
import bf.ibam.vagrantFront.repository.BookRepository;
import lombok.Data;

@Data
@Service
public class BookServive {
	
	@Autowired
	private BookRepository bookRepo;
	
	
	public List<Book> getlistBook() { 
		return bookRepo.getAllBook();
	}	
	
public void save (Book book) {
	bookRepo.enregistrerLivre(book);
}

public void deleteById(Long id){
	bookRepo.deleteBook(id);
}


public Optional<Book> findById(Long id){
	return Optional.of(bookRepo.getBookById(id));
}

}
