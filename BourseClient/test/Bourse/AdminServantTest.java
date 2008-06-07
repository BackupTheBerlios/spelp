package Bourse;

import junit.framework.TestCase;
import BourseCorba.Admin;
import BourseCorba.ServerException;

public class AdminServantTest extends TestCase {

	private Admin adminRef = null;

	private int idUtilisateur;

	private int idCompte;

	@Override
	protected void setUp() throws Exception {
		adminRef = ORBConnection.getInstance().getAdminRef();
		idUtilisateur = ORBConnection.getInstance().getIdTristan();
		idCompte = ORBConnection.getInstance().getIdCompte();
	}

	public void testAffecterCompte() {
		try {
			adminRef.affecterCompte(idUtilisateur, idCompte);
		} catch (ServerException e) {
			System.err.println(e.reason);
			e.printStackTrace();
			fail();
		}
	}

	public void testAjoutCash() {
		try {
			adminRef.ajoutCash(idCompte, 100.0);
		} catch (ServerException e) {			
			System.err.println(e.reason);
			e.printStackTrace();
			fail();
		}
	}

	public void testCreerCompte() {
		int idCompte2 = adminRef.creerCompte(100);
		assertNotSame(idCompte, idCompte2);
	}

	public void testDiminuerCash() {
		try {
			adminRef.diminuerCash(idCompte, 100.0);
		} catch (ServerException e) {			
			System.err.println(e.reason);
			e.printStackTrace();
			fail();
		}
	}

	public void testGetComptes() {
		/*CompteAdmin [] result = adminRef.getComptes();
		assertNotNull(result);
		for (CompteAdmin ca : result) {
			assertNotNull(ca);			
		}*/
	}

	public void testInsererUtilisateur() {
		try {
			int idNico = adminRef.insererUtilisateur("CASTEL", "NICOLAS",
					"NICASTEL", "1234", 1);
			assertNotSame(idUtilisateur, idNico);
		} catch (ServerException e) {
			System.err.println(e.reason);
			e.printStackTrace();
			fail();
		}
	}
}
