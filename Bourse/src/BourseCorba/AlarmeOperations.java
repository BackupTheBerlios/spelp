package BourseCorba;


/**
* BourseCorba/AlarmeOperations.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from bourse.idl
* mardi 8 avril 2008 19 h 04 CEST
*/

public interface AlarmeOperations 
{
  short type ();
  void type (short newType);
  double seuil ();
  void seuil (double newSeuil);
  void notifie ();
} // interface AlarmeOperations
