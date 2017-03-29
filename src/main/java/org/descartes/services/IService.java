/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.descartes.services;

import java.util.List;
import org.descartes.domain.Article;
import org.descartes.domain.Compte;
/**
 *
 * @author alexgreen
 */
public interface IService {
    public abstract List<Article> getAllArticles();
	public abstract Compte getAuteur(String title);
	public abstract Article findArticle(String title);
	public abstract void addArticle( String title,Compte auteur,String text);
	
	public abstract Compte findCompte(String identifiant); 
	public abstract List<?> findAll();
	public abstract void addCompte(String identifiant, String password);
	public abstract void modifyPassword(String identifiant, String password);
}
