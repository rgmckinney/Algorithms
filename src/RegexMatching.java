import java.util.*;

public class RegexMatching {
    public static boolean isMatch(String str, String reg) {
        int regIdx = 0;
        boolean isAsterisk;
        for (int strIdx=0; strIdx < str.length(); strIdx++) {
            // If there is an * following regex's char
            if (regIdx + 1 < reg.length() && reg.charAt(regIdx + 1) == '*')
                isAsterisk = true;
            else
                isAsterisk = false;


            if (reg.charAt(regIdx) != '.' && !isAsterisk && str.charAt(strIdx) != reg.charAt(regIdx)) {
                return false;
            }
            else if (isAsterisk) {
                if (regIdx + 1 < reg.length() && reg.charAt(regIdx) == '.' &&
                        reg.charAt(regIdx+1) == '*')
                    return true;

                while (strIdx < str.length() && str.charAt(strIdx) == reg.charAt(regIdx))
                    strIdx++;
            }

            if (++regIdx >= reg.length()) {
                if (strIdx + 1 >= str.length())
                    return true;
                else
                    return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        assert(!isMatch("aa", "a"));
        assert(isMatch("aa", "aa"));
        assert(isMatch("aaaa", "a*"));
        assert(isMatch("ab", ".*"));
    }
}
