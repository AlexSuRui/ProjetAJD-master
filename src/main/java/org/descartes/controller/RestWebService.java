package org.descartes.controller;
/*
 * @author: Rui SU
 * @description: This is the controller of this application using Thymeleaf as the html templates
 */
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.descartes.domain.Article;
import org.descartes.domain.Commentaire;
import org.descartes.domain.Compte;
import org.descartes.services.SystemService;
import org.eclipse.persistence.internal.sessions.coordination.corba.sun.CommandDataHelper;
//import org.descartes.services.ICompteService;
import org.descartes.services.IService;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.SessionAttributes;
//import org.springframework.web.bind.annotation.RestController;
//@RestController 

@SessionAttributes("user")
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
	public String indexAction(Model model, HttpSession session, HttpServletRequest request){
		List<Article> articles;
		Compte compte = (Compte) request.getSession().getAttribute("compte");		
		if(compte!=null){
			 articles = compte.getArticles();
			 System.out.println(articles);
		}else{
			 articles= null;
		}
		model.addAttribute("compte", compte);
		model.addAttribute("articles", articles);
		return "index";
	}
	/**
	 * Action login
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
	public String valideLogin(@RequestParam(value="identifiant") String identifiant, @RequestParam(value="password") String password, HttpSession session){
		System.out.println(identifiant);
		Compte compte = serviceSystem.findCompte(identifiant.substring(1));
		if(compte.getPassword().equals(password.substring(1))){
			session.setAttribute("compte", compte);
			return "redirect:/";
		}else
			return "redirect:/login";
	}
	
	 @RequestMapping(value = "/logout")
     public String logout(HttpSession session ) {
        session.invalidate();
        return "redirect:/login.html";
     }
	
	/**
	 * Action sign up
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/signup")
	public String signupAction(Model model){
		model.addAttribute("compte", new Compte());
		return "/comptes/signup";
	}
	/**
	 * 
	 */
	@RequestMapping(value = "/validSignup", method = RequestMethod.POST)
	public String valideSignup(@RequestParam(value="identifiant") String identifiant, @RequestParam(value="password") String password){
		Compte compte = serviceSystem.addCompte(identifiant.substring(1), password.substring(1));
		if(compte != null){
			return "redirect:/login";
		}else
			return "redirect:/signup";
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
		List<Commentaire> commentaires = article.getComments();
		model.addAttribute("article", article);
		model.addAttribute("compte", compte);
		model.addAttribute("commentaires", commentaires);
		model.addAttribute("commentaire", new Commentaire());
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
	public String postArticle(@RequestParam String title,@RequestParam String text,HttpSession session, HttpServletRequest request){
		Compte auteur = (Compte) request.getSession().getAttribute("compte");
		if(auteur!=null){
			serviceSystem.addArticle(title, auteur,text);
			return "redirect:/";
		}else{
			return "redirect:/addArticle";
		}
	}
	
	@RequestMapping(value ="/createCommentaire", method = RequestMethod.POST)
	public String postCommentaire(@RequestParam String text, HttpServletRequest request){
		Article article = serviceSystem.findArticle("example");
		Compte auteur  = (Compte)request.getSession().getAttribute("compte");
		if(auteur !=null){
			serviceSystem.addCommentaire(text, auteur, article);
			return "redirect:/article?title="+article.getTitle();
		}else
			return "redirect:/";
		
	}
}
