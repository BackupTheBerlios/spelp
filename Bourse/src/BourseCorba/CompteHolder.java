package BourseCorba;

/**
* BourseCorba/CompteHolder.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from bourse.idl
* dimanche 1 juin 2008 16 h 45 CEST
*/

public final class CompteHolder implements org.omg.CORBA.portable.Streamable
{
  public BourseCorba.Compte value = null;

  public CompteHolder ()
  {
  }

  public CompteHolder (BourseCorba.Compte initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = BourseCorba.CompteHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    BourseCorba.CompteHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return BourseCorba.CompteHelper.type ();
  }

}
