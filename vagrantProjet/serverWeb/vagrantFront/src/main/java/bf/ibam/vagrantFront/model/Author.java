package bf.ibam.vagrantFront.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Author {
	 private Long id;
    private String code;
    private String nom;
    private String prenom;
    private String nationalite;
  }