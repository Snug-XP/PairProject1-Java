import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) {
		int countChar;
		int countLinnes;
		Map<String, String> wordsMap;
		countChar = count_Characters("input.txt");
		wordsMap = count_Words("input.txt");
		countLinnes = count_Lines("input.txt");
		
		writeToFile(countChar,wordsMap,countLinnes);
	}
	private static void writeToFile(int countChar, Map<String, String>wordsMap , int countLinnes) {
		try {
			int countWords = Integer.parseInt( wordsMap.get("count_words") );
			
			File output_file=new File("result.txt");
			OutputStreamWriter writer;
			writer = new OutputStreamWriter(new FileOutputStream(output_file));
			BufferedWriter bufferedWriter = new BufferedWriter(writer);
			bufferedWriter.write("characters: "+countChar+"\r\n");
			bufferedWriter.write("words: "+countWords+"\r\n");
			bufferedWriter.write("lines: "+countLinnes+"\r\n");
            bufferedWriter.flush();
            
            System.out.println("characters: "+countChar);
            System.out.println("words: "+countWords);
            System.out.println("lines: "+countLinnes);
            
            if( countWords <= 0 )
            {
            	writer.close();
            	return;
            }
            
            int n = 10;
            while( countWords > 0 && n-- > 0)
            {
            	String temp = "";
            	int maxNum=-1;
            	Iterator<Map.Entry<String, String>> iterator = wordsMap.entrySet().iterator();
            	while (iterator.hasNext()) {
            		Map.Entry<String, String> entry = iterator.next();
            		if(  Integer.parseInt( entry.getValue()) >= maxNum && !entry.getKey().equals("count_words") )
            		{
            			if( Integer.parseInt( entry.getValue()) == maxNum && entry.getKey().compareTo(temp) > 0 )
            			{//相同频率的单词选字典序靠前的单词
            				continue;
            			}
            			temp = entry.getKey();
            			maxNum = Integer.parseInt( entry.getValue());
            		}
            	}
            	bufferedWriter.write("<"+temp+">: "+maxNum+"\r\n");
            	bufferedWriter.flush();
                System.out.println("<"+temp+">: "+maxNum);

            	wordsMap.remove(temp);
            	countWords = countWords-maxNum;
            }
            
            writer.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	private static int count_Lines(String filePath) {
		try {
			File input_file=new File(filePath);
			if(input_file.isFile() && input_file.exists())//判断文件是否存在
			{ 
				InputStreamReader reader = new InputStreamReader(new FileInputStream(input_file));
				BufferedReader bufferedReader = new BufferedReader(reader);
				String str = null;
				int countLinnes = 0;
				while( (str = bufferedReader.readLine()) != null)
				{
					for(int i = 0 ; i < str.length() ; i++)
					{
						if( 32< str.charAt(i) && str.charAt(i) < 127 )
						{
							countLinnes++;
							break;
						}
					}
				}
				reader.close();
				return countLinnes;
			} else{
				System.out.println("找不到指定的文件");
				return -1;
			}
		} catch (IOException e) {
			System.out.println("读取文件内容出错");
			e.printStackTrace();
			return -1;
		}
	}



	private static Map<String, String> count_Words(String filePath) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("count_words", "0");
		
		try {
			File input_file=new File(filePath);
			if(input_file.isFile() && input_file.exists()){ //判断文件是否存在
				InputStreamReader reader = new InputStreamReader(new FileInputStream(input_file));
				BufferedReader bufferedReader = new BufferedReader(reader);
				String str = null;
				
				while( (str = bufferedReader.readLine()) != null)
				{
					str = str.toLowerCase();
					for(int i = 0 ; i < (str.length()-4) ; i++)
					{
						if( 'a'<= str.charAt(i) && str.charAt(i) <= 'z' )
						{
							if( 'a'<= str.charAt(i+1) && str.charAt(i+1) <= 'z' )
							{
								if( 'a'<= str.charAt(i+2) && str.charAt(i+2) <= 'z' )
								{
									if( 'a'<= str.charAt(i+3) && str.charAt(i+3) <= 'z' )
									{//找到单词
										int j;
										for(j = i+4 ; j < str.length() ; j++)
										{//看单词是否结束
											if( 'a'> str.charAt(j) || str.charAt(j) > 'z' )
											{
												if(48>str.charAt(j)||str.charAt(j)>57)//不是数字
													break;
											}
										}
										String temp = str.substring(i,j);//截取字符串索引号i到j区域（包括i，不包括j）
										//加到图里去
										if( map.containsKey(temp) )
										{
											int n = Integer.parseInt( map.get(temp) );
											n++;
											map.put(temp, n+"");
										}
										else 
											map.put(temp,"1");
										
										int n = Integer.parseInt( map.get("count_words") );
										n++;//总单词个数加一
										map.put("count_words", n+"");
										
										i=j;
									}
									else i = i+3;
								}
								else i = i+2;
							}
							else i = i+1;
						}
						
					}
				}
				reader.close();
				return map;
					
			} else{
           	 System.out.println("找不到指定的文件");
           	 return null;
            }
		} catch (IOException e) {
			System.out.println("读取文件内容出错");
			e.printStackTrace();
			return null;
		}
	}
	

	public static int count_Characters(String filePath){
        try {
                File input_file=new File(filePath);
                File output_file=new File("result.txt");
                if(input_file.isFile() && input_file.exists()){ //判断文件是否存在
                    InputStreamReader reader = new InputStreamReader(new FileInputStream(input_file));
                    OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(output_file));
                    int countChar = 0;
                    int temp;
                    int last_char=-1;
                    int n=0;
                    while((temp = reader.read() )!= -1){
                    	countChar++;
                    	n++;
                    	if(last_char=='\r'||temp=='\n')
                    	{
                    		n=0;
                    		countChar--;
                    	}
                    	System.out.print((char) temp);
                    	last_char=temp;
                    }
                    System.out.println("\n sum="+countChar);
                    
                    reader.close();
                    writer.close();
                    return countChar;
                 }
                 else{
                	 System.out.println("找不到指定的文件");
                	 return -1;
                 }
        } catch (Exception e) {
            System.out.println("读取文件内容出错");
            e.printStackTrace();
            return -1;
        }
     
    }


}
