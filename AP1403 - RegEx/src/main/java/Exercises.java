import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
// including  LocalDate , DateTimeFormatter , DateTimeParseException
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
public class Exercises {

    /*
        complete the method below, so it will validate an email address
     */
    public boolean validateEmail(String email) {
        String regex = "^[a-zA-Z0-9._%+-]+(?<!\\.)@[a-zA-Z0-9.-]+(?<!-\\.)\\.[a-zA-Z]{2,3}$";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);

        return matcher.matches();
    }

    /*
        this method should find a date in string
        note that it should be in british or american format
        if there's no match for a date, return null
     */


    public String findDate(String input) {
        String datePattern = "\\b(\\d{2}[/-]\\d{2}[/-]\\d{4}|\\d{4}[/-]\\d{2}[/-]\\d{2})\\b";
        Matcher matcher = Pattern.compile(datePattern).matcher(input);

        while (matcher.find()) {
            String matchedDate = matcher.group(1);
            try {
                // Try parsing as dd/MM/yyyy or dd-MM-yyyy
                if (matchedDate.matches("\\d{2}[/-]\\d{2}[/-]\\d{4}")) {
                    String separator = matchedDate.contains("/") ? "/" : "-";
                    LocalDate.parse(matchedDate,
                            DateTimeFormatter.ofPattern("dd" + separator + "MM" + separator + "yyyy"));
                    return matchedDate;
                }
                // Try parsing as yyyy/MM/dd or yyyy-MM-dd
                else if (matchedDate.matches("\\d{4}[/-]\\d{2}[/-]\\d{2}")) {
                    String separator = matchedDate.contains("/") ? "/" : "-";
                    LocalDate.parse(matchedDate,
                            DateTimeFormatter.ofPattern("yyyy" + separator + "MM" + separator + "dd"));
                    return matchedDate;
                }
            } catch (DateTimeParseException ignored) {
                continue; // Try next match if parsing fails
            }
        }
        return null;
    }
    /*
        given a string, implement the method to detect all valid passwords
        then, it should return the count of them

        a valid password has the following properties:
        - at least 8 characters \\".{8}*"
        - has to include at least one uppercase letter, and at least a lowercase
        - at least one number and at least a special char "!@#$%^&*"
        - has no white-space in it
     */

    public int findValidPasswords(String string) {
        if(string == null || string.isEmpty()){ return 0;}

        String[] words = string.split("\\s+");

        int validPass = 0;

        Pattern pattern = Pattern.compile("^(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*[!@#$%^&*]).{8,}$");

        for(String word : words){
            if (pattern.matcher(word).matches()){
                validPass++;
            }
        }

        return validPass;

    }

    /*
        you should return a list of *words* which are palindromic
        by word we mean at least 3 letters with no whitespace in it

        note: your implementation should be case-insensitive, e.g. Aba -> is palindrome
     */
    public List<String> findPalindromes(String string) {
        List<String> list = new ArrayList<>();
        if(string == null || string.isEmpty()){
            return  list;
        }
        String [] words = string.split("\\s+");


        for(String word : words){
            // Remove all non-alphabetic characters forexample turn  madam, to madam
            String cleanWord = word.replaceAll("[^a-zA-Z]", "");
            if(cleanWord.length() >= 3){
                String lowerWord = cleanWord.toLowerCase();
                if(isPalindrome(lowerWord)){
                    list.add(cleanWord); // Add the cleaned word (without punctuation)
                }
            }
        }
        return list;
    }
    private boolean isPalindrome(String string){
        int right = string.length() - 1;
        int left = 0;
        while(left < right){
            if(string.charAt(left) != string.charAt(right)){
                return false;

            }
            left++;
            right--;
        }
        return true;
    }

    public static void main(String[] args) {

    }
}
