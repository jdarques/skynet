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
		
		reseau.ajouterPasserelle(cible);
		
		Assert.assertTrue(reseau.getNoeud(cible).isPasserelle());
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
	
	/**
	 * Permet de tester la récupération du noeud ayant le moins de dépendances
	 */
	@Test
	public void getLienAvecMinDependanceTest()
	{
		Reseau reseau = creerReseauComplexiteMoyenne();
		
		// Le noeud 3 correspond au noeud central du réseau de complexité moyenne
		Assert.assertEquals(2, reseau.getLienAvecMinDependance(reseau.mapNoeud.get(3)).getPosition());
	}
	
	/**
	 * Permet de tester la récupération d'un noeud à partir de sa position
	 */
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
	
	/**
	 * Permet de tester la suppression d'un lien
	 */
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
		
		Assert.assertTrue(noeudDeux.getLiens().contains(noeudZero));
		Assert.assertTrue(noeudDeux.getLiens().contains(noeudUn));
		Assert.assertTrue(noeudDeux.getLiens().contains(noeudTrois));
		
		Assert.assertTrue(noeudTrois.getLiens().contains(noeudDeux));
		Assert.assertTrue(noeudTrois.getLiens().contains(noeudQuatre));
		
		//Séparation du graphe en deux
		reseau.supprimerLien(source, cible);
		
		Assert.assertTrue(noeudDeux.getLiens().contains(noeudZero));
		Assert.assertTrue(noeudDeux.getLiens().contains(noeudUn));
		//Le noeud a été supprimé
		Assert.assertFalse(noeudDeux.getLiens().contains(noeudTrois));
		
		//Le noeud a été supprimé
		Assert.assertFalse(noeudTrois.getLiens().contains(noeudDeux));
		Assert.assertTrue(noeudTrois.getLiens().contains(noeudQuatre));
	}
	
	/**
	 * Permet de vérifier qu'on supprime les liens avec une passerelle en priorité
	 * de skynet
	 */
	@Test
	public void supprimerLienOptimiseSiPasserelleTest()
	{
		Reseau reseau = creerReseauComplexiteMoyenne();
		
		Noeud noeudDeux = reseau.getNoeud(2);
		Noeud noeudTrois = reseau.getNoeud(3);
		
		//Le noeud 2 est une passerelle
		noeudDeux.setPasserelle(true);
		
		//Le lien 3 a un lien avec le lien 2
		Assert.assertTrue(noeudTrois.getLiens().contains(noeudDeux));
		
		//Skynet est sur le noeud 3
		reseau.supprimerLienOptimise(3);
		
		//Le lien 3 n'a plus de lien avec le lien 2
		Assert.assertFalse(noeudTrois.getLiens().contains(noeudDeux));
	}
	
	/**
	 * Permet de tester qu'on supprime de préférence le lien qui amène à plus de lien
	 */
	@Test
	public void supprimerLienOptimiseSiPasDePasserelleTest()
	{
		Reseau reseau = creerReseauComplexiteMoyenne();
		
		//Le noeud zero est la passerelle
		Noeud noeudZero = reseau.getNoeud(0);
		noeudZero.setPasserelle(true);
		
		Noeud noeudDeux = reseau.getNoeud(2);
		Noeud noeudTrois = reseau.getNoeud(3);
		Noeud noeudQuatre = reseau.getNoeud(4);
		
		//Le lien 0 a un lien avec le lien 2
		Assert.assertTrue(noeudZero.getLiens().contains(noeudDeux));
		
		//Skynet est sur le noeud 3
		//L'embuscade est mise en oeuvre une seule et unique fois
		reseau.supprimerLienOptimise(3);
		
		//Le lien 0 n'a plus de lien avec le lien 2
		Assert.assertFalse(noeudZero.getLiens().contains(noeudDeux));
		
		//Le lien 3 a un lien avec le lien 4
		Assert.assertTrue(noeudTrois.getLiens().contains(noeudQuatre));
		
		//Suppression du lien de skynet amenant le plus de liens
		reseau.supprimerLienOptimise(3);
		
		//Le lien 0 n'a plus de lien avec le lien 2
		Assert.assertFalse(noeudTrois.getLiens().contains(noeudQuatre));
	}
	
	/**
	 * Permet de tester la récupération de la passerelle la plus accesible
	 */
	@Test
	public void getPasserellePlusAccessibleTest()
	{
		Reseau reseau = creerReseauComplexiteMoyenne();
		
		//Les noeuds 2 et 4 sont des passerelles
		reseau.getNoeud(2).setPasserelle(true);
		reseau.getNoeud(4).setPasserelle(true);
		
		Assert.assertEquals(4,reseau.getPasserellePlusAccessible().getPosition());
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
