import polytheque.controller.PolythequeApplication;
import polytheque.view.TacheDAffichage;

/**
 * Classe permettant de lancer l'application.
 * @author Johan Brunet
 *
 */
public class PolythequeLauncher {

	/**
	 * Création du controller et de la view.
	 * Association des deux pour qu'ils puissent interagir.
	 * @param args
	 * 		Les arguments du main.
	 */
	public static void main(String[] args) {
		PolythequeApplication polythequeApplication = new PolythequeApplication();
		TacheDAffichage tacheDAffichageDeLApplication = new TacheDAffichage(polythequeApplication);
		tacheDAffichageDeLApplication.run();
	}
}
