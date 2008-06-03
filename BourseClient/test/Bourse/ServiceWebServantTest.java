package Bourse;

import BourseCorba.ServerException;
import BourseCorba.ServiceWeb;
import junit.framework.TestCase;

public class ServiceWebServantTest extends TestCase {

	private ServiceWeb serviceWebRef;

	private int idTitre;

	protected void setUp() throws Exception {
		serviceWebRef = ORBConnection.getInstance().getServiceWebRef();
		idTitre = ORBConnection.getInstance().getIdTitre();
	}

	public void testCreerTitre() {
		try {
			int result = serviceWebRef.creerTitre("TITRETEST2", 1.0);
			assertTrue(result > 0);
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}

	}

	public void testMajCoursAction() {
		try {
			serviceWebRef.majCoursAction(idTitre, 2.0);
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}
}
