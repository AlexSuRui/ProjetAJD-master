/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.descartes.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Compte {

	String identifiant;
	String password;
	long id;
	String type;
	List<Article> articles;

	public Compte() {
		super();
	}

	public Compte(String identifiant, String password) {
		this.identifiant = identifiant;
		this.password = password;
	}
	
	public Compte(String identifiant, String password, String type){
		this.identifiant = identifiant;
		this.password = password;
		this.type = type;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getIdentifiant() {
		return identifiant;
	}

	public void setIdentifiant(String identifiant) {
		this.identifiant = identifiant;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "Compte [identifiant" + identifiant + "]";

	}
	@OneToMany(cascade = CascadeType.ALL)
	public List<Article> getArticles(){
		return articles;
	}
	public void setArticles(List<Article> articles){
		this.articles = articles;
	}

}
