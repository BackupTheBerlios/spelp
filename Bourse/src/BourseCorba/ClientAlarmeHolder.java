package BourseCorba;

/**
* BourseCorba/ClientAlarmeHolder.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from bourse.idl
* dimanche 1 juin 2008 16 h 45 CEST
*/

public final class ClientAlarmeHolder implements org.omg.CORBA.portable.Streamable
{
  public BourseCorba.ClientAlarme value = null;

  public ClientAlarmeHolder ()
  {
  }

  public ClientAlarmeHolder (BourseCorba.ClientAlarme initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = BourseCorba.ClientAlarmeHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    BourseCorba.ClientAlarmeHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return BourseCorba.ClientAlarmeHelper.type ();
  }

}
