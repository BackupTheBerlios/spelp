package BourseCorba;


/**
* BourseCorba/ListeTitresHolder.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from bourse.idl
* dimanche 1 juin 2008 16 h 45 CEST
*/

public final class ListeTitresHolder implements org.omg.CORBA.portable.Streamable
{
  public BourseCorba.Titre value[] = null;

  public ListeTitresHolder ()
  {
  }

  public ListeTitresHolder (BourseCorba.Titre[] initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = BourseCorba.ListeTitresHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    BourseCorba.ListeTitresHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return BourseCorba.ListeTitresHelper.type ();
  }

}
