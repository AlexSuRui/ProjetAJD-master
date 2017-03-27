package org.descartes.controller;

import java.util.List;

import org.descartes.domain.Article;
import org.descartes.domain.Compte;
import org.descartes.services.SystemService;
import org.descartes.services.ICompteService;
import org.descartes.services.IService;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController 
@EnableAutoConfiguration 
public class RestWebService {
	
//	ServicePersonne servicePersonne = new ServicePersonne();
	IService serviceSystem = new SystemService();
	@RequestMapping(value = "/compte", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public List<?> getComptes(){
		return serviceSystem.findAll();
	}
	
	@RequestMapping(value = "/compte/{identifiant}", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public Compte getCompte(@PathVariable("identifiant") String identifiant){
		return serviceSystem.findCompte(identifiant);
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
	
	@RequestMapping(value = "/article", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public List<?> getArticles(){
		return serviceSystem.findArticles();
	}
	
	@RequestMapping(value = "/article/{title}", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public Article getArticle(String title){
		return serviceSystem.findArticle(title);
	}
	
	@RequestMapping(value = "/article/{auteur}", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public Compte getArticleAuteur(String title){
		return serviceSystem.getAuteur(title);
	}
	
	@RequestMapping(value ="/article", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.OK)
	public void postArticl(@RequestBody String title, Compte auteur, String text){
		System.out.println("The title of articl is" +title+ ", author is "+auteur );
		serviceSystem.addArticle(title, auteur,text);
	}
}
