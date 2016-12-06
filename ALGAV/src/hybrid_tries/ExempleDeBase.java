package hybrid_tries;

public class ExempleDeBase {

	public static void main(String[] args) {
		
		int cpt=1;
		
		HybridTrie t = new HybridTrie();
		t.addKey("A", cpt++);
		t.addKey("quel", cpt++);
		t.addKey("genial", cpt++);
		t.addKey("professeur", cpt++);
		t.addKey("de", cpt++);
		t.addKey("dactylographie", cpt++);
		t.addKey("sommes", cpt++);
		t.addKey("nous", cpt++);
		t.addKey("redevables", cpt++);
		t.addKey("de", cpt++);
		t.addKey("la", cpt++);
		t.addKey("superbe", cpt++);
		t.addKey("phrase", cpt++);
		t.addKey("ci", cpt++);
		t.addKey("dessous", cpt++);
		t.addKey(",", cpt++);
		t.addKey("un", cpt++);
		t.addKey("modele", cpt++);
		t.addKey("du", cpt++);
		t.addKey("genre", cpt++);
		t.addKey(",", cpt++);
		t.addKey("que", cpt++);
		t.addKey("toute", cpt++);
		t.addKey("dactylo", cpt++);
		t.addKey("connait", cpt++);
		t.addKey("par", cpt++);
		t.addKey("coeur", cpt++);
		t.addKey("puisque", cpt++);
		t.addKey("elle", cpt++);
		t.addKey("fait", cpt++);
		t.addKey("appel", cpt++);
		t.addKey("a", cpt++);
		t.addKey("chacune", cpt++);
		t.addKey("des", cpt++);
		t.addKey("touches", cpt++);
		t.addKey("du", cpt++);
		t.addKey("clavier", cpt++);
		t.addKey("de", cpt++);
		t.addKey("la", cpt++);
		t.addKey("machine", cpt++);
		t.addKey("a", cpt++);
		t.addKey("ecrire", cpt++);
		t.addKey("?", cpt++);
		
		t.displayWords("");
		System.out.println("/////////////////////////////////////");
		System.out.println("Liste des mots de l'arbre t :" + t.ListeMots());
		
		System.out.println("le mot 'fait' existe : " + t.search("fait"));
		System.out.println("le mot 'clavie' existe : " + t.search("clavie"));
		System.out.println("le mot 'clavier' existe :" + t.search("clavier"));
		System.out.println("le mot 'clavierr' existe : " + t.search("clavierr"));
		
		System.out.println("nombre de mots : " + t.countWords());
		
		System.out.println("hauteur moyenne : " + t.averageDepth());
		
		System.out.println("nombre de mots prefixés par 'dactylo' : " + t.numberPrefixOf("dactylo"));
		System.out.println("nombre de mots prefixés par 'p' : " + t.numberPrefixOf("p"));
		System.out.println("nombre de mots prefixés par 'abc' : " + t.numberPrefixOf("abc"));
		
    }
}
