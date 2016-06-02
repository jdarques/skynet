package skynet;


import org.junit.Before;
import org.junit.Test;

import junit.framework.Assert;
import skynet.Player.Noeud;
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

	/**
	 * Test de l'ajout d'un lien dans le réseau
	 */
	@Test
	public void ajouterLienTest() {
		
		int source = 0;
		int cible = 1;
		
		Reseau reseau = creerReseauComplexiteTresSimple(source,cible);
		
		Assert.assertTrue(reseau.mapNoeud.containsKey(source));
		Assert.assertTrue(reseau.mapNoeud.containsKey(cible));
		
		Assert.assertTrue(reseau.mapNoeud.get(source).getLiens().contains(reseau.mapNoeud.get(cible)));
		Assert.assertTrue(reseau.mapNoeud.get(cible).getLiens().contains(reseau.mapNoeud.get(source)));

	}
	
	/**
	 * Test d'ajout d'une passerelle
	 */
	@Test
	public void ajouterPasserelleTest() {
		
		int source = 0;
		int cible = 1;
		
		Reseau reseau = creerReseauComplexiteTresSimple(source,cible);
		
		reseau.ajouterPasserelle(2);
		
		Assert.assertTrue(reseau.getNoeud(2).isPasserelle());
	}
	
	/**
	 * Permet de tester la récupération du noeud ayant le plus de dépendances
	 */
	@Test
	public void getLienAvecMaxDependanceTest()
	{
		Reseau reseau = creerReseauComplexiteMoyenne();
		
		// Le noeud 3 correspond au noeud central du réseau de complexité moyenne
		Assert.assertEquals(4, reseau.getLienAvecMaxDependance(reseau.mapNoeud.get(3)).getPosition());
	}
	
	@Test
	public void getLienAvecMinDependanceTest()
	{
		Reseau reseau = creerReseauComplexiteMoyenne();
		
		// Le noeud 3 correspond au noeud central du réseau de complexité moyenne
		Assert.assertEquals(2, reseau.getLienAvecMaxDependance(reseau.mapNoeud.get(3)).getPosition());
	}
	
	@Test
	public void getNoeudTest()
	{
		int source = 0;
		int cible = 1;
		Reseau reseau = creerReseauComplexiteTresSimple(source,cible);
		
		Assert.assertNotNull(reseau.getNoeud(source));
		Assert.assertEquals(source, reseau.getNoeud(source).getPosition());
		
		Assert.assertNotNull(reseau.getNoeud(cible));
		Assert.assertEquals(cible, reseau.getNoeud(cible).getPosition());
	}
	
	@Test
	public void supprimerLienTest()
	{
		Reseau reseau = creerReseauComplexiteMoyenne();
		int source = 2;
		int cible = 3;
		
		Noeud noeudZero = reseau.mapNoeud.get(0);
		Noeud noeudUn = reseau.mapNoeud.get(1);
		Noeud noeudDeux = reseau.mapNoeud.get(2);
		Noeud noeudTrois = reseau.mapNoeud.get(3);
		Noeud noeudQuatre = reseau.mapNoeud.get(4);
		Noeud noeudCinq = reseau.mapNoeud.get(5);
		Noeud noeudSix = reseau.mapNoeud.get(6);
		Noeud noeudSept = reseau.mapNoeud.get(7);
		
		
		Assert.assertTrue(noeudDeux.getLiens().contains(noeudZero));
		Assert.assertTrue(noeudDeux.getLiens().contains(noeudUn));
		Assert.assertTrue(noeudDeux.getLiens().contains(noeudTrois));
		
		Assert.assertTrue(noeudQuatre.getLiens().contains(noeudTrois));
		Assert.assertTrue(noeudQuatre.getLiens().contains(noeudCinq));
		Assert.assertTrue(noeudQuatre.getLiens().contains(noeudSix));
		Assert.assertTrue(noeudQuatre.getLiens().contains(noeudSept));
		
		//Séparation du graphe en deux
		reseau.supprimerLien(source, cible);
		
		Assert.assertTrue(noeudDeux.getLiens().contains(noeudZero));
		Assert.assertTrue(noeudDeux.getLiens().contains(noeudUn));
		//Le noeud a été supprimé
		Assert.assertFalse(noeudDeux.getLiens().contains(noeudTrois));
		
		//Le noeud a été supprimé
		Assert.assertFalse(noeudQuatre.getLiens().contains(noeudTrois));
		Assert.assertTrue(noeudQuatre.getLiens().contains(noeudCinq));
		Assert.assertTrue(noeudQuatre.getLiens().contains(noeudSix));
		Assert.assertTrue(noeudQuatre.getLiens().contains(noeudSept));
		
		
	}
	
	/**
	 * Création d'un réseau pour les tests
	 * 
	 * @param source noeud source
	 * @param cible noeud cible
	 * @return le reseau créé
	 * */
	private Reseau creerReseauComplexiteTresSimple(int source,int cible)
	{
		Reseau reseau = new Reseau();
		reseau.ajouterLien(0, 1);
		return reseau;
	}

	/**
	 * Création d'un réseau pour les tests
	 * O 	 	   5
	 * 	\		  /
	 * 	 2 - 3 - 4 - 6 
	 *  /		  \
	 * 1 	  	   7
	 * 
	 * @return le réseau créé
	 * */
	private Reseau creerReseauComplexiteMoyenne()
	{
		Reseau reseau = new Reseau();
		reseau.ajouterLien(0, 2);
		reseau.ajouterLien(1, 2);
		reseau.ajouterLien(2, 3);
		reseau.ajouterLien(3, 4);
		reseau.ajouterLien(4, 5);
		reseau.ajouterLien(4, 6);
		reseau.ajouterLien(4, 7);
		return reseau;
	}

}
