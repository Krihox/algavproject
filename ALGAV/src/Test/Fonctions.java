package Test;

import hybrid_tries.HybridTrie;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

import Patricia_tries.*;

public class Fonctions {

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
	
	public static PatriciaTrie fill_PatriciaTrie(String file){
		try(BufferedReader br= new BufferedReader(new FileReader(file))){
			//long deb = System.currentTimeMillis();
			PatriciaTrie p=new PatriciaTrie();
			String line;
			while ((line = br.readLine()) != null) {
				line=line.concat(" ");
				Patricia_tries.Fonctions.addWord(line,p);
			}
			//long fin = System.currentTimeMillis();
			//String texte [] = file.split("/");
			//String txt=texte[texte.length-1];
			//System.out.println("Temps de construction du PatriciaTrie du fichier " + txt +"\nest de : " + (fin-deb) + "ms\n" + "nombre de mots dans l'arbre:" + Patricia_tries.Fonctions.compteMots(p));
			return p;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static HybridTrie fill_HybridTrie(String file){
		try(BufferedReader br= new BufferedReader(new FileReader(file))){
			//long deb = System.currentTimeMillis();
			HybridTrie h=new HybridTrie();
			String line;
			int cpt = 1;
			while ((line = br.readLine()) != null) {
				h.addKey(line, cpt);
				cpt++;
			}
			//long fin = System.currentTimeMillis();
			//String texte [] = file.split("/");
			//String txt=texte[texte.length-1];
			//System.out.println("Temps de construction de l'Hybrid trie du fichier " + txt +"\nest de : " + (fin-deb) + "ms\n" + "nombre de mots dans l'arbre:" + h.ComptageMots());
			return h;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	
	
	
	
	public static PatriciaTrie fill_PatriciaTrie(String []files){
		PatriciaTrie p=new PatriciaTrie();
		//long deb = System.currentTimeMillis();

		for(int i =0; i<files.length;i++){
			try(BufferedReader br= new BufferedReader(new FileReader(files[i]))){
				String line;
				while ((line = br.readLine()) != null) {
					line=line.concat(" ");
					Patricia_tries.Fonctions.addWord(line,p);
				}
			} catch (IOException e) {
				e.printStackTrace();
				return null;
			}
		}
		//long fin = System.currentTimeMillis();
		//System.out.println("Temps de construction du PatriciaTrie des fichiers" +"\nest de : " + (fin-deb) + "ms\n" + "nombre de mots dans l'arbre:" + Patricia_tries.Fonctions.compteMots(p));

		return p;
	}
	
	
	
	public static HybridTrie fill_HybridTrie(String []files){
		HybridTrie h=new HybridTrie();
		//long deb = System.currentTimeMillis();
		int cpt = 1;
		for(int i =0; i<files.length;i++){
			try(BufferedReader br= new BufferedReader(new FileReader(files[i]))){
				String line;
				while ((line = br.readLine()) != null) {
					line=line.concat(" ");
					h.addKey(line,cpt);
					cpt++;
				}
			} catch (IOException e) {
				e.printStackTrace();
				return null;
			}
		}
		//long fin = System.currentTimeMillis();
		//System.out.println("Temps de construction de l'Hybrid trie des fichiers" +"\nest de : " + (fin-deb) + "ms\n" + "nombre de mots dans l'arbre:" + h.ComptageMots());

		return h;
	}
	
}
