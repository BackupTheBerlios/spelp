package BourseCorba;


/**
* BourseCorba/ActionMontant.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from bourse.idl
* dimanche 1 juin 2008 16 h 45 CEST
*/

public final class ActionMontant implements org.omg.CORBA.portable.IDLEntity
{
  public String libelleTitre = null;
  public int idAction = (int)0;
  public double montant = (double)0;

  public ActionMontant ()
  {
  } // ctor

  public ActionMontant (String _libelleTitre, int _idAction, double _montant)
  {
    libelleTitre = _libelleTitre;
    idAction = _idAction;
    montant = _montant;
  } // ctor

} // class ActionMontant
