package BourseCorba;


/**
* BourseCorba/CompteOperations.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from bourse.idl
* mardi 13 mai 2008 19 h 17 CEST
*/

public interface CompteOperations 
{

  // retourne la liste des titres disponibles a l'achat
  BourseCorba.Titre[] getTitres ();
  BourseCorba.TitreDetaille getTitre (int id) throws BourseCorba.ServerException;
  double getMontantPortefeuille ();
  BourseCorba.ActionMontant[] getActionsAvecMontant ();

  // retourne l'id de l'action achetee a partir du titre
  int acheterAction (int idTitre) throws BourseCorba.ServerException;
  void vendreAction (int idAction) throws BourseCorba.ServerException;
  void positionnerAlarme (BourseCorba.Alarme a);
  void enregistrerClientAlarme (BourseCorba.ClientAlarme ca);
  double cash ();
} // interface CompteOperations
