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

Nous avons utilisé thymeleaf comme le web template et changé le port par 8010, donc vous pouvez l'ouvrir directement avec localhost:8010

