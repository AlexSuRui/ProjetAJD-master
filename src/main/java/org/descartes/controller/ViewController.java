/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.descartes.controller;

import org.descartes.domain.Compte;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ViewController {
	
	RestWebService implement = new RestWebService();
	
	@RequestMapping(value = "")
	public String greeting(Model model){
		Compte compte = implement.getCompte("RuiSU");
		model.addAttribute("compte", compte);
		return "index";
	}
	
	@RequestMapping(value = "/welcome")
	public String welcome(Model model){
		Compte compte = implement.getCompte("RuiSU");
		model.addAttribute("compte",compte);
		return "/welcome/welcome";
	}
}
