package skynet;


import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import junit.framework.Assert;
import skynet.Player.Reseau;

/**
 * Classe permettant de tester la classe Reseau
 * @author JDARQUES
 *
 */
public class ReseauTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void ajouterLienTest() {
		
		Reseau reseau = new Reseau();
		int source = 1;
		int cible = 2;
		
		reseau.ajouterLien(source, cible);
		
		Assert.assertTrue(reseau.mapNoeud.containsKey(source));
		Assert.assertTrue(reseau.mapNoeud.containsKey(cible));
		
		Assert.assertTrue(reseau.mapNoeud.get(source).getLiens().contains(reseau.mapNoeud.get(cible)));
		Assert.assertTrue(reseau.mapNoeud.get(cible).getLiens().contains(reseau.mapNoeud.get(source)));

	}
	
	@Test
	public void ajouterPasserelleTest() {
		fail("Not yet implemented");
	}
	
	@Test
	public void getLienAvecMaxDependanceTest()
	{
		fail("Not yet implemented");
	}
	
	@Test
	public void getLienAvecMinDependanceTest()
	{
		fail("Not yet implemented");
	}
	
	@Test
	public void getNoeudTest()
	{
		fail("Not yet implemented");
	}
	
	@Test
	public void supprimerLienTest()
	{
		fail("Not yet implemented");
	}

}
