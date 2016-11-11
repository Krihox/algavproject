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
	
	public static void main(String [] args){
		/*PatriciaTrie p = new PatriciaTrie();
		
		System.out.println(" value cle vide.." +p.getKey(0)+"..");*/
		
		
		String a = "abcdefgh";
		String b = "abcd";
		String s = prefixe(a,b);
		String w = a.substring(s.length(), a.length()-1);
		
		System.out.println("reste du mot " + w);
	
	}
}