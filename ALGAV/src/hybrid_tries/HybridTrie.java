package hybrid_tries;

import java.util.ArrayList;

public class HybridTrie {
	
	private char character;
	private int value;
	private HybridTrie[] children = new HybridTrie[3];
	
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
	
	// Version avec les 'nill' initialisés en tant que noeuds vides
	/*public int countWords(){
		if(!this == null){
			if(this.getValue() != 0){
				return 1 + this.getInf().countWords() + this.getEq().countWords() + this.getSup().countWords();
			}
			else{
				return 0 + this.getInf().countWords() + this.getEq().countWords() + this.getSup().countWords();
			}
		}
		else{
			return 0;
		}
		
	}*/

	public int countWords(){
		
		if(this.getValue() != 0){
			if(!(this.getInf() == null)){
				if(!(this.getEq() == null)){
					if(!(this.getSup() == null)){
						return 1 + this.getInf().countWords() + this.getEq().countWords() + this.getSup().countWords();
					}
					else{ //this.getInf is non-null ; this.getEq is non-null ; this.getSup is null
						return 1 + this.getInf().countWords() + this.getEq().countWords();
					}
				}
				else{ // this.getInf is non-null ; this.getEq is null
					if(!(this.getSup() == null)){
						return 1 + this.getInf().countWords() + this.getSup().countWords();
					}
					else{ // this.getInf is non-null ; this.getEq is null ; this.getSup is null
						return 1 + this.getInf().countWords();
					}
				}
			}
			else{ //this.getInf is null
				if(!(this.getEq() == null)){
					if(!(this.getSup() == null)){
						return 1 + this.getEq().countWords() + this.getSup().countWords();
					}
					else{ //this.getInf is null ; this.getEq is non-null ; this.getSup is null
						return 1 + this.getEq().countWords();
					}
				}
				else{ // this.getInf is null ; this.getEq is null
					if(!(this.getSup() == null)){
						return 1 + this.getSup().countWords();
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
						return 0 + this.getInf().countWords() + this.getEq().countWords() + this.getSup().countWords();
					}
					else{ //this.getInf is non-null ; this.getEq is non-null ; this.getSup is null
						return 0 + this.getInf().countWords() + this.getEq().countWords();
					}
				}
				else{ // this.getInf is non-null ; this.getEq is null
					if(!(this.getSup() == null)){
						return 0 + this.getInf().countWords() + this.getSup().countWords();
					}
					else{ // this.getInf is non-null ; this.getEq is null ; this.getSup is null
						return 0 + this.getInf().countWords();
					}
				}
			}
			else{ //this.getInf is null
				if(!(this.getEq() == null)){
					if(!(this.getSup() == null)){
						return 0 + this.getEq().countWords() + this.getSup().countWords();
					}
					else{ //this.getInf is null ; this.getEq is non-null ; this.getSup is null
						return 0 + this.getEq().countWords();
					}
				}
				else{ // this.getInf is null ; this.getEq is null
					if(!(this.getSup() == null)){
						return 0 + this.getSup().countWords();
					}
					else{ // this.getInf is null ; this.getEq is null ; this.getSup is null
						return 0;
					}
				}
			}
		}
	}
	

	// Version avec les 'nill' initialisés en tant que noeuds vides
	/*public int countNill(){
		if(!this.isEmpty()){
			return 0 + this.getInf().countNill() + this.getEq().countNill() + this.getSup().countNill();
		}
		else{
			return 1;
		}
	}*/
	
	public int countNill(){
		if(!(this.getInf() == null)){
			if(!(this.getEq() == null)){
				if(!(this.getSup() == null)){
					return this.getInf().countNill() + this.getEq().countNill() + this.getSup().countNill();
				}
				else{ //this.getInf is non-null ; this.getEq is non-null ; this.getSup is null
					return 1 + this.getInf().countNill() + this.getEq().countNill();
				}
			}
			else{ // this.getInf is non-null ; this.getEq is null
				if(!(this.getSup() == null)){
					return 1 + this.getInf().countNill() + this.getSup().countNill();
				}
				else{ // this.getInf is non-null ; this.getEq is null ; this.getSup is null
					return 2 + this.getInf().countNill();
				}
			}
		}
		else{ //this.getInf is null
			if(!(this.getEq() == null)){
				if(!(this.getSup() == null)){
					return 1 + this.getEq().countNill() + this.getSup().countNill();
				}
				else{ //this.getInf is null ; this.getEq is non-null ; this.getSup is null
					return 2 + this.getEq().countNill();
				}
			}
			else{ // this.getInf is null ; this.getEq is null
				if(!(this.getSup() == null)){
					return 2 + this.getSup().countNill();
				}
				else{ // this.getInf is null ; this.getEq is null ; this.getSup is null
					return 3;
				}
			}
		}
	}
	
	public Boolean search(String word){
		if(word.length() == 0){
			return false;
		}
		char c = word.charAt(0);
		if (c < this.character){
			if(this.getInf() == null){
				return false;
			}
			return this.getInf().search(word);			
		}
		else if (c > this.character){
			if(this.getSup() == null){
				return false;
			}
			return this.getSup().search(word);
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
			return this.getEq().search(word.substring(1, word.length()));
		}
	}
	
	private int max(int a, int b, int c){
		if (a>b && a>c)
			return a;
		if(b>a && b>c)
			return b;
		return c;
	}
	
	// Version avec les 'nill' initialisés en tant que noeuds vides
	/*public int height(){
		if(!this.isEmpty()){
			return 1 + max(this.getInf().height(),  this.getEq().height(), this.getSup().height());
		}
		else{
			return 1;
		}
	}*/
	
	public int height(){
		if(!(this.getInf() == null)){
			if(!(this.getEq() == null)){
				if(!(this.getSup() == null)){
					return 1 + max(this.getInf().height(), this.getEq().height(), this.getSup().height());
				}
				else{ //this.getInf is non-null ; this.getEq is non-null ; this.getSup is null
					return 1 + max(this.getInf().height(), this.getEq().height(), 0);
				}
			}
			else{ // this.getInf is non-null ; this.getEq is null
				if(!(this.getSup() == null)){
					return 1 + max(this.getInf().height(), 0, this.getSup().height());
				}
				else{ // this.getInf is non-null ; this.getEq is null ; this.getSup is null
					return 1 + this.getInf().height();
				}
			}
		}
		else{ //this.getInf is null
			if(!(this.getEq() == null)){
				if(!(this.getSup() == null)){
					return 1 + max(0, this.getEq().height(), this.getSup().height());
				}
				else{ //this.getInf is null ; this.getEq is non-null ; this.getSup is null
					return 1 + this.getEq().height();
				}
			}
			else{ // this.getInf is null ; this.getEq is null
				if(!(this.getSup() == null)){
					return 1 + this.getSup().height();
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
	
	// Version avec les 'nill' initialisés en tant que noeuds vides
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
	
	public int averageDepth(){
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
	
	public HybridTrie deleteWord(String word){
		HybridTrie t = this.clone();
		if(t.search(word)){
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
	
	
	public int numberPrefixOf(String word){
		HybridTrie t = this.searchWordTree(word);
		
		if(t == null){
			return 0;
		}
		else{
			if(t.getValue() != 0){
				if(!(t.getEq() == null))
					return 1+t.getEq().countWords();
				else
					return 1;
			}
			else{
				if(!(t.getEq() == null))
					return t.getEq().countWords();
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
