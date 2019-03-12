import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) {
		int countChar;
		int countLinnes;
		Map<String, String> wordsMap;
		countChar = wordCount.count_Characters("input.txt");
		wordsMap = wordCount.count_Words("input.txt");
		countLinnes = wordCount.count_Lines("input.txt");
		
		wordCount.writeToFile(countChar,wordsMap,countLinnes);
	}
	
	


}
