package Patricia_tries;

import java.util.ArrayList;


public class Main {
	public static void main(String [] args){
		PatriciaTrie p2 = new PatriciaTrie();
		String word2 = "A quel genial professeur de dactylographie sommes nous redevables de la superbe phrase ci dessous , un modele du genre , que toute dactylo connait par coeur puisque elle fait appel a chacune des touches du clavier de la machine a ecrire ? ";
		long deb_h = System.nanoTime();
		Fonctions.addListOfWords(word2,p2);
		long elapsed_h = System.nanoTime() - deb_h;
		System.out.println("Temps de construction du Patricia-trie à partir des mots de l'exemple de base: " + elapsed_h+ "ns \n");
		//p2.printTrie();
		ArrayList<String> list= Fonctions.listeMots(p2);
		System.out.println("Voici la liste des mots se trouvant dans le patricia trie, classee dans l'ordre alphabetique:");
		Fonctions.afficheListeMots(list);	
		System.out.println("\nNombre de mots dans le patricia trie: " + Fonctions.compteMots(p2)+"\n");
		System.out.println("Nombre de Nil dans le patricia trie: " + Fonctions.compteNil(p2)+"\n");
		System.out.println("hauteur du patricia trie: " + Fonctions.hauteur(p2)+"\n");
		System.out.println("profondeur du patricia trie: " + Fonctions.profondeurMoyenne(p2)+"\n");
		System.out.println("nombre de mots prefixe par 'p': "+Fonctions.searchPrefixe("p", p2)+"\n");
		Fonctions.deleteWord("redevables ", p2);
		
		System.out.println("le mot dacty existe-t-il? " + Fonctions.searchWord("dacty ",p2)+"\n");
	}
}
