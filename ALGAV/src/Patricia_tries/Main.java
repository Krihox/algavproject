package Patricia_tries;

import java.io.File;

public class Main {
	public static void main(String [] args){
//		PatriciaTrie p = new PatriciaTrie();
//		String word = "A quel genial professeur de dactylographie sommes nous redevables de la superbe phrase ci dessous, un modele du genre, que toute dactylo connait par coeur puisque elle fait appel a chacune des touches du clavier de la machine a ecrire ?";
//		Fonctions.addListOfWords(word,p);
////		System.out.println(" SUPPRESSION \n");
//		p.printTrie();
//		System.out.println("Nil " + Fonctions.compteNil(p));
//		System.out.println("hauteur " + Fonctions.hauteur(p));
//		System.out.println("profondeur " + Fonctions.profondeurMoyenne(p));
		String[] files = Fonctions.listPath(new File("C:/Users/Vaniisha/git/algavproject/ALGAV/src/Shakespeare/"));
		for(int i = 0; i<files.length;i++){
			Fonctions.fill_PatriciaTrie(files[i]);
		}
		//e.printTrie();
	//Fonctions.delete("redevables ", p);
//		System.out.println("PRINT");
//		p.printTrie();
//		System.out.println("Nombre de mots =" + p.number_words);
//		System.out.println("Mots ? " + Fonctions.searchWord("dacty ",p) + "hauteur="+p.hauteur());
	//System.out.println("Search " + Fonctions.compteMots( p) + "Compte "+ p.number_words);
	}
}
