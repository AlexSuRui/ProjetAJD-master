package org.descartes.controller;
/*
 * @author: Rui SU
 * @description: This is the controller of this application using Thymeleaf as the html templates
 */
import java.util.List;

import org.descartes.domain.Article;
import org.descartes.domain.Compte;
import org.descartes.services.SystemService;
//import org.descartes.services.ICompteService;
import org.descartes.services.IService;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
//import org.springframework.web.bind.annotation.RestController;
//@RestController 
@Controller
@EnableAutoConfiguration 
public class RestWebService {
	
	IService serviceSystem = new SystemService();
		
	/**
	 * Index page
	 * @param model : a mode to pass variables
	 * @return index page
	 */
	@RequestMapping(value = "")
	public String indexAction(Model model){
		Compte compte = serviceSystem.findCompte("RuiSU");
		List<Article> articles = serviceSystem.getAllArticles();
		model.addAttribute("articles", articles);
		return "index";
	}
	/**
	 * @param model
	 */
	@RequestMapping(value = "/login")
	public String loginAction(Model model){
		model.addAttribute("compte", new Compte());
		return "/comptes/login";
	}
	/**
	 * Valid login
	 * Don't understand why the parameter received start always with a "," so only can start with the second char 
	 * @param identifiant
	 * @param password
	 * @return
	 */
	@RequestMapping(value = "/validLogin", method = RequestMethod.POST)
	public String getCompte(@RequestParam(value="identifiant") String identifiant, @RequestParam(value="password") String password){
		System.out.println(identifiant);
		Compte compte = serviceSystem.findCompte(identifiant.substring(1));
		if(compte.getPassword().equals(password.substring(1))){
			return "index";
		}else
			return "comptes/login";
	}
	
	/**
	 * To find and list all the users
	 * @param model : a mode to pass variables
	 * @return
	 */
	@RequestMapping(value = "/comptes", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public String getComptes(Model model){
		@SuppressWarnings("unchecked")
		List<Compte> comptes = (List<Compte>) serviceSystem.findAll();
		model.addAttribute("comptes", comptes);
		return "/comptes/showComptes";
		
	}
	
	@RequestMapping(value = "/compte", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.OK)
	public void postCompte(@RequestBody String identifiant, String password){
		System.out.println(identifiant);
		serviceSystem.addCompte(identifiant, password);
	}
	
	@RequestMapping(value = "/compte/{identifiant}", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.OK)
	public void modifyPassword(@RequestBody String identifiant, String password){
		System.out.println("your identifiant is" + identifiant);
		serviceSystem.modifyPassword(identifiant, password);
		
	}
	
	/**
	 * Find and list all articles
	 * @param model : a mode to pass variables
	 * @return return to a page with a table to show all articles.
	 */
	@RequestMapping(value = "/articles")
	public String showArticlesAction(Model model){
		List<Article> articles= serviceSystem.getAllArticles();
		model.addAttribute("articles", articles);
		return "/articles/allArticles";
	}
	
	/**
	 * Display the content of an article
	 * @param title: title of article (maybe will change in the future because title could be duplicated) 
	 * @param model : a mode to pass variables
	 * @return page showArticle to display the content
	 */
	@RequestMapping(value ="/article" ,method = RequestMethod.GET)
	public String getArticle(@RequestParam(value="title") String title, Model model){
		Article article =  serviceSystem.findArticle(title);
		Compte compte = article.getAuteur();
		model.addAttribute("article", article);
		model.addAttribute("compte", compte);
		return "/articles/showArticle";
	}
	
	@RequestMapping(value = "/article/{auteur}", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public Compte getArticleAuteur(String title){
		return serviceSystem.getAuteur(title);
	}
	
	@RequestMapping(value = "/addArticle")
	public String createArticleAction(Model model){
		model.addAttribute("article", new Article());
		return "/articles/addArticle";
	}
	
	/**
	 * add a new article
	 * @param title
	 * @param text
	 * @return
	 */
	@RequestMapping(value ="/createArticle", method = RequestMethod.POST)
	public String postArticle(@RequestParam String title,@RequestParam String text){
		Compte auteur = serviceSystem.findCompte("RuiSU");
		serviceSystem.addArticle(title, auteur,text);
		return "/index";
	}
}
