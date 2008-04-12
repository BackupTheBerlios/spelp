package BourseCorba;


/**
* BourseCorba/_CompteStub.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from bourse.idl
* mardi 8 avril 2008 19 h 04 CEST
*/

public class _CompteStub extends org.omg.CORBA.portable.ObjectImpl implements BourseCorba.Compte
{


  // retourne la liste des titres disponibles a l'achat
  public BourseCorba.Titre[] getTitres ()
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("getTitres", true);
                $in = _invoke ($out);
                BourseCorba.Titre $result[] = BourseCorba.ListeTitresHelper.read ($in);
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return getTitres (        );
            } finally {
                _releaseReply ($in);
            }
  } // getTitres

  public BourseCorba.TitreDetaille getTitre (int id) throws BourseCorba.ServerException
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("getTitre", true);
                $out.write_long (id);
                $in = _invoke ($out);
                BourseCorba.TitreDetaille $result = BourseCorba.TitreDetailleHelper.read ($in);
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                if (_id.equals ("IDL:BourseCorba/ServerException:1.0"))
                    throw BourseCorba.ServerExceptionHelper.read ($in);
                else
                    throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return getTitre (id        );
            } finally {
                _releaseReply ($in);
            }
  } // getTitre

  public double getMontantPortefeuille ()
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("getMontantPortefeuille", true);
                $in = _invoke ($out);
                double $result = $in.read_double ();
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return getMontantPortefeuille (        );
            } finally {
                _releaseReply ($in);
            }
  } // getMontantPortefeuille

  public BourseCorba.ActionMontant[] getActionsAvecMontant ()
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("getActionsAvecMontant", true);
                $in = _invoke ($out);
                BourseCorba.ActionMontant $result[] = BourseCorba.ListeActionMontantHelper.read ($in);
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return getActionsAvecMontant (        );
            } finally {
                _releaseReply ($in);
            }
  } // getActionsAvecMontant


  // retourne l'id de l'action achetee a partir du titre
  public int acheterAction (int idTitre) throws BourseCorba.ServerException
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("acheterAction", true);
                $out.write_long (idTitre);
                $in = _invoke ($out);
                int $result = $in.read_long ();
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                if (_id.equals ("IDL:BourseCorba/ServerException:1.0"))
                    throw BourseCorba.ServerExceptionHelper.read ($in);
                else
                    throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return acheterAction (idTitre        );
            } finally {
                _releaseReply ($in);
            }
  } // acheterAction

  public void vendreAction (int idAction) throws BourseCorba.ServerException
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("vendreAction", true);
                $out.write_long (idAction);
                $in = _invoke ($out);
                return;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                if (_id.equals ("IDL:BourseCorba/ServerException:1.0"))
                    throw BourseCorba.ServerExceptionHelper.read ($in);
                else
                    throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                vendreAction (idAction        );
            } finally {
                _releaseReply ($in);
            }
  } // vendreAction

  public void positionnerAlarme (BourseCorba.Alarme a)
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("positionnerAlarme", true);
                BourseCorba.AlarmeHelper.write ($out, a);
                $in = _invoke ($out);
                return;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                positionnerAlarme (a        );
            } finally {
                _releaseReply ($in);
            }
  } // positionnerAlarme

  public double cash ()
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("cash", true);
                $in = _invoke ($out);
                double $result = $in.read_double ();
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return cash (        );
            } finally {
                _releaseReply ($in);
            }
  } // cash

  // Type-specific CORBA::Object operations
  private static String[] __ids = {
    "IDL:BourseCorba/Compte:1.0"};

  public String[] _ids ()
  {
    return (String[])__ids.clone ();
  }

  private void readObject (java.io.ObjectInputStream s) throws java.io.IOException
  {
     String str = s.readUTF ();
     String[] args = null;
     java.util.Properties props = null;
     org.omg.CORBA.Object obj = org.omg.CORBA.ORB.init (args, props).string_to_object (str);
     org.omg.CORBA.portable.Delegate delegate = ((org.omg.CORBA.portable.ObjectImpl) obj)._get_delegate ();
     _set_delegate (delegate);
  }

  private void writeObject (java.io.ObjectOutputStream s) throws java.io.IOException
  {
     String[] args = null;
     java.util.Properties props = null;
     String str = org.omg.CORBA.ORB.init (args, props).object_to_string (this);
     s.writeUTF (str);
  }
} // class _CompteStub
