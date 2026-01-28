import java.sql.Time;
import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

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

    public List<Integer> doubling(List<Integer> nums) {
        return nums
                .stream()
                .map(n -> n * 2)
                .collect(Collectors.toList());

        // oder auch
//        nums.replaceAll(n -> n * 2);
//        return nums;
    }

    public static List<String> copies3(List<String> strings) {
        return strings
                .stream()
                .map(str -> str + str + str)
                .collect(Collectors.toList());
    }

    static class Solution {
        public boolean containsDuplicate(int[] nums) {
            int numsLength = nums.length;

            Set<Integer> hash = Arrays.stream(nums)
                    .boxed()
                    .collect(Collectors.toSet());

            return (hash.size() < numsLength);
        }
    }

    public static int bigDiff(int[] nums) {
        int min = nums[0];
        int max = nums[0];

        for (int i = 0; i < nums.length - 1; i++) {
            int localMin = Math.min(nums[i], nums[i + 1]);
            int localMax = Math.max(nums[i], nums[i + 1]);

            if (localMin < min) {
                min = localMin;
            }

            if (localMax > max) {
                max = localMax;
            }
        }


        return max - min;
    }

    public static List<String> moreY(List<String> strings) {
        return strings
                .stream()
                .map(str -> "y" + str + "y")
                .collect(Collectors.toList());


    }


//    Intuition
//            <!—— Describe your first thoughts on how to solve this problem.
//
//            Approach
//            <!—— Describe your approach to solving the problem.
//
//            Complexity
//    Time complexity:
//<!—— Add your time complexity here, e.g. $$0(n)$$
//
//    Space complexity:
//<!—— Add your space complexity here, e.g. $$0(n)$$

    public static int[] sortArray(int[] array) {

        boolean run = true;

        for (int i = 0; i < array.length && run == true; i++) {
            run = false;

            for (int j = 0; j < array.length - 1; j++) {
                if (array[j] > array[j + 1]) {
                    // int speicher als Zwischenspeicher für Dreieckstausch.
                    int greater = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = greater;
                    run = true;
                }

            }
        }
        return array;
    }

    public static int returnMinimum(int[] array) {
        // hier noch Test ob Array = null etc.
        int minimum = array[0];
        // Startwert einfach auf den ersten Wert im Array gesetzt. Das ist ja schon ein möglicher Kandidat für minimum
        // Ansonsten könnte man Integer.MAX_VALUE nehmen.
        for (int i : array) {
            if (i < minimum) {
                minimum = i;
            }
        }
        return minimum;
    }

    public static void linearSearch(int[] array, int search) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == search) {
                System.out.println("Index: " + i);
                return;
            }
        }

        System.out.println("nicht gefunden");
    }

    public static List<Integer> no9(List<Integer> nums) {
        return nums.stream()
                .map(x -> x * x + 10)
                .filter(x -> x % 10 != 5 || x % 10 != 6)
                .collect(Collectors.toList());
    }


    public static void main(String[] args) {
//        System.out.println(userCompare("bb", 1, "zz", 2));
        int[] array = {10, 12, 4, 4, 3, 5, 6};
//        int[] sorted = sortArray(array);
//        System.out.println(Arrays.toString(sorted));
//        System.out.println(returnMinimum(array));
        linearSearch(array, 4);
    }

}





