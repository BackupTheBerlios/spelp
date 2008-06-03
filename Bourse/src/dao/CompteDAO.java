package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;
import java.util.LinkedList;

public class CompteDAO {
	
	private static CompteDAO instance = new CompteDAO();

	private CompteDAO () {}
	
	public void affecterUtilisateurComtpe(String idUser, String idCompte) throws Exception {
		String affecterUtilisateurComtpe = String.format(
				"UPDATE COMPTE " +
				"SET id_utilisateur = %s " +
				"WHERE id = %s ", idUser,idCompte);
		
		int rs = 0;
		Statement s = null ;
		try {
			s = BourseDAO.getInstance().getConnection().createStatement();
			rs = s.executeUpdate(affecterUtilisateurComtpe);
			BourseDAO.getInstance().getConnection().commit();
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			throw e  ;
		}
		finally {
			try {
				s.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				throw e  ;
			}
		} 
	}
	
	public void updateCompte (Compte cpt) throws Exception {
		String update = String.format(
				"UPDATE COMPTE " +
				"SET cash = %s, " +
				"id_utilisateur = %s " +
				"WHERE id = %s ",String.valueOf(cpt.getCash()),String.valueOf(cpt.getId_utilisateur()),String.valueOf(cpt.getId())
				);
		
		int rs = 0 ;
		Statement s = null ;
		Utilisateur result = new Utilisateur() ;
		try {
			s = BourseDAO.getInstance().getConnection().createStatement();
			rs = s.executeUpdate(update);
			BourseDAO.getInstance().getConnection().commit();
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			throw e  ;
		}
		finally {
			try {
				s.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				throw e  ;
			}
		} 
	}
	 
	public Compte getCompteByUser (String idUser) throws Exception{ 
		String selectCompte = String.format(
				"SELECT id,cash,id_utilisateur " +
				"FROM COMPTE " +
				"WHERE id_utilisateur = %s ",idUser);
		
		ResultSet rs = null ;
		Statement s = null ;
		Compte result = new Compte() ;
		try {
			s = BourseDAO.getInstance().getConnection().createStatement();
			rs = s.executeQuery(selectCompte);
			
			if (!rs.next())
			{
				throw new Exception("Pas d'utilisateur");
			}
			result.setId(rs.getLong(1));
			result.setCash(rs.getDouble(2));
			result.setId_utilisateur(rs.getLong(3));
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw (e); 
		}
		finally {
			try {
				rs.close() ;
				s.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				throw (e);
			}
		} 
		return result ;
	}
	
	public Compte getCompte (String idCompte) throws Exception{ 
		String selectCompte = String.format(
				"SELECT id,cash,id_utilisateur " +
				"FROM COMPTE " +
				"WHERE id = %s ",idCompte);
		
		ResultSet rs = null ;
		Statement s = null ;
		Compte result = new Compte() ;
		try {
			s = BourseDAO.getInstance().getConnection().createStatement();
			rs = s.executeQuery(selectCompte);
			if (!rs.next())
			{
				throw new Exception("Compte inconnu");
			}
			result.setId(rs.getLong(1));
			result.setCash(rs.getDouble(2));
			result.setId_utilisateur(rs.getLong(3));
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			throw (e); 
		}
		finally {
			try {
				rs.close() ;
				s.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				throw (e);
			}
		} 
		return result ;
	}
	
	public Compte creerCompte (Compte cpt) throws Exception{
		long idCompte = BourseDAO.getInstance().generateId("COMPTE","id");
		String update = String.format(
				"INSERT INTO COMPTE (id, cash) VALUES ( " +
				"%s, %s)",String.valueOf(idCompte),String.valueOf(cpt.getCash())
		);
		int rs = 0 ;
		Statement s = null ;
		try {
			s = BourseDAO.getInstance().getConnection().createStatement();
			rs = s.executeUpdate(update);
			BourseDAO.getInstance().getConnection().commit();
			cpt.setId(idCompte);
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			throw e  ;
		}
		finally {
			try {
				s.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				throw e  ;
			}
		}
		return cpt ;
	}

	public Collection<Compte> getComptes () throws Exception{
		String selectCompte ="SELECT id,cash,id_utilisateur FROM COMPTE ";
		
		ResultSet rs = null ;
		Statement s = null ;
		LinkedList<Compte> result = new LinkedList<Compte>() ; 
		try {
			s = BourseDAO.getInstance().getConnection().createStatement();
			rs = s.executeQuery(selectCompte);
			while (rs.next())
			{
				Compte tmp = new Compte();
				tmp.setId(rs.getLong(1));
				tmp.setCash(rs.getDouble(2));
				tmp.setId_utilisateur(rs.getLong(3));
				result.add(tmp);
			}
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			throw (e); 
		}
		finally {
			try {
				rs.close() ;
				s.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				throw (e);
			}
		} 
		return result ;
	}
	
	public static CompteDAO getInstance() {
		// TODO Auto-generated method stub
		return instance  ;
	}
	
}
