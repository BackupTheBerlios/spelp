package BourseCorba;

/**
* BourseCorba/TitreHolder.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from bourse.idl
* mardi 8 avril 2008 19 h 04 CEST
*/

public final class TitreHolder implements org.omg.CORBA.portable.Streamable
{
  public BourseCorba.Titre value = null;

  public TitreHolder ()
  {
  }

  public TitreHolder (BourseCorba.Titre initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = BourseCorba.TitreHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    BourseCorba.TitreHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return BourseCorba.TitreHelper.type ();
  }

}
