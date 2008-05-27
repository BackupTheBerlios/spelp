package BourseCorba;


/**
* BourseCorba/_AdminImplBase.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from bourse.idl
* mardi 20 mai 2008 18 h 27 CEST
*/

public abstract class _AdminImplBase extends org.omg.CORBA.portable.ObjectImpl
                implements BourseCorba.Admin, org.omg.CORBA.portable.InvokeHandler
{

  // Constructors
  public _AdminImplBase ()
  {
  }

  private static java.util.Hashtable _methods = new java.util.Hashtable ();
  static
  {
    _methods.put ("insererUtilisateur", new java.lang.Integer (0));
    _methods.put ("creerCompte", new java.lang.Integer (1));
    _methods.put ("affecterCompte", new java.lang.Integer (2));
    _methods.put ("getComptes", new java.lang.Integer (3));
    _methods.put ("ajoutCash", new java.lang.Integer (4));
    _methods.put ("diminuerCash", new java.lang.Integer (5));
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

  // retourne l identifiant de l utilisateur
       case 0:  // BourseCorba/Admin/insererUtilisateur
       {
         try {
           String nom = in.read_string ();
           String prenom = in.read_string ();
           String login = in.read_string ();
           String mdp = in.read_string ();
           int role = in.read_long ();
           int $result = (int)0;
           $result = this.insererUtilisateur (nom, prenom, login, mdp, role);
           out = $rh.createReply();
           out.write_long ($result);
         } catch (BourseCorba.ServerException $ex) {
           out = $rh.createExceptionReply ();
           BourseCorba.ServerExceptionHelper.write (out, $ex);
         }
         break;
       }


  // retourne l identifiant du compte
       case 1:  // BourseCorba/Admin/creerCompte
       {
         double cash = in.read_double ();
         int $result = (int)0;
         $result = this.creerCompte (cash);
         out = $rh.createReply();
         out.write_long ($result);
         break;
       }

       case 2:  // BourseCorba/Admin/affecterCompte
       {
         try {
           int idUser = in.read_long ();
           int idCompte = in.read_long ();
           this.affecterCompte (idUser, idCompte);
           out = $rh.createReply();
         } catch (BourseCorba.ServerException $ex) {
           out = $rh.createExceptionReply ();
           BourseCorba.ServerExceptionHelper.write (out, $ex);
         }
         break;
       }

       case 3:  // BourseCorba/Admin/getComptes
       {
         BourseCorba.CompteAdmin $result[] = null;
         $result = this.getComptes ();
         out = $rh.createReply();
         BourseCorba.ListeCompteAdminHelper.write (out, $result);
         break;
       }

       case 4:  // BourseCorba/Admin/ajoutCash
       {
         try {
           int idCompte = in.read_long ();
           double valeur = in.read_double ();
           this.ajoutCash (idCompte, valeur);
           out = $rh.createReply();
         } catch (BourseCorba.ServerException $ex) {
           out = $rh.createExceptionReply ();
           BourseCorba.ServerExceptionHelper.write (out, $ex);
         }
         break;
       }

       case 5:  // BourseCorba/Admin/diminuerCash
       {
         try {
           int idCompte = in.read_long ();
           double valeur = in.read_double ();
           this.diminuerCash (idCompte, valeur);
           out = $rh.createReply();
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
    "IDL:BourseCorba/Admin:1.0"};

  public String[] _ids ()
  {
    return (String[])__ids.clone ();
  }


} // class _AdminImplBase
