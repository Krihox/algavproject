package Patricia_tries;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Fonctions {
	
	/** methode prefixe
	 * @param a : String
	 * @param b : String
	 * @return le prefixe en commun entre a et b
	 */
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
	
	/** methode addWord ajoute un mot dans le patricia trie
	 * 
	 * @param word : String
	 * @param a : PatriciaTrie
	 * @return : PatriciaTrie a
	 */
	public static PatriciaTrie addWord(String word, PatriciaTrie a){
		
		if( (a==null || a.getKey(word.charAt(0))==null) ){
			if(a==null){
				a=new PatriciaTrie();
			}
			if(a.getKey(word.charAt(0))==null){
				a.setKey(word.charAt(0), word);
			}
			a.setKey(word.charAt(0), word);
			return a;
		}
		if(word.charAt(0) == ' '){
			a.setSon(word.charAt(0), null);
		}
		
		if(word.length()>1){
			char c = word.charAt(0);
			String p = prefixe(word,a.getKey(c));
			/*si meme taille alors construire arbre avec la suite du mot*/
			if(p.equals(a.getKey(c))){
				String restWord = word.substring(p.length(), word.length());
				if(restWord.length()==0){
					return a;
				}
				if(restWord.charAt(restWord.length()-1)!=' '){
					restWord=restWord.concat(" ");
				}
				PatriciaTrie t = new PatriciaTrie();
				t.setKey(restWord.charAt(0), restWord);
				a.setSon(p.charAt(0), addWord(restWord,a.getSon(p.charAt(0))));
				return a;
			}
			else{
				String cle_actuel = a.getKey(c);
				PatriciaTrie sub_tree_cle_actuel=a.getSon(c);
				
				int length = a.getKey(c).length();
				
				String non_common_rest = cle_actuel.substring(p.length(), length);
				a.setKey(c, p);				
				PatriciaTrie t = new PatriciaTrie();
				t.setKey(non_common_rest.charAt(0), non_common_rest);
				t.setSon(non_common_rest.charAt(0), sub_tree_cle_actuel);
				String word_suite = word.substring(p.length());
				t.setKey(word.charAt(p.length()),word_suite);
				a.setSon(p.charAt(0), t);
				return a;
			}
		}
		return a;
	}

	/** methode deleteWord supprime un mot d un patricia trie (s'il existe)
	 * 
	 * @param word : String
	 * @param a : PatriciaTrie
	 * @return PatriciaTrie
	 */
	public static PatriciaTrie deleteWord(String word,PatriciaTrie a){	
		if(word.length()==0){
			return null;
		}
		String c = a.getKey(word.charAt(0));		
		if(c!=null){
			if(word.compareTo(c)==0){
				if(a.getSon(word.charAt(0))==null){
					a.setKey(word.charAt(0), null);
					return a;
				}
				return a;
			}
			else{
				String p = prefixe(c,word);
				if(p.length()!=a.getKey(word.charAt(0)).length()){
					return null;
				}
				String nextWord = word.substring(p.length());
				PatriciaTrie t = new PatriciaTrie();				
				if(a.getSon(p.charAt(0))!=null){
					t=deleteWord(nextWord,a.getSon(p.charAt(0)));
					if(t==null){
						return a;
					}
					a.setSon(p.charAt(0), t);
				}
				else{
					if(nextWord.length()==0){
						a.setSon(p.charAt(0),null);
						return a;
					}
					return null;
				}
			}
		}
		return a;
	}

	/** methode addListOfWords, ajoute un ensemble de mots (d'une phrase) dans un patricia trie
	 * 
	 * @param phrase : String
	 * @param a : PatriciaTrie
	 * @return PatriciaTrie
	 */
	public static PatriciaTrie addListOfWords(String phrase, PatriciaTrie a){
		String [] words = phrase.split(" ");
		int i=0;
		for(i=0;i<words.length;i++){
			words[i]=words[i].concat(" ");
			addWord(words[i],a);
		}
		return a;
	}

	/** methode searchWord, recherche un certain mot dans un patricia trie
	 * 
	 * @param word : String
	 * @param a : PatriciaTrie
	 * @return : boolean
	 */
	public static boolean searchWord(String word,PatriciaTrie a){
		if(a==null || word.length()==0){
			return false;
		}
		
		String current_key = a.getKey(word.charAt(0));
		if(current_key!=null){
			if(current_key.equals(word)){
				return true;
			}
			else if(current_key.length() > word.length()){
				return false;
			}
			else{
				String prefixe = prefixe(current_key,word);
				if(prefixe.length()>current_key.length()){
					return false;
				}
				else{
					String nextWord = word.substring(prefixe.length());
					if(a.getSon(prefixe.charAt(0))==null){
						return false;
					}
					else{
						return searchWord(nextWord,a.getSon(prefixe.charAt(0)));
					}
				}
			}
		}
		return false;
	}
	
	/** m�thode compteMots, compte le nombre de mots, dans un patricia-trie
	 * 
	 * @param a : PatriciaTrie
	 * @return : int
	 */
	public static int compteMots(PatriciaTrie a){
		int res =0;
		if(a==null){
			return 0;
		}
		for(int i =0;i<a.getSons().length;i++){
			if(a.getKey(i)!=null && a.getSon(i)==null){
				if(a.getKey(i).contains(" ")){
					res+=1;
				}
			}else if(a.getKey(i)!=null){
				res+=compteMots(a.getSon(i));
			}
		}
		return res;
		
	}

	/** methode compteNil, compte le nombre de noeud vide, dans un patricia-trie
	 * 
	 * @param a : PatriciaTrie
	 * @return : int
	 */
	public static int compteNil(PatriciaTrie a){
		int res =0;
		if(a==null){
			return 1;
		}
		for(int i =0;i<a.getSons().length;i++){
			if(a.getKey(i)==null){
				res+=1;
			}else if(a.getKey(i)!=null){
				res+=compteNil(a.getSon(i));
			}
		}
		return res;
		
	}
	
	/** methode hauteur, calcule la hauteur d'un patricia-trie
	 * 
	 * @param a : PatriciaTrie
	 * @return : int
	 */
	public static int hauteur (PatriciaTrie a){
		if(a==null){
			return 0;
		}
		int max=0;
		for(int i =0;i<a.getSons().length;i++){
			if(a.getKey(i)!=null){
				if(a.getSon(i)!=null){
					max=Math.max(max, hauteur(a.getSon(i)));
				}
			}
		}
		return 1+max;
	}

	/** methode profondeurMoyenne, calcule la profondeur moyenne d'un arbre
	 * 
	 * @param a : PatriciaTtrie
	 * @return : int
	 */
	public static int profondeurMoyenne (PatriciaTrie a){
		if(a==null){
			return 0;
		}
		int max=0;
		int nb_noeud=0;
		for(int i =0;i<a.getSons().length;i++){
			if(a.getKey(i)!=null){
				if(a.getSon(i)!=null){
					max+=hauteur(a.getSon(i));
					nb_noeud++;
				}
			}
		}
		return (max/nb_noeud);
	}
	
	/** methode searchPrefixe, compte le nombre de mots ayant un certain prefixe
	 * 
	 * @param word : String
	 * @param a : PatriciaTrie
	 * @return : int
	 */
	public static int searchPrefixe(String word, PatriciaTrie a){
		if(a==null || word.length()==0){
			return 0;
		}
		String current_key = a.getKey(word.charAt(0));
		PatriciaTrie current_node=a.getSon(word.charAt(0));
		int res=0;
		if(current_key!=null){
			if(current_key.equals(word)){
				PatriciaTrie sub = a.getSon(word.charAt(0));
				if(sub!=null)
					res+=compteMots(sub);
			}else{
				String prefixe = prefixe(word,current_key);
				String end_word = word.substring(prefixe.length());	
				
				if(prefixe!=null && (end_word==null || end_word.length()==0)){
					if(current_node!=null){
						return compteMots(current_node);
					}
					return res;
				}
				else if(prefixe!=null && end_word!=null){
					return res+=searchPrefixe(end_word,current_node);
				}
				else{
					
				}
			}
		}
		return res;
	}

	/** methode fill_PatriciaTrie, creer un patricia-trie et le remplit avec les mots contenus dan un fichier
	 * 
	 * @param file : String 
	 * @return : PatriciaTrie
	 */
	public static PatriciaTrie fill_PatriciaTrie(String file){
		try(BufferedReader br= new BufferedReader(new FileReader(file))){
			long deb = System.currentTimeMillis();
			PatriciaTrie p=new PatriciaTrie();
			String line;
			while ((line = br.readLine()) != null) {
				line=line.concat(" ");
				addWord(line,p);
			}
			long fin = System.currentTimeMillis();
			String texte [] = file.split("/");
			String txt=texte[texte.length-1];
			System.out.println("Temps de construction du PatriciaTrie du fichier " + txt +"\nest de : " + (fin-deb) + "ms\n" + "nombre de mots:" + compteMots(p));
			return p;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	/** methode listPath, liste le nom de tous les fichiers present dans un repertoire donn�
	 * 
	 * @param path : File
	 * @return : String []
	 */
	public static String[] listPath(File path) {
	    File files[];
	    files = path.listFiles();
	    Arrays.sort(files);
	    String [] fichiers = new String[files.length];
	    for (int i = 0, n = files.length; i < n; i++) {
	      fichiers[i]=files[i].toString();
	    }
	    return fichiers;
	  }	
	
	/*** methode listerMots, parcours un patricia trie de maniere prefixe pour recuperer les mots contenu dans le trie
	 * 
	 * @param a : PatriciaTrie
	 * @param prefixe : le prefixe d'un mot
	 * @param l : la liste de mots
	 */

	public static void listerMots (PatriciaTrie a,String prefixe, ArrayList<String> l){
		if(a==null){
			return ;
		}
		
		for(int i =0; i<a.getSons().length;i++){
			if(a.getKey(i)!=null && !a.getKey(i).contains(" ")){
				if(a.getSon(i)!=null){
					PatriciaTrie aa = a.getSon(i);
					listerMots(aa,prefixe+a.getKey(i),l);
				}	
			}
			else if(a.getKey(i)!=null && a.getKey(i).contains(" ")){
				l.add(prefixe+a.getKey(i));

			}
		}
	}
	
	/*** la methode listeMots, renvoie une liste contenant tous les mots pr�sent dans un Patricia-trie
	 * 
	 * @param a: PatriciaTrie
	 * @return : ArrayList<String>
	 */
	
	public static ArrayList<String> listeMots(PatriciaTrie a){
		ArrayList<String> list = new ArrayList<String>();
		listerMots(a,"",list);
		return list;
	}
	
	/*** la methode afficheListeMots, affiche les mots contenus dans une liste
	 * 
	 * @param list : ArrayList<String>
	 */
	public static void afficheListeMots(ArrayList<String> list){
		System.out.print("|");
		for(int i = 0; i< list.size();i++){
			System.out.print(list.get(i)+"|");
		}
		System.out.println();
	}
	
	
}