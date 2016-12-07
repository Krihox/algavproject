package Tempo_Test;

import java.util.ArrayList;

import Patricia_tries.PatriciaTrie;
import hybrid_tries.HybridTrie;

public class Test {
	
	public static PatriciaTrie HybridToPatricia(HybridTrie h){
		
		ArrayList<String> words_list = h.ListeMots();
		PatriciaTrie p = new PatriciaTrie();
		
		for(int i=0 ; i<words_list.size() ; i++){
			Patricia_tries.Fonctions.addWord(words_list.get(i), p);
		}
		
		return p;
	}

	public static void main(String[] args) {
		HybridTrie h = new HybridTrie();
		h.addKey("abc", 1);
		h.addKey("son", 2);
		h.addKey("att", 3);
		
		PatriciaTrie p = HybridToPatricia(h);
		p.printTrie();
	}
	
}
