package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;
import java.util.LinkedList;

public class UtilisateurDAO {
	private static UtilisateurDAO instance = new UtilisateurDAO();

	
	private UtilisateurDAO () {
		
	}
	public Utilisateur getUtilisateur (String id) throws Exception {
		String selectUser = String.format(
				"select id,prenom,nom,login,mdp,role from UTILISATEUR " +
				"WHERE ID = %s", id);
		
		ResultSet rs = null ;
		Statement s = null ;
		Utilisateur result = new Utilisateur() ;
		try {
			s = BourseDAO.getInstance().getConnection().createStatement();
			rs = s.executeQuery(selectUser);
			if (!rs.next())
			{
				throw new Exception("Pas d'utilisateur");
			}
			result.setId(rs.getLong(1));
			result.setLogin(rs.getString(4));
			result.setMdp(rs.getString(5));
			result.setNom(rs.getString(3));
			result.setPrenom(rs.getString(2));
			result.setRole(rs.getInt(6));
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			try {
				rs.close() ;
				s.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} 
		
		return result ;
	}
	
	public Utilisateur getUtilisateur (String login, String mdp) {
		String selectUser = String.format(
				"select id,prenom,nom,login,mdp,role from UTILISATEUR " +
				"WHERE login = '%s' " +
				"AND mdp = '%s'", login,mdp);
		
		ResultSet rs = null ;
		Statement s = null ;
		Utilisateur result = null ;
		try {
			s = BourseDAO.getInstance().getConnection().createStatement();
			rs = s.executeQuery(selectUser);
			if (rs.next()){
				result = new Utilisateur();
				result.setId(rs.getLong(1));
				result.setLogin(rs.getString(4));
				result.setMdp(rs.getString(5));
				result.setNom(rs.getString(3));
				result.setPrenom(rs.getString(2));
				result.setRole(rs.getInt(6));
			}
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			try {
				rs.close() ;
				s.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} 
		
		return result ;
	}
	
	public Collection<Utilisateur> getUtilisateurs () throws Exception{
		String selectUtilisateur ="SELECT id,prenom,nom,login,mdp,role FROM UTILISATEUR ";
		ResultSet rs = null ;
		Statement s = null ;
		LinkedList<Utilisateur> result = new LinkedList<Utilisateur>() ; 
		try {
			s = BourseDAO.getInstance().getConnection().createStatement();
			rs = s.executeQuery(selectUtilisateur);
			while (rs.next())
			{
				Utilisateur tmp = new Utilisateur () ;
				tmp.setId(rs.getLong(1));
				tmp.setPrenom(rs.getString(2));
				tmp.setNom(rs.getString(3));
				tmp.setLogin(rs.getString(4));
				tmp.setMdp(rs.getString(5));
				tmp.setRole(rs.getInt(6));
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
	
	public Utilisateur creerUtilisateur (Utilisateur usr) throws Exception{
		long idUser = BourseDAO.getInstance().generateId("UTILISATEUR","id");
		String update = String.format(
				"INSERT INTO UTILISATEUR (id, prenom, nom, login, mdp, role) VALUES ( " +
				"%s, '%s', '%s', '%s', '%s', %s)",
				String.valueOf(idUser),
				usr.getPrenom(),
				usr.getNom(),
				usr.getLogin(),
				usr.getMdp(),
				String.valueOf(usr.getRole())
		);
		int rs = 0;
		Statement s = null ;
		try {
			s = BourseDAO.getInstance().getConnection().createStatement();
			rs = s.executeUpdate(update);
			BourseDAO.getInstance().getConnection().commit();
			usr.setId(idUser);
		}
		catch (SQLException e) {
			e.printStackTrace() ;
			throw e  ;
		}
		finally {
			try {
				s.close();
			} catch (SQLException e) {
				e.printStackTrace() ;
				throw e  ;
			}
		}
		return usr ;
	}

	public static UtilisateurDAO getInstance() {
		// TODO Auto-generated method stub
		return instance ;
	}
}
