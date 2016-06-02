package skynet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.Logger;

/**
 * Classe Player permettant d'ex�cuter les tests d'acceptance.
 * Cette classe peut �tre copi�e dans codingame afin de valider les tests d'acception apr�s avoir supprim� la premi�re ligne de la classe
 * ("package skynet")
 * 
 **/
public class Player {

	protected static Logger logger=
		    Logger.getLogger(Player.class.getName());
	
	Reseau reseau = new Reseau();
	
	/**
	 * Fonction main
	 * @param args arguments de la fonction du main
	 */
	public static void main(String args[]) {

		// Entr�e standard
		Scanner in = new Scanner(System.in);

		int N = in.nextInt(); // Le nombre de noeuds
		int L = in.nextInt(); // Le nombre de liens
		int E = in.nextInt(); // Le nombre de passerelles
		
		logger.info("Nombre de noeuds :" + N);
		logger.info("Nombre de liens  :" + L);
		logger.info("Nombre de passerelles  :" + E);

		for (int i = 0; i < L; i++) {
			
			//R�cup�ration des noeuds correspondant � un lien
			int N1 = in.nextInt(); 
			int N2 = in.nextInt();
			
			logger.info(String.format("Lien : %d %d", N1,N2));
		}
		for (int i = 0; i < E; i++) {
			
			//R�cup�ration de la position des passerelles
			int EI = in.nextInt();

			logger.info(String.format("Passerelle : %d", EI));
		}

		//Boucle infinie du jeu
		while (true) {
			
			//R�cup�ration de la position de skynet
			int SI = in.nextInt(); 
			
			logger.info(String.format("Position de skynet : %d", SI));
		}
	}
	
	/**
	 * La classe R�seau contient l'ensemble des noeuds du r�seau. 
	 * Cette classe permet d'obtenir les informations du r�seau et permet d'agir sur les liens entre les noeuds
	 * @author JDARQUES
	 *
	 */
	static class Reseau
	{
		/**
		 * Map contenant l'ensemble des noeuds du r�seau
		 * La cl� correspond � la position du noeud (son index)
		 */
		Map<Integer,Noeud> mapNoeud = new HashMap<>();
		
		/**
		 * Constructeur
		 */
		public Reseau()
		{
			
		}
		
		/**
		 * Ajouter un lien au r�seau
		 * @param source position du noeud source
		 * @param cible position du noeud cible
		 */
		public void ajouterLien(int source,int cible)
		{
			//TODO
		}
		
		/**
		 * Supprimer un lien du r�seau
		 * @param source position du noeud source
		 * @param cible position du noeud cible
		 */
		public void supprimerLien(int source,int cible)
		{
			//TODO
		}
		
		/**
		 * Permet d'ajouter une passerelle
		 * @param position position de la passerelle
		 */
		public void ajouterPasserelle(Integer position)
		{
			//TODO
		}
		
		/**
		 * R�cup�ration d'un noeud du r�seau
		 * @param position position du noeud � r�cup�rer
		 * @return le noeud correspondant � la position
		 */
		public Noeud getNoeud(int position)
		{
			//TODO
			return null;
		}
		
		/**
		 * R�cup�ration du lien (du noeud en param�tre) ayant le moins de liens possibles  
		 * @param noeud noeud
		 * @return le lien (du noeud en param�tre) ayant le moins de liens possibles 
		 */
		public Noeud getLienAvecMinDependance(Noeud noeud)
		{
			//TODO
			return null;
		}
		
		/**
		 * R�cup�ration du lien (du noeud en param�tre) ayant le plus de liens possibles  
		 * @param noeud noeud
		 * @return le lien (du noeud en param�tre) ayant le plus de liens possibles 
		 */
		public Noeud getLienAvecMaxDependance(Noeud noeud)
		{
			//TODO
			return null;
		}
		
		
	}
	
	/**
	 * La classe noeud correspond � un noeud du r�seau. 
	 * Cette classe indique la position du noeud et ses liens possibles
	 * @author JDARQUES
	 *
	 */
	static class Noeud
	{
		/**
		 * Position du noeud
		 */
		private int position;
		
		/**
		 * Indique si le lien est une passerelle
		 */
		private boolean isPasserelle;
		
		/**
		 * Liste des liens du noeud
		 */
		private List<Noeud> liens = new ArrayList<>();
		
		/**
		 * Constructeur
		 * @param position position du noeud
		 */
		public Noeud(int position)
		{
			this.position = position;
		}
		
		/**
		 * Permet d'ajouter un lien au noeud
		 * @param noeud lien � ajouter
		 */
		public void ajouterLien(Noeud noeud)
		{
			//TODO
		}
		
		/**
		 * @return la liste des liens du noeud
		 */
		public List<Noeud> getLiens() {
			return liens;
		}

		/**
		 * @return la position du noeud
		 */
		public int getPosition() {
			return position;
		}

		/**
		 * Getter
		 * @return true s'il s'agit d'une passerelle et false dans le cas inverse
		 */
		public boolean isPasserelle() {
			return isPasserelle;
		}

		/**
		 * Setter
		 * @param isPasserelle true s'il s'agit d'une passerelle et false dans le cas inverse
		 */
		public void setPasserelle(boolean isPasserelle) {
			this.isPasserelle = isPasserelle;
		}

	}
}






