package regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegularExpression {


    public static void main(String[] args) {
        Pattern pattern = Pattern.compile("/api/v2/department/*", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher("/api/v2/data/reporting");
        boolean matchFound = matcher.find();
        if(matchFound) {
            System.out.println("Match found");
        } else {
            System.out.println("Match not found");
        }
    }

}
