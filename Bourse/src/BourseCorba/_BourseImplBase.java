package BourseCorba;


/**
* BourseCorba/_BourseImplBase.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from bourse.idl
* dimanche 1 juin 2008 16 h 45 CEST
*/

public abstract class _BourseImplBase extends org.omg.CORBA.portable.ObjectImpl
                implements BourseCorba.Bourse, org.omg.CORBA.portable.InvokeHandler
{

  // Constructors
  public _BourseImplBase ()
  {
  }

  private static java.util.Hashtable _methods = new java.util.Hashtable ();
  static
  {
    _methods.put ("getServiceWeb", new java.lang.Integer (0));
    _methods.put ("connectUser", new java.lang.Integer (1));
    _methods.put ("connectAdmin", new java.lang.Integer (2));
  }

  public org.omg.CORBA.portable.OutputStream _invoke (String $method,
                                org.omg.CORBA.portable.InputStream in,
                                org.omg.CORBA.portable.ResponseHandler $rh)
  {
    org.omg.CORBA.portable.OutputStream out = null;
    java.lang.Integer __method = (java.lang.Integer)_methods.get ($method);
    if (__method == null)
      throw new org.omg.CORBA.BAD_OPERATION (0, org.omg.CORBA.CompletionStatus.COMPLETED_MAYBE);

    switch (__method.intValue ())
    {
       case 0:  // BourseCorba/Bourse/getServiceWeb
       {
         try {
           BourseCorba.ServiceWeb $result = null;
           $result = this.getServiceWeb ();
           out = $rh.createReply();
           BourseCorba.ServiceWebHelper.write (out, $result);
         } catch (BourseCorba.ServerException $ex) {
           out = $rh.createExceptionReply ();
           BourseCorba.ServerExceptionHelper.write (out, $ex);
         }
         break;
       }

       case 1:  // BourseCorba/Bourse/connectUser
       {
         try {
           String login = in.read_string ();
           String mdp = in.read_string ();
           BourseCorba.Compte $result = null;
           $result = this.connectUser (login, mdp);
           out = $rh.createReply();
           BourseCorba.CompteHelper.write (out, $result);
         } catch (BourseCorba.ServerException $ex) {
           out = $rh.createExceptionReply ();
           BourseCorba.ServerExceptionHelper.write (out, $ex);
         }
         break;
       }

       case 2:  // BourseCorba/Bourse/connectAdmin
       {
         try {
           String login = in.read_string ();
           String mdp = in.read_string ();
           BourseCorba.Admin $result = null;
           $result = this.connectAdmin (login, mdp);
           out = $rh.createReply();
           BourseCorba.AdminHelper.write (out, $result);
         } catch (BourseCorba.ServerException $ex) {
           out = $rh.createExceptionReply ();
           BourseCorba.ServerExceptionHelper.write (out, $ex);
         }
         break;
       }

       default:
         throw new org.omg.CORBA.BAD_OPERATION (0, org.omg.CORBA.CompletionStatus.COMPLETED_MAYBE);
    }

    return out;
  } // _invoke

  // Type-specific CORBA::Object operations
  private static String[] __ids = {
    "IDL:BourseCorba/Bourse:1.0"};

  public String[] _ids ()
  {
    return (String[])__ids.clone ();
  }


} // class _BourseImplBase
