package hybrid_tries;

public class DisplayHybridTrie {

	public static void main(String[] args) {
		
		HybridTrie t = new HybridTrie();
		t.addKey("abc", 1);
		t.addKey("son", 2);
		t.addKey("att", 3);
		
		System.out.println("La racine est :" + t.getCharacter());
		System.out.println("Le fils gauche de la racine est :" + t.getInf().getCharacter());
		System.out.println("Le fils du milieu de la racine est :" + t.getEq().getCharacter());
		System.out.println("Le fils droit de la racine est :" + t.getSup().getCharacter());
		
		System.out.println("La racine est :" + t.getCharacter());
		System.out.println("La racine est :" + t.getCharacter());
		System.out.println("La racine est :" + t.getCharacter());
		
		t.displayWords("");
    }
	
	/*public void displayWords(HybridTrie t, String current_word, int index, ArrayList<HybridTrie> t_list, ArrayList<String> str_list){
		
		if(!t.isEmpty()){
			if(!t.getInf().isEmpty()){
				t_list.add(index, t.getInf());
				str_list.add(index, current_word);
				index++;
			}
			if(!t.getSup().isEmpty()){
				t_list.add(index, t.getSup());
				str_list.add(index, current_word);
				index++;
			}
			
			System.out.println(t.getCharacter());
			current_word += t.getCharacter();
			
			if(!t.getEq().isEmpty()){
				displayWords(t.getEq(), current_word, index, t_list, str_list);
			}
			
			if(!t_list.isEmpty()){
				
			}
		}
	}*/
	
	
	
	
}
