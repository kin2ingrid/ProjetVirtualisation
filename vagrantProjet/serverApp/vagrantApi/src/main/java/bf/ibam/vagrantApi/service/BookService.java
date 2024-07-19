package bf.ibam.vagrantApi.service;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import bf.ibam.vagrantApi.model.Author;
import bf.ibam.vagrantApi.model.Book;
import bf.ibam.vagrantApi.repository.AuthorRepository;
import bf.ibam.vagrantApi.repository.BookRepository;

import java.util.List;
import java.util.Map;
import java.util.Optional;
@Data
@Service
public class BookService {
	
@Autowired
  private final BookRepository bookRepository;

@Autowired 
   private AuthorRepository authorRepository;
 


    public List<Book> findAll() { 
        List<Book> booksFind = bookRepository.findAll();
        System.out.println("***detailBook***: " + booksFind);
       // logger.info("Fetched listBook: " + booksFind);

        return booksFind;
    }

    public Optional<Book> findById(Long id) {
        return bookRepository.findById(id);
    }

    @Transactional
    public Book save(Book book) { 
    	 // Enregistrer l'auteur s'il n'existe pas déjà
    	  if (book.getAuthor() != null && book.getAuthor().getId() != null) {
              Author author = authorRepository.findById(book.getAuthor().getId())
                              .orElseThrow(() -> new RuntimeException("Author not found"));
              book.setAuthor(author);
          }
        // Enregistrer le livre avec l'auteur
        bookRepository.save(book);

        return bookRepository.save(book);
    }
    
 
    public void deleteById(Long id) {
        bookRepository.deleteById(id);
    }
    
    
    public Optional<Book> getBookById(Long id) {
       Optional<Book> bookOptional = bookRepository.findById(id);
        if (bookOptional.isPresent()) {
            return bookOptional;
        } else {
            throw new RuntimeException("Book not found for this id " + id);
        }
    }

    
    public Book updateBook(Long id, Book bookDetails) {
       Optional<Book> bookOptional = bookRepository.findById(id);
       if (bookOptional.isPresent()) {
          Book book = bookOptional.get();
           book.setIsbn(bookDetails.getIsbn());
         book.setTitre(bookDetails.getTitre());
         //  book.setAuthor(bookDetails.getAuthor());
           return bookRepository.save(book);
        } else {
          throw new RuntimeException("Book not found for this id " + id);
        }
   }

}
