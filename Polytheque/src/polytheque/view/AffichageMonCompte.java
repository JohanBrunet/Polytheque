package polytheque.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import polytheque.model.pojos.Adherent;

/**
 * Classe permettant de gerer la modification des informations liees au compte de l'utilisateur.
 * 
 * @author Godefroi Roussel, Johan Brunet
 *
 */
@SuppressWarnings("serial")
public class AffichageMonCompte extends JPanel implements ActionListener {

	private JLabel userName;
	private JLabel userFirstName;
	private JLabel userBirthday;
	private JLabel userPseudo;
	private JTextField userRue;
	private JTextField userCP;
	private JTextField userVille;
	private JTextField userPhone;
	private JTextField userMail;
	private JPasswordField password;	
	private JButton boutonValider;
	private Adherent adherentCourant;

	/**
	 * Une tache d'affichage de l'application.
	 */
	private TacheDAffichage tacheDAffichageDeLApplication;

	/**
	 * Creation de la page permettant de changer quelques informations concernant l'utilisateur actuel.
	 * 
	 * @param afficheAppli
	 *      Une tache d'affichage de l'application.
	 * @param adherent
	 * 		L'adhérent dont on veut afficher le profil. 
	 */
	public AffichageMonCompte(TacheDAffichage afficheAppli, Adherent adherent) {
		this.tacheDAffichageDeLApplication = afficheAppli;
		this.adherentCourant = adherent;
		this.setLayout(null);
		ajouterChamps();
		ajouterBoutons();
	}

	/**
	 * Fonction permettant d'afficher tous les champs de la page ainsi que les zones de saisies pour l'utilisateur (avec 
	 * les informations concernant l'utilisateur directement dans les zones de saisies quand il peut modifier ces informations).
	 */
	public void ajouterChamps() {

		JLabel titrePrincipal = new JLabel("Mon compte");
		titrePrincipal.setHorizontalAlignment(SwingConstants.CENTER);
		titrePrincipal.setBounds(350, 20, 260, 30);
		this.add(titrePrincipal);

		JLabel labelUserName = new JLabel("Nom :");
		labelUserName.setBounds(300, 150, 100, 30);
		this.add(labelUserName);
		this.userName = new JLabel(this.adherentCourant.getNom());
		this.userName.setBounds(350, 150, 100, 30);
		this.add(this.userName);

		JLabel labelUserFirstName = new JLabel("Prenom :");
		labelUserFirstName.setBounds(300, 180, 100, 30);
		this.add(labelUserFirstName);
		this.userFirstName = new JLabel(this.adherentCourant.getPrenom());
		this.userFirstName.setBounds(360, 180, 100, 30);
		this.add(this.userFirstName);

		JLabel labelUserBirthday = new JLabel("Date de naissance :");
		labelUserBirthday.setBounds(300, 210, 150, 30);
		this.add(labelUserBirthday);
		this.userBirthday = new JLabel(this.adherentCourant.getDateNaissance().toString());
		this.userBirthday.setBounds(420, 210, 100, 30);
		this.add(this.userBirthday);

		JLabel labelUserRue = new JLabel("Rue :");
		labelUserRue.setBounds(300, 240, 100, 30);
		this.add(labelUserRue);
		this.userRue = new JTextField(this.adherentCourant.getRue());
		this.userRue.setBounds(350, 240, 100, 30);
		this.add(this.userRue);

		JLabel labelUserCP = new JLabel("Code Postal:");
		labelUserCP.setBounds(300, 270, 100, 30);
		this.add(labelUserCP);
		this.userCP = new JTextField(this.adherentCourant.getCP());
		this.userCP.setBounds(390, 270, 100, 30);
		this.add(this.userCP);

		JLabel labelUserVille = new JLabel("Ville :");
		labelUserVille.setBounds(300, 300, 100, 30);
		this.add(labelUserVille);
		this.userVille = new JTextField(this.adherentCourant.getVille());
		this.userVille.setBounds(350, 300, 100, 30);
		this.add(this.userVille);

		JLabel labelUserMail = new JLabel("Mail :");
		labelUserMail.setBounds(300, 330, 100, 30);
		this.add(labelUserMail);
		this.userMail = new JTextField(this.adherentCourant.getMail());
		this.userMail.setBounds(350, 330, 100, 30);
		this.add(this.userMail);

		JLabel labelUserTelephone = new JLabel("Telephone :");
		labelUserTelephone.setBounds(300, 360, 100, 30);
		this.add(labelUserTelephone);
		this.userPhone = new JTextField(this.adherentCourant.getTelephone());
		this.userPhone.setBounds(370, 360, 100, 30);
		this.add(this.userPhone);

		JLabel labelUserPseudo = new JLabel("Pseudo :");
		labelUserPseudo.setBounds(300, 390, 100, 30);
		this.add(labelUserPseudo);
		this.userPseudo = new JLabel(this.adherentCourant.getPseudo());
		this.userPseudo.setBounds(360, 390, 100, 30);
		this.add(this.userPseudo);

		JLabel labelpassword = new JLabel("Mot de passe :");
		labelpassword.setBounds(300, 420, 100, 30);
		this.add(labelpassword);
		this.password = new JPasswordField(this.adherentCourant.getMdp());
		this.password.setBounds(380, 420, 190, 30);
		this.password.setColumns(10);
		this.add(this.password);
	}

	/**
	 * Fonction permettant d'afficher le bouton pour valider les modifications faites par l'utilisateur
	 */
	public void ajouterBoutons(){
		this.boutonValider = new JButton("Valider");
		this.boutonValider.setBounds(200, 500, 200, 30);
		this.boutonValider.addActionListener(this);
		this.add(this.boutonValider);
	}

	@Override
	/**
	 * param event
	 * 	Permet de rendre le bouton fonctionnel
	 * La fonction va verifier si le bouton selectionne correspond au bouton pour valider les modifications. Si oui, on effectue les modifications.
	 */
	public void actionPerformed(ActionEvent event) {
		JButton boutonSelectionne = (JButton) event.getSource();

		if (boutonSelectionne == this.boutonValider) {
			String password = new String(this.password.getPassword());
			Adherent adherent = new Adherent(this.adherentCourant.getIdAdherent(), this.userName.getText(), this.userFirstName.getText(),this.adherentCourant.getDateNaissance(), this.userRue.getText(), this.userCP.getText(), this.userVille.getText(), this.userMail.getText(), this.userPhone.getText(), this.userPseudo.getText(), password, this.adherentCourant.isAdmin(), this.adherentCourant.estAJour(),this.adherentCourant.peutEmprunter(), this.adherentCourant.getCompteurRetard(), this.adherentCourant.getNbNonRecup());
			this.tacheDAffichageDeLApplication.afficherMessage("Vos modifications ont bien �t� prises en compte !", "Modification termin�e", JOptionPane.INFORMATION_MESSAGE);
			this.tacheDAffichageDeLApplication.modifAdherent(adherent);
			return;
		}
		return;
	}
}
