package bf.ibam.vagrantFront.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import bf.ibam.vagrantFront.model.Author;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
public class AuthorRepository {
	public final String ApiUrl = "http://192.168.1.3:8082/apiAuteur"; //chemin de l'api a atteindre
	private RestTemplate rest =new RestTemplate();//permet la communication avec l'appi

	  public List<Author> getAllAuthor() {
	        String url = ApiUrl + "/listAuteurs"; // URL du endpoint

	        try {
	            ResponseEntity<List<Author>> resp = rest.exchange(
	                url,
	                HttpMethod.GET,
	                null,
	                new ParameterizedTypeReference<List<Author>>() {}
	            );
	            return resp.getBody();
	        } catch (Exception e) {
	            e.printStackTrace();
	            return null;
	        }
	    }


	//creation dans la bd
	public void enregistrerAuteur(Author author) {
		String url = ApiUrl + "/enregistrer"; //Url du endpoint appelle
		HttpEntity<Author> request = new HttpEntity<>(author);
		ResponseEntity<Author> resp = rest.exchange(
				url, 
				HttpMethod.POST, 
				request, //element à envoyer
				Author.class); //element contenant la reponse du la valeur du type qui est dans l'api
	}
	
	//MAJ
	public Author updateAuthor(Long id, Author author) {
		String url = ApiUrl +"/"+id; //Url du endpoint appelle
		HttpEntity<Author> request = new HttpEntity<>(author);
		ResponseEntity<Author> resp = rest.exchange(
				url, 
				HttpMethod.PUT, 
				request, //element à envoyer
				Author.class);
		return resp.getBody();
	}
	
	
	//delete
	public void deleteAuthor(Long id) {
		String url = ApiUrl +"/"+id; //Url du endpoint appelle
		ResponseEntity<Void> resp = rest.exchange(
				url, 
				HttpMethod.DELETE, 
				null, //element à envoyer
				Void.class);
	}
	
	//find by id
	
	  public Author getAuthorById(Long id) {
	        String url = ApiUrl + "/" + id;
	        ResponseEntity<Author> response = 
	        		rest.getForEntity(url, Author.class);
	        return response.getBody();
	    }


    public Optional<Author> findById(Long id){
    	 String url = ApiUrl + "/" + id;
	        ResponseEntity<Author> response = 
	        		rest.getForEntity(url, Author.class);
	        return Optional.of(response.getBody());
    }
	
	
}
