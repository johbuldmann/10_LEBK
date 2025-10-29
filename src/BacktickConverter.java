import java.awt.*;
import java.awt.datatransfer.*;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BacktickConverter {

    public static String convertBackticksToCode(String text) {
        // Regex für alles zwischen `backticks`
        Pattern pattern = Pattern.compile("`(.*?)`");
        Matcher matcher = pattern.matcher(text);
        // Ersetze alle Treffer durch <code>…</code>
        return matcher.replaceAll("<code>$1</code>");
    }

    public static void convertClipboardText() {

        Clipboard clip = Toolkit.getDefaultToolkit().getSystemClipboard();

        // if Text ist verfügbar?
        if (clip.isDataFlavorAvailable(DataFlavor.stringFlavor)) {

            Transferable t = clip.getContents(null);

            try {
                String clipText = (String) t.getTransferData(DataFlavor.stringFlavor);
                System.out.println("alter Text aus Zwischenablage\n" + clipText);
                System.out.println();
                String convertedText = convertBackticksToCode(clipText);
                System.out.println("neuer Text nach Konvertierung\n" + convertedText);
                StringSelection convertedSelection = new StringSelection(convertedText);
                clip.setContents(convertedSelection, null);
            } catch (IOException | UnsupportedFlavorException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Im Clipboard war kein Text");
        }
    }

    public static void main(String[] args) {

//        String ankiText = "mit `instanceof` geprüft werden, um eine `ClassCastException` zu vermeiden (z.B. `Auto a = (Auto) f;`).";
//            String htmlText = convertBackticksToCode(ankiText);
//            System.out.println(htmlText);

        convertClipboardText();

    }
}
