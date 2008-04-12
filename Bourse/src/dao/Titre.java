package dao;

public class Titre {
	private String libelle ;
	private int code ;
	private String dateIntro ;
	private double coursIntro ; 
	private double cours ;
	
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getDateIntro() {
		return dateIntro;
	}
	public void setDateIntro(String dateIntro) {
		this.dateIntro = dateIntro;
	}
	public double getCoursIntro() {
		return coursIntro;
	}
	public void setCoursIntro(double coursIntro) {
		this.coursIntro = coursIntro;
	}
	public String getLibelle() {
		return libelle;
	}
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
	public double getCours() {
		return cours;
	}
	public void setCours(double cours) {
		this.cours = cours;
	}
	
}
