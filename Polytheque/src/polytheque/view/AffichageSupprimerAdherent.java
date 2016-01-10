package polytheque.view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class AffichageSupprimerAdherent extends JPanel implements ActionListener {

	private JTextField userName;
	private JButton boutonValider;
	
	/**
	 * Une tache d'affichage de l'application.
	 */
	private TacheDAffichage tacheDAffichageDeLApplication;

	/**
	 * Création de la page d'accueil.
	 * 
	 * @param tacheDAffichageDeLApplication
	 *            Une tache d'affichage de l'application.
	 */
	public AffichageSupprimerAdherent(TacheDAffichage afficheAppli){
		this.setLayout(new BorderLayout());

		this.tacheDAffichageDeLApplication = afficheAppli;
		ajouterChamps();
	}

	private void ajouterChamps() {
		JLabel labelUserName = new JLabel("Nom a supprimer :");
		labelUserName.setBounds(500, 100, 100, 30);
		this.add(labelUserName, BorderLayout.NORTH);
		this.userName = new JTextField();
		this.userName.setBounds(450, 200, 100, 30);
		this.userName.setColumns(10);
		this.add(this.userName, BorderLayout.CENTER);

		this.boutonValider = new JButton("Valider");
		this.boutonValider.setBounds(350, 450, 100, 30);
		this.boutonValider.addActionListener(this);
		this.add(this.boutonValider, BorderLayout.CENTER);
	}
	
	@Override
	public void actionPerformed(ActionEvent event) {
		JButton boutonSelectionne = (JButton) event.getSource();
		
		if (boutonSelectionne == this.boutonValider)
		{
			String nom = new String(this.userName.getText());
			this.tacheDAffichageDeLApplication.supprimerAdherent(nom);	
		}
		return;
	}
}