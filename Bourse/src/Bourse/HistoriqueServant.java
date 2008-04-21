package Bourse;

import dao.Historique;
import dao.HistoriqueDAO;
import BourseCorba._HistoriqueImplBase;

public class HistoriqueServant extends _HistoriqueImplBase {

	private int id ;
	
	public HistoriqueServant (int id) {
		super() ;
		this.id = id ;
	}
	
	public String dateHistorique() {
		String result = "" ;
		try {
			Historique h = HistoriqueDAO.getInstance().getHistorique(String.valueOf(id));
			result = h.getDate_histo() ;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return result;
	}

	public void dateHistorique(String newDateHistorique) {
		// TODO Auto-generated method stub
		// inutile
	}

	public double valeur() {
		double result = 0 ;
		try {
			Historique h = HistoriqueDAO.getInstance().getHistorique(String.valueOf(id));
			result = h.getValeur() ;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return result;
	}

	public void valeur(double newValeur) {
		// TODO Auto-generated method stub
		// inutile
	}

}
