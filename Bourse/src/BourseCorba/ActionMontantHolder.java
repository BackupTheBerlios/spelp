package BourseCorba;

/**
* BourseCorba/ActionMontantHolder.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from bourse.idl
* dimanche 1 juin 2008 16 h 45 CEST
*/

public final class ActionMontantHolder implements org.omg.CORBA.portable.Streamable
{
  public BourseCorba.ActionMontant value = null;

  public ActionMontantHolder ()
  {
  }

  public ActionMontantHolder (BourseCorba.ActionMontant initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = BourseCorba.ActionMontantHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    BourseCorba.ActionMontantHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return BourseCorba.ActionMontantHelper.type ();
  }

}
