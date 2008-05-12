package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedList;

public class HistoriqueDAO {
	private static HistoriqueDAO instance = new HistoriqueDAO();
	
	public static HistoriqueDAO getInstance() {
		return instance ;
	}
	
	private HistoriqueDAO () {}

	public int creerHistorique(Historique h) throws Exception {
		long idHisto = BourseDAO.getInstance().generateId("TITRE","code");
		String update = String.format(
				"INSERT INTO HISTORIQUE (id, valeur, date_histo, id_titre) VALUES ( " +
				"%s, %s, '%s', %s)",
				String.valueOf(idHisto),
				h.getValeur(),
				new java.sql.Timestamp(new Date().getTime()).toString(),
				h.getId_titre()
		);
		int rs = 0;
		Statement s = null ;
		try {
			s = BourseDAO.getInstance().getConnection().createStatement();
			rs = s.executeUpdate(update);
			BourseDAO.getInstance().getConnection().commit();
			h.setId((int) idHisto);
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
		return (int) idHisto ;
	}
	
	public Historique getHistorique (String idHisto) throws Exception{ 
		String selectHisto = String.format(
				"SELECT id,valeur,date_histo,id_titre " +
				"FROM HISTORIQUE " +
				"WHERE id = %s ",idHisto);
		
		ResultSet rs = null ;
		Statement s = null ;
		Historique result = new Historique() ;
		try {
			s = BourseDAO.getInstance().getConnection().createStatement();
			rs = s.executeQuery(selectHisto);
			if (!rs.next())
			{
				throw new Exception("Pas d'historique");
			}
			result.setId(rs.getInt(1));
			result.setValeur(rs.getDouble(2));
			result.setDate_histo(rs.getTime(3).toString());
			result.setId_titre(rs.getInt(4));
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
	
	public Collection<Historique> getHistoriquesByTitre(int idTitre) throws SQLException {
		Collection<Historique> result = new LinkedList<Historique>() ;
		String selectHisto ="SELECT id,valeur,date_histo " +
		"FROM HISTORIQUE " +
		"WHERE id_titre = " + idTitre ;
		ResultSet rs = null ;
		Statement s = null ;
		try {
			s = BourseDAO.getInstance().getConnection().createStatement();
			rs = s.executeQuery(selectHisto);
			while (rs.next())
			{
				Historique tmp = new Historique () ;
				tmp.setId(rs.getInt(1));
				tmp.setValeur(rs.getDouble(2));
				tmp.setDate_histo(rs.getTime(3).toString());
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
	
}
