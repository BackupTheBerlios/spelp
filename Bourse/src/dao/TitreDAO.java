package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedList;



public class TitreDAO {
	private static TitreDAO instance = new TitreDAO();
	
	public static TitreDAO getInstance() {
		return instance ;
	}
	
	private TitreDAO () {}
	
	public Titre creerTitre (Titre t) throws Exception{
		long idTitre = BourseDAO.getInstance().generateId("TITRE","code");
		String update = String.format(
				"INSERT INTO TITRE (code, libelle, date_intro, cours_intro, cours) VALUES ( " +
				"%s, '%s', '%s', %s, %s)",
				String.valueOf(idTitre),
				t.getLibelle(),
				new java.sql.Date(new Date().getTime()).toString(),
				t.getCoursIntro(),
				t.getCoursIntro()
		);
		int rs = 0;
		Statement s = null ;
		try {
			s = BourseDAO.getInstance().getConnection().createStatement();
			rs = s.executeUpdate(update);
			BourseDAO.getInstance().getConnection().commit();
			t.setCode((int) idTitre);
		}
		catch (SQLException e) {
			throw e  ;
		}
		finally {
			try {
				s.close();
			} catch (SQLException e) {
				throw e  ;
			}
		}
		return t ;
	}

	public Titre getTitre(int idTitre) throws Exception {
		String selectTitre = String.format(
				"select code,libelle,date_intro,cours_intro, cours from TITRE " +
				"WHERE code = %s " , String.valueOf(idTitre));
		
		ResultSet rs = null ;
		Statement s = null ;
		Titre result = null ;
		try {
			s = BourseDAO.getInstance().getConnection().createStatement();
			rs = s.executeQuery(selectTitre);
			if (rs.next()){
				result = new Titre();
				result.setCode(rs.getInt(1));
				result.setLibelle(rs.getString(2));
				result.setDateIntro(rs.getDate(3).toString());
				result.setCoursIntro(rs.getDouble(4));
				result.setCours(rs.getDouble(5));
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}
		finally {
			try {
				rs.close() ;
				s.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} 
		return result ;
	}

	public Collection<Titre> getTitres() throws Exception {
		String selectTitre ="SELECT code,libelle,date_intro,cours_intro,cours FROM TITRE ";
		ResultSet rs = null ;
		Statement s = null ;
		LinkedList<Titre> result = new LinkedList<Titre>() ; 
		try {
			s = BourseDAO.getInstance().getConnection().createStatement();
			rs = s.executeQuery(selectTitre);
			while (rs.next())
			{
				Titre tmp = new Titre () ;
				tmp.setCode(rs.getInt(1));
				tmp.setLibelle(rs.getString(2));
				tmp.setDateIntro(rs.getDate(3).toString());
				tmp.setCoursIntro(rs.getDouble(4));
				tmp.setCours(rs.getDouble(5));
				result.add(tmp);
			}
		}
		catch (SQLException e) {
			throw (e); 
		}
		finally {
			try {
				rs.close() ;
				s.close();
			} catch (SQLException e) {
				throw (e);
			}
		} 
		return result ;
	}

	public void updateTitre(Titre t) throws Exception {
		String update = String.format(
				"UPDATE TITRE " +
				"SET cours = %s " +
				"WHERE code = %s ",
				String.valueOf(t.getCours()),
				String.valueOf(t.getCode())
				);
		
		int rs = 0 ;
		Statement s = null ;
		try {
			s = BourseDAO.getInstance().getConnection().createStatement();
			rs = s.executeUpdate(update);
			BourseDAO.getInstance().getConnection().commit();
		}
		catch (SQLException e) {
			throw e  ;
		}
		finally {
			try {
				s.close();
			} catch (SQLException e) {
				throw e  ;
			}
		} 
	}
}
