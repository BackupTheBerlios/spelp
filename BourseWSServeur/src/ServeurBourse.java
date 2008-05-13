import org.omg.CORBA.ORB;
import org.omg.CosNaming.NameComponent;
import org.omg.CosNaming.NamingContext;
import org.omg.CosNaming.NamingContextHelper;

import BourseCorba.Bourse;
import BourseCorba.BourseHelper;
import BourseCorba.ServiceWeb;



public class ServeurBourse {
	public Bourse connect() throws Exception {
		// create and initialize the ORB
		String[] args = new String[1] ;
        ORB orb = ORB.init(args, null);

        // get the root naming context
        org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
        NamingContext ncRef = NamingContextHelper.narrow(objRef);

        // resolve the Object Reference in Naming
        NameComponent nc = new NameComponent("Bourse", "");
        NameComponent path[] = {nc}; 
     
        Bourse bourseRef = BourseHelper.narrow(ncRef.resolve(path));
        return bourseRef ;
	}
	
	public int creerTitre(String libelle, double coursIntro) {
		int result = 0;
		try{
			Bourse bourseRef = connect();
			ServiceWeb serviceWebRef = bourseRef.getServiceWeb() ;
            result = serviceWebRef.creerTitre(libelle, coursIntro) ;
          
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public void majCoursAction(int idTitre, double nouveauCours) {
		try{
			Bourse bourseRef = connect();
			ServiceWeb serviceWebRef = bourseRef.getServiceWeb() ;
            serviceWebRef.majCoursAction(idTitre, nouveauCours) ;          
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
