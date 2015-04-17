import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;


public class fileUtils {
	public static String content = "";

	private fileUtils() {
	}

	public static String readFrom(File file) throws IOException{
		Scanner input = new Scanner(file);
		while (input.nextLine() != null){
			content += input.nextLine();
		}
		return content;
	}
	public static void writeTo(String str, File file) throws IOException {
		PrintWriter pw = new PrintWriter(file);
		pw.println(str);
		pw.close();
	}
	
	public static Map<String, String> parseProperties(File file) throws IOException{
		BufferedReader br = null;
		br = new BufferedReader(new FileReader(file));
		String currentLine = "";
		Map<String, String> hm = new LinkedHashMap<String, String>();
		while((currentLine = br.readLine()) != null){
			char[] charArray = currentLine.toCharArray();
			int i = 0;
			int index = 0;
			if (charArray[0] == '#')
				continue;
			String key = "";
			String value = "";
			while(charArray[i] != '='){	
				key += charArray[i];
				i++;
			}
			index = i + 1;
			i = currentLine.length() -1;
			while(i != index){
				if (charArray[i] == '#')
					value = "";
				else
					value += charArray[i];
				i--;
			}
			String newValue = new StringBuilder(value).reverse().toString();
			hm.put(key + ':', newValue);
		}		
		return hm;
	}
	//4.Subtitles
	public static Path reduceFilePath(Path p){
		return p.normalize();
	}
	public static void badLink(Path folder)throws IOException{
		if(Files.isSymbolicLink(folder)){
			if(Files.readSymbolicLink(folder) != null)
				badLink(Files.readSymbolicLink(folder));
			else
				System.out.print(folder);
		}
	}
	public static void compress(Path filePath) throws FileNotFoundException{
		File f = filePath.toFile();
		String text = new Scanner(new File(f.toString())).useDelimiter("\\Z").next();
		String result = "";
		List<String> l = new ArrayList<String>();
		for (String word : text.split(" ")) {
			if (l.contains(word) == true){
				result += l.indexOf(word);
			}
			else{
				l.add(word);
				result += l.indexOf(word);
			}
			result += '~';
		}
		PrintWriter pw = new PrintWriter(f);
		pw.println(result);
		pw.close();
	}
	public static void main(String[] args) {
		File file = new File("/home/georgi/testfile.txt");
		Path p = Paths.get("/home/georgi/testfile.txt");
		String str = "this is a test string";
		try{
			if (!file.exists()) {
				file.createNewFile();
			}
			fileUtils.writeTo(str, file);
			//System.out.print(FileUtils.readFromPath(p));
			//System.out.println(FileUtils.readFrom(file));
		}
		catch(IOException e){
			e.printStackTrace();
		}
		File testParseFile = new File("/home/georgi/testParseFile.txt");
		try{
		Map<String, String> parsedFile = fileUtils.parseProperties(testParseFile);
		System.out.println(parsedFile.toString());
		} catch(IOException e){
			e.printStackTrace();
		}
		Path testPath = Paths.get("/etc/../etc/../etc/../");
		//System.out.print(reduceFilePath(testPath));
		/*
		try{
		fileUtils.badLink(p);
		}catch(IOException e){
			e.printStackTrace();
		}
		*/
		Path p2 = Paths.get("/home/georgi/testCompress.txt");
		/*
		try{
		fileUtils.compress(p2);
		}catch(FileNotFoundException e){
			e.printStackTrace();
		}
		*/

	}

}


