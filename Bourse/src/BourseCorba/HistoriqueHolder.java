package BourseCorba;

/**
* BourseCorba/HistoriqueHolder.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from bourse.idl
* mardi 13 mai 2008 19 h 17 CEST
*/

public final class HistoriqueHolder implements org.omg.CORBA.portable.Streamable
{
  public BourseCorba.Historique value = null;

  public HistoriqueHolder ()
  {
  }

  public HistoriqueHolder (BourseCorba.Historique initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = BourseCorba.HistoriqueHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    BourseCorba.HistoriqueHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return BourseCorba.HistoriqueHelper.type ();
  }

}
