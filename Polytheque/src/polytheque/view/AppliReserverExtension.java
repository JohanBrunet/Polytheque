
package polytheque.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.toedter.calendar.JDateChooser;

import polytheque.model.pojos.Reservation;

@SuppressWarnings("serial")

public class AppliReserverExtension extends JPanel implements ActionListener {

	private TacheDAffichage tacheDAffichageDeLApplication;
	private JButton boutonValider;
	private JTextField searchContent;
	private JDateChooser dateChooser;


	public AppliReserverExtension(TacheDAffichage afficheAppli) {		
		this.tacheDAffichageDeLApplication = afficheAppli;
		creerPanneauRecherche();
		creerPanneauDate();
	}


	private void creerPanneauRecherche() {
		JPanel searchPanel = new JPanel();
		searchPanel.setPreferredSize(new Dimension(TacheDAffichage.LARGEUR, 50));
		JLabel labelSearch = new JLabel("Recherche par nom :");
		labelSearch.setBounds(400, 100, 100, 30);
		searchPanel.add(labelSearch);
		this.searchContent = new JTextField();
		this.searchContent.setBounds(450, 100, 100, 30);
		this.searchContent.setColumns(10);
		searchPanel.add(this.searchContent, BorderLayout.NORTH);
		this.add(searchPanel);		
	}

	private void creerPanneauDate() {
		JPanel DatePanel = new JPanel();
		DatePanel.setPreferredSize(new Dimension(TacheDAffichage.LARGEUR, 50));
		JLabel labelDate = new JLabel("Cliquez sur la date a laquelle vous voudriez emprunter le jeux :");
		labelDate.setBounds(400, 200, 100, 30);
		DatePanel.add(labelDate);
		this.dateChooser = new JDateChooser();
		this.dateChooser.setBounds(450, 200, 150, 30);
		DatePanel.add(this.dateChooser);
		this.add(DatePanel);
		this.boutonValider = new JButton("Valider");
		this.boutonValider.setBounds(400, 300, 100, 30);
		this.boutonValider.addActionListener(this);
		this.add(boutonValider);
	}

	public void actionPerformed(ActionEvent e) {
		JButton boutonSelectionne = (JButton) e.getSource();
		Date currentDate = new Date(new java.util.Date().getTime());
		if (boutonSelectionne == this.boutonValider && this.dateChooser.getDate() != null && this.searchContent.getText() != null && this.dateChooser.getDate().after(currentDate)) {
			int NbExemplaires = this.tacheDAffichageDeLApplication.getExt(this.searchContent.getText()).getNbExemplaires();
			int NbReserves = this.tacheDAffichageDeLApplication.getExt(this.searchContent.getText()).getNbReserves();
			if (NbExemplaires > NbReserves ) {
				Date dateReservationExt = new Date(this.dateChooser.getDate().getTime());
				int IdAdherent = this.tacheDAffichageDeLApplication.getAdherentByNothing().getIdAdherent();
				int IdExtention = this.tacheDAffichageDeLApplication.getExt(this.searchContent.getText()).getIdExtension();
				Reservation reservation = new Reservation(IdExtention,dateReservationExt,IdAdherent);

				if(this.tacheDAffichageDeLApplication.createReservation3(reservation,IdAdherent,IdExtention,dateReservationExt)) {
					this.tacheDAffichageDeLApplication.afficherMessage("R�servation d'extension confirm�e"," Confirmation", JOptionPane.INFORMATION_MESSAGE);
					this.tacheDAffichageDeLApplication.afficherAccueil();
				}
			}
			else {
				this.tacheDAffichageDeLApplication.afficherMessage("Cette extension n'est plus disponible, veuillez en choisir un autre !"," Probl�me ", JOptionPane.INFORMATION_MESSAGE);
			}
		}
		else {
			this.tacheDAffichageDeLApplication.afficherMessage("Veuillez verifier que toute les informations requises on �t� saisies"," Erreur", JOptionPane.INFORMATION_MESSAGE);
		}
	}
}