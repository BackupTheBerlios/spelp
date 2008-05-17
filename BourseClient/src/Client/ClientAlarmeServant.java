package Client;

import BourseCorba._ClientAlarmeImplBase;

public class ClientAlarmeServant extends _ClientAlarmeImplBase {

	public void notifie(int idTitre, double nouveauCours) {
		System.out.println("Alarme !!");
		System.out.println("Titre : "+idTitre);
		System.out.println("Cours : "+nouveauCours);
	}

}
