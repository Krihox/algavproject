package Patricia_tries;


public class PatriciaTrie {
	String [] keys;
	PatriciaTrie[]sons;
	
	/*Constructeur*/
	public PatriciaTrie(){
		this.keys=new String[128];
		this.sons=new PatriciaTrie[128];
	}
	
	/*Getter et Setter*/
	public String getKey(int i){
		return this.keys[i];
		
	}
	
	public void setKey(int i,String s){
		this.keys[i]=s;
	}
	
	public void setSon(int i,PatriciaTrie p){
		this.sons[i]=p;
	}
	
	public PatriciaTrie getSon(int i){
		return this.sons[i];
	}
	
	public PatriciaTrie[] getSons(){
		return this.sons;
	}
	
	public String [] getKeys(){
		return this.keys;
	}
	
	public String notNullKey(){
		int i = 0,n=0;
		String notNull = null;
		for(i= 0; i<this.getKeys().length;i++){
			if(this.getKey(i) !=null){
				notNull=this.getKey(i);
				n++;
			}
		}
		return (n==1)?notNull:null;
	}
	
	/*Methode d'affichage d'un patricia-trie*/
	public void printTrie(){
		int i = 0;
		System.out.print("(");
		for(i=0; i<128;i++){
			String c = this.getKey(i);
			if(c == null){
				
			}else{
				System.out.print("."+ c);
				if(this.getSon(i)!=null){
					this.getSon(i).printTrie();
				}
			}
		}
		System.out.println(")");
	}
	
}