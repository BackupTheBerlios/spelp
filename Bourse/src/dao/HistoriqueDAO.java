package dao;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

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
				new java.sql.Time(new Date().getTime()).toString(),
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
	
	
}
