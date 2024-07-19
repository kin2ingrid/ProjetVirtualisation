package bf.ibam.vagrantFront.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import bf.ibam.vagrantFront.model.Author;
import bf.ibam.vagrantFront.service.AuthorService;

@Controller
public class AuthorController {
	
	@Autowired
	private AuthorService authorService;


  @GetMapping("/auteurs")
     public String getAllAuthors(Model model) {

         model.addAttribute("listAuthors", authorService.getlistAuteur());
         model.addAttribute("newAuthor", new Author());
         return "authorList";
     }
    	
	
	
  @GetMapping("/new")
  public String showNewAuthorForm(Model model) {
      model.addAttribute("author", new Author());
      return "authorForm";
  }

  @PostMapping("saveAuthor")
  public String saveAuthor(@ModelAttribute Author author) {
      System.out.println(author);
      System.out.println(author.getPrenom());
      authorService.save(author);
      return "redirect:/auteurs";
  }

  @GetMapping("/editAuthor")
  public String showEditBookForm(@RequestParam(name = "id") Long id, Model model) {
      Author auteur = authorService.findById(id);//.orElseThrow(() -> new RuntimeException("ex"));
      model.addAttribute("author", auteur);
      return "authorForm";
  }

  @GetMapping("/{id}")
  public String getAuthorById(@PathVariable Long id, Model model) {
      model.addAttribute("author", authorService.findById(id));//.orElse(new Author()));
      return "author-form";
  }


  @PostMapping("/deleteAuthor")
  public String deleteAuthor(@RequestParam(name = "id") Long id) {
      authorService.deleteById(id);
      return "redirect:/auteurs";
  }
}
