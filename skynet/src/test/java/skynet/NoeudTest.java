package skynet;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import skynet.Player.Noeud;

/**
 * Classe de test pour la classe Noeud
 * @author JDARQUES
 *
 */
public class NoeudTest {

	@Before
	public void setUp() throws Exception {
	}

	/**
	 * Permet de tester l'ajout d'un lien dans un noeud
	 */
	@Test
	public void ajouterLienTest() {
		
		int source = 0;
		int cible = 1;
		
		Noeud noeudSource = new Noeud(source);
		Noeud noeudCible = new Noeud(cible);
		
		noeudSource.ajouterLien(noeudCible);
		
		Assert.assertTrue(noeudSource.getLiens().contains(noeudCible));
	}
}
