package dao;

public class Historique {
	private int id ;
	private double valeur ;
	private String date_histo ;
	private int id_titre ;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public double getValeur() {
		return valeur;
	}
	public void setValeur(double valeur) {
		this.valeur = valeur;
	}
	public String getDate_histo() {
		return date_histo;
	}
	public void setDate_histo(String date_histo) {
		this.date_histo = date_histo;
	}
	public int getId_titre() {
		return id_titre;
	}
	public void setId_titre(int id_titre) {
		this.id_titre = id_titre;
	}
}
