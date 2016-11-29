package hybrid_tries;

public class DisplayHybridTrie {

	public static void main(String[] args) {
		
		HybridTrie t = new HybridTrie();
		t.addKey("abc", 1);
		t.addKey("son", 2);
		t.addKey("att", 3);
		t.addKey("attends", 4);
		
		System.out.println("La racine est :" + t.getCharacter());
		System.out.println("Le fils gauche de la racine est :" + t.getInf().getCharacter());
		System.out.println("Le fils du milieu de la racine est :" + t.getEq().getCharacter());
		System.out.println("Le fils droit de la racine est :" + t.getSup().getCharacter());
		
		System.out.println(t.countNill());
		System.out.println(t.height());
		System.out.println();
		
		t.displayWords("");
		
		System.out.println(t.numberPrefixOf("at"));
    }
}
