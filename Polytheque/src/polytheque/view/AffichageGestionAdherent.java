package polytheque.view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import polytheque.model.pojos.Adherent;
import polytheque.view.modeles.ModeleTableauListeJeux;

/**
 * Classe permettant de gerer la modification des informations li�es au compte de l'utilisateur.
 * 
 * @author Godefroi Roussel
 *
 */
@SuppressWarnings("serial")
public class AffichageGestionAdherent extends JPanel implements ActionListener {
	
	public final static int LONGUEUR_COLONNE_0 = 150;
	public final static int LONGUEUR_COLONNE_1 = 150;
	public final static int LONGUEUR_COLONNE_2 = 70;
	public final static int LONGUEUR_COLONNE_3 = 50;
	public final static int LONGUEUR_COLONNE_4 = 60;
	public final static int LONGUEUR_COLONNE_5 = 100;
	public final static int LONGUEUR_COLONNE_6 = 100;
	public final static int LONGUEUR_COLONNE_7 = 100;
	public final static int LONGUEUR_COLONNE_8 = 150;

	/**
	 * Hauteur des lignes.
	 */
	public final static int HAUTEUR_DES_LIGNES = 35;
	
	/**
	 * Nombre de colonnes du tableau.
	 */
	public final static int NOMBRE_COLONNES = 12;

	/**
	 * Les libellés des entêtes.
	 */
	public final static String[] LIBELLES = new String[] {"Nom", "Prénom", "Date de naissance", "Rue", 
														  "Code postal", "Ville", "Mail", "Télphone", "Pseudo", 
														  "Sur liste noire", "Est à jour", "Nombre de retards"};

	private JTextField searchContent;
	private JTextField userName;
	private JTextField userFirstName;
	private JTextField userBirthday;
	private JTextField userPseudo;
	private JTextField userRue;
	private JTextField userCP;
	private JTextField userVille;
	private JTextField userPhone;
	private JTextField userMail;
	private JPasswordField password;	
		
	private JButton boutonCreerAdherent;
	private JButton boutonRetourAccueil;
	private JButton boutonModifierAdherent;
	private JButton boutonSupprimerAdherent;
	private JButton boutonRecherche;
		
		/**
		 * Une tache d'affichage de l'application.
		 */
		private TacheDAffichage tacheDAffichageDeLApplication;

		/**
		 * Creation de la page d'accueil.
		 * 
		 * @param tacheDAffichageDeLApplication
		 *            Une tache d'affichage de l'application.
		 * @return 
		 */
		public AffichageGestionAdherent(TacheDAffichage afficheAppli, ArrayList<Adherent> listeAdherents){
			this.tacheDAffichageDeLApplication = afficheAppli;
			this.setLayout(new BorderLayout());
			
			creerPanneauRecherche();
			creerTableau(listeAdherents);
			ajouterBoutons();
		}
		
		/**
		 * Panneau de recherche
		 */
		private void creerPanneauRecherche() {
			JPanel searchPanel = new JPanel();
			
			JLabel labelSearch = new JLabel("Recherche par nom :");
			labelSearch.setBounds(300, 0, 100, 30);
			searchPanel.add(labelSearch);
			this.searchContent = new JTextField();
			this.searchContent.setBounds(450, 0, 100, 30);
			this.searchContent.setColumns(10);
			searchPanel.add(this.searchContent, BorderLayout.NORTH);
			
			this.boutonRecherche = new JButton("Rechercher");
			this.boutonRecherche.addActionListener(this);
			searchPanel.add(boutonRecherche, BorderLayout.NORTH);

			this.add(searchPanel, BorderLayout.NORTH);
		}
		
		public void creerTableau(ArrayList<Adherent> listeAdherents) {
			JPanel arrayPanel = new JPanel();
			arrayPanel.setLayout(new BorderLayout());
			
			JTable tableau = new JTable(new ModeleTableauListeJeux(initialiserDonnees(listeAdherents), LIBELLES));
			tableau.getColumn(LIBELLES[0]).setPreferredWidth(LONGUEUR_COLONNE_0);
			tableau.getColumn(LIBELLES[1]).setPreferredWidth(LONGUEUR_COLONNE_1);
			tableau.getColumn(LIBELLES[2]).setPreferredWidth(LONGUEUR_COLONNE_2);
			tableau.getColumn(LIBELLES[3]).setPreferredWidth(LONGUEUR_COLONNE_3);
			tableau.getColumn(LIBELLES[4]).setPreferredWidth(LONGUEUR_COLONNE_4);
			tableau.getColumn(LIBELLES[5]).setPreferredWidth(LONGUEUR_COLONNE_5);
			tableau.getColumn(LIBELLES[6]).setPreferredWidth(LONGUEUR_COLONNE_6);
			tableau.getColumn(LIBELLES[7]).setPreferredWidth(LONGUEUR_COLONNE_7);

			tableau.setRowHeight(HAUTEUR_DES_LIGNES);
			tableau.getTableHeader().setReorderingAllowed(true);
			tableau.getTableHeader().setResizingAllowed(true);
			tableau.setAutoCreateRowSorter(true);
			
			arrayPanel.add(new JScrollPane(tableau), BorderLayout.CENTER);
			this.add(arrayPanel, BorderLayout.CENTER);
		}
		
		/**
		 * Initialiser les données du tableau.
		 * 
		 * @param tachesARealiser
		 *            Une collection de taches à réaliser.
		 * @return Un tableau d'objets.
		 */
		private static Object[][] initialiserDonnees(ArrayList<Adherent> listeAdherents)
		{
			Object[][] donnees = new Object[listeAdherents.size()][NOMBRE_COLONNES];
			
			int index = 0;		
			for (Adherent adherentCourant : listeAdherents)
			{
				donnees[index][0] = adherentCourant.getNom();
				donnees[index][1] = adherentCourant.getPrenom();
				donnees[index][2] = adherentCourant.getDateNaissance();
				donnees[index][3] = adherentCourant.getRue();
				donnees[index][4] = adherentCourant.getCP();
				donnees[index][5] = adherentCourant.getVille();
				donnees[index][6] = adherentCourant.getMail();
				donnees[index][7] = adherentCourant.getTelephone();
				donnees[index][8] = adherentCourant.getPseudo();
				donnees[index][9] = adherentCourant.peutEmprunter();
				donnees[index][10] = adherentCourant.estAJour();
				donnees[index][11] = adherentCourant.getCompteurRetard();
				index++;
			}		
			return donnees;
		}
		
		public void ajouterBoutons(){
			JPanel boutonsPanel = new JPanel();
			this.boutonCreerAdherent = new JButton("Créer Adherent");
			this.boutonCreerAdherent.setBounds(200, 500, 100, 30);
			this.boutonCreerAdherent.addActionListener(this);
			boutonsPanel.add(this.boutonCreerAdherent);
			
			this.boutonSupprimerAdherent = new JButton("Supprimer Adherent");
			this.boutonSupprimerAdherent.setBounds(400, 500, 100, 30);
			this.boutonSupprimerAdherent.addActionListener(this);
			boutonsPanel.add(this.boutonSupprimerAdherent);
			
			this.boutonModifierAdherent = new JButton("Modifier Adherent");
			this.boutonModifierAdherent.setBounds(600, 500, 100, 30);
			this.boutonModifierAdherent.addActionListener(this);
			boutonsPanel.add(this.boutonModifierAdherent);

			this.boutonRetourAccueil = new JButton("Accueil");
			this.boutonRetourAccueil.setBounds(800, 500, 200, 30);
			this.boutonRetourAccueil.addActionListener(this);
			boutonsPanel.add(this.boutonRetourAccueil);
			
			this.add(boutonsPanel, BorderLayout.SOUTH);
		}

		@Override
		public void actionPerformed(ActionEvent event) {
			JButton boutonSelectionne = (JButton) event.getSource();

			if (boutonSelectionne == this.boutonRecherche)
			{
				this.tacheDAffichageDeLApplication.rechercherAdherent(this.searchContent.getText());
				return;
			}
			
			if (boutonSelectionne == this.boutonCreerAdherent)
			{
				return;
			}
			
			if (boutonSelectionne == this.boutonRetourAccueil)
			{
				this.tacheDAffichageDeLApplication.afficherAccueil();
				return;
			}
			
			if (boutonSelectionne == this.boutonSupprimerAdherent)
			{
				this.tacheDAffichageDeLApplication.afficherMessage("Fonctionnalité pas disponible", "Non disponible !", JOptionPane.INFORMATION_MESSAGE);
				return;
			}

			if (boutonSelectionne == this.boutonModifierAdherent)
			{
				this.tacheDAffichageDeLApplication.afficherModificationAdherent();
				return;
			}
			return;
		}




}
