package Patricia_tries;


public class PatriciaTrie {
	String [] keys;
	PatriciaTrie[]sons;
	
	public PatriciaTrie(){
		this.keys=new String[128];
		this.sons=new PatriciaTrie[128];
	}
	
	
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
	public int hauteur(){
		int i=0;
		int max=0;
		for(i=0;i<this.sons.length;i++){
			if(this.sons[i]!=null)
				max=Math.max(max,this.sons[i].hauteur());
		}
		return 1+max;
	}
	
	public static String prefixe (String a, String b){
		int min = Math.min(a.length(), b.length());
		int i=0;
		String prefixe = new String();
		for(i=0;i<min;i++){
			char aa = a.charAt(i);
			char bb = b.charAt(i);
			if(aa != bb){
				return prefixe;
			}
			prefixe+=aa;
		}
		return prefixe;
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
	
	public void printTrie(){
		int i = 0;
		System.out.print("(");
		for(i=0; i<128;i++){
			String c = this.getKey(i);
			if(c == null){
				System.out.print("*");
			}else{
				System.out.print(" "+ c);
				if(this.getSon(i)!=null){
					this.getSon(i).printTrie();
					//System.out.println();
				}
			}
		}
		System.out.println(")");
	}
	
}