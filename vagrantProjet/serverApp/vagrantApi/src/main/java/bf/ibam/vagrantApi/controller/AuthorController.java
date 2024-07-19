package bf.ibam.vagrantApi.controller;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import bf.ibam.vagrantApi.model.Author;
import bf.ibam.vagrantApi.service.AuthorService;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("apiAuteur")

public class AuthorController {

    @Autowired
    private AuthorService authorService;


    @GetMapping("/listAuteurs")
    public  List<Author> getListAuthor() {
    	return authorService.findAll();
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Author> getAuthorById(@PathVariable Long id) {
        Optional<Author> author = authorService.findById(id);
        if (author.isPresent()) {
            return ResponseEntity.ok(author.get());
        } else {
           return ResponseEntity.notFound().build();
    }
    }    
    
    @PostMapping("/enregistrer")
    public ResponseEntity<Author> createAuthor(@RequestBody Author author) {
      //  System.out.println("Received book: " + book.getBook());
        System.out.println("Received bookLabel: " + author.getNom());
      //  System.out.println("Received author ID: " + book.getAuthor().getAuthor());
        Author creatAuthor = authorService.save(author);
        return ResponseEntity.ok(creatAuthor);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Author> updateAuthor(@PathVariable Long id, 
    		@RequestBody Author authorDetails) {
    	           return ResponseEntity.ok(authorService.save(authorDetails));
         }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
    	authorService.deleteById(id);
        return ResponseEntity.noContent().build();
   }  
    
}


