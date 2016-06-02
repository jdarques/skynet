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
	
	/**
	 * Permet de tester la récupération d'un lien qui est une passerelle
	 * 1   2 (Passerelle)
	 * 	\ /
	 * 	 O
	 * 	 |
	 *   3 
	 */
	@Test
	public void getLienPasserelleTest()
	{
		Noeud noeudSource = new Noeud(0);
		Noeud noeudUn = new Noeud(1);
		Noeud noeudDeux = new Noeud(2);
		
		//Le noeud deux est une passerelle
		noeudDeux.setPasserelle(true);
		
		Noeud noeudTrois = new Noeud(3);
		
		noeudSource.ajouterLien(noeudUn);
		noeudSource.ajouterLien(noeudDeux);
		noeudSource.ajouterLien(noeudTrois);
		
		Assert.assertTrue(noeudSource.getLienPasserelle().equals(noeudDeux));
		
		//Le noeud deux n'est plus une passerelle
		noeudDeux.setPasserelle(false);
		
		Assert.assertNull(noeudSource.getLienPasserelle());
		
	}
	
	
}
