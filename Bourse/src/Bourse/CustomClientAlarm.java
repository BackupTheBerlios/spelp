package Bourse;

import BourseCorba.ClientAlarme;

public class CustomClientAlarm {
	private int idCompte ;
	private ClientAlarme client ;
	public int getIdCompte() {
		return idCompte;
	}
	public void setIdCompte(int idCompte) {
		this.idCompte = idCompte;
	}
	public ClientAlarme getClient() {
		return client;
	}
	public void setClient(ClientAlarme client) {
		this.client = client;
	}
}
