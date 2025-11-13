import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CodingBat {


    public List wordsWithoutList(String[] words, int len) {
        List<String> wordsWith = new ArrayList<>(Arrays.asList(words));

        wordsWith.removeIf(String -> String.length() == len);

        return wordsWith;
    }

    public String[] wordsFront(String[] words, int n) {
        String[] output = new String[n];

        for (int i = 0; i < output.length; i++) {
            output[i] = words[i];
        }
        return output;
    }

    public boolean dividesSelf(int n) {
        int original = n;
        while (n > 0) {
            int lastDigit = n % 10;
            if (lastDigit != 0 && original % lastDigit == 0) {
                n = n / 10;
            } else {
                return false;
            }
        }
        return true;
    }

    public int bigHeights(int[] heights, int start, int end) {
//        int i = start;
        int countBigSteps = 0;
        for (int i = start + 1; i <= end; i++) {
            if (Math.abs(heights[i - 1] - heights[i]) >= 4) {
                countBigSteps++;
            }
        }
        return countBigSteps;
    }

    public int[] copyEndy(int[] nums, int count) {
        int[] output = new int[count];
        int counter = 0;
        for (int i = 0; counter < output.length; i++) {
            if (isEndy(nums[i])) {
                output[counter++] = nums[i];
            }
        }

        return output;
    }

    public boolean isEndy(int num) {
        return (num >= 0 && num <= 10 || num >= 90 && num <= 100);
    }




    public static int userCompare(String aName, int aID, String bName, int bID) {

        return aID;
    }

    public boolean hasOne(int n) {
        int lastDigit;

        while (n > 0) {
            lastDigit = n % 10;
            if (lastDigit == 1) {
                return true;

            }
            n = n / 10;
        }
        return false;
    }

    public static boolean scoresClump(int[] scores) {
        boolean test = false;
        for (int i = 0; i < scores.length - 2; i++) {

            for (int j = 0; j <= 2; j++) {
                if ((Math.abs(scores[i] - scores[i + j])) < 2) {
                    test = true;
                } else {
                    test = false;
                }
            }
            if (test == true) {
                break;
            }

        }
        return test;
    }

    public String[] fizzArray2(int n) {
        String[] resultingArray = new String[n];
        if (resultingArray.length == 0) {
            return resultingArray;
        }

        for (int i = 0; i < n; i++) {
            resultingArray[i] = String.valueOf(i);
        }

        return resultingArray;
    }

//    public boolean modThree(int[] nums) {
//
//    }

    public static void main(String[] args) {
//        System.out.println(userCompare("bb", 1, "zz", 2));
        int[] test = {3,4,6};
        System.out.println(scoresClump(test));
    }

}





