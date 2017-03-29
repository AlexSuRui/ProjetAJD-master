package org.descartes.domain;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

public class Commentaire {

	Long id;
	String text;
	Compte compte;
	Article article;
	
	public Commentaire(){
		super();
	}
	
	public Commentaire(String text, Compte compte, Article article){
		this.text = text;
		this.compte = compte;
		this.article = article;	
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId(){
		return this.id;
	}
	
	public void setId(Long id){
		this.id = id;
	}
	
	public String getText(){
		return this.text;
	}
	
	public void setText(String text){
		this.text =text;
	}
	
	public Compte getAuteur(){
		return this.compte;
	}
	
	public void setAuteur(Compte auteur){
		this.compte = auteur;
	}
	
	@ManyToOne
	@JoinColumn(name="Article_id")
	public Article getArticle(){
		return this.article;
	}
	
	public void setArticle(Article article){
		this.article = article;
	}
}
