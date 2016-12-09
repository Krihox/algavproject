package Test;

import hybrid_tries.HybridTrie;

import java.io.File;

import Patricia_tries.PatriciaTrie;

public class MainTest {
	
	public static void main(String[] args) {
		
		File shakespeare =new File("/users/nfs/Etu7/3600157/Documents/testalgavproject/algavproject/ALGAV/Shakespeare/");
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
		
		System.out.println("**\nAjout**");
		System.out.println("Temps d'ajout d'un nouveau mot dans l'hybrid trie : " + (fin-deb) + "ms");
		System.out.println("Temps d'ajout d'un nouveau mot dans le patricia trie : " + (fin1-deb1) + "ms");
		
		deb = System.currentTimeMillis();
		h.Suppression("what");
		h.Suppression("lightning");
		h.Suppression("hurlyburly");
		h.Suppression("heath");
		h.Suppression("graymalkin");
		h.Suppression("and");
		h.Suppression("donalbain");
		fin = System.currentTimeMillis();
		
		deb1 = System.currentTimeMillis();
		Patricia_tries.Fonctions.deleteWord("what ",p);
		Patricia_tries.Fonctions.deleteWord("lightning ",p);
		Patricia_tries.Fonctions.deleteWord("hurlyburly ",p);
		Patricia_tries.Fonctions.deleteWord("heath ",p);
		Patricia_tries.Fonctions.deleteWord("graymalkin ",p);
		Patricia_tries.Fonctions.deleteWord("and ",p);
		Patricia_tries.Fonctions.deleteWord("donalbain ",p);
		fin1 = System.currentTimeMillis();
		
		System.out.println("\n**Suppression**");
		System.out.println("Temps de suppression de 7 mots dans l'hybrid trie :" + (fin-deb) + "ms");
		System.out.println("Temps de suppression de 7 mots dans le patricia trie :" + (fin1-deb1) + "ms");
		
		System.out.println("\n**Profondeur**");
		System.out.println("Profondeur de l'hybrid trie :" + h.Hauteur());
		System.out.println("Profondeur du patricia trie :" + Patricia_tries.Fonctions.hauteur(p));
		
		System.out.println("\n**Pointeur Nil**");
		System.out.println("Nombre de pointeurs vers nill dans l'hybrid trie :" + h.ComptageNill());
		System.out.println("Nombre de pointeurs vers nill dans le patricia trie :" + Patricia_tries.Fonctions.compteNil(p));
		
		deb = System.currentTimeMillis();
		int nb_prefixe_h = h.Prefixe("wh");
		fin = System.currentTimeMillis();
		
		deb1 = System.currentTimeMillis();
		int nb_prefixe_p = Patricia_tries.Fonctions.searchPrefixe("wh", p);
		fin1 = System.currentTimeMillis();
		
		System.out.println("\n**Nombre de mots ayant un prefixe donne**");
		System.out.println("Nombre de mots prefixes par 'wh' dans l'hybrid trie :" + nb_prefixe_h);
		System.out.println("Temps de calcul des prefixes de 'wh' dans l'hybrid trie :" + (fin-deb));
		System.out.println("Nombre de mots prefixes par 'wh' dans le patricia trie :" + nb_prefixe_p);
		System.out.println("Temps de calcul des prefixes de 'wh' dans le patricia trie :" + (fin1-deb1));
		
		System.out.println("Hauteur de h avant equilibrage : " + h.Hauteur());
		System.out.println("Profondeur moyenne de h avant equilibrage : " + h.ProfondeurMoyenne());
		h = h.Suppression("what");
		h = h.addEquilibrage("what", 1);
		System.out.println("Hauteur de h après equilibrage : " + h.Hauteur());
		System.out.println("Profondeur moyenne de h apres equilibrage : " + h.ProfondeurMoyenne());
    }
}
