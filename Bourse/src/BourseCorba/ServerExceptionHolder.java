package BourseCorba;

/**
* BourseCorba/ServerExceptionHolder.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from bourse.idl
* dimanche 1 juin 2008 16 h 45 CEST
*/

public final class ServerExceptionHolder implements org.omg.CORBA.portable.Streamable
{
  public BourseCorba.ServerException value = null;

  public ServerExceptionHolder ()
  {
  }

  public ServerExceptionHolder (BourseCorba.ServerException initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = BourseCorba.ServerExceptionHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    BourseCorba.ServerExceptionHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return BourseCorba.ServerExceptionHelper.type ();
  }

}
