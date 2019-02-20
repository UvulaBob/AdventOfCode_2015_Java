import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test {

    public static void main(String[] args) {
        ArrayList<String> matches = new ArrayList<>();
        Pattern pattern = Pattern.compile("!((?:abc|123)+)!");
        Matcher m = pattern.matcher("!abc123! !abc123! !abc123! !abc123! !abc123!");
        while (m.find()) {
            matches.add(m.group(0));
        }

        System.out.println(matches.toString());
    }
}
