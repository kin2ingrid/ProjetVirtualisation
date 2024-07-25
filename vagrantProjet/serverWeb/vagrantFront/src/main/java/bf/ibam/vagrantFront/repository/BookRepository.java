package bf.ibam.vagrantFront.repository;

import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import bf.ibam.vagrantFront.model.Book;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
public class BookRepository {
public final String ApiUrl = "http://192.168.1.3:8082/apiBook"; //chemin de l'api a atteindre

private final RestTemplate rest = new RestTemplate();	//permet la communication avec l'appi

private static final Logger logger = LoggerFactory.getLogger(BookRepository.class);

public List<Book> getAllBook() {
    String url = ApiUrl + "/listLivres"; // URL du point de terminaison

    try {
        ResponseEntity<String> rawResponse = rest.exchange(
            url,
            HttpMethod.GET,
            null,
            String.class
        );

        // Log the raw JSON response
        String jsonResponse = rawResponse.getBody();
        System.out.println("Raw JSON response: " + jsonResponse);

        // Parse the JSON response into a list of Book objects
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(jsonResponse, new TypeReference<List<Book>>() {});

    } catch (Exception e) {
        e.printStackTrace();
        return Collections.emptyList();
    }
}



	//creation dans la bd
	public void enregistrerLivre(Book book) {
		String url = ApiUrl + "/enregistrer"; //Url du endpoint appelle
		HttpEntity<Book> request = new HttpEntity<>(book);
		ResponseEntity<Book> resp = rest.exchange(
				url, 
				HttpMethod.POST, 
				request, //element à envoyer
				Book.class); //element contenant la reponse du la valeur du type qui est dans l'api
	}
	
	//MAJ
	public Book updateAuthor(Long id, Book book) {
		String url = ApiUrl +"/"+id; //Url du endpoint appelle
		HttpEntity<Book> request = new HttpEntity<>(book);
		ResponseEntity<Book> resp = rest.exchange(
				url, 
				HttpMethod.PUT, 
				request, //element à envoyer
				Book.class);
		return resp.getBody();
	}
	
	
	//delete
	public void deleteBook(Long id) {
		String url = ApiUrl +"/"+id; //Url du endpoint appelle
		ResponseEntity<Void> resp = rest.exchange(
				url, 
				HttpMethod.DELETE, 
				null, //element à envoyer
				Void.class);
	}
	
	  public Book getBookById(Long id) {
	        String url = ApiUrl + "/" + id;
	        ResponseEntity<Book> response = 
	        		rest.getForEntity(url, Book.class);
	        return response.getBody();
	    }


}
