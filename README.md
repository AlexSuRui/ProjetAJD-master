# ProjetAJD

Télécharger le  ZIP

Décompresser

Commande "gradle build" pour construire le projet

Commande "gradke eclipse" pour créer projet Eclipse

Importation sous Eclipse

Démarrer le serveur de base de données (hsqldb 1.8.*)  :

dans dossier lib d'hsqldb : java -cp hsqldb.jar org.hsqldb.Server

Démarrer l'outil d'inspection de la base (en mode serveur à la connexion) :

dans dossier lib d'hsqldb : java -cp hsqldb.jar org.hsqldb.util.DatabaseManager

Lancer le projet : le main est dans org.descartes.ProjetAjdApplication (run as java application)

J'ai utilisé thymeleaf comme le web template et changé le port par 8010, donc vous pouvez l'ouvrir directement avec 
localhost:8010

Ce site contient un système de gestion de compte (s'inscrire et se connecter) et un système de gestion d'article (créer un article ou chercher les articles)

Pour créer un article, il faut d'abord se connecter. Après connecter, les articles associés à l'utilisateur sont affichées sur le page d'accueil.

Les utilisateurs peuvent ajouter les commentaires sur les articles.
