package BourseCorba;


/**
* BourseCorba/_CompteImplBase.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from bourse.idl
* mardi 20 mai 2008 18 h 27 CEST
*/

public abstract class _CompteImplBase extends org.omg.CORBA.portable.ObjectImpl
                implements BourseCorba.Compte, org.omg.CORBA.portable.InvokeHandler
{

  // Constructors
  public _CompteImplBase ()
  {
  }

  private static java.util.Hashtable _methods = new java.util.Hashtable ();
  static
  {
    _methods.put ("getTitres", new java.lang.Integer (0));
    _methods.put ("getTitre", new java.lang.Integer (1));
    _methods.put ("getMontantPortefeuille", new java.lang.Integer (2));
    _methods.put ("getActionsAvecMontant", new java.lang.Integer (3));
    _methods.put ("acheterAction", new java.lang.Integer (4));
    _methods.put ("vendreAction", new java.lang.Integer (5));
    _methods.put ("positionnerAlarme", new java.lang.Integer (6));
    _methods.put ("enregistrerClientAlarme", new java.lang.Integer (7));
    _methods.put ("cash", new java.lang.Integer (8));
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

  // retourne la liste des titres disponibles a l'achat
       case 0:  // BourseCorba/Compte/getTitres
       {
         BourseCorba.Titre $result[] = null;
         $result = this.getTitres ();
         out = $rh.createReply();
         BourseCorba.ListeTitresHelper.write (out, $result);
         break;
       }

       case 1:  // BourseCorba/Compte/getTitre
       {
         try {
           int id = in.read_long ();
           BourseCorba.TitreDetaille $result = null;
           $result = this.getTitre (id);
           out = $rh.createReply();
           BourseCorba.TitreDetailleHelper.write (out, $result);
         } catch (BourseCorba.ServerException $ex) {
           out = $rh.createExceptionReply ();
           BourseCorba.ServerExceptionHelper.write (out, $ex);
         }
         break;
       }

       case 2:  // BourseCorba/Compte/getMontantPortefeuille
       {
         double $result = (double)0;
         $result = this.getMontantPortefeuille ();
         out = $rh.createReply();
         out.write_double ($result);
         break;
       }

       case 3:  // BourseCorba/Compte/getActionsAvecMontant
       {
         BourseCorba.ActionMontant $result[] = null;
         $result = this.getActionsAvecMontant ();
         out = $rh.createReply();
         BourseCorba.ListeActionMontantHelper.write (out, $result);
         break;
       }


  // retourne l'id de l'action achetee a partir du titre
       case 4:  // BourseCorba/Compte/acheterAction
       {
         try {
           int idTitre = in.read_long ();
           int $result = (int)0;
           $result = this.acheterAction (idTitre);
           out = $rh.createReply();
           out.write_long ($result);
         } catch (BourseCorba.ServerException $ex) {
           out = $rh.createExceptionReply ();
           BourseCorba.ServerExceptionHelper.write (out, $ex);
         }
         break;
       }

       case 5:  // BourseCorba/Compte/vendreAction
       {
         try {
           int idAction = in.read_long ();
           this.vendreAction (idAction);
           out = $rh.createReply();
         } catch (BourseCorba.ServerException $ex) {
           out = $rh.createExceptionReply ();
           BourseCorba.ServerExceptionHelper.write (out, $ex);
         }
         break;
       }

       case 6:  // BourseCorba/Compte/positionnerAlarme
       {
         BourseCorba.Alarme a = BourseCorba.AlarmeHelper.read (in);
         this.positionnerAlarme (a);
         out = $rh.createReply();
         break;
       }

       case 7:  // BourseCorba/Compte/enregistrerClientAlarme
       {
         BourseCorba.ClientAlarme ca = BourseCorba.ClientAlarmeHelper.read (in);
         this.enregistrerClientAlarme (ca);
         out = $rh.createReply();
         break;
       }

       case 8:  // BourseCorba/Compte/cash
       {
         double $result = (double)0;
         $result = this.cash ();
         out = $rh.createReply();
         out.write_double ($result);
         break;
       }

       default:
         throw new org.omg.CORBA.BAD_OPERATION (0, org.omg.CORBA.CompletionStatus.COMPLETED_MAYBE);
    }

    return out;
  } // _invoke

  // Type-specific CORBA::Object operations
  private static String[] __ids = {
    "IDL:BourseCorba/Compte:1.0"};

  public String[] _ids ()
  {
    return (String[])__ids.clone ();
  }


} // class _CompteImplBase
