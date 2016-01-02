package polytheque.model.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import polytheque.model.pojos.Adherent;

public class AdherentDAO extends DAO {

	/**
	 * Methode de creation
	 * @param adherent
	 * @return boolean 
	 */
	public boolean create(Adherent adherent) {
		try {
			super.connect();
			PreparedStatement psInsert = connection.prepareStatement("INSERT INTO "
					+ "ADHERENT(nom, prenom, date_naissance, rue, cp, ville, mail, telephone, pseudo, mdp, admin,"
					+ "liste_noire, droits, nb_retards)"
					+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)"); 
			// On n'ajoute pas l'ID du jeu car il s'incrémente automatiquement dans la base de données
			psInsert.setString(1, adherent.getNom());
			psInsert.setString(2, adherent.getPrenom());
			psInsert.setDate(3, adherent.getDateNaissance());
			psInsert.setString(4, adherent.getRue());
			psInsert.setString(5, adherent.getCP());
			psInsert.setString(6, adherent.getVille());
			psInsert.setString(7, adherent.getMail());
			psInsert.setString(8, adherent.getTelephone());
			psInsert.setString(9, adherent.getPseudo());
			psInsert.setString(10, adherent.getMdp());
			psInsert.setBoolean(11, adherent.isAdmin());
			psInsert.setBoolean(12, true);
			psInsert.setBoolean(13, true);
			psInsert.setInt(14, 0);

			psInsert.executeUpdate();
			psInsert.closeOnCompletion();

			ResultSet idResult = psInsert.getGeneratedKeys();
			if (idResult != null && idResult.next()) {
				adherent.setIdAdherent(idResult.getInt(1));;
			} else {
				throw new SQLException();
			}

			super.disconnect();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Methode pour effacer
	 * @param id
	 * @return boolean 
	 */
	public boolean delete(int id) {
		try {
			super.connect();
			PreparedStatement psDelete = connection.prepareStatement("DELETE * FROM ADHERENT WHERE id_adherent = ?"); 
			psDelete.setInt(1, id);
			psDelete.execute();
			psDelete.closeOnCompletion();

			super.disconnect();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Methode de mise a jour
	 * @param obj
	 * @return boolean
	 */
	public boolean update(Adherent adherent) {
		try {
			super.connect();
			PreparedStatement psUpdate = connection.prepareStatement("UPDATE ADHERENT "
					+ "SET nom = ?, prenom = ?, date_naissance = ?, rue = ?, cp = ?, ville = ?, mail = ?, telephone = ?,"
					+ "pseudo = ?, mdp = ?, admin = ?,liste_noire = ?, droits = ?, nb_retards = ?)"
					+ "WHERE id_adherent = ?"); 
			psUpdate.setString(1, adherent.getNom());
			psUpdate.setString(2, adherent.getPrenom());
			psUpdate.setDate(3, adherent.getDateNaissance());
			psUpdate.setString(4, adherent.getRue());
			psUpdate.setString(5, adherent.getCP());
			psUpdate.setString(6, adherent.getVille());
			psUpdate.setString(7, adherent.getMail());
			psUpdate.setString(8, adherent.getTelephone());
			psUpdate.setString(9, adherent.getPseudo());
			psUpdate.setString(10, adherent.getMdp());
			psUpdate.setBoolean(11, adherent.isAdmin());
			psUpdate.setBoolean(12, adherent.peutEmprunter());
			psUpdate.setBoolean(13, adherent.estAJour());
			psUpdate.setInt(14, adherent.getCompteurRetard());
			psUpdate.setInt(15, adherent.getIdAdherent());

			psUpdate.executeUpdate();
			psUpdate.closeOnCompletion();

			super.disconnect();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Methode de recherche des informations
	 * @param id
	 * @return adherent
	 */
	public Adherent retreive(int id) {
		try {
			super.connect();
			PreparedStatement psSelect = connection.prepareStatement("SELECT * FROM ADHERENT WHERE id_adherent = ?");
			psSelect.setInt(1, id);
			psSelect.execute();
			psSelect.closeOnCompletion();

			ResultSet resSet = psSelect.getResultSet();
			Adherent adherent = null;
			if (resSet.next()) {
				adherent = new Adherent(id, resSet.getString("nom"), resSet.getString("prenom"), resSet.getDate("date_naissance"), 
									    resSet.getString("rue"), resSet.getString("code_postal"),resSet.getString("ville"),resSet.getString("mail"),resSet.getString("telephone"), 
									    resSet.getString("pseudo"), resSet.getString("mdp"), resSet.getBoolean(12), 
									    resSet.getBoolean("liste_noire"), resSet.getBoolean("droits"), resSet.getInt("nb_retards"));
			}
			super.disconnect();
			return adherent;
		} catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public boolean connectionAuthorized(String userName, String password) {
		boolean isAuthorized = false;
		try {
			super.connect();
			PreparedStatement psSelect = connection.prepareStatement("SELECT * FROM ADHERENT WHERE pseudo = ? AND mdp = ?");
			psSelect.setString(1, userName);
			psSelect.setString(2, password);
			psSelect.execute();
			psSelect.closeOnCompletion();

			ResultSet resSet = psSelect.getResultSet();
			if (resSet.next()) {
				isAuthorized = true;
			}
			super.disconnect();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return isAuthorized;
	}
}
