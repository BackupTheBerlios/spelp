package Bourse;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;

import BourseCorba.CompteAdmin;
import BourseCorba.ServerException;
import BourseCorba._AdminImplBase;
import dao.Compte;
import dao.CompteDAO;
import dao.Titre;
import dao.TitreDAO;
import dao.Utilisateur;
import dao.UtilisateurDAO;

public class AdminServant extends _AdminImplBase {

	public void affecterCompte(int idUser, int idCompte) throws ServerException {
		try {
			CompteDAO.getInstance().affecterUtilisateurComtpe (String.valueOf(idUser),String.valueOf(idCompte)); 
		} 
		catch (Exception e) {
			e.printStackTrace();
			throw new ServerException(e.getMessage());
		}
	}

	public void ajoutCash(int idCompte, double valeur) throws ServerException {
		try {
			Compte compte = CompteDAO.getInstance().getCompte(String.valueOf(idCompte));
			compte.setCash(compte.getCash()+valeur);
			CompteDAO.getInstance().updateCompte(compte);
		} 
		catch (Exception e) {
			e.printStackTrace();
			throw new ServerException(e.getMessage());
		}
	}

	public int creerCompte(double cash) {
		Compte cpt = new Compte() ;
		cpt.setCash(cash);
		try {
			cpt = CompteDAO.getInstance().creerCompte(cpt) ;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return (int) cpt.getId();
	}

	public void diminuerCash(int idCompte, double valeur) throws ServerException {
		try {
			Compte compte = CompteDAO.getInstance().getCompte(String.valueOf(idCompte));
			compte.setCash(compte.getCash()-valeur);
			CompteDAO.getInstance().updateCompte(compte);
		} 
		catch (Exception e) {
			e.printStackTrace();
			throw new ServerException(e.getMessage());
		}
	}

	public CompteAdmin[] getComptes() {
		Collection<Compte> c;
		CompteAdmin[] resultArray = null ;
		try {
			c = CompteDAO.getInstance().getComptes();
			LinkedList<CompteAdmin> result = new LinkedList<CompteAdmin>();
			Collection<Utilisateur> u = UtilisateurDAO.getInstance().getUtilisateurs();
			for (Compte ctmp : c){
				Utilisateur tmp = null ;
				if (ctmp.getId_utilisateur() != 0){
					 tmp = (Utilisateur) Util.getElement(u, ctmp.getId_utilisateur(), "id");
					 result.add(new CompteAdmin(tmp.getNom(),String.valueOf(ctmp.getId())));
				}
				else {
					result.add(new CompteAdmin("",String.valueOf(ctmp.getId())));
				}
				
			}
			resultArray = new CompteAdmin[result.size()];
			resultArray = result.toArray(resultArray);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultArray ;
	}

	public int insererUtilisateur(String nom, String prenom, String login,
			String mdp, int role) throws ServerException {
		Utilisateur user = UtilisateurDAO.getInstance().getUtilisateur(login, mdp);
		if (user == null) {
			Utilisateur usr = new Utilisateur();
			usr.setNom(nom);
			usr.setPrenom(prenom);
			usr.setLogin(login);
			usr.setMdp(mdp);
			usr.setRole(role);
			try {
				usr = UtilisateurDAO.getInstance().creerUtilisateur(usr);
				return (int) usr.getId();
			} catch (Exception e) {
				e.printStackTrace();
				throw new ServerException(e.getMessage());
			}
		}
		else {
			throw new BourseCorba.ServerException("user inexistant");
		}
		
		
	}

	public int creerTitre(String libelle, double coursIntro)
			throws ServerException {
		Titre t = new Titre() ;
		t.setLibelle(libelle) ;
		t.setCoursIntro(coursIntro);
		try {
			t = TitreDAO.getInstance().creerTitre(t);
		}  catch (Exception e) {
			e.printStackTrace();
			throw new ServerException(e.getMessage());
		}
		return t.getCode();
	}

}
