package dao;

public class Alarme {
	private int id;
	private int type;
	private double seuil;
	private int id_compte;
	private int id_titre;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public double getSeuil() {
		return seuil;
	}
	public void setSeuil(double seuil) {
		this.seuil = seuil;
	}
	public int getId_compte() {
		return id_compte;
	}
	public void setId_compte(int id_compte) {
		this.id_compte = id_compte;
	}
	public int getId_titre() {
		return id_titre;
	}
	public void setId_titre(int id_titre) {
		this.id_titre = id_titre;
	}
}
