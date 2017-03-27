/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.descartes.services;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.descartes.domain.Article;
import org.descartes.domain.Compte;
import org.descartes.factory.Factory;
import org.descartes.factory.IFactory;

public class SystemService implements ICompteService, IArticleService {

	EntityManager entityManager;
	IFactory factory;

	public SystemService() {
		// TODO Auto-generated constructor stub
		super();
		this.factory = new Factory();
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("manager1");
		entityManager = emf.createEntityManager();
//		this.addCompte("RuiSU", "12345");
	}

	@Override
	public Compte findCompte(String identifiant) {
		// TODO Auto-generated method stub
		List<?> liste = entityManager.createQuery("SELECT p FROM Compte p WHERE p.identifiant LIKE :identifiantCompte")
				.setParameter("identifiantCompte", identifiant).getResultList();
		return (Compte) liste.get(0);

	}

	@Override
	public List<?> findAll() {
		// TODO Auto-generated method stub
		List<?> liste = entityManager.createQuery("SELECT p FROM Compte p").getResultList();
		return liste;
	}

	@Override
	public void addCompte(String identifiant, String password) {
		// TODO Auto-generated method stub
		EntityTransaction tx = entityManager.getTransaction();
		tx.begin();
		Compte p = factory.creatCompte(identifiant, password);		
		entityManager.persist(p);
//		this.addArticle("My first blog",p );
		tx.commit();

	}

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

	@SuppressWarnings("unchecked")
	@Override
	public List<Article> findArticles() {
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

	@Override
	public Compte getAuteur(String title) {
		// TODO Auto-generated method stub
		Article tmp = this.findArticle(title);

		return tmp.getAuteur();
	}

	@Override
	public void addArticle(String title, Compte auteur, String text) {
		// TODO Auto-generated method stub
		EntityTransaction tx = entityManager.getTransaction();
		tx.begin();
		Article p = factory.creatArticle(title, auteur,text);
		entityManager.persist(p);
		tx.commit();
	}

}
