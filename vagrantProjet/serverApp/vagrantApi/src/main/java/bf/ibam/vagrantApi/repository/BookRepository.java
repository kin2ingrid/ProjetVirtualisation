package bf.ibam.vagrantApi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import bf.ibam.vagrantApi.model.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long>{
 

}
