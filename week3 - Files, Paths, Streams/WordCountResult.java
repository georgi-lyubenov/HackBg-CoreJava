import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;


public class WordCountResult {
	public static int wc;
	public static int lc;
	public static int cc;
	public WordCountResult() {
		wc = cc = 0;
		lc = 1;
	}
	public WordCountResult(int wordCount, int linesCount, int charactersCount) {
		wc = wordCount;
		lc = linesCount;
		cc = charactersCount;
	}

	public WordCountResult wordCount(File file) throws FileNotFoundException{
		   boolean inword = false;
		   String text = new Scanner(new File(file.toString())).useDelimiter("\\Z").next();
           for (int i = 0; i < text.length(); i++) 
           {
        	   cc ++;
        	   if (text.charAt(i) == '\n')
        		   lc ++;
               if (text.charAt(i) == ' ' || text.charAt(i) == '\n' || text.charAt(i) == '\t') 
               {
                   inword = false;
               } 
               else if (inword == false){
            	   inword = true;
            	   wc ++;
               }
           }
		   return new WordCountResult(wc, lc, cc);
		   
	}
	public void print(){
		System.out.println("words: " + wc);
		System.out.println("lines: " + lc);
		System.out.println("characters: " + cc);
	}
	public static void main(String[] args) {
		File file = new File("/home/georgi/testfile.txt");
		WordCountResult obj = new WordCountResult();
		try{
		obj.wordCount(file).print();
		}catch(FileNotFoundException e){
			e.printStackTrace();
		}
	}

}
