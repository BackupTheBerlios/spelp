package BourseCorba;


/**
* BourseCorba/ServerException.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from bourse.idl
* dimanche 1 juin 2008 16 h 45 CEST
*/

public final class ServerException extends org.omg.CORBA.UserException
{
  public String reason = null;

  public ServerException ()
  {
    super(ServerExceptionHelper.id());
  } // ctor

  public ServerException (String _reason)
  {
    super(ServerExceptionHelper.id());
    reason = _reason;
  } // ctor


  public ServerException (String $reason, String _reason)
  {
    super(ServerExceptionHelper.id() + "  " + $reason);
    reason = _reason;
  } // ctor

} // class ServerException
