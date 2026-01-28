public class ISBN {
    public static void main(String[] args) {
//        String isbn = "3-87791-896-4";  // hier läuft es korrekt und die Prüfziffer 4 wird ausgegeben.
//        String isbn = "3-7723-7442-3";  // hier ist aber doch was falsch? da sollte die Prüfziffer 5 sein und nicht 4.
        // hier zum Testen: https://staff.ub.tu-berlin.de/~usw/isbntest.html

        String isbn = "3-499-13599-?";   // Prüfziffer sollte hier x sein.

        int multiplicator = 1;
        int sum = 0;

        for (int i = 0; i < isbn.length()-1; i++) {     // isbn.length()-2 ist sehr kritisch, da müssen die ISBN immer vollständig eingegeben werden.
            String substring = isbn.substring(i, i + 1);
            System.out.println(substring);

            if (substring.equals("-")) {
                continue;
            } else {
                sum += Integer.parseInt(substring) * multiplicator;
                multiplicator++;
            }
        }

        String checkSum = "" + sum % 11;
        if (checkSum.equals("10")) checkSum = "X";

        System.out.println("\nPrüfziffer: " + checkSum);
    }
}
