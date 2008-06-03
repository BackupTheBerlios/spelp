package Bourse;

import junit.framework.TestCase;
import BourseCorba.ActionMontant;
import BourseCorba.Alarme;
import BourseCorba.Compte;
import BourseCorba.ServerException;
import BourseCorba.Titre;
import BourseCorba.TitreDetaille;
import Client.ClientAlarmeServant;

public class CompteServantTest extends TestCase {
	
	private Compte compteRef ;
	
	private int idTitre ;

	private int idAction;

	private ClientAlarmeServant clientAlarmeRef;
	
	@Override
	protected void setUp() throws Exception {
		compteRef = ORBConnection.getInstance().getCompteRef();
		idTitre = ORBConnection.getInstance().getIdTitre();
		idAction = ORBConnection.getInstance().getIdAction();
		clientAlarmeRef = ORBConnection.getInstance().getClientAlarmeRef();
	}

	public void testAcheterAction() {
		try {
			int result = compteRef.acheterAction(idTitre);
			assertTrue(result>0);
		} catch (ServerException e) {
			System.err.println(e.reason);
			e.printStackTrace();
			fail();
		}
	}

	public void testVendreAction() {
		try {
			compteRef.vendreAction(idAction);			
		} catch (ServerException e) {
			System.err.println(e.reason);
			e.printStackTrace();
			fail();
		}
	}

	public void testCash() {
		try {
			double result = compteRef.cash();
			assertTrue(result>0);
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}

	public void testGetActionsAvecMontant() {
		try {
			ActionMontant [] result = compteRef.getActionsAvecMontant();
			assertNotNull(result);
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}

	public void testGetMontantPortefeuille() {
		try {
			double result = compteRef.getMontantPortefeuille();
			assertTrue(result>0);
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}

	public void testGetTitre() {
		try {
			TitreDetaille result = compteRef.getTitre(idTitre);
			assertNotNull(result);
		} catch (ServerException e) {
			System.err.println(e.reason);
			e.printStackTrace();
			fail();
		}
	}

	public void testGetTitres() {
		try {
			Titre[] result = compteRef.getTitres();
			assertNotNull(result);
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}

	public void testPositionnerAlarme() {
		Alarme al = new Alarme();
		al.seuil = 1.0;
		al.idTitre = idTitre;
		al.type = 0;
		try {
			compteRef.positionnerAlarme(al);
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}

	public void testEnregistrerClientAlarme() {
		compteRef.enregistrerClientAlarme(clientAlarmeRef);
	}

}
