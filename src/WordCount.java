import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class WordCount {
	public static void main(String[] args) {
		boolean countLines = false, countWords = false, countChars = false, countBytes = false;
		String fileName = null;
		
		for (int i = 0; i < args.length; i++) {
			if (args[i].equals("-l")) {
				countLines = true;
			} else if (args[i].equals("-w")) {
				countWords = true;
			} else if (args[i].equals("-m")) {
				countChars = true;
			} else if (args[i].equals("-c")) {
				countBytes = true;
			} else {
				fileName = args[i];
			}
		}
		
		if(fileName == null) {
			System.out.println("Usage: java WordCount [-l] [-w] [-m] [-c] [filename]");
			return;
		}
		
		int lineCount = 0;
		int wordCount = 0;
		int charCount = 0;
		long byteCount = 0;
		
		try(BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
			String line;
			while((line = reader.readLine()) != null) {
				lineCount++;
				charCount = charCount + line.length() + 1;
				String[] words = line.split("\\s+");
				wordCount = wordCount + words.length;
			}
			byteCount = Files.size(Paths.get(fileName));
			
		}catch(IOException e) {
			System.out.println("Error reading file - "+e.getMessage());
			e.printStackTrace();
			return;
		}
		
		for(int i = 0; i < args.length; i++) {
			if (args[i].equals("-l")) {
				System.out.print("\t" + lineCount);
			} else if (args[i].equals("-w")) {
				System.out.print("\t" + wordCount);
			} else if (args[i].equals("-m")) {
				System.out.print("\t" + charCount);
			} else if (args[i].equals("-c")) {
				System.out.print("\t" + byteCount);
			} else {
				break;
			}
		}
		
		if(args.length == 1) {
			System.out.print("\t" + byteCount + "\t" + lineCount + "\t" + wordCount);
		}
		
		System.out.print("\t" + fileName + "\n");
		
	}

}
