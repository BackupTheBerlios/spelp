package BourseCorba;

/**
* BourseCorba/ServiceWebHolder.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from bourse.idl
* mardi 13 mai 2008 19 h 17 CEST
*/

public final class ServiceWebHolder implements org.omg.CORBA.portable.Streamable
{
  public BourseCorba.ServiceWeb value = null;

  public ServiceWebHolder ()
  {
  }

  public ServiceWebHolder (BourseCorba.ServiceWeb initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = BourseCorba.ServiceWebHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    BourseCorba.ServiceWebHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return BourseCorba.ServiceWebHelper.type ();
  }

}
