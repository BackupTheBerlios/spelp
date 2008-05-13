package Client;
import javax.xml.namespace.QName;

import org.apache.axis.client.Call;
import org.apache.axis.client.Service;

public class Client {
		String endpoint = "http://localhost:8080/serveurBourse/ServeurBourse.jws";   	  
	
	
		public Client() {
			super();
		}
		public int creerTitre(String libelle, double coursIntro) {
			Integer ret =0;
			try {
				Service  service = new Service();
				Call     call    = (Call) service.createCall();	  
				call.setTargetEndpointAddress( new java.net.URL(endpoint) );
				call.setOperationName(new QName("http://localhost:8080/serveurBourse/ServeurBourse.jws", "creerTitre"));	   
				ret = (Integer) call.invoke( new Object[] { libelle,  coursIntro} );
			} catch (Exception e) {
				e.printStackTrace();
			}
			return ret;
		}

		public void majCoursAction(int idTitre, double nouveauCours) {
			try {
				Service  service = new Service();
				Call     call    = (Call) service.createCall();	  
				call.setTargetEndpointAddress( new java.net.URL(endpoint) );
				call.setOperationName(new QName("http://localhost:8080/serveurBourse/ServeurBourse.jws", "majCoursAction"));	   
				call.invoke( new Object[] { idTitre,  nouveauCours} );
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		public static void main(String [] args) {
			Client c = new Client() ;
			c.creerTitre("testWS", 1);

		}
	}
