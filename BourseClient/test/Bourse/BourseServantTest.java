package Bourse;

import junit.framework.TestCase;
import BourseCorba.Bourse;
import BourseCorba.ServerException;

public class BourseServantTest extends TestCase {

	public void testConnectAdmin() {
		Bourse bourseRef = ORBConnection.getInstance().getBourseRef();
		try {			
			bourseRef.connectAdmin("", "");
			fail();
		} catch (ServerException e) {
			
		}
		try {
			bourseRef.connectAdmin("admin", "admin");
		} catch (ServerException e) {
			e.printStackTrace();
			fail();
		}
	}

	public void testConnectUser() {
		Bourse bourseRef = ORBConnection.getInstance().getBourseRef();
		try {			
			assertNotNull(bourseRef.connectUser("", ""));
			fail();
		} catch (ServerException e) {
			e.printStackTrace();			
		}
	}

	public void testGetServiceWeb() {
		Bourse bourseRef = ORBConnection.getInstance().getBourseRef();
		try {
			bourseRef.getServiceWeb();
		} catch (ServerException e) {
			e.printStackTrace();
			fail();
		}
	}

}
