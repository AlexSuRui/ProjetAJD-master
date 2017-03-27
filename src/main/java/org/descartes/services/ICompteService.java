/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.descartes.services;

/**
 *
 * @author alexgreen
 */
import java.util.List;

import org.descartes.domain.Compte;

public interface ICompteService extends IService{

	public abstract Compte findCompte(String identifiant); 
	public abstract List<?> findAll();
	public abstract void addCompte(String identifiant, String password);
	public abstract void modifyPassword(String identifiant, String password);
}	