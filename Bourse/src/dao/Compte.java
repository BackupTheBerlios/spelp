package dao;

public class Compte {
	private long id ;
	private double cash ;
	private long id_utilisateur ;
	public long getId_utilisateur() {
		return id_utilisateur;
	}
	public void setId_utilisateur(long id_utilisateur) {
		this.id_utilisateur = id_utilisateur;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}

	public double getCash() {
		return cash;
	}
	public void setCash(double cash) {
		this.cash = cash;
	}
}
