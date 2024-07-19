package bf.ibam.vagrantFront.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bf.ibam.vagrantFront.model.Author;
import bf.ibam.vagrantFront.repository.AuthorRepository;
import lombok.Data;

@Data
@Service
public class AuthorService {
	
	@Autowired
	private AuthorRepository authorRepo;
	
	
	public List<Author> getlistAuteur() { 
			 return authorRepo.getAllAuthor();
		}	
	
	public void save (Author author) {
		authorRepo.enregistrerAuteur(author);
	}

	public void deleteById(Long id){
		authorRepo.deleteAuthor(id);
	}
	
	public Author findById(Long id){
		return authorRepo.getAuthorById(id);
	}
	
	  public Author getAuthorById(Long id) {
	        return authorRepo.findById(id).orElseThrow(() -> new RuntimeException("Author not found"));
	    }
	
/*
    public Author findOrCreateAuthorByName(String name) {
    	 return authorRepo.findByName(name).orElseGet(() -> {
             Author newAuthor = new Author();
             newAuthor.setNom(name);
             return authorRepo.enregistrerAuteur(newAuthor);
         });
    }*/

}
