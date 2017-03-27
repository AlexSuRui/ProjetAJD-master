package org.descartes.factory;

import org.descartes.domain.Article;
import org.descartes.domain.Compte;

public class Factory implements IFactory {

	@Override
	public Compte creatCompte(String identifiant, String password) {
		// TODO Auto-generated method stub
		return new Compte(identifiant,password);
	}

	@Override
	public Article creatArticle(String title, Compte auther, String text) {
		// TODO Auto-generated method stub
		return new Article(title, auther, text);
	}

}
