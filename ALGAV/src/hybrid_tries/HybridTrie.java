package hybrid_tries;

public class HybridTrie {
	
	private char character;
	private int value;
	private HybridTrie[] children = new HybridTrie[3];
	
	@SuppressWarnings("null")
	public HybridTrie(){
		this.setCharacter((Character) null);
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
			if(key.length() ==1){
				this.setCharacter(key.charAt(0));
				this.setValue(value);
				this.setInf(new HybridTrie());
				this.setEq(new HybridTrie());
				this.setSup(new HybridTrie());
			}
			else{
				HybridTrie temp = new HybridTrie(key.charAt(0),0,new HybridTrie(),new HybridTrie(),new HybridTrie());				
				temp.getEq().addKey(key.substring(1, key.length()), value);
				
				this.setEq(temp);
			}
		}
		else{
			char c = key.charAt(0);
			if (c < this.character){
				this.getInf().addKey(key.substring(1, key.length()), value);			
			}
			else if (c > this.character){
				this.getSup().addKey(key.substring(1, key.length()), value);
			}
			else{
				this.getEq().addKey(key.substring(1, key.length()), value);
			}
		}
	}
	
	public Boolean isEmpty(){
		return this.getCharacter()==(Character)null 
				&& this.getValue()==0 
				&& this.getInf()==null 
				&& this.getEq()==null 
				&& this.getSup()==null;
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
