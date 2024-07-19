package bf.ibam.vagrantApi.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import bf.ibam.vagrantApi.model.Author;

@Repository
public interface AuthorRepository extends JpaRepository <Author, Long> {


}
