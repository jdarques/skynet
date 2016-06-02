package skynet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.Logger;

/**
 * Classe Player permettant d'ex�cuter les tests d'acceptance.
 * Cette classe peut �tre copi�e dans codinggame afin de valider les tests d'acceptance apr�s avoir supprim� la premi�re ligne de la classe
 * ("package skynet")
 * 
 **/
class Player {

	protected static Logger logger=
		    Logger.getLogger(Player.class.getName());
	
	/**
	 * Fonction main
	 * @param args arguments de la fonction du main
	 */
	public static void main(String args[]) {

		Reseau reseau = new Reseau();
		
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
			
			reseau.ajouterLien(N1, N2);
		}
		for (int i = 0; i < E; i++) {
			
			//R�cup�ration de la position des passerelles
			int EI = in.nextInt();
			
			reseau.ajouterPasserelle(EI);
		}

		//Boucle infinie du jeu
		while (true) {
			
			//R�cup�ration de la position de skynet
			int SI = in.nextInt(); 
			
			logger.info(String.format("Position de skynet : %d", SI));
			
			//Suppression du lien le plus adapt�
			reseau.supprimerLienOptimise(SI);
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
			logger.info(String.format("Ajout du lien : %d %d", source,cible));
			
			Noeud noeudSource = creerOuRecupererNoeud(source);
			Noeud noeudCible = creerOuRecupererNoeud(cible);
			
			//Cr�ation du lien entre le noeud source et le noeud cible
			noeudSource.ajouterLien(noeudCible);
			noeudCible.ajouterLien(noeudSource);
			
			// Ajout des noeuds � la map contenant l'ensemble des noeuds du r�seau
			mapNoeud.put(source,noeudSource);
			mapNoeud.put(cible,noeudCible);
		}
		
		/**
		 * Supprimer un lien du r�seau
		 * @param source position du noeud source
		 * @param cible position du noeud cible
		 */
		public void supprimerLien(int source,int cible)
		{
			logger.info(String.format("Suppression du lien %d %d", source, cible));
			
			Noeud noeudSource = mapNoeud.get(source);
			Noeud noeudCible = mapNoeud.get(cible);
			
			//Supprime les liens entre le noeud source et le noeud cible
			noeudSource.getLiens().remove(noeudCible);
			noeudCible.getLiens().remove(noeudSource);
			
			// Coupure du lien
			System.out.println(source + " " + cible);
		}
		
		/**
		 * Permet d'ajouter une passerelle
		 * @param position position de la passerelle
		 */
		public void ajouterPasserelle(Integer position)
		{
			logger.info(String.format("Ajout de la passerelle : %d", position));
			mapNoeud.get(position).setPasserelle(true);
		}
		
		/**
		 * R�cup�ration d'un noeud du r�seau
		 * @param position position du noeud � r�cup�rer
		 * @return le noeud correspondant � la position
		 */
		public Noeud getNoeud(int position)
		{
			return mapNoeud.get(position);
		}
		
		/**
		 * R�cup�ration du lien (du noeud en param�tre) ayant le moins de liens possibles  
		 * @param noeud noeud
		 * @return le lien (du noeud en param�tre) ayant le moins de liens possibles 
		 */
		public Noeud getLienAvecMinDependance(Noeud noeud)
		{
			Noeud noeudAvecMinLien = null;
			
			if (noeud.getLiens() != null)
			{
				for (Noeud lien : noeud.getLiens())
				{
					// Si noeudAvecMinLien n'est pas initialis� ou si le lien courant � moins de d�pendances que noeudAvecMinLien
					if (noeudAvecMinLien == null || 
							lien.getLiens().size() < noeudAvecMinLien.getLiens().size())
					{
						noeudAvecMinLien = lien;
					}
				}
			}
			
			return noeudAvecMinLien;
		}
		
		/**
		 * R�cup�ration du lien (du noeud en param�tre) ayant le plus de liens possibles  
		 * @param noeud noeud
		 * @return le lien (du noeud en param�tre) ayant le plus de liens possibles 
		 */
		public Noeud getLienAvecMaxDependance(Noeud noeud)
		{
			Noeud noeudAvecMaxLien = null;
			
			if (noeud.getLiens() != null)
			{
				for (Noeud lien : noeud.getLiens())
				{
					// Si noeudAvecMaxLien n'est pas initialis� ou si le lien courant � plus de d�pendances que noeudAvecMaxLien
					if (noeudAvecMaxLien == null || 
							lien.getLiens().size() > noeudAvecMaxLien.getLiens().size())
					{
						noeudAvecMaxLien = lien;
					}
				}
			}
			
			return noeudAvecMaxLien;
		}
		
		/**
		 * Permet de supprimer le lien le plus adapt� afin de bloquer skynet
		 * @param skynetPosition position de skynet
		 */
		public void supprimerLienOptimise(int skynetPosition)
		{
			//R�cup�ration du noeud courant de skynet
			Noeud skynet = getNoeud(skynetPosition);
			
			if (skynet.getLienPasserelle() != null)
			{
				int passerelle = skynet.getLienPasserelle().getPosition();
				
				logger.info(String.format("Suppression du lien vers la passerelle : %d", passerelle));
				supprimerLien(skynet.getPosition(),skynet.getLienPasserelle().getPosition());
			}
			else
			{
				logger.info(String.format("Suppression d'un des liens de skynet ayant le plus de d�pendances. "
						+ "Position skynet : %d", skynetPosition));
				supprimerLien(skynet.getPosition(),getLienAvecMaxDependance(skynet).getPosition());
			}
		}
		
		/**
		 * R�cup�rer le noeud ou le cr�er s'il n'existe pas
		 * @param position position du noeud
		 * @return le noeud correspondant � la position
		 */
		private Noeud creerOuRecupererNoeud(int position)
		{
			Noeud noeud = null;
			
			if (!mapNoeud.containsKey(position))
			{
				logger.info(String.format("Le noeud %d n'existe pas. Cr�ation du noeud", position));
				noeud = new Noeud(position);
			}
			else
			{
				noeud = mapNoeud.get(position);
			}
			return noeud;
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
			//Ajout du lien s'il n'existe pas d�ja
			if (!liens.contains(noeud))
			{
				liens.add(noeud);
			}
		}
		
		/**
		 * Si le noeud � un lien qui est une passerelle, la m�thode retourne cette passerelle
		 * Sinon retourne null
		 * @return le lien du noeud correspond � une passerelle, sinon null
		 */
		public Noeud getLienPasserelle()
		{
			Noeud passerelle = null;
			
			for (Noeud lien : liens)
			{
				if (lien.isPasserelle)
				{
					passerelle = lien;
				}
			}
			return passerelle;
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






