package bf.ibam.vagrantFront.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Book {
   private Long id;
    private String titre;
    private String isbn;
    private Author author;


   }
