package polytheque.model.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import polytheque.model.pojos.Reservation;

public class ReservationDAO extends DAO {

	
	public boolean create(Reservation reservation, int idAdherent, int idJeu, int idJextension) {
		try {
			super.connect();
			PreparedStatement psInsert = connection.prepareStatement("INSERT INTO "
					+ "RESERVATION(date_reservation, id_adherent, id_jeu, id_extension)"
					+ "VALUES (?, ?, ?, ?)"); 
			
			psInsert.setInt(1, reservation.getDate()); //A voir pcq return type"date"
			psInsert.setInt(2, idAdherent);
			psInsert.setInt(3, idJeu);
			psInsert.setInt(4, idJextension);

			psInsert.executeUpdate();

			ResultSet idResult = psInsert.getGeneratedKeys();
			if (idResult != null && idResult.next()) {
				reservation.setIdReservation(idResult.getInt(1));; 
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
	 * @param Jeu
	 * @return boolean 
	 */
	public boolean delete(int id) {
		try {
			super.connect();
			PreparedStatement psDelete = connection.prepareStatement("DELETE * FROM RESERVATION WHERE id_reservation = ?"); 
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
	public boolean update(Reservation reservation, int idAdherent, int idJeu, int idJextension, int idReservation) {
		try {

			super.connect();
			PreparedStatement psUpdate = connection.prepareStatement("UPDATE RESERVATION "
					+ "SET date_reservation = ?, id_adherent = ?, id_jeu = ?, id_extension = ?)" + " WHERE id_reservation = ?");
			psUpdate.setInt(1, reservation.getDate());
			psUpdate.setInt(2, idAdherent);
			psUpdate.setInt(3, idJeu);
			psUpdate.setInt(4, idJextension);
			psUpdate.setInt(5, idReservation);
		
			
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
	 * @return T
	 */
	public Reservation retreive(int idReservation) {
		try {
			super.connect();
			PreparedStatement psSelect = connection.prepareStatement("SELECT * FROM RESERVATION WHERE id_reservation = ?");
			psSelect.setInt(1, idReservation);
			psSelect.execute();
			psSelect.closeOnCompletion();

			ResultSet resSet = psSelect.getResultSet();
			Reservation reservation = null;
			if (resSet.next()) { // On se place sur le 1er résultat
				reservation = new Reservation(idReservation, resSet.getInt(1), resSet.getInt(2), resSet.getInt(3), resSet.getInt(4));
			}
			super.disconnect();
			return reservation;
		} catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
}
