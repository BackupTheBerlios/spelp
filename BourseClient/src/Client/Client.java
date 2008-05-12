package Client;

import javax.naming.directory.InitialDirContext;

import org.omg.CORBA.ORB;
import org.omg.CosNaming.NameComponent;
import org.omg.CosNaming.NamingContext;
import org.omg.CosNaming.NamingContextHelper;

import BourseCorba.ActionMontant;
import BourseCorba.Admin;
import BourseCorba.Bourse;
import BourseCorba.BourseHelper;
import BourseCorba.Compte;
import BourseCorba.CompteAdmin;
import BourseCorba.Historique;
import BourseCorba.ServiceWeb;
import BourseCorba.Titre;
import BourseCorba.TitreDetaille;

public class Client {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try{
			
			// create and initialize the ORB
            ORB orb = ORB.init(args, null);
 
            // get the root naming context
            org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
            NamingContext ncRef = NamingContextHelper.narrow(objRef);

            // resolve the Object Reference in Naming
            NameComponent nc = new NameComponent("Bourse", "");
            NameComponent path[] = {nc}; 
         
            Bourse bourseRef = BourseHelper.narrow(ncRef.resolve(path));
            
            // Creation de deux comptes par l'admin
            Admin adminRef = bourseRef.connectAdmin("admin", "admin");     
            int idCompte = adminRef.creerCompte(100);
            System.out.println("compte creer " + idCompte);
            adminRef.ajoutCash(idCompte, 100);
            adminRef.diminuerCash(idCompte, 50);
            int idNico = adminRef.insererUtilisateur("CASTEL", "NICOLAS", "NICASTEL", "1234", 1);
            int idTristan = adminRef.insererUtilisateur("FAURE", "TRISTAN", "TRFAURE", "1234", 1);
            
            System.out.println("DEUX IDS : Nico : "+ idNico + " & Tristan : "+idTristan);
            adminRef.affecterCompte(idNico, idCompte);
            CompteAdmin[] comptes = adminRef.getComptes();
            for (CompteAdmin a : comptes){
            	System.out.format("Compte : %s, utilisateur : %s%n"
            			, a.id,a.nom);
            }
            
            // creation de deux titres via l'interface du service web            
            ServiceWeb serviceWebRef = bourseRef.getServiceWeb() ;
            int titreAlcatel = serviceWebRef.creerTitre("ALCATEL", 1.25) ;
            int titreCS = serviceWebRef.creerTitre("CS", 0.95) ;
            System.out.println("ALCATEL (1.25) : " + titreAlcatel );
            System.out.println("CS (0.95) : " + titreCS);
            
            // Mise a hour du cours d'una action via le service web
            serviceWebRef.majCoursAction(titreAlcatel, 2.50);        
            
            // Connection d'un client             
            Compte compteNico = bourseRef.connectUser("NICASTEL", "1234");
            if (compteNico != null){
            	System.out.println(compteNico);
            	System.out.println("CASH " + compteNico.cash());
            	
            	// Achat d'actions
                compteNico.acheterAction(titreAlcatel);
                System.out.println("CASH APRES ACHAT ACTION " + compteNico.cash());
                compteNico.acheterAction(titreAlcatel);
                System.out.println("CASH APRES ACHAT ACTION " + compteNico.cash());
                compteNico.acheterAction(titreCS);
                System.out.println("CASH APRES ACHAT ACTION " + compteNico.cash());
                
                // Affichage du portefeuille
                ActionMontant[] actions = compteNico.getActionsAvecMontant();
                for (ActionMontant a : actions){
                	System.out.println("Action : " + a.libelleTitre + "(" + a.idAction + ") = " + a.montant );
                }
                double portefeuille = compteNico.getMontantPortefeuille() ;
                System.out.println("MONTANT PORTEFEUILLE : " + portefeuille);
                
                // Affichage des cours des titres
                Titre[] allTitres = compteNico.getTitres() ;
                for (Titre t : allTitres) {
                	System.out.println("TITRE " + t.id + " : " + t.libelle + " = " + t.cours);
                }
                
                // Affichage detaillé des cours des titres
                Titre[] allTitres2 = compteNico.getTitres() ;
                for (Titre t : allTitres2) {
                	System.out.println("TITRE " + t.id + " : " + t.libelle + " = " + t.cours);

                	// Affichage detaillé du titre courant
                	TitreDetaille titre = compteNico.getTitre(t.id);
                	System.out.println("TITRE DETAILLE :\n " +
                			"\tLIBELLE : \t" + titre.libelle +"\n " +
                			"\tINTRO :  \t" + titre.coursIntrodution +" le " + titre.dateIntroduction + "\n" +
                			"\tPLUS BAS : \t" + titre.coursLePlusBas +"\n " +
                			"\tPLUS HAUT : \t" + titre.coursLePlusHaut + "\n");
                	
                	// Affichage de l'historique du titre courant
                	Historique[] histos = titre.histo;
                	System.out.println("HISTORIQUE : ");
                	for (Historique h : histos){
                		System.out.println(h.dateHistorique() + " : " + h.valeur());
                	}                	
                }               
                
            }
            
           
            
        } catch (Exception e) {
            System.out.println("ERROR : " + e) ;
            e.printStackTrace(System.out);
        }

	}

}
