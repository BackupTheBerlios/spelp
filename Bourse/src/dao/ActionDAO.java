package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;
import java.util.LinkedList;

public class ActionDAO {
	private static ActionDAO instance = new ActionDAO();
	
	public static ActionDAO getInstance() {
		return instance ;
	}
	
	private ActionDAO () {}

	public Action creerAction(Action action) throws Exception {
		long idAction = BourseDAO.getInstance().generateId("ACTION","id");
		String update = String.format(
				"INSERT INTO ACTION (id, id_titre, id_compte) VALUES ( " +
				"%s, %s, %s)",
				String.valueOf(idAction),
				String.valueOf(action.getId_titre()),
				String.valueOf(action.getId_compte())
		);
		int rs = 0;
		Statement s = null ;
		try {
			s = BourseDAO.getInstance().getConnection().createStatement();
			rs = s.executeUpdate(update);
			BourseDAO.getInstance().getConnection().commit();
			action.setId((int) idAction);
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
		return action ;
	}

	public Collection<Action> getAllActionsByIdCompte(long id) throws Exception {
		Collection <Action> result = new LinkedList<Action>();
		
		String selectUtilisateur ="SELECT id,id_titre,id_compte " +
				"FROM ACTION " +
				"WHERE id_compte = " + id ;
		ResultSet rs = null ;
		Statement s = null ;
		try {
			s = BourseDAO.getInstance().getConnection().createStatement();
			rs = s.executeQuery(selectUtilisateur);
			while (rs.next())
			{
				Action tmp = new Action () ;
				tmp.setId(rs.getInt(1));
				tmp.setId_titre(rs.getInt(2));
				tmp.setId_compte(rs.getInt(3));
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
