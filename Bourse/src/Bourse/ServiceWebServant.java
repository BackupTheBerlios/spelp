package Bourse;

import BourseCorba._ServiceWebImplBase;
import dao.BourseDAO;
import dao.HistoriqueDAO;
import dao.Titre;
import dao.TitreDAO;

public class ServiceWebServant extends _ServiceWebImplBase {

	public int creerTitre(String libelle, double coursIntro) {
		Titre t = new Titre() ;
		t.setLibelle(libelle) ;
		t.setCoursIntro(coursIntro);
		try {
			t = TitreDAO.getInstance().creerTitre(t);
		}  catch (Exception e) {
			e.printStackTrace();
		}
		return t.getCode();

	}

	public void majCoursAction(int idTitre, double nouveauCours) {
		try {
			Titre t = TitreDAO.getInstance().getTitre(idTitre);
			dao.Historique h = new dao.Historique () ;
			int idHistorique = (int) BourseDAO.getInstance().generateId("HISTORIQUE", "id");
			h.setValeur(t.getCours());
			h.setId_titre(t.getCode());			
			t.setCours(nouveauCours);
			int idHIstorique = HistoriqueDAO.getInstance().creerHistorique(h);
			TitreDAO.getInstance().updateTitre(t);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
