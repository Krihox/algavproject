package Test;

import hybrid_tries.HybridTrie;

import java.io.File;

import Patricia_tries.PatriciaTrie;

public class MainTest {
	
	public static void main(String[] args) {
		
		File shakespeare = new File("/users/nfs/Etu7/3600157/Documents/testalgavproject/algavproject/ALGAV/Shakespeare/");
		String[] files = Fonctions.listPath(shakespeare);
		
		/*for(int i=0; i< files.length; i++)
			Fonctions.fill_HybridTrie(files[i]);
		
		for(int i=0; i< files.length; i++)
			Fonctions.fill_PatriciaTrie(files[i]);*/
		
		
		HybridTrie h = Fonctions.fill_HybridTrie(files);
		PatriciaTrie p = Fonctions.fill_PatriciaTrie(files);
		
		long deb = System.currentTimeMillis();
		h.addKey("graymalvzdlkgp", 1);
		long fin = System.currentTimeMillis();
		
		long deb1 = System.currentTimeMillis();
		Patricia_tries.Fonctions.addWord("graymalvzdlkgp", p);
		long fin1 = System.currentTimeMillis();
		
		System.out.println("Temps d'ajout d'un nouveau mot dans l'hybrid trie :" + (fin-deb));
		System.out.println("Temps d'ajout d'un nouveau mot dans le patricia trie :" + (fin1-deb1));
		
		deb = System.currentTimeMillis();
		h.deleteWord("what");
		h.deleteWord("lightning");
		h.deleteWord("hurlyburly");
		h.deleteWord("heath");
		h.deleteWord("graymalkin");
		h.deleteWord("and");
		h.deleteWord("donalbain");
		fin = System.currentTimeMillis();
		
//		deb1 = System.currentTimeMillis();
//		Patricia_tries.Fonctions.deleteWord("what ",p);
//		Patricia_tries.Fonctions.deleteWord("lightning ",p);
//		Patricia_tries.Fonctions.deleteWord("hurlyburly ",p);
//		Patricia_tries.Fonctions.deleteWord("heath ",p);
//		Patricia_tries.Fonctions.deleteWord("graymalkin ",p);
//		Patricia_tries.Fonctions.deleteWord("and ",p);
//		Patricia_tries.Fonctions.deleteWord("donalbain ",p);
//		fin1 = System.currentTimeMillis();
		
		System.out.println("Temps de suppression de 7 mots dans l'hybrid trie :" + (fin-deb));
		//System.out.println("Temps de suppression de 7 mots dans le patricia trie :" + (fin1-deb1));
		
		System.out.println("Profondeur de l'hybrid trie :" + h.height());
		System.out.println("Profondeur du patricia trie :" + Patricia_tries.Fonctions.hauteur(p));
    }
}
