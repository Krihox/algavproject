package hybrid_tries;

public class DisplayHybridTrie {

	public static void main(String[] args) {
		
		/*
		 * Creation de l'hybrid trie + ajout de huit mots
		 */
		HybridTrie t = new HybridTrie();
		t.addKey("abc", 1);
		t.addKey("son", 2);
		t.addKey("att", 3);
		t.addKey("attends", 4);
		t.addKey("ballon", 5);
		t.addKey("canon", 6);
		t.addKey("dormir", 7);
		t.addKey("ellipse", 8);
		t.addKey("feuille", 9);
		
		/*
		 *  TEST SUR t SANS EQUILIBRAGE
		 */
		System.out.println("La racine est : " + t.getCharacter());
		if(t.getInf() != null)
			System.out.println("Le fils gauche de la racine est : " + t.getInf().getCharacter());
		if(t.getEq() != null)
			System.out.println("Le fils du milieu de la racine est : " + t.getEq().getCharacter());
		if(t.getSup() != null)
			System.out.println("Le fils droit de la racine est : " + t.getSup().getCharacter() + "\n");
		
		System.out.println("Nombre de pointeurs vers nill dans t : " + t.ComptageNill());
		System.out.println("Hauteur de t : " + t.Hauteur());
		
		System.out.println("Liste des mots de l'arbre t : " + t.ListeMots());
		
		
		
		System.out.println("Profondeur moyenne de t : " + t.ProfondeurMoyenne());
		
		System.out.println("Prefixe de 'at' : " + t.Prefixe("at") + "\n");
		
		/*
		 * EQUILIBRAGE DE t
		 */
		t = t.Suppression("feuille");
		t = t.addEquilibrage("feuille",9);
		
		/*
		 * TESTS SUR t APRES EQUILIBRAGE
		 */
		
		System.out.println("EQ : La racine est : " + t.getCharacter());
		if(t.getInf() != null)
			System.out.println("EQ : Le fils gauche de la racine est : " + t.getInf().getCharacter());
		if(t.getEq() != null)
			System.out.println("EQ : Le fils du milieu de la racine est : " + t.getEq().getCharacter());
		if(t.getSup() != null)
			System.out.println("EQ : Le fils droit de la racine est : " + t.getSup().getCharacter() + "\n");
		
		System.out.println("EQ : Nombre de pointeurs vers nill dans t : " + t.ComptageNill());
		System.out.println("EQ : Hauteur de t : " + t.Hauteur());
		
		System.out.println("EQ : Liste des mots de l'arbre t : " + t.ListeMots());
		
		System.out.println("EQ : Profondeur moyenne de t : " + t.ProfondeurMoyenne());
		
		System.out.println("EQ : Prefixe de 'at' : " + t.Prefixe("at") + "\n");
		
		HybridTrie t2 = t.Suppression("att");
		System.out.println("EQ : Liste des mots de t2 : " + t2.ListeMots());
		
		HybridTrie t3 = t.Suppression("attends");
		System.out.println("EQ : Liste des mots de t3 :" + t3.ListeMots());
    }
}
