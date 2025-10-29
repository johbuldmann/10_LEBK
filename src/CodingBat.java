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

}