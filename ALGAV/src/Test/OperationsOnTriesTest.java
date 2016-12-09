package Test;

import hybrid_tries.HybridTrie;

import java.io.File;

import Patricia_tries.PatriciaTrie;

public class OperationsOnTriesTest {
	
	public static void main(String[] args) {
	
	File shakespeare = new File("/users/nfs/Etu7/3600157/Documents/testalgavproject/algavproject/ALGAV/Shakespeare/");
	String[] files = Fonctions.listPath(shakespeare);
	
	long deb_h = System.nanoTime();
	HybridTrie h = Fonctions.fill_HybridTrie(files);
	long elapsed_h = System.nanoTime() - deb_h;
	
	long deb_p = System.nanoTime();
	PatriciaTrie p = Fonctions.fill_PatriciaTrie(files);
	long elapsed_p = System.nanoTime() - deb_p;
	
	System.out.println("Temps d'ajout de tous les mots de l'oeuvre de Shakespeare dans l'hybrid trie : " + elapsed_h + " ns");
	System.out.println("Temps d'ajout de tous les mots de l'oeuvre de Shakespeare dans le patricia trie : " + elapsed_p + " ns");
	
	System.out.println("-----------------------------------------------------------------------------");
	
	String[] words_to_insert_10 = {"un", "deux", "trois", "quatre", "cinq", "six", "sept", "huit", "neuf", "dix"};
	
	deb_h = System.nanoTime();
	for(int i=0; i<words_to_insert_10.length; i++){
		h.addKey(words_to_insert_10[i], 1);
	}
	elapsed_h = System.nanoTime() - deb_h;
	
	deb_p = System.nanoTime();
	for(int i=0; i<words_to_insert_10.length; i++){
		String tmp = words_to_insert_10[i]=words_to_insert_10[i]+" ";
		Patricia_tries.Fonctions.addWord(tmp, p);
	}
	elapsed_p = System.nanoTime() - deb_p;
	
	System.out.println("Temps d'ajout de dix mots dans l'hybrid trie : " + elapsed_h + " ns");
	System.out.println("Temps d'ajout de dix mots dans le patricia trie : " + elapsed_p + " ns");
	
	System.out.println("-----------------------------------------------------------------------------");
	
	
	/*
	 * Source : Wikipedia -> Analyse de la complexité des algorithmes
	 */
	String[] words_to_insert_50 = {"Quand", "les", "scientifiques", "ont", "voulu", "enoncer", "formellement",
			"et", "rigoureusement", "ce", "qu", "est", "l", "efficacité", "d", "un", "algorithme", "ou", "au", "contraire",
			"sa", "complexite", "ils", "se", "sont", "rendu", "compte", "que", "la", "comparaison", "des", "algorithmes",
			"entre", "eux", "etait", "necessaire", "outils", "pour", "le", "faire", "a", "epoque", "etaient",
			"primitifs", "Dans", "prehistoire", "de", "informatique", "les", "annees"};
	
	deb_h = System.nanoTime();
	for(int i=0; i<words_to_insert_50.length; i++){
		h.addKey(words_to_insert_50[i], 1);
	}
	elapsed_h = System.nanoTime() - deb_h;
	
	deb_p = System.nanoTime();
	for(int i=0; i<words_to_insert_50.length; i++){
		String tmp = words_to_insert_50[i]=words_to_insert_50[i]+" ";
		Patricia_tries.Fonctions.addWord(tmp, p);
		
	}
	elapsed_p = System.nanoTime() - deb_p;
	
	System.out.println("Temps d'ajout de cinquante mots non existants dans l'hybrid trie : " + elapsed_h + " ns");
	System.out.println("Temps d'ajout de cinquante mots non existants dans le patricia trie : " + elapsed_p + " ns");
	
	System.out.println("-----------------------------------------------------------------------------");
	
	deb_h = System.nanoTime();
	for(int i=0; i<words_to_insert_50.length; i++){
		h.addKey(words_to_insert_50[i], 1);
	}
	elapsed_h = System.nanoTime() - deb_h;
	
	deb_p = System.nanoTime();
	for(int i=0; i<words_to_insert_50.length; i++){
		String tmp = words_to_insert_50[i]=words_to_insert_50[i]+" ";
		Patricia_tries.Fonctions.addWord(tmp, p);
	}
	elapsed_p = System.nanoTime() - deb_p;
	
	System.out.println("Temps d'ajout de cinquante mots existants dans l'hybrid trie : " + elapsed_h + " ns");
	System.out.println("Temps d'ajout de cinquante mots  existants dans le patricia trie : " + elapsed_p + " ns");
	
	System.out.println("-----------------------------------------------------------------------------");
	
	deb_h = System.nanoTime();
	for(int i=0; i<words_to_insert_50.length; i++){
		h.Suppression(words_to_insert_50[i]);
	}
	elapsed_h = System.nanoTime() - deb_h;
	
	deb_p = System.nanoTime();
	for(int i=0; i<words_to_insert_50.length; i++){
		String tmp = words_to_insert_50[i]=words_to_insert_50[i]+" ";
		Patricia_tries.Fonctions.deleteWord(tmp, p);
	}
	elapsed_p = System.nanoTime() - deb_p;
	
	System.out.println("Temps de suppression de cinquante mots dans l'hybrid trie : " + elapsed_h + " ns");
	System.out.println("Temps de suppression de cinquante mots dans le patricia trie : " + elapsed_p + " ns");
	
	System.out.println("-----------------------------------------------------------------------------");
	
	deb_h = System.nanoTime();
	for(int i=0; i<words_to_insert_10.length; i++){
		h.Recherche(words_to_insert_10[i]);
	}
	elapsed_h = System.nanoTime() - deb_h;
	
	deb_p = System.nanoTime();
	for(int i=0; i<words_to_insert_10.length; i++){
		String tmp = words_to_insert_10[i]=words_to_insert_10[i]+" ";
		Patricia_tries.Fonctions.searchWord(tmp, p);
	}
	elapsed_p = System.nanoTime() - deb_p;
	
	System.out.println("Temps de recherche de dix mots recemment inseres dans l'hybrid trie : " + elapsed_h + " ns");
	System.out.println("Temps de recherche de dix mots recemment inseres dans le patricia trie : " + elapsed_p + " ns");
	
	System.out.println("-----------------------------------------------------------------------------");
	
	/*
	 * Source : Wikipedia -> Most common words in english
	 */
	String[] common_words_english_20 = {"the", "because", "know", "which", "government",
			"important", "world", "week", "woman", "point", "fact", "man", "seem", "feel", "into", "high",
			"different", "from", "about", "take"};
	
	deb_h = System.nanoTime();
	for(int i=0; i<common_words_english_20.length; i++){
		h.Recherche(common_words_english_20[i]);
	}
	elapsed_h = System.nanoTime() - deb_h;
	
	deb_p = System.nanoTime();
	for(int i=0; i<common_words_english_20.length; i++){
		String tmp = common_words_english_20[i]=common_words_english_20[i]+" ";
		Patricia_tries.Fonctions.searchWord(tmp, p);
	}
	elapsed_p = System.nanoTime() - deb_p;
	
	System.out.println("Temps de recherche de vingt mots courants en anglais dans l'hybrid trie : " + elapsed_h + " ns");
	System.out.println("Temps de recherche de vingt mots courants en anglais dans le patricia trie : " + elapsed_p + " ns");
	
	System.out.println("-----------------------------------------------------------------------------");
	
	deb_h = System.nanoTime();
	for(int i=0; i<words_to_insert_50.length; i++){
		h.Recherche(words_to_insert_50[i]);
	}
	elapsed_h = System.nanoTime() - deb_h;
	
	deb_p = System.nanoTime();
	for(int i=0; i<words_to_insert_50.length; i++){
		String tmp = words_to_insert_50[i]+" ";
		Patricia_tries.Fonctions.searchWord(tmp, p);
	}
	elapsed_p = System.nanoTime() - deb_p;
	
	System.out.println("Temps de recherche de cinquante mots non existants dans l'hybrid trie : " + elapsed_h + " ns");
	System.out.println("Temps de recherche de cinquante mots non existants dans le patricia trie : " + elapsed_p + " ns");
	
	System.out.println("-----------------------------------------------------------------------------");
	
	deb_h = System.nanoTime();
	int height_h = h.Hauteur();
	elapsed_h = System.nanoTime() - deb_h;
	
	deb_p = System.nanoTime();
	int height_p = Patricia_tries.Fonctions.hauteur(p);
	elapsed_p = System.nanoTime() - deb_p;
	
	System.out.println("Hauteur de l'hybrid trie : " + height_h + " ; Temps de calcul : " + elapsed_h + " ns");
	System.out.println("Hauteur du Patricia trie : " + height_p + " ; Temps de calcul : " + elapsed_p + " ns");
	
	System.out.println("-----------------------------------------------------------------------------");
	
	deb_h = System.nanoTime();
	int average_depth_h = h.ProfondeurMoyenne();
	elapsed_h = System.nanoTime() - deb_h;
	
	deb_p = System.nanoTime();
	int average_depth_p = Patricia_tries.Fonctions.profondeurMoyenne(p);
	elapsed_p = System.nanoTime() - deb_p;
	
	System.out.println("Profondeur moyenne de l'hybrid trie : " + average_depth_h + " ; Temps de calcul : " + elapsed_h + " ns");
	System.out.println("Profondeur moyenne du Patricia trie : " + average_depth_p + " ; Temps de calcul : " + elapsed_p + " ns");
	
	System.out.println("-----------------------------------------------------------------------------");
	
	deb_h = System.nanoTime();
	int nb_words_h = h.ComptageMots();
	elapsed_h = System.nanoTime() - deb_h;
	
	deb_p = System.nanoTime();
	int nb_words_p = Patricia_tries.Fonctions.compteMots(p);
	elapsed_p = System.nanoTime() - deb_p;
	
	System.out.println("Nombre de mots dans l'hybrid trie : " + nb_words_h + " ; Temps de calcul : " + elapsed_h + " ns");
	System.out.println("Nombre de mots dans le Patricia trie : " + nb_words_p + " ; Temps de calcul : " + elapsed_p + " ns");
	
	System.out.println("-----------------------------------------------------------------------------");
	
	deb_h = System.nanoTime();
	h.ListeMots();
	elapsed_h = System.nanoTime() - deb_h;
	
	deb_p = System.nanoTime();
	Patricia_tries.Fonctions.listeMots(p);
	elapsed_p = System.nanoTime() - deb_p;
	
	System.out.println("Temps de calcul de la récupération de la liste des mots dans l'hybrid trie : " + elapsed_h + " ns");
	System.out.println("Temps de calcul de la récupération de la liste des mots dans le patricia trie : " + elapsed_p + " ns");
	
	System.out.println("-----------------------------------------------------------------------------");
	
	}
}
