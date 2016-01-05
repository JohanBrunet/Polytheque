package polytheque.model.pojos;

public class Jeu {

	private int idJeu;
	private String nom;
	private String description;
	private int ageMini;
	private String edition;
	private boolean disponibilite;
	private String statut;
	private String anneeParution;
	private int nbExemplaires;
	private int nbReserves;
	private int nbJoueursMin;
	private int nbJoueursMax;
	private String categorie;
	private String editeur;

	/**
	 * Construire un nouveau jeu dont on connait l'identifiant
	 * 
	 * @param id
	 * 		L'identifiant du jeu
	 * @param nom
	 * 		Le nom du jeu
	 * @param description
	 * 		La description du jeu
	 * @param anneeParution
	 * 		L'année de parution du jeu
	 * @param statut
	 * 		Le statut du jeu
	 * @param nbExemplaires
	 * 		Le nombre d'exemplaires total du jeu
	 * @param nbReserves
	 * 		Le nombre d'exemplaires réservés
	 * @param ageMini
	 * 		L'age minimal auquel on peut jouer au jeu
	 * @param nbJoueurs
	 * 		Le nombre de joueurs requis (au minimum)
	 * @param categorie
	 * 		La catégorie du jeu
	 * @param editeur
	 * 		L'éditeur du jeu
	 */
	public Jeu(int id, String nom, String description, String anneeParution, String statut, int nbExemplaires, int nbReserves, 
			int ageMini, int nbJoueursMin, int nbJoueursMax, String categorie, String editeur) {
		this.setIdJeu(id);
		this.setNom(nom);
		this.setDescription(description);
		this.setAnneeParution(anneeParution);
		this.setStatus(statut);
		this.setAgeMini(ageMini);
		this.setNbExemplaires(nbExemplaires);
		this.setNbReserves(nbReserves);
		this.setNbJoueursMin(nbJoueursMin);
		this.setNbJoueursMax(nbJoueursMax);
		this.setCategorie(categorie);
		this.setEditeur(editeur);
	}

	/**
	 * Construire un nouveau jeu dont on ne connait pas l'identifiant
	 * 
	 * @param nom
	 * 		Le nom du jeu
	 * @param description
	 * 		La description du jeu
	 * @param anneeParution
	 * 		L'année de parution du jeu
	 * @param statut
	 * 		Le statut du jeu
	 * @param nbExemplaires
	 * 		Le nombre d'exemplaires total du jeu
	 * @param nbReserves
	 * 		Le nombre d'exemplaires réservés
	 * @param ageMini
	 * 		L'age minimal auquel on peut jouer au jeu
	 * @param nbJoueurs
	 * 		Le nombre de joueurs requis (au minimum)
	 * @param categorie
	 * 		La catégorie du jeu
	 * @param editeur
	 * 		L'éditeur du jeu
	 */
	public Jeu(String nom, String description, String anneeParution, String statut, int nbExemplaires, int nbReserves, 
			int ageMini, int nbJoueursMin, int nbJoueursMax, String categorie, String editeur) {
		this.setNom(nom);
		this.setDescription(description);
		this.setAnneeParution(anneeParution);
		this.setStatus(statut);
		this.setAgeMini(ageMini);
		this.setNbExemplaires(nbExemplaires);
		this.setNbReserves(nbReserves);
		this.setNbJoueursMin(nbJoueursMin);
		this.setNbJoueursMax(nbJoueursMax);
		this.setCategorie(categorie);
		this.setEditeur(editeur);
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getAgeMini() {
		return ageMini;
	}

	public void setAgeMini(int ageMini) {
		this.ageMini = ageMini;
	}

	public String getEdition() {
		return edition;
	}

	public void setEdition(String edition) {
		this.edition = edition;
	}

	public boolean getDisponibilite() {
		return this.disponibilite;
	}

	public void setDisponibilite(boolean disponibilite) {
		this.disponibilite = disponibilite;
	}

	public String getStatut() {
		return statut;
	}

	public void setStatus(String status) {
		this.statut = status;
	}

	public String getAnneeParution() {
		return anneeParution;
	}

	public void setAnneeParution(String anneeParution2) {
		this.anneeParution = anneeParution2;
	}

	public int getNbExemplaires() {
		return nbExemplaires;
	}

	public void setNbExemplaires(int nbExemplaires) {
		this.nbExemplaires = nbExemplaires;
	}

	public int getNbReserves() {
		return nbReserves;
	}

	public void setNbReserves(int nbReserves) {
		this.nbReserves = nbReserves;
	}

	public int getIdJeu() {
		return idJeu;
	}

	public void setIdJeu(int idJeu) {
		this.idJeu = idJeu;
	}

	public String getCategorie() {
		return categorie;
	}

	public void setCategorie(String categorie) {
		this.categorie = categorie;
	}

	public String getEditeur() {
		return editeur;
	}

	public void setEditeur(String editeur) {
		this.editeur = editeur;
	}

	public int getNbJoueursMin() {
		return nbJoueursMin;
	}

	public void setNbJoueursMin(int nbJoueursMin) {
		this.nbJoueursMin = nbJoueursMin;
	}

	public int getNbJoueursMax() {
		return nbJoueursMax;
	}

	public void setNbJoueursMax(int nbJoueursMax) {
		this.nbJoueursMax = nbJoueursMax;
	}
}
