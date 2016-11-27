package Patricia_tries;

public class Fonctions {
	
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
	
	public static PatriciaTrie addWord(String word, PatriciaTrie a){
		System.out.println("WORD "+ word+"*" + "word de [0]"+ word.charAt(0));
		if( (a==null || a.getKey(word.charAt(0))==null) ){
			if(a==null){
				System.out.println("LAAAA\n");
				a=new PatriciaTrie();
			}
			
			if(a.getKey(word.charAt(0))==null){
				System.out.println("LAAAA2\n");
				a.setKey(word.charAt(0), word);
			}
			
			a.setKey(word.charAt(0), word);
			return a;
		}
		if(word.charAt(0) == ' '){
			System.out.println("fin du mot");
			a.setSon(word.charAt(0), new PatriciaTrie());
		}
		
		if(word.length()>1){
			char c = word.charAt(0);
			System.out.println("Case = " + c);
			String p = prefixe(word,a.getKey(c));
			/*si meme taille alors construire arbre avec la suite du mot*/
			if(p.equals(a.getKey(c))){
				System.out.println("prefixe = "+p + "clé = "+c);

				String restWord = word.substring(p.length(), word.length());
				if(restWord.length()==0){
					return a;
				}
				System.out.println("-->word =*" +restWord+"*");	
				if(restWord.charAt(restWord.length()-1)!=' '){
					restWord=restWord.concat(" ");
				}
				PatriciaTrie t = new PatriciaTrie();
				t.setKey(restWord.charAt(0), restWord);
				System.out.println("Avant ajout noeud = "+a.getKey(p.charAt(0)));
				a.setSon(p.charAt(0), addWord(restWord,a.getSon(p.charAt(0))));
				System.out.println("Apres ajout\n");

				return a;
			}
			else{
				String cle_actuel = a.getKey(c);
				PatriciaTrie sub_tree_cle_actuel=a.getSon(c);
				
				int length = a.getKey(c).length();
				
				String non_common_rest = cle_actuel.substring(p.length(), length);
				
				System.out.println("-->"+ a.getKey(c) + " le reste="+ non_common_rest);
				a.setKey(c, p);
				
				PatriciaTrie t = new PatriciaTrie();
				t.setKey(non_common_rest.charAt(0), non_common_rest);
				t.setSon(non_common_rest.charAt(0), sub_tree_cle_actuel);
				String word_suite = word.substring(p.length());
				t.setKey(word.charAt(p.length()),word_suite);
				System.out.println("je vais " + word_suite);
				a.setSon(p.charAt(0), t);
				return a;
			}
		}

		return a;
	}

	public static PatriciaTrie deleteWord(String word,PatriciaTrie a){	
		System.out.println("1.word ="+ word+"*");
		if(word.length()==0){
			return null;
		}
		
		String c = a.getKey(word.charAt(0));
		//System.out.println("la clé " +c+"*");
		
		if(c!=null){
			//System.out.println("boucle");
			
			if(word.compareTo(c)==0){
				//System.out.println("word ="+ word+ "cle " +c);
				a.setKey(word.charAt(0), new String());
				return a;
			}
			else{
				String p = prefixe(c,word);
				if(p.length()!=a.getKey(word.charAt(0)).length()){
					System.out.println("Your word does not exist");
					return null;
				}
				//System.out.println("prefixe = " + p);
				String nextWord = word.substring(p.length());
				
				PatriciaTrie t = new PatriciaTrie();
				//System.out.println("nextWord =*" +nextWord + "*prefixe = "+p.charAt(0) + "*Val al =" + a.getSon(p.charAt(0)));
				
				if(a.getSon(p.charAt(0))!=null){
					//System.out.println("FILS");
					a.setSon(word.charAt(0), a.getSon(word.charAt(0)));
					//System.out.println("je vais dans " + a.getKey(word.charAt(0)));
					t=deleteWord(nextWord,a.getSon(p.charAt(0)));
					if(t==null){
						System.out.println("Your word does not exist\n");
						return a;
					}
					
					//System.out.println("Je suis pas null " +t);
					String s = new String();
					//System.out.println(" J'affiche");
					t.printTrie();
					//System.out.println(" J'ai fini d'afficher");
					if((s=t.notNullKey())==null){
						//System.out.println("not null");
						//System.out.println("Avant getKey " +a.getKey(nextWord.charAt(0)));
						a.setKey(nextWord.charAt(0),new String());
						//System.out.println("Apres getKey " +a.getKey(nextWord.charAt(0)));
						a.setSon(nextWord.charAt(0),t);
						return a;
					}else{
						String w = a.getKey(word.charAt(0));
						w.concat(s);
						//System.out.println("w = " + w);
						a.setKey(w.charAt(0),w);
						a.setSon(word.charAt(0),a.getSon(s.charAt(0)));
						return a;
					}
				}
				else{
					if(nextWord.length()==0){
						//System.out.println( "->prefixe = " + p);
						a.setSon(p.charAt(0),null);
						return a;
					}
					//System.out.println( "nextWord*"+(nextWord.length())+"* "+a.getKey(p.charAt(0))+" Your word does not exist");
					return null;
				}
			}
		}
		System.out.println("Your word does not exist");
		return null;
	}

	
	public static PatriciaTrie addListOfWords(String phrase, PatriciaTrie a){
		String [] words = phrase.split(" ");
		int i=0;
		for(i=0;i<words.length;i++){
			words[i]=words[i].concat(" ");
			System.out.println("WOrd i = "+i+" words[i] =*"+words[i]+"*");
			if((a=addWord(words[i],a))!=null){
				a.number_words++;
			}
			a.printTrie();
		}
		return a;
	}
	
	
	public static void main(String [] args){
		PatriciaTrie p = new PatriciaTrie();
		String word = "A quel genial professeur de dactylographie sommes nous redevables de la superbe";
		addListOfWords(word,p);
		System.out.println(" SUPPRESSION \n");
		deleteWord("redevons ", p);
		p.printTrie();
		System.out.println("Nombre de mots" + p.number_words);
	
	}
}