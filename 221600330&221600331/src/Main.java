import java.util.*;

public class Main {

	public static void main(String[] args) {
		int countChar;
		int countLinnes;
		System.out.print("请输入文件名：");
		Scanner scan = new Scanner(System.in);
		String file_name = scan.nextLine();
		scan.close();
		Map<String, String> wordsMap;
		countChar = wordCount.count_Characters(file_name);
		wordsMap = wordCount.count_Words(file_name);
		countLinnes = wordCount.count_Lines(file_name);
		
		wordCount.writeToFile(countChar,wordsMap,countLinnes);
	}
	
	


}
