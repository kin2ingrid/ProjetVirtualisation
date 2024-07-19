package bf.ibam.vagrantApi.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "book")
public class Book {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "titre")
    private String titre;
    @Column(name = "isbn")
    private String isbn;
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "author_id")
    @JsonManagedReference
    private Author author;
   
    @Override
    //excluant les références aux autres classes dans les méthodes pour eviter la la récursion
    public String toString() {
        return "Book{id=" + id + ", titre='" + titre + "', isbn='" + isbn + "'}";
    }


}
