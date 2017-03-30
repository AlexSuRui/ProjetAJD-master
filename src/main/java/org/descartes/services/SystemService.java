/**
 * @author: RuiSU, githbu:alexgreen
 */
package org.descartes.services;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.descartes.domain.Article;
import org.descartes.domain.Commentaire;
import org.descartes.domain.Compte;
//import org.descartes.factory.Factory;
//import org.descartes.factory.IFactory;

public class SystemService implements IService {

	EntityManager entityManager;
//	IFactory factory;

	public SystemService() {
		// TODO Auto-generated constructor stub
		super();
//		this.factory = new Factory();
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("manager1");
		entityManager = emf.createEntityManager();
		
	}
	/**
	 * find a compte with it's identifiant
	 * @param identifant:String
	 */
	@Override
	public Compte findCompte(String identifiant) {
		// TODO Auto-generated method stub
		List<?> liste = entityManager.createQuery("SELECT p FROM Compte p WHERE p.identifiant LIKE :identifiantCompte")
				.setParameter("identifiantCompte",identifiant).getResultList();
		int x = liste.size();
		System.out.println(x);
		return (Compte) liste.get(0);

	}

	/**
	 * find all comptes
	 */
	@Override
	public List<?> findAll() {
		// TODO Auto-generated method stub
		List<?> liste = entityManager.createQuery("SELECT p FROM Compte p").getResultList();
		return liste;
	}
	/**
	 * create a new compte
	 * @param identifant, password
	 */
	@Override
	public Compte addCompte(String identifiant, String password) {
		// TODO Auto-generated method stub
		EntityTransaction tx = entityManager.getTransaction();
		tx.begin();
		Compte p = new Compte(identifiant, password);		
		entityManager.persist(p);
//		this.addArticle("My first blog",p );
		tx.commit();
		return p;

	}
	/**
	 * modify password
	 * unused
	 */
	@Override
	public void modifyPassword(String identifiant, String password) {
		// TODO Auto-generated method stub
		Compte tmp = findCompte(identifiant);
		tmp.setPassword(password);
		EntityTransaction tx = entityManager.getTransaction();
		tx.begin();
		entityManager.merge(tmp);
		tx.commit();
	}

	/**
	 * get all blogs
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Article> getAllArticles() {
		// TODO Auto-generated method stub
		List<?> liste = entityManager.createQuery("SELECT a FROM Article a").getResultList();
		return (List<Article>) liste;
	}

	
	
	@Override
	public Article findArticle(String title) {
		// TODO Auto-generated method stub
		List<?> liste = entityManager.createQuery("SELECT p FROM Article p WHERE p.title LIKE :titleArticle")
				.setParameter("titleArticle", title).getResultList();
		return (Article) liste.get(0);
	}

	/**
	 * get the auteur with the title
	 * 
	 */
	@Override
	public Compte getAuteur(String title) {
		// TODO Auto-generated method stub
		Article tmp = this.findArticle(title);

		return tmp.getAuteur();
	}

	/**
	 * add a blog
	 * @param title, auteur, text
	 */
	@Override
	public void addArticle(String title, Compte auteur, String text) {
		// TODO Auto-generated method stub
		EntityTransaction tx = entityManager.getTransaction();
		tx.begin();
		Article p = new Article(title, auteur,text);
		List<Article> list = new ArrayList<Article>();
		list = auteur.getArticles();
		list.add(p);
		auteur.setArticles(list);
		entityManager.persist(p);
		entityManager.merge(auteur);
		tx.commit();
	}
	/**
	 * Unused 
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Article> getAllArticlesByAuteur(Compte auteur) {
		List<?> liste = entityManager.createQuery("SELECT p FROM Article p WHERE p.compte_id LIKE :auteurArticle")
				.setParameter("auteurArticle", auteur.getId()).getResultList();
		// TODO Auto-generated method stub
		return  null;
	}
	/**
	 * Unused 
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Commentaire> findCommentaireByArticle(Article article) {
		// TODO Auto-generated method stub
		List<Commentaire> liste = entityManager.createQuery("SELECT p FROM Commentaire p WHERE p.Article_id LIKE :auteurArticle")
				.setParameter("auteurArticle", article.getId()).getResultList();
		return liste;
	}

	/**
	 * add a comment to a blog
	 */
	@Override
	public void addCommentaire(String text, Compte auteur, Article article) {
		// TODO Auto-generated method stub
		EntityTransaction tx = entityManager.getTransaction();
		tx.begin();
		Commentaire comment = new Commentaire(text,auteur,article);
		List<Commentaire> list = new ArrayList<Commentaire>();
		list = article.getComments();
		list.add(comment);
		article.setComments(list);
		entityManager.persist(comment);
		entityManager.persist(article);
		tx.commit();
			
	}

}
