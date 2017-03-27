package org.descartes.factory;

import org.descartes.domain.Article;
import org.descartes.domain.Compte;

public interface IFactory {
	
	Compte creatCompte(String identifiant, String password);
	Article creatArticle(String title,Compte auther, String text );
}
