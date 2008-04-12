package BourseCorba;


/**
* BourseCorba/AdminOperations.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from bourse.idl
* mardi 8 avril 2008 19 h 04 CEST
*/

public interface AdminOperations 
{

  // retourne l identifiant de l utilisateur
  int insererUtilisateur (String nom, String prenom, String login, String mdp, int role) throws BourseCorba.ServerException;

  // retourne l identifiant du compte
  int creerCompte (double cash);
  void affecterCompte (int idUser, int idCompte) throws BourseCorba.ServerException;
  BourseCorba.CompteAdmin[] getComptes ();
  void ajoutCash (int idCompte, double valeur) throws BourseCorba.ServerException;
  void diminuerCash (int idCompte, double valeur) throws BourseCorba.ServerException;
} // interface AdminOperations
