package bf.ibam.vagrantFront.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import bf.ibam.vagrantFront.model.Author;
import bf.ibam.vagrantFront.model.Book;
import bf.ibam.vagrantFront.service.AuthorService;
import bf.ibam.vagrantFront.service.BookServive;



@Controller
public class BookController {

@Autowired
private BookServive bookService;

@Autowired 
private AuthorService authorService;
	
	
	
	    @GetMapping("/")
    public String getAllBooksAndAuthors(Model model) {
        List<Book> listBooks = bookService.getlistBook();
        List<Author> authors = authorService.getlistAuteur();
        model.addAttribute("listBooks", listBooks);
        model.addAttribute("authors", authors);
        model.addAttribute("newBook", new Book());
        return "booksAndAuthors";

    }
	    
	    
    /**
     * affiche la liste des livres

     */
    @GetMapping("/livre")
    public String getAllBooks(Model model) {
       List<Book> listBooks = bookService.getlistBook();
        List<Author> authors = authorService.getlistAuteur();
        model.addAttribute("listBooks", listBooks);
        model.addAttribute("authors", authors);
       model.addAttribute("newBook", new Book());
        return "books";

         }
    /**
     * affiche le formulaire de saisie des livres

     */
    @GetMapping("/showNewBookForm")
    public String showNewBookForm(Model model) {
    	 model.addAttribute("book", new Book());
         model.addAttribute("authors", authorService.getlistAuteur());
        return "book_form";
    }
    
    /**
     * affiche le formulaire d'edition des livres

     */
    
   @GetMapping("/edit")
    public String showEditBookForm(@RequestParam(name = "id") Long id, Model model) {
        //recuperer le livre de la bd
        Book  book = bookService.findById(id).orElseThrow(()-> new RuntimeException("ex"));
        model.addAttribute("book", book);
        return "book_form";
    }
    
    
    
    /**
     * creation d'un nouveau  livres

     */
   
    @PostMapping("/saveBook")
    public String saveOrUpdateBook(@ModelAttribute Book book) {
        bookService.save(book);
        return "redirect:/livre";
    }
    

    /**
     * suppression d'un livre

     */

@PostMapping("/deleteBook")
public String deleteBook(@RequestParam(name = "id") Long id) {
    bookService.deleteById(id);
    return "redirect:/livre";
}

}