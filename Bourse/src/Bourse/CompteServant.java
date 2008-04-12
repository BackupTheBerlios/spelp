package Bourse;

import java.util.Collection;
import java.util.LinkedList;

import BourseCorba.ActionMontant;
import BourseCorba.Alarme;
import BourseCorba.ServerException;
import BourseCorba.Titre;
import BourseCorba.TitreDetaille;
import BourseCorba._CompteImplBase;
import dao.Action;
import dao.ActionDAO;
import dao.Compte;
import dao.CompteDAO;
import dao.TitreDAO;

public class CompteServant extends _CompteImplBase {
	private long id ; 
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int acheterAction(int idTitre) throws ServerException{
		try {
			dao.Titre t = TitreDAO.getInstance().getTitre(idTitre);
			Compte compteCourant = CompteDAO.getInstance().getCompte(String.valueOf(id));
			double cash = compteCourant.getCash() ;
			cash = cash - t.getCours() ;
			compteCourant.setCash(cash) ;
			Action nouvelleAction = new Action() ;
			nouvelleAction.setId_compte((int) id);
			nouvelleAction.setId_titre(t.getCode());
			nouvelleAction = ActionDAO.getInstance().creerAction (nouvelleAction) ;
			CompteDAO.getInstance().updateCompte(compteCourant);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServerException(e.getMessage());
		}
		return 0;
	}

	public double cash() {
		double result = 0 ;
		try {
			Compte c = CompteDAO.getInstance().getCompte(String.valueOf(id));
			result = c.getCash();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}


	public ActionMontant[] getActionsAvecMontant() {
		LinkedList<ActionMontant> resultList = new LinkedList<ActionMontant>() ;
		try {
			Collection<Action> actions = ActionDAO.getInstance().getAllActionsByIdCompte(id);
			for (Action a : actions){
				ActionMontant tmp = new ActionMontant () ;
				tmp.idAction = a.getId() ;
				dao.Titre tTmp = TitreDAO.getInstance().getTitre(a.getId_titre());
				tmp.libelleTitre = tTmp.getLibelle();
				tmp.montant = tTmp.getCours() ;
				resultList.add(tmp);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		ActionMontant[] resultArray = new ActionMontant[resultList.size()];
		resultArray = resultList.toArray(resultArray);
		return resultArray;
	}

	public double getMontantPortefeuille() {
		double result = 0 ;
		try {
			Collection<Action> actions = ActionDAO.getInstance().getAllActionsByIdCompte(id);
			for (Action a : actions){
				dao.Titre tTmp = TitreDAO.getInstance().getTitre(a.getId_titre());
				result += tTmp.getCours();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result ;
	}

	// TODO !!!!
	public TitreDetaille getTitre(int id) throws ServerException{
		TitreDetaille td = new TitreDetaille () ;
		
		return td;
	}

	public Titre[] getTitres() {
		LinkedList<Titre> resultList = new LinkedList<Titre>() ;
		try {
			Collection<dao.Titre> titres = TitreDAO.getInstance().getTitres() ;
			for (dao.Titre t : titres) {
				Titre tTmp = new Titre() ;
				tTmp.id = t.getCode() ;
				tTmp.libelle = t.getLibelle() ;
				tTmp.cours = t.getCours() ;
				resultList.add(tTmp);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}	
		Titre[] resultArray = new Titre[resultList.size()];
		resultArray = resultList.toArray(resultArray);
		return resultArray ;
	}


	public void positionnerAlarme(Alarme a) {
		// TODO Auto-generated method stub

	}

	public void vendreAction(int idAction)throws ServerException {
		// TODO Auto-generated method stub

	}

}
