package BourseCorba;

/**
* BourseCorba/AlarmeHolder.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from bourse.idl
* dimanche 1 juin 2008 16 h 45 CEST
*/

public final class AlarmeHolder implements org.omg.CORBA.portable.Streamable
{
  public BourseCorba.Alarme value = null;

  public AlarmeHolder ()
  {
  }

  public AlarmeHolder (BourseCorba.Alarme initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = BourseCorba.AlarmeHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    BourseCorba.AlarmeHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return BourseCorba.AlarmeHelper.type ();
  }

}
