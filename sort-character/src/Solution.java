import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Solution {
    public static void main(String[] args) {
        System.out.println("Input one line of words (S) : ");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        String charVowel = procVowel(input);
		String charConsonant = procConsonant(input);
			
		System.out.println("Vowel Characters : ");
		System.out.println(charVowel);
		System.out.println("Consonant Characters : ");
		System.out.println(charConsonant);  
    }

    public static String procVowel(String param) {
        String str = param.toLowerCase().replaceAll("[^aiueo]", "");
        return sortStr(str);    
    }

    public static String procConsonant(String param) {
        String str = param.toLowerCase().replaceAll("[aiueo\\W]", "");
        return sortStr(str);  
    }

    public static String sortStr(String str) {
        Map<Character, Integer> firstCharMap = new HashMap<Character, Integer>();
        for (int i = 0; i < str.length(); i++) {
            firstCharMap.putIfAbsent(str.charAt(i), i);
        }
        
        List<Integer> strIndexList = IntStream.range(0, str.length())
            .boxed()
            .collect(Collectors.toList());
        
        strIndexList.sort(Comparator.<Integer>comparingInt(i -> firstCharMap.get(str.charAt(i))).thenComparingInt(i -> i));
                
        String sortedStr = strIndexList.stream()
            .map(i -> String.valueOf(str.charAt(i)))
            .collect(Collectors.joining(""));

        return sortedStr;
    }
}
