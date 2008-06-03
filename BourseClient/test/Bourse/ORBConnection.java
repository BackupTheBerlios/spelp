package Bourse;

import org.omg.CORBA.ORB;
import org.omg.CORBA.ORBPackage.InvalidName;
import org.omg.CosNaming.NameComponent;
import org.omg.CosNaming.NamingContext;
import org.omg.CosNaming.NamingContextHelper;
import org.omg.CosNaming.NamingContextPackage.CannotProceed;
import org.omg.CosNaming.NamingContextPackage.NotFound;

import BourseCorba.Admin;
import BourseCorba.Bourse;
import BourseCorba.BourseHelper;
import BourseCorba.Compte;
import BourseCorba.ServerException;
import BourseCorba.ServiceWeb;
import Client.ClientAlarmeServant;

public class ORBConnection {
	private ORB orb;

	private Bourse bourseRef;

	private Admin adminRef;

	private Compte compteRef;

	private ServiceWeb serviceWebRef;

	private int idUtilisateur;

	private int idCompte;
	
	private int idTitre;

	private int idAction;

	private ClientAlarmeServant clientAlarmeRef;

	private ORBConnection() {
		// create and initialize the ORB
		String[] s = new String[2];
		orb = ORB.init(s, null);

		// get the root naming context
		org.omg.CORBA.Object objRef;
		try {
			objRef = orb.resolve_initial_references("NameService");
			NamingContext ncRef = NamingContextHelper.narrow(objRef);
			// resolve the Object Reference in Naming
			NameComponent nc = new NameComponent("Bourse", "");
			NameComponent path[] = { nc };

			bourseRef = BourseHelper.narrow(ncRef.resolve(path));
			try {
				adminRef = bourseRef.connectAdmin("admin", "admin");
				idUtilisateur = adminRef.insererUtilisateur("FAURE", "TRISTAN",
						"TRFAURE", "1234", 1);
				idCompte = adminRef.creerCompte(100);
				adminRef.affecterCompte(idCompte, idUtilisateur);
				compteRef = bourseRef.connectUser("TRFAURE", "1234");
				serviceWebRef = bourseRef.getServiceWeb();
				idTitre = serviceWebRef.creerTitre("TITRETEST", 2.5);
				idAction = compteRef.acheterAction(idTitre);
				
				//	Connection du client Alarme
                // create servant and register it with the ORB
    			clientAlarmeRef = new ClientAlarmeServant();
    			orb.connect(clientAlarmeRef);			

    			// bind the Object Reference in Naming
    			nc = new NameComponent("ClientAlarme", "");
    			NameComponent path2[] = {nc}; 
    			ncRef.rebind(path2, clientAlarmeRef);
			} catch (Exception e) {
				if (e instanceof ServerException) {
					System.err.println(((ServerException)e).reason);
				}
				e.printStackTrace();
			}

		} catch (InvalidName e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotFound e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (CannotProceed e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (org.omg.CosNaming.NamingContextPackage.InvalidName e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static ORBConnection instance = new ORBConnection();

	public static ORBConnection getInstance() {
		return instance;
	}

	public Bourse getBourseRef() {
		return bourseRef;
	}

	public ORB getOrb() {
		return orb;
	}

	public Admin getAdminRef() {
		return adminRef;
	}

	public int getIdCompte() {
		return idCompte;
	}

	public int getIdTristan() {
		return idUtilisateur;
	}

	public Compte getCompteRef() {
		return compteRef;
	}

	public ServiceWeb getServiceWebRef() {
		return serviceWebRef;
	}

	public int getIdTitre() {
		return idTitre;
	}

	public int getIdAction() {
		return idAction;
	}

	public ClientAlarmeServant getClientAlarmeRef() {
		return clientAlarmeRef;
	}

	public void setClientAlarmeRef(ClientAlarmeServant clientAlarmeRef) {
		this.clientAlarmeRef = clientAlarmeRef;
	}
}
