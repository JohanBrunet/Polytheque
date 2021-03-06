package polytheque.model.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import polytheque.model.pojos.Adherent;

/**
 * Classe permettant de manipuler des objets de type Adherent dans la BDD.
 * 
 * @author Johan Brunet
 *
 */
public class AdherentDAO extends DAO {

	/**
	 * Méthode de création d'un nouvel adhérent.
	 * @param adherent
	 * @return boolean 
	 */
	public boolean create(Adherent adherent) {
		try {
			super.connect();
			PreparedStatement psInsert = connection.prepareStatement("INSERT INTO "
					+ "ADHERENT(nom, prenom, date_naissance, rue, code_postal, ville, mail, telephone, pseudo, mdp, admin, "
					+ "liste_noire, droits, nb_retards, nb_non_recup) "
					+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS); 
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
			psInsert.setBoolean(12, adherent.peutEmprunter());
			psInsert.setBoolean(13, adherent.estAJour());
			psInsert.setInt(14, adherent.getCompteurRetard());
			psInsert.setInt(15, adherent.getNbNonRecup());

			psInsert.executeUpdate();

			ResultSet idResult = psInsert.getGeneratedKeys();
			if (idResult != null && idResult.next()) {
				adherent.setIdAdherent(idResult.getInt(1));
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
	 * @param pseudo
	 * 		Le pseudo de l'adhérent à supprimer.
	 * @return true si la suppression a été effectuée, false sinon.
	 */
	public boolean deleteAdherent(String pseudo) {
		try {
			super.connect();
			PreparedStatement psDelete = connection.prepareStatement("DELETE FROM ADHERENT WHERE pseudo = ?"); 
			psDelete.setString(1, pseudo);
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
	 * Methode de mise a jour.
	 * @param adherent
	 * 		L'adhérent à mettre à jour
	 * @return true si la modification a été effectuée,false sinon.
	 */
	public boolean update(Adherent adherent) {
		try {
			super.connect();
			PreparedStatement psUpdate = connection.prepareStatement("UPDATE ADHERENT "
					+ "SET nom = ?, prenom = ?, date_naissance = ?, rue = ?, code_postal = ?, ville = ?, mail = ?, telephone = ?,"
					+ "pseudo = ?, mdp = ?, admin = ?,liste_noire = ?, droits = ?, nb_retards = ?, nb_non_recup = ? "
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
			psUpdate.setInt(15, adherent.getNbNonRecup());
			psUpdate.setInt(16, adherent.getIdAdherent());

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
	 * Mise à jour du nombre de retards de l'adhérent;
	 * @param compteurRetard
	 * 		Le nombre de retards à enregistrer.
	 * @param id
	 * 		L'ID de l'adhérent à qui ajouter les retards.
	 * @return
	 */
	public boolean updateRetard(int compteurRetard, int id) {
		try {
			super.connect();
			PreparedStatement psUpdate = connection.prepareStatement("UPDATE ADHERENT "
					+ "SET nb_retards = ? "
					+ "WHERE id_adherent = ?"); 
			psUpdate.setInt(1, compteurRetard);
			psUpdate.setInt(2, id);
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
	 * Methode de recherche des informations.
	 * @param id
	 *		L'ID de l'adhérent à retrouver.
	 * @return adherent
	 */
	public Adherent retreive(int id) {
		Adherent adherent = null;
		try {
			super.connect();
			PreparedStatement psSelect = connection.prepareStatement("SELECT * FROM ADHERENT WHERE id_adherent = ?");
			psSelect.setInt(1, id);
			psSelect.execute();
			psSelect.closeOnCompletion();

			ResultSet resSet = psSelect.getResultSet();
			if (resSet.next()) {
				adherent = new Adherent(id, resSet.getString("nom"), resSet.getString("prenom"), resSet.getDate("date_naissance"), 
						resSet.getString("rue"), resSet.getString("code_postal"),resSet.getString("ville"),resSet.getString("mail"),resSet.getString("telephone"), 
						resSet.getString("pseudo"), resSet.getString("mdp"), resSet.getBoolean(12), 
						resSet.getBoolean("liste_noire"), resSet.getBoolean("droits"), resSet.getInt("nb_retards"), resSet.getInt("nb_non_recup"));
			}
			super.disconnect();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return adherent;
	}

	/**
	 * Retrouver un adhérent grâce à son pseudo.
	 * @param pseudo
	 * 		Le pseudo de l'adhérent à retrouver.
	 * @return L'adhérent récupéré.
	 */
	public Adherent getByPseudo(String pseudo) {
		Adherent adherent = null;
		try {
			super.connect();
			PreparedStatement psSelect = connection.prepareStatement("SELECT * FROM ADHERENT WHERE pseudo = ?");
			psSelect.setString(1, pseudo);
			psSelect.execute();
			psSelect.closeOnCompletion();

			ResultSet resSet = psSelect.getResultSet();
			if (resSet.next()) {
				adherent = new Adherent(resSet.getInt("id_adherent"), resSet.getString("nom"), resSet.getString("prenom"), resSet.getDate("date_naissance"), 
						resSet.getString("rue"), resSet.getString("code_postal"),resSet.getString("ville"),resSet.getString("mail"),resSet.getString("telephone"), 
						pseudo, resSet.getString("mdp"), resSet.getBoolean(12), 
						resSet.getBoolean("liste_noire"), resSet.getBoolean("droits"), resSet.getInt("nb_retards"), resSet.getInt("nb_non_recup"));
			}
			super.disconnect();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return adherent;
	}
	/**
	 * Methode de recuperation des jeux
	 * @return La liste de tous les jeux
	 */
	public ArrayList<Adherent> getAll() {
		ArrayList<Adherent> tousLesAdherent = new ArrayList<>();
		try {
			super.connect();
			PreparedStatement psSelect = connection.prepareStatement("SELECT * FROM ADHERENT ORDER BY nom ASC");
			psSelect.execute();
			psSelect.closeOnCompletion();
			ResultSet resSet = psSelect.getResultSet();
			while (resSet.next()) { // On se place sur le 1er résultat				
				tousLesAdherent.add(new Adherent(resSet.getInt("id_adherent"), resSet.getString("nom"), resSet.getString("prenom"), resSet.getDate("date_naissance"), 
						resSet.getString("rue"), resSet.getString("code_postal"), resSet.getString("ville"), 
						resSet.getString("mail"), resSet.getString("telephone"), resSet.getString("pseudo"), 
						resSet.getString("mdp"), resSet.getBoolean("admin"), resSet.getBoolean("liste_noire"),
						resSet.getBoolean("droits"), resSet.getInt("nb_retards"), resSet.getInt("nb_non_recup")));
			}
			super.disconnect();

		} catch(SQLException e) {
			e.printStackTrace();
		}
		return tousLesAdherent;
	}

	/**
	 * Methode de recherche d'adhérent(s)
	 * @param nomAdherent
	 * 			Le nom du jeu à récupérer dans la BDD
	 * @return Un jeu
	 */
	public ArrayList<Adherent> searchByName(String nomAdherent) {
		ArrayList<Adherent> adherentsFiltres = new ArrayList<>();
		String filtre = "%" + nomAdherent.toLowerCase() + "%";
		try {
			super.connect();
			PreparedStatement psSelect = connection.prepareStatement("SELECT * FROM ADHERENT WHERE nom  LIKE ? ORDER BY nom ASC");
			psSelect.setString(1, filtre);
			psSelect.execute();
			psSelect.closeOnCompletion();

			ResultSet resSet = psSelect.getResultSet();
			while (resSet.next()) { // On se place sur le 1er résultat
				adherentsFiltres.add(new Adherent(resSet.getInt("id_adherent"), resSet.getString("nom"), resSet.getString("prenom"), resSet.getDate("date_naissance"), 
						resSet.getString("rue"), resSet.getString("code_postal"), resSet.getString("ville"), 
						resSet.getString("mail"), resSet.getString("telephone"), resSet.getString("pseudo"), 
						resSet.getString("mdp"), resSet.getBoolean("admin"), resSet.getBoolean("liste_noire"),
						resSet.getBoolean("droits"), resSet.getInt("nb_retards"), resSet.getInt("nb_non_recup")));
			}
			super.disconnect();

		} catch(SQLException e) {
			e.printStackTrace();
		}
		return adherentsFiltres;
	}

	public Adherent connectionAuthorized(String userName, String password) {
		Adherent adherent = null;
		try {
			super.connect();
			PreparedStatement psSelect = connection.prepareStatement("SELECT * FROM ADHERENT WHERE pseudo = ? AND mdp = ?");
			psSelect.setString(1, userName);
			psSelect.setString(2, password);
			psSelect.execute();
			psSelect.closeOnCompletion();

			ResultSet resSet = psSelect.getResultSet();
			if (resSet.next()) {
				adherent = new Adherent(resSet.getInt("id_adherent"), resSet.getString("nom"), resSet.getString("prenom"), resSet.getDate("date_naissance"), 
						resSet.getString("rue"), resSet.getString("code_postal"),resSet.getString("ville"),resSet.getString("mail"),resSet.getString("telephone"), 
						resSet.getString("pseudo"), resSet.getString("mdp"), resSet.getBoolean(12), 
						resSet.getBoolean("liste_noire"), resSet.getBoolean("droits"), resSet.getInt("nb_retards"), resSet.getInt("nb_non_recup"));
			}
			super.disconnect();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return adherent;
	}

	/**
	 * Obtient la colonne définissant si un adhérent est ou non un
	 * administrateur
	 * 
	 * @param pseudo
	 * 		Le pseudo de 'adhérent dont on veut connaitre les droits admin.
	 * @return La valeur de la colonne définissant si l'adhérent est administrateur
	 */
	public boolean isAdmin(String pseudo) {
		boolean isAdmin = false;
		try {
			super.connect();

			PreparedStatement psSelect = connection.prepareStatement("SELECT admin FROM ADHERENT WHERE pseudo = ?");
			psSelect.setString(1, pseudo);
			psSelect.execute();
			psSelect.closeOnCompletion();

			ResultSet resultSet = psSelect.getResultSet();
			if (resultSet.next()) {
				if(resultSet.getBoolean("admin")) {
					isAdmin = true;
				}
			}

			super.disconnect();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return isAdmin;
	}
}
