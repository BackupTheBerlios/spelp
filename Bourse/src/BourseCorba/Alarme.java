package BourseCorba;


/**
* BourseCorba/Alarme.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from bourse.idl
* mardi 13 mai 2008 19 h 17 CEST
*/

public final class Alarme implements org.omg.CORBA.portable.IDLEntity
{
  public short type = (short)0;
  public double seuil = (double)0;
  public int idTitre = (int)0;

  public Alarme ()
  {
  } // ctor

  public Alarme (short _type, double _seuil, int _idTitre)
  {
    type = _type;
    seuil = _seuil;
    idTitre = _idTitre;
  } // ctor

} // class Alarme
