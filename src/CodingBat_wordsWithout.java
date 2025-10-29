import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CodingBat_wordsWithout {

    public static String[] wordsWithout(String[] words, String target) {
//        int counter = 0;
//        for (String s : words) {
//            if (s.equals(target)) {
//                counter++;
//            }
//        }
//        String[] output = new String[words.length - counter];
//
//        int outputIndex = 0;
//        for (int i = 0; i < words.length; i++) {
//            if (!words[i].equals(target)) {
//                output[outputIndex] = words[i];
//                outputIndex++;
//            }
//        }
//
//        return output;
//    }
        /// /////////////////////////
        List<String> wordList = new ArrayList<String>(Arrays.asList(words));
        wordList.removeIf(String -> String.equals(target) );

        return wordList.toArray(new String[0]);


    }

    public static void main(String[] args) {
        String[] array = {"a", "b", "c", "a"};


//        String test = wordsWithout(array, "a").toString(); // → ["b", "c"]

//        System.out.println(test);
        System.out.println(Arrays.toString(wordsWithout(array, "a")));

    }




}


