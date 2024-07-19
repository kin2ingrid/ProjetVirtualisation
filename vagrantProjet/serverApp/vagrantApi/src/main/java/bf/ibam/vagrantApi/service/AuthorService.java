package bf.ibam.vagrantApi.service;

import lombok.Data;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bf.ibam.vagrantApi.model.Author;
import bf.ibam.vagrantApi.repository.AuthorRepository;

import java.util.List;
import java.util.Optional;
@Data
@Service
public class AuthorService {

    private static final Logger logger = LoggerFactory.getLogger(AuthorService.class);

    @Autowired
    private AuthorRepository authorRepository;

    
    public List<Author> findAll() {
        List<Author> authors = authorRepository.findAll();
        authors.forEach(author -> {
            //System.out.println("Author: " + author.getAuthor());
            System.out.println("AuthorLabel: " + author.getNom());
        });
        return authors;
//        List<Author> authors = authorRepository.findAll();
//        logger.info("Fetched Authors: " + authors);
//        return authors;
//
    }
    public Optional<Author> findById(Long id) {
        Optional<Author> author = authorRepository.findById(id);
        author.ifPresent(a -> {
//            System.out.println("Author: " + a.getAuthor());
//            System.out.println("AuthorLabel: " + a.getAuthorLabel());
        });
        return author;
     }

    public Author save(Author author) {
        return authorRepository.save(author);
    }

    public Author updateAuthor(Long id, Author authorDetails) {
        Optional<Author> authorOptional = authorRepository.findById(id);
        if (authorOptional.isPresent()) {
            Author author = authorOptional.get();
            author.setCode(authorDetails.getCode());
            author.setNom(authorDetails.getNom());
            author.setPrenom(authorDetails.getPrenom());
            author.setNationalite(authorDetails.getNationalite());
            return authorRepository.save(author);
        } else {
            return null;
        }
    }
    public void deleteById(Long id) {
        authorRepository.deleteById(id);
    }



}
