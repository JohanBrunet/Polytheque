package polytheque.view.modeles;

import java.sql.Date;

import javax.swing.table.AbstractTableModel;

/**
 * Classe permettant de modéliser la liste des jeux.
 * 
 * @author Johan Brunet
 */
@SuppressWarnings({ "serial"})
public class ModeleTableauAdherents extends AbstractTableModel {
	/**
	 * Les données des cellules.
	 */
	private Object[][] donnees;

	/**
	 * Les titres des colonnes.
	 */
	private String[] libelles;

	/**
	 * Création d'un modèle de tableau.
	 * 
	 * @param donnees
	 *            Des données.
	 * @param libelles
	 *            Des libellés.
	 */
	public ModeleTableauAdherents(Object[][] donnees, String[] libelles) {
		this.donnees = donnees;
		this.libelles = libelles;
	}

	@Override
	public int getColumnCount() {
		return this.libelles.length;
	}

	@Override
	public int getRowCount() {
		return this.donnees.length;
	}

	@Override
	public Object getValueAt(int row, int col) {
		return this.donnees[row][col];
	}

	@Override
	public String getColumnName(int col) {
		return this.libelles[col];
	}

	@Override
	public Class<?> getColumnClass(int col) {
		switch (col) {
		case 0 : 
			return String.class;
		case 1 : 
			return String.class;
		case 2 : 
			return Date.class;
		case 3 : 
			return String.class;
		case 4 : 
			return String.class;
		case 5 : 
			return String.class;
		case 6 : 
			return String.class;
		case 7 : 
			return String.class;
		case 8 : 
			return String.class;
		case 9 : 
			return Boolean.class;
		case 10 : 
			return Boolean.class;
		case 11 : 
			return Integer.class;
		default : 
			return Object.class;
		}
	}

	@Override
	public boolean isCellEditable(int row, int col) {
		int index;
		for (index = 0; index < this.libelles.length; index++) {
			if (this.getColumnName(col).equals(this.libelles[index])) {
				return false;
			}
		}
		return true;
	}

	public void refresh(Object[][] donnees) {
		this.donnees = donnees;
		this.fireTableDataChanged();
	}
}
