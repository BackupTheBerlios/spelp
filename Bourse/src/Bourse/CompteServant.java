package Bourse;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import BourseCorba.ActionMontant;
import BourseCorba.Alarme;
import BourseCorba.ClientAlarme;
import BourseCorba.Historique;
import BourseCorba.ServerException;
import BourseCorba.Titre;
import BourseCorba.TitreDetaille;
import BourseCorba._CompteImplBase;
import dao.Action;
import dao.ActionDAO;
import dao.AlarmeDAO;
import dao.Compte;
import dao.CompteDAO;
import dao.HistoriqueDAO;
import dao.TitreDAO;

import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CompteServant extends _CompteImplBase {
	private long id ; 
	
	private static List<CustomClientAlarm> clientsAlarme = new LinkedList<CustomClientAlarm>();
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}	

	public static int SUP = 0 ;
	public static int INF = 1 ;
        
        public Alarme[] getAlarmes() {
            try {
                Collection<dao.Alarme> alarmes = AlarmeDAO.getInstance().getAllAlarmesByIdCompte(id);
                Alarme[] result = new Alarme[alarmes.size()];
                LinkedList<Alarme> liste = new LinkedList<Alarme>();
                for (dao.Alarme alarmeDuCompte : alarmes) {
                    Alarme a = new Alarme();
                    a.idTitre = alarmeDuCompte.getId_titre();
                    a.seuil = alarmeDuCompte.getSeuil();
                    a.type = (short) alarmeDuCompte.getType();
                    liste.add(a) ;
                }
                result = liste.toArray(result);
                return result ;
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            return new Alarme[0];
        }
        
        
	public static void checkAlarmes (final int idTitre, final double cours) {
		final List<CustomClientAlarm> toDestroy = new LinkedList<CustomClientAlarm>();
		final Collection<Thread> collecTreads = new LinkedList<Thread>() ;
		synchronized (clientsAlarme) {
			// pour toutes les alarmes on va tester si une doit etre declenchee
			for (final CustomClientAlarm c : clientsAlarme){
				try {
					Collection<dao.Alarme> alarmes = AlarmeDAO.getInstance().getAllAlarmesByIdCompte(c.getIdCompte());
					for (dao.Alarme alarmeDuCompte : alarmes){
						if (alarmeDuCompte.getId_titre() == idTitre){
							if (alarmeDuCompte.getType() == SUP){
								if (cours > alarmeDuCompte.getSeuil()){
									// notification asynchrone de l'alarme
									final ClientAlarme tmp = c.getClient();
										Thread t = new Thread(new Runnable(){
											public void run() {
												try {
													tmp.notifie(idTitre,cours);
												}
												// si la notification provoque une erreur 
												// c'est du a un probleme de deconnexion
												// donc on supprime la reference du client
												catch (Exception e){
													toDestroy.add(c);
												}
											}});
										collecTreads.add(t);
										t.start() ;
								}
							}
							else {
								if (cours < alarmeDuCompte.getSeuil()){
									Thread t = new Thread(new Runnable(){
										public void run() {
											try {
												c.getClient().notifie(idTitre,cours);
											}
											// si la notification provoque une erreur 
											// c'est du a un probleme de deconnexion
											// donc on supprime la reference du client
											catch (Exception e){
												toDestroy.add(c);
											}
										}});
									collecTreads.add(t);
									t.start() ;
								}
							}
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		// on va attendre que les alarmes soient traitées dans un thread 
		// et le serveur pourra continuer sa tache
		Thread clean = new Thread(new Runnable(){
			public void run() {
				for (Thread t : collecTreads){
					try {
						t.join();
					} catch (InterruptedException e) {
						e.printStackTrace();
					} 
				}
				// une fois tous les threads d'alarmes terminés 
				// on supprime les clients de la liste d'alarme
				for (int i = 0 ; i < toDestroy.size();i++){
					synchronized(clientsAlarme){
						CustomClientAlarm tmp = toDestroy.get(i);
						clientsAlarme.remove(toDestroy.get(i));
					}
				}
			}
		});
		clean.start();
		
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
			return nouvelleAction.getId();
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServerException(e.getMessage());
		}		
	}
	
	public void vendreAction(int idAction)throws ServerException {
		try {
			Action aVendre = ActionDAO.getInstance().getActionById(idAction);
			dao.Titre t = TitreDAO.getInstance().getTitre(aVendre.getId_titre());
			Compte compteCourant = CompteDAO.getInstance().getCompte(String.valueOf(id));
			double cash = compteCourant.getCash() ;
			cash = cash + t.getCours() ;
			compteCourant.setCash(cash) ;
			ActionDAO.getInstance().supprimerAction(idAction)  ;
			CompteDAO.getInstance().updateCompte(compteCourant);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServerException(e.getMessage());
		}
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

	public TitreDetaille getTitre(int id) throws ServerException{
		TitreDetaille td = new TitreDetaille () ;
		dao.Titre t;
		try {
			// information from the titre
			t = TitreDAO.getInstance().getTitre(id);
			td.id = id ;
			td.libelle = t.getLibelle() ;
			td.dateIntroduction = t.getDateIntro();
			td.coursIntrodution = t.getCoursIntro() ;
			Collection<dao.Historique> listeHistoriques = HistoriqueDAO.getInstance().getHistoriquesByTitre(id);
			Historique[] historiques = new HistoriqueServant[listeHistoriques.size()];
			double plushaut = td.coursIntrodution ;
			double plusbas = td.coursIntrodution;
			int i = 0 ;
			for (dao.Historique h : listeHistoriques) {	
				if (h.getValeur() >= plushaut) {
					plushaut = h.getValeur() ;
				}
				if (h.getValeur() < plusbas){
					plusbas = h.getValeur() ;
				}
				historiques[i] = new HistoriqueServant(h.getId()) ;
				i ++ ;
			}
			if (plusbas != Double.MAX_VALUE) {
				td.coursLePlusBas = plusbas ;				
			}
			else {
				td.coursLePlusBas = 0 ;
			}
			td.coursLePlusHaut = plushaut ;
			// information from histo
			td.histo = historiques  ;
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServerException(e.getMessage());
		} 
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
		dao.Alarme al = new dao.Alarme();	
		al.setSeuil(a.seuil);
		al.setType(a.type);
		al.setId_titre(a.idTitre);
		al.setId_compte((int) this.id);
		try {
                    AlarmeDAO.getInstance().creerAlarme(al);
		} catch (Exception e) {
                    e.printStackTrace();
		}
	}

	public void enregistrerClientAlarme(ClientAlarme ca) {
		CustomClientAlarm custom = new CustomClientAlarm();
		custom.setClient(ca);
		custom.setIdCompte((int) this.id);
		clientsAlarme.add(custom);
	}

    

    

}
