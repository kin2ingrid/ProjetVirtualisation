/**
 * 
 */
package bf.ibam.vagrantApi.model;

import com.fasterxml.jackson.annotation.JsonBackReference;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * entite auteur
 */
@Entity 
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="Author")
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)    private Long id;
    @Column(name = "code")
    private String code;
    @Column(name = "nom")
    private String nom;
    @Column(name = "prenom")
    private String prenom;
    @Column(name = "nationalite")
  private String nationalite;
   @OneToOne(mappedBy = "author", fetch = FetchType.LAZY)
   @JsonBackReference
    private Book book;
   

   @Override
   public String toString() {
       return "Author{id=" + id + ", code='" + code + "', nom='" + nom + "', prenom='" + prenom + "', nationalite='" + nationalite + "'}";
   }
   
}
