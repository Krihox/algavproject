package hybrid_tries;

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
				if(this.getValue()==0){
					this.setCharacter(key.charAt(0));
					this.setValue(value);
					this.setInf(new HybridTrie());
					this.setEq(new HybridTrie());
					this.setSup(new HybridTrie());
				}
			}
			else{
				this.setCharacter(key.charAt(0));
				this.setInf(new HybridTrie());
				this.setEq(new HybridTrie());
				this.setSup(new HybridTrie());
				this.getEq().addKey(key.substring(1, key.length()), value);
			}
		}
		else{
			if(key.length() == 1){
				this.setValue(value);
			}
			else{
				char c = key.charAt(0);
				if (c < this.character){
					this.getInf().addKey(key, value);			
				}
				else if (c > this.character){
					this.getSup().addKey(key, value);
				}
				else{
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
		if(!this.isEmpty()){
			if(!this.getInf().isEmpty()){
				this.getInf().displayWords(current_word);
			}
			if(!this.getSup().isEmpty()){
				this.getSup().displayWords(current_word);
			}
			if(!this.getEq().isEmpty()){
				current_word+=this.getCharacter();
				this.getEq().displayWords(current_word);
			}
			if(this.getValue()!= 0){
				if(this.getEq().isEmpty()){
					current_word+=this.getCharacter();
				}
				System.out.println(current_word);
			}
		}
	}
	
	public int countWords(){
		if(!this.isEmpty()){
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
		
	}
	
	public Boolean search(String word){
		if(word.length() == 0){
			return false;
		}
		char c = word.charAt(0);
		if (c < this.character){
			if(this.getInf().isEmpty()){
				return false;
			}
			return this.getInf().search(word);			
		}
		else if (c > this.character){
			if(this.getSup().isEmpty()){
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
			if(this.getEq().isEmpty()){
				return false;
			}
			return this.getEq().search(word.substring(1, word.length()));
		}
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
