package Patricia_tries;


public class Main {
	public static void main(String [] args){
		PatriciaTrie p2 = new PatriciaTrie();
		String word2 = "A quel genial professeur de dactylographie sommes nous redevables de la superbe phrase ci dessous, un modele du genre, que toute dactylo connait par coeur puisque elle fait appel a chacune des touches du clavier de la machine a ecrire ? ";
		Fonctions.addListOfWords(word2,p2);
		//p2.printTrie();
		System.out.println("Nombre de mots dans le patricia trie: " + Fonctions.compteMots(p2));
		System.out.println("Nombre de compteur Nil dans le patricia trie: " + Fonctions.compteNil(p2));
		System.out.println("hauteur du patricia trie: " + Fonctions.hauteur(p2));
		System.out.println("profondeur du patricia trie: " + Fonctions.profondeurMoyenne(p2));
		System.out.println("nombre de mots prefixe par 'p': "+Fonctions.searchPrefixe("p", p2));
		Fonctions.deleteWord("redevables ", p2);

		System.out.println("le mot dacty existe-t-il? " + Fonctions.searchWord("dacty ",p2));
	}
}
