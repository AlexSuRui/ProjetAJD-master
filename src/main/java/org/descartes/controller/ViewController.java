/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.descartes.controller;

import org.descartes.domain.Article;
import org.descartes.domain.Compte;
//import org.descartes.factory.Factory;
//import org.descartes.factory.IFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ViewController {
	
	RestWebService implement = new RestWebService();
//	IFactory factory = new Factory();
	
//	@RequestMapping(value = "")
//	public String greeting(Model model){
//		Compte compte = implement.getCompte("RuiSU");
//		model.addAttribute("compte", compte);
//		return "index";
//	}
//	
//	@RequestMapping(value = "/welcome")
//	public String welcome(Model model){
//		Compte compte = implement.getCompte("RuiSU");
//		model.addAttribute("compte",compte);
//		return "/welcome/welcome";
//	}
//	
//	@RequestMapping(value = "/addArticle")
//	public String addArticle(Model model){
//		model.addAttribute("article", new Article());
//		return "/articles/addArticle";
//	}
//	
//	@RequestMapping(value = "/article/{title}")
//	public String showArticle(Model model, @PathVariable("title") String title){
//		Compte compte = implement.getCompte("RuiSU");
//		Article article = implement.getArticle(title);
//		model.addAttribute("article",article);
//		return "/article/showArticle";
//	}
	
}
