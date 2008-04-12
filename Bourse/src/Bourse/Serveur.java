package Bourse;

import org.omg.CORBA.ORB;
import org.omg.CosNaming.NameComponent;
import org.omg.CosNaming.NamingContext;
import org.omg.CosNaming.NamingContextHelper;

public class Serveur {
	public static void main (String[] args){
		while (true){	
			try{
				// create and initialize the ORB
				ORB orb = ORB.init(args, null);

				// create servant and register it with the ORB
				BourseServant bourseRef = new BourseServant();
				orb.connect(bourseRef);


				// get the root naming context
				org.omg.CORBA.Object objRef = 
					orb.resolve_initial_references("NameService");
				NamingContext ncRef = NamingContextHelper.narrow(objRef);

				// bind the Object Reference in Naming
				NameComponent nc = new NameComponent("Bourse", "");
				NameComponent path[] = {nc};
				ncRef.rebind(path, bourseRef);
				// wait for invocations from clients
				java.lang.Object sync = new java.lang.Object();
				synchronized (sync) {
					sync.wait();
				}
			} 
			catch (Exception e) {
				System.err.println("ERROR: " + e);
				e.printStackTrace(System.out);
			}
		}
	}
}

