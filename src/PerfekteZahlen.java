import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;

public class PerfekteZahlen {
    public static void main(String[] args) {


//        long[] list = new ArrayList;
        List<Long> test = new ArrayList<>();

//        for (long i = 1; i <= 10000; i++) {
//            ArrayList<Long> allTeiler = returnAllTeiler(i);
//            long sum = allTeiler.stream().mapToLong(Long::longValue).sum();
//            if (i == sum) {
//                test.add(i);
//            }
//        }


        for (long i = 1; i <= 10000; i++) {
            if (isPerfect(i)) {
                test.add(i);
            }
        }

        System.out.println(test);
    }

//    public static ArrayList<Long> returnAllTeiler(long number) {
//        ArrayList<Long> allTeiler = new ArrayList<>();
//        long upperBound = number / 2;
//
//        for (long i = 1; i <= upperBound; i++) {
//            if (number % i == 0) {
//                allTeiler.add(i);
//            }
//        }
//        return allTeiler;
//    }

    static boolean isPerfect(long n) {
        long sum = 0;
        for (long i = 1; i <= n / 2; i++) {
            if (n % i == 0) {
                sum += i;
            }
        }
        return sum == n;
    }
}
