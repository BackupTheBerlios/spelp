package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;
import java.util.LinkedList;


public class AlarmeDAO {
	private static AlarmeDAO instance = new AlarmeDAO();
	
	public static AlarmeDAO getInstance() {
		return instance ;
	}
	
	private AlarmeDAO () {}

	public Alarme creerAlarme(Alarme alarme) throws Exception {
		long idAlarme = BourseDAO.getInstance().generateId("ALARME","id");
		String update = String.format(
				"INSERT INTO ALARME (id, type, seuil, id_titre, id_compte) VALUES ( " +
				"%s, %s, %s, %s, %s)",
				String.valueOf(idAlarme),			
				String.valueOf(alarme.getType()),
				String.valueOf(alarme.getSeuil()),
				String.valueOf(alarme.getId_titre()),
				String.valueOf(alarme.getId_compte())
		);
		int rs = 0;
		Statement s = null ;
		try {
			s = BourseDAO.getInstance().getConnection().createStatement();
			rs = s.executeUpdate(update);
			BourseDAO.getInstance().getConnection().commit();
			alarme.setId((int) idAlarme);
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
		return alarme ;
	}

	public Collection<Alarme> getAllAlarmesByIdCompte(long id) throws Exception {
		Collection <Alarme> result = new LinkedList<Alarme>();
		
		String selectAlarme ="SELECT id, type, seuil, id_titre, id_compte " +
				"FROM ALARME " +
				"WHERE id_compte = " + id ;
		ResultSet rs = null ;
		Statement s = null ;
		try {
			s = BourseDAO.getInstance().getConnection().createStatement();
			rs = s.executeQuery(selectAlarme);
			while (rs.next())
			{
				Alarme tmp = new Alarme () ;
				tmp.setId(rs.getInt(1));
				tmp.setType(rs.getInt(2));
				tmp.setSeuil(rs.getDouble(3));				
				tmp.setId_titre(rs.getInt(4));
				tmp.setId_compte(rs.getInt(5));
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
