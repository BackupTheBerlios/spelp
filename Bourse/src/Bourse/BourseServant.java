package Bourse;

import dao.BourseDAO;
import dao.CompteDAO;
import dao.Utilisateur;
import dao.UtilisateurDAO;
import BourseCorba.Admin;
import BourseCorba.Compte;
import BourseCorba.ServiceWeb;
import BourseCorba._AdminImplBase;
import BourseCorba._BourseImplBase;

public class BourseServant extends _BourseImplBase{

	public Admin connectAdmin(String login, String mdp) throws BourseCorba.ServerException {
		AdminServant a = null ;
		try {
			Utilisateur user = UtilisateurDAO.getInstance().getUtilisateur(login, mdp);
			if(user != null && user.getRole() == Utilisateur.ROLE_ADMIN){
				a = new AdminServant () ;
			}
			else {
				throw new BourseCorba.ServerException("user pas admin");
			}
		} catch (Exception e) {
			throw new BourseCorba.ServerException("user inexistant");
		}
		return a ;
	}

	public Compte connectUser(String login, String mdp)throws BourseCorba.ServerException {
		CompteServant a = null ;
		try {
			Utilisateur user = UtilisateurDAO.getInstance().getUtilisateur(login, mdp);
			if(user != null && user.getRole() == Utilisateur.ROLE_USER){
				a = new CompteServant() ;
				dao.Compte c = CompteDAO.getInstance().getCompteByUser(String.valueOf(user.getId()));
				if (c != null){
					a.setId(c.getId());
				}
				else {
					throw new BourseCorba.ServerException("user sans compte");
				}				
			}
			else {
				throw new BourseCorba.ServerException("user inconnu");
			}
		} catch (Exception e) {
			throw new BourseCorba.ServerException(e.getMessage());
		}
		return a ;
	}

	public ServiceWeb getServiceWeb() throws BourseCorba.ServerException {
		return new ServiceWebServant () ;
	}

}
