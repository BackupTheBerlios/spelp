package BourseCorba;


/**
* BourseCorba/_ServiceWebImplBase.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from bourse.idl
* mardi 8 avril 2008 19 h 04 CEST
*/

public abstract class _ServiceWebImplBase extends org.omg.CORBA.portable.ObjectImpl
                implements BourseCorba.ServiceWeb, org.omg.CORBA.portable.InvokeHandler
{

  // Constructors
  public _ServiceWebImplBase ()
  {
  }

  private static java.util.Hashtable _methods = new java.util.Hashtable ();
  static
  {
    _methods.put ("creerTitre", new java.lang.Integer (0));
    _methods.put ("majCoursAction", new java.lang.Integer (1));
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
       case 0:  // BourseCorba/ServiceWeb/creerTitre
       {
         String libelle = in.read_string ();
         double coursIntro = in.read_double ();
         int $result = (int)0;
         $result = this.creerTitre (libelle, coursIntro);
         out = $rh.createReply();
         out.write_long ($result);
         break;
       }

       case 1:  // BourseCorba/ServiceWeb/majCoursAction
       {
         int idTitre = in.read_long ();
         double nouveauCours = in.read_double ();
         this.majCoursAction (idTitre, nouveauCours);
         out = $rh.createReply();
         break;
       }

       default:
         throw new org.omg.CORBA.BAD_OPERATION (0, org.omg.CORBA.CompletionStatus.COMPLETED_MAYBE);
    }

    return out;
  } // _invoke

  // Type-specific CORBA::Object operations
  private static String[] __ids = {
    "IDL:BourseCorba/ServiceWeb:1.0"};

  public String[] _ids ()
  {
    return (String[])__ids.clone ();
  }


} // class _ServiceWebImplBase
