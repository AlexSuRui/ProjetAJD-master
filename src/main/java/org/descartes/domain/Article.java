/**
 * @author alexgreen
 */
package org.descartes.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Article {
	
	Long id;
	String title;
	Compte auteur;
//	String date;
	String text;
	List<Commentaire> comments;
	
	public Article(){
		super();
	}
	
	public Article(String title){
		this.title = title;
//		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
//		Date date = new Date();
//		this.date = dateFormat.format(date);
	}
	
	public Article(Compte auteur){
		this.title ="default title";
		this.auteur = auteur;
//		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
//		Date date = new Date();
//		this.date = dateFormat.format(date);
	}
	
	public Article(String title, Compte auteur){
		this.title = title;
		this.auteur = auteur;
//		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
//		Date date = new Date();
//		this.date = dateFormat.format(date);
	}
	
	public Article(String title, Compte auteur, String text){
		this.title= title;
		this.auteur = auteur;
//		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
//		Date date = new Date();
//		this.date = dateFormat.format(date);
		this.text = text;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	@ManyToOne
	@JoinColumn(name="Compte_id")
	public Compte getAuteur(){
		return auteur;
	}
	
    public void setAuteur(Compte auteur){
		this.auteur = auteur;
	}
	
	public String getTitle(){
		return title;
	}
	
	public void setTitle(String title){
		this.title = title;
	}
	
//	public String getDate(){
//		return date;
//	}
	
//	public void setDate(String date){
//		this.date = date;
//	}
	
	public String getText(){
		return text;
	}
	
	public void setText(String text){
		this.text= text;
	}
	@Override
	public String toString() {
		return "Article [title" + title + "]";

	}
	@OneToMany(cascade = CascadeType.ALL)
	public List<Commentaire> getComments(){
		return comments;
	}
	
	public void setComments(List<Commentaire> comments){
		this.comments = comments;
	}
}
