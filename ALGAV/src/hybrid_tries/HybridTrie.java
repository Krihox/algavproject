package hybrid_tries;

import java.util.ArrayList;

public class HybridTrie {
	
	private char character;
	private int value;
	private HybridTrie[] children = new HybridTrie[3];
	
	private static final int THRESHOLD = 1;
	
	public HybridTrie(){
		this.setCharacter('0');
		this.setValue(0);
		this.setInf(null);
		this.setEq(null);
		this.setSup(null);
	}
	
	public HybridTrie(char _character, int _value, HybridTrie _inf, HybridTrie _eq, HybridTrie _sup){
		this.setCharacter(_character);
		this.setValue(_value);
		this.setInf(_inf);
		this.setEq(_eq);
		this.setSup(_sup);
	}
	
	/* 
	 * Adds key to the hybrid trie ; recursively creates new sub-hybrid tries if necessary.
	 * The value is contained in the trie that contains the key's last letter
	 * */
	public void addKey(String key, int value){
		if (this.isEmpty()){
			if(key.length() == 1){
				this.setCharacter(key.charAt(0));
				this.setValue(value);
				this.setInf(null);
				this.setEq(null);
				this.setSup(null);
			}
			else{
				this.setCharacter(key.charAt(0));
				this.setInf(null);
				this.setEq(new HybridTrie());
				this.setSup(null);
				this.getEq().addKey(key.substring(1, key.length()), value);
			}
		}
		else{
			char c = key.charAt(0);
			if (c < this.character){
				if(this.getInf() == null)
					this.setInf(new HybridTrie());				
				this.getInf().addKey(key, value);			
			}
			else if (c > this.character){
				if(this.getSup() == null)
					this.setSup(new HybridTrie());
				this.getSup().addKey(key, value);
			}
			else{
				if(key.length() == 1)
					this.setValue(value);
				else{
					if(this.getEq() == null)
						this.setEq(new HybridTrie());
					this.getEq().addKey(key.substring(1, key.length()), value);
				}
			}
		}
	}
	
	private int countLeftNodes(){
		int cpt = 0;
		if(this.getInf() != null)
			if(this.getEq() != null)
				cpt += max(1+this.getInf().countLeftNodes(), this.getEq().countLeftNodes());
			else
				cpt += 1 +this.getInf().countLeftNodes();
		else{
			if(this.getEq() != null)
				cpt += this.getEq().countLeftNodes();
		}
		return cpt;
	}
	
	private int countRightNodes(){
		int cpt = 0;
		if(this.getSup() != null)
			if(this.getEq() != null)
				cpt += max(1+this.getSup().countLeftNodes(), this.getEq().countLeftNodes());
			else
				cpt += 1 +this.getSup().countLeftNodes();
		else{
			if(this.getEq() != null)
				cpt += this.getEq().countLeftNodes();
		}
		return cpt;
	}
	
	private HybridTrie equilibrage(){
		
		 ArrayList<String> words_list = this.ListeMots();		 
		 String[] words_to_insert = new String[words_list.size()];
		 
		 int indexOfMiddle = words_list.size()/2 + words_list.size()%2 - 1;
		 
		 int j=0;
		 for(int i = 0; i<words_list.size()-1; i+=2){
			 words_to_insert[i] = words_list.get(indexOfMiddle+j);
			 j++;
		 }
		 
		 if(indexOfMiddle%2 == 0)
			 words_to_insert[words_list.size()-1] = words_list.get(indexOfMiddle+j);
		 
		 j=1;
		 for(int i = 1; i<words_list.size(); i+=2){
			 words_to_insert[i] = words_list.get(indexOfMiddle-j);
			 j++;
		 }
		 
		 HybridTrie t = new HybridTrie();
		 
		 for(int i=0; i<words_to_insert.length;i++){
			 t.addKey(words_to_insert[i], i+1);
		 }
		 
		 return t;		 
	}
	
	public HybridTrie addEquilibrage(String key, int value){
		this.addKey(key,value);
		if(!(Math.abs(this.countLeftNodes() - this.countRightNodes()) > THRESHOLD))
			return this;
		else
			return this.equilibrage();
	}
	
	public Boolean isEmpty(){
		return this.getCharacter()=='0'
				&& this.getValue()==0 
				&& this.getInf()==null 
				&& this.getEq()==null 
				&& this.getSup()==null;
	}
	
	
	public void displayWords(String current_word){
		if(!(this.getInf() == null)){
			this.getInf().displayWords(current_word);
		}
		if(this.getValue()!= 0){
			current_word+=this.getCharacter();
			System.out.println(current_word);
			current_word = current_word.substring(0,current_word.length()-1);
		}
		if(!(this.getEq() == null)){
			current_word+=this.getCharacter();
			this.getEq().displayWords(current_word);
			current_word = current_word.substring(0,current_word.length()-1);
		}
		if(!(this.getSup() == null)){
			this.getSup().displayWords(current_word);
		}
	}
	
	private void remplirListe(ArrayList<String> list, String current_word){
		if(!(this.getInf() == null)){
			this.getInf().remplirListe(list, current_word);
		}
		if(this.getValue()!= 0){
			current_word+=this.getCharacter();
			list.add(current_word);
			current_word = current_word.substring(0,current_word.length()-1);
		}
		if(!(this.getEq() == null)){
			current_word+=this.getCharacter();
			this.getEq().remplirListe(list, current_word);
			current_word = current_word.substring(0,current_word.length()-1);
		}
		if(!(this.getSup() == null)){
			this.getSup().remplirListe(list, current_word);
		}
	}
	
	public ArrayList<String> ListeMots(){
		ArrayList<String> words_list = new ArrayList<String>();
		
		this.remplirListe(words_list, "");
		
		return words_list;
		
	}
	
	// Version avec les 'nill' initialis�s en tant que noeuds vides
	/*public int ComptageMots(){
		if(!this == null){
			if(this.getValue() != 0){
				return 1 + this.getInf().ComptageMots() + this.getEq().ComptageMots() + this.getSup().ComptageMots();
			}
			else{
				return 0 + this.getInf().ComptageMots() + this.getEq().ComptageMots() + this.getSup().ComptageMots();
			}
		}
		else{
			return 0;
		}
		
	}*/

	public int ComptageMots(){
		
		if(this.getValue() != 0){
			if(!(this.getInf() == null)){
				if(!(this.getEq() == null)){
					if(!(this.getSup() == null)){
						return 1 + this.getInf().ComptageMots() + this.getEq().ComptageMots() + this.getSup().ComptageMots();
					}
					else{ //this.getInf is non-null ; this.getEq is non-null ; this.getSup is null
						return 1 + this.getInf().ComptageMots() + this.getEq().ComptageMots();
					}
				}
				else{ // this.getInf is non-null ; this.getEq is null
					if(!(this.getSup() == null)){
						return 1 + this.getInf().ComptageMots() + this.getSup().ComptageMots();
					}
					else{ // this.getInf is non-null ; this.getEq is null ; this.getSup is null
						return 1 + this.getInf().ComptageMots();
					}
				}
			}
			else{ //this.getInf is null
				if(!(this.getEq() == null)){
					if(!(this.getSup() == null)){
						return 1 + this.getEq().ComptageMots() + this.getSup().ComptageMots();
					}
					else{ //this.getInf is null ; this.getEq is non-null ; this.getSup is null
						return 1 + this.getEq().ComptageMots();
					}
				}
				else{ // this.getInf is null ; this.getEq is null
					if(!(this.getSup() == null)){
						return 1 + this.getSup().ComptageMots();
					}
					else{ // this.getInf is null ; this.getEq is null ; this.getSup is null
						return 1;
					}
				}
			}
		}
		else{
			if(!(this.getInf() == null)){
				if(!(this.getEq() == null)){
					if(!(this.getSup() == null)){
						return 0 + this.getInf().ComptageMots() + this.getEq().ComptageMots() + this.getSup().ComptageMots();
					}
					else{ //this.getInf is non-null ; this.getEq is non-null ; this.getSup is null
						return 0 + this.getInf().ComptageMots() + this.getEq().ComptageMots();
					}
				}
				else{ // this.getInf is non-null ; this.getEq is null
					if(!(this.getSup() == null)){
						return 0 + this.getInf().ComptageMots() + this.getSup().ComptageMots();
					}
					else{ // this.getInf is non-null ; this.getEq is null ; this.getSup is null
						return 0 + this.getInf().ComptageMots();
					}
				}
			}
			else{ //this.getInf is null
				if(!(this.getEq() == null)){
					if(!(this.getSup() == null)){
						return 0 + this.getEq().ComptageMots() + this.getSup().ComptageMots();
					}
					else{ //this.getInf is null ; this.getEq is non-null ; this.getSup is null
						return 0 + this.getEq().ComptageMots();
					}
				}
				else{ // this.getInf is null ; this.getEq is null
					if(!(this.getSup() == null)){
						return 0 + this.getSup().ComptageMots();
					}
					else{ // this.getInf is null ; this.getEq is null ; this.getSup is null
						return 0;
					}
				}
			}
		}
	}
	

	// Version avec les 'nill' initialis�s en tant que noeuds vides
	/*public int ComptageNill(){
		if(!this.isEmpty()){
			return 0 + this.getInf().ComptageNill() + this.getEq().ComptageNill() + this.getSup().ComptageNill();
		}
		else{
			return 1;
		}
	}*/
	
	public int ComptageNill(){
		if(!(this.getInf() == null)){
			if(!(this.getEq() == null)){
				if(!(this.getSup() == null)){
					return this.getInf().ComptageNill() + this.getEq().ComptageNill() + this.getSup().ComptageNill();
				}
				else{ //this.getInf is non-null ; this.getEq is non-null ; this.getSup is null
					return 1 + this.getInf().ComptageNill() + this.getEq().ComptageNill();
				}
			}
			else{ // this.getInf is non-null ; this.getEq is null
				if(!(this.getSup() == null)){
					return 1 + this.getInf().ComptageNill() + this.getSup().ComptageNill();
				}
				else{ // this.getInf is non-null ; this.getEq is null ; this.getSup is null
					return 2 + this.getInf().ComptageNill();
				}
			}
		}
		else{ //this.getInf is null
			if(!(this.getEq() == null)){
				if(!(this.getSup() == null)){
					return 1 + this.getEq().ComptageNill() + this.getSup().ComptageNill();
				}
				else{ //this.getInf is null ; this.getEq is non-null ; this.getSup is null
					return 2 + this.getEq().ComptageNill();
				}
			}
			else{ // this.getInf is null ; this.getEq is null
				if(!(this.getSup() == null)){
					return 2 + this.getSup().ComptageNill();
				}
				else{ // this.getInf is null ; this.getEq is null ; this.getSup is null
					return 3;
				}
			}
		}
	}
	
	public Boolean Recherche(String word){
		if(word.length() == 0){
			return false;
		}
		char c = word.charAt(0);
		if (c < this.character){
			if(this.getInf() == null){
				return false;
			}
			return this.getInf().Recherche(word);			
		}
		else if (c > this.character){
			if(this.getSup() == null){
				return false;
			}
			return this.getSup().Recherche(word);
		}
		else{
			if(word.length()==1){
				if (this.getValue() != 0){
					return true;
				}
				else{
					return false;
				}
			}
			if(this.getEq() == null){
				return false;
			}
			return this.getEq().Recherche(word.substring(1, word.length()));
		}
	}
	
	private int max(int a, int b){
		if (a>b)
			return a;
		else
			return b;
	}
	
	private int max(int a, int b, int c){
		if (a>b && a>c)
			return a;
		if(b>a && b>c)
			return b;
		return c;
	}
	
	// Version avec les 'nill' initialis�s en tant que noeuds vides
	/*public int Hauteur(){
		if(!this.isEmpty()){
			return 1 + max(this.getInf().Hauteur(),  this.getEq().Hauteur(), this.getSup().Hauteur());
		}
		else{
			return 1;
		}
	}*/
	
	public int Hauteur(){
		if(!(this.getInf() == null)){
			if(!(this.getEq() == null)){
				if(!(this.getSup() == null)){
					return 1 + max(this.getInf().Hauteur(), this.getEq().Hauteur(), this.getSup().Hauteur());
				}
				else{ //this.getInf is non-null ; this.getEq is non-null ; this.getSup is null
					return 1 + max(this.getInf().Hauteur(), this.getEq().Hauteur(), 0);
				}
			}
			else{ // this.getInf is non-null ; this.getEq is null
				if(!(this.getSup() == null)){
					return 1 + max(this.getInf().Hauteur(), 0, this.getSup().Hauteur());
				}
				else{ // this.getInf is non-null ; this.getEq is null ; this.getSup is null
					return 1 + this.getInf().Hauteur();
				}
			}
		}
		else{ //this.getInf is null
			if(!(this.getEq() == null)){
				if(!(this.getSup() == null)){
					return 1 + max(0, this.getEq().Hauteur(), this.getSup().Hauteur());
				}
				else{ //this.getInf is null ; this.getEq is non-null ; this.getSup is null
					return 1 + this.getEq().Hauteur();
				}
			}
			else{ // this.getInf is null ; this.getEq is null
				if(!(this.getSup() == null)){
					return 1 + this.getSup().Hauteur();
				}
				else{ // this.getInf is null ; this.getEq is null ; this.getSup is null
					return 1;
				}
			}
		}
	}
	
	private Boolean isLeaf(){
		if (this.getInf() == null
				&& this.getEq() == null
				&& this.getSup() == null)
			return true;
		return false;
	}
	
	// Version avec les 'nill' initialis�s en tant que noeuds vides
	/*private int nbLeafs(){
		if (this.isEmpty())
			return 0;
		if(this.isLeaf())
			return 1;
		else{
			return this.getInf().nbLeafs() + this.getEq().nbLeafs() + this.getSup().nbLeafs();
		}
	}*/
	
	private int nbLeafs(){
		if (this.isEmpty())
			return 0;
		if(this.isLeaf())
			return 1;
		else{
			if(!(this.getInf() == null)){
				if(!(this.getEq() == null)){
					if(!(this.getSup() == null)){
						return this.getInf().nbLeafs() + this.getEq().nbLeafs() + this.getSup().nbLeafs();
					}
					else{ //this.getInf is non-null ; this.getEq is non-null ; this.getSup is null
						return this.getInf().nbLeafs() + this.getEq().nbLeafs();
					}
				}
				else{ // this.getInf is non-null ; this.getEq is null
					if(!(this.getSup() == null)){
						return this.getInf().nbLeafs() + this.getSup().nbLeafs();
					}
					else{ // this.getInf is non-null ; this.getEq is null ; this.getSup is null
						return this.getInf().nbLeafs();
					}
				}
			}
			else{ //this.getInf is null
				if(!(this.getEq() == null)){
					if(!(this.getSup() == null)){
						return this.getEq().nbLeafs() + this.getSup().nbLeafs();
					}
					else{ //this.getInf is null ; this.getEq is non-null ; this.getSup is null
						return this.getEq().nbLeafs();
					}
				}
				else{ // this.getInf is null ; this.getEq is null
					if(!(this.getSup() == null)){
						return this.getSup().nbLeafs();
					}
					else{ // this.getInf is null ; this.getEq is null ; this.getSup is null
						  // case already treated with isLeaf() ...
						return 1;
					}
				}
			}
		}
	}
	
	/*private int totalLeafDepth(int depth){
		if(this.isEmpty())
			return 0;
		if(this.isLeaf())
			return depth;
		else{
			depth++;
			return this.getInf().totalLeafDepth(depth)
					+ this.getEq().totalLeafDepth(depth)
					+ this.getSup().totalLeafDepth(depth);
		}
	}*/
	
	private int totalLeafDepth(int depth){
		if(this.isEmpty())
			return 0;
		if(this.isLeaf())
			return depth;
		else{
			depth++;
			if(!(this.getInf() == null)){
				if(!(this.getEq() == null)){
					if(!(this.getSup() == null)){
						return this.getInf().totalLeafDepth(depth) + this.getEq().totalLeafDepth(depth) + this.getSup().totalLeafDepth(depth);
					}
					else{ //this.getInf is non-null ; this.getEq is non-null ; this.getSup is null
						return this.getInf().totalLeafDepth(depth) + this.getEq().totalLeafDepth(depth);
					}
				}
				else{ // this.getInf is non-null ; this.getEq is null
					if(!(this.getSup() == null)){
						return this.getInf().totalLeafDepth(depth) + this.getSup().totalLeafDepth(depth);
					}
					else{ // this.getInf is non-null ; this.getEq is null ; this.getSup is null
						return this.getInf().nbLeafs();
					}
				}
			}
			else{ //this.getInf is null
				if(!(this.getEq() == null)){
					if(!(this.getSup() == null)){
						return this.getEq().totalLeafDepth(depth) + this.getSup().totalLeafDepth(depth);
					}
					else{ //this.getInf is null ; this.getEq is non-null ; this.getSup is null
						return this.getEq().totalLeafDepth(depth);
					}
				}
				else{ // this.getInf is null ; this.getEq is null
					if(!(this.getSup() == null)){
						return this.getSup().totalLeafDepth(depth);
					}
					else{ // this.getInf is null ; this.getEq is null ; this.getSup is null
						  // case already treated with isLeaf() ...
						return 0;
					}
				}
			}
		}
	}
	
	public int ProfondeurMoyenne(){
		//0 if root shouldn't be included into the depth ; 1 if it does
		return this.totalLeafDepth(0) / this.nbLeafs();
	}
	
	
	private Boolean delWord(String word){
		
		char c = word.charAt(0);
		
		if(word.length() == 1){
			if(this.getCharacter() == c){
				this.value = 0;
				return (this.getInf() == null 
						&& this.getEq() == null 
						&& this.getSup() == null);
			}
		}
		
		if(c < this.getCharacter()){
			if(this.getInf().delWord(word)){
				this.setInf(null);
			}
		}
		if(c > this.getCharacter()){
			if(this.getSup().delWord(word)){
				this.setSup(null);
			}
		}
		if(c == this.getCharacter()){
			if(this.getEq().delWord(word.substring(1, word.length()))){
				this.setEq(null);
			}
		}
		return (this.getInf() == null 
				&& this.getEq() == null 
				&& this.getSup() == null
				&& this.getValue() == 0);
		
	}
	
	public HybridTrie Suppression(String word){
		HybridTrie t = this.clone();
		if(t.Recherche(word)){
			t.delWord(word);
			return t;
		}
		return t;
	}
	
	
private HybridTrie searchWordTree(String word){
		
		char c = word.charAt(0);
		
		if(word.length() == 0){
			return null;
		}
		if(word.length() == 1){
			if (c == this.character){
				return this;
			}
		}
		if(c<this.character){
			if(!(this.getInf() == null))
				return this.getInf().searchWordTree(word);
			else
				return null;
		}
		if(c>this.character){
			if(!(this.getSup() == null))
				return this.getSup().searchWordTree(word);
			else
				return null;
		}
		if(c==this.character){
			if(!(this.getEq() == null)){
				return this.getEq().searchWordTree(word.substring(1, word.length()));
			}
		}
		return null;		
	}
	
	
	public int Prefixe(String word){
		HybridTrie t = this.searchWordTree(word);
		
		if(t == null){
			return 0;
		}
		else{
			if(t.getValue() != 0){
				if(!(t.getEq() == null))
					return 1+t.getEq().ComptageMots();
				else
					return 1;
			}
			else{
				if(!(t.getEq() == null))
					return t.getEq().ComptageMots();
				else
					return 0;
			}
		}
	}
	
	public HybridTrie clone(){
		HybridTrie t = new HybridTrie();
		
		t.setCharacter(this.getCharacter());
		t.setValue(this.getValue());
		
		if(!(this.getEq() == null))
			t.setEq(this.getEq().clone());
		else
			t.setEq(new HybridTrie());
		
		if(!(this.getInf() == null))
			t.setInf(this.getInf().clone());
		else
			t.setInf(new HybridTrie());
		
		if(!(this.getSup() == null))
			t.setSup(this.getSup().clone());
		else
			t.setSup(new HybridTrie());

		return t;
	}

	public char getCharacter() {
		return character;
	}

	public void setCharacter(char c) {
		this.character = c;
	}


	public int getValue() {
		return value;
	}


	public void setValue(int value) {
		this.value = value;
	}


	public HybridTrie getInf() {
		return this.children[0];
	}


	public void setInf(HybridTrie inf) {
		this.children[0] = inf;
	}


	public HybridTrie getEq() {
		return this.children[1];
	}


	public void setEq(HybridTrie eq) {
		this.children[1] = eq;
	}


	public HybridTrie getSup() {
		return this.children[2];
	}


	public void setSup(HybridTrie sup) {
		this.children[2] = sup;
	}

}
