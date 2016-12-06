package hybrid_tries;

public class DisplayHybridTrie {

	public static void main(String[] args) {
		
		HybridTrie t = new HybridTrie();
		t.addKey("abc", 1);
		t.addKey("son", 2);
		t.addKey("att", 3);
		t.addKey("attends", 4);
		
		System.out.println("La racine est :" + t.getCharacter());
		if(t.getInf() != null)
			System.out.println("Le fils gauche de la racine est :" + t.getInf().getCharacter());
		if(t.getEq() != null)
			System.out.println("Le fils du milieu de la racine est :" + t.getEq().getCharacter());
		if(t.getSup() != null)
			System.out.println("Le fils droit de la racine est :" + t.getSup().getCharacter());
		
		System.out.println(t.ComptageNill());
		System.out.println(t.Hauteur());
		
		System.out.println("Liste des mots de l'arbre t :" + t.ListeMots());
		
		System.out.println("/////////////////////////////////////");
		
		t.displayWords("");
		
		HybridTrie t2 = t.Suppression("att");
		System.out.println("t2 words :");
		t2.displayWords("");
		
		HybridTrie t3 = t.Suppression("attends");
		System.out.println("t3 words :");
		t3.displayWords("");
		
		
		System.out.println("profondeur moyenne : " + t.ProfondeurMoyenne());
		
		System.out.println(t.Prefixe("at"));
    }
}
