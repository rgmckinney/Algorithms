import java.util.*;

public class RegexMatching {
    public static boolean isMatch(String str, String reg) {
        int regIdx = reg.length() - 1;
        int strIdx = str.length() - 1;
        boolean isAsterisk;

        while (strIdx >= 0 && regIdx >= 0) {
            // Check if asterisked
            if (reg.charAt(regIdx) == '*') {
                regIdx--;
                isAsterisk = true;
            }
            else {
                isAsterisk = false;
            }

            // If asterisked character
            if (isAsterisk) {
                if (reg.charAt(regIdx) == '.') {
                    // Match rest of str
                    strIdx = -1;
                }
                else {
                    // Loop through all str's chars that match the asterisked
                    while (str.charAt(strIdx) == reg.charAt(regIdx)) {
                        strIdx--;
                        if (strIdx < 0)
                            break;
                    }
                }

                regIdx--;
            }
            // If normal comparison
            else {
                if (reg.charAt(regIdx) != '.' && str.charAt(strIdx) != reg.charAt(regIdx)) {
                    return false;
                }
                strIdx--;
                regIdx--;
            }
        }

        if (strIdx < 0) {
            // Loop through any asterisked chars in reg
            while (regIdx >= 0 && reg.charAt(regIdx) == '*') {
                regIdx -= 2;
            }

            if (regIdx >= 0) {
                return false;
            }

            return true;
        }

        return false;
    }
/*
    public static boolean isMatch(String str, String reg) {
        int regIdx = 0;
        int strIdx = 0;
        boolean isAsterisk;

        while (strIdx < str.length() && regIdx < reg.length()) {
            // Check if asterisked
            if (regIdx + 1 < reg.length() && reg.charAt(regIdx + 1) == '*')
                isAsterisk = true;
            else
                isAsterisk = false;

            // If asterisked character
            if (isAsterisk) {
                if (reg.charAt(regIdx) == '.') {
                    // Match rest of str
                    strIdx = str.length();
                }
                else {
                    // Loop through all str's chars that match the asterisked
                    while (str.charAt(strIdx) == reg.charAt(regIdx)) {
                        strIdx++;
                        if (strIdx >= str.length())
                            break;
                    }
                }

                regIdx += 2;
            }
            // If normal comparison
            else {
                if (reg.charAt(regIdx) != '.' && str.charAt(strIdx) != reg.charAt(regIdx)) {
                    return false;
                }
                strIdx++;
                regIdx++;
            }
        }

        if (strIdx < str.length() || regIdx < reg.length())
            return false;

        return true;
    }
*/
    public static void main(String[] args) {
        assert(!isMatch("aa", "a"));
        assert(isMatch("aa", "aa"));
        assert(isMatch("aaaa", "a*"));
        assert(isMatch("ab", ".*"));
        assert(isMatch("aab", "c*a*b"));
        assert(!isMatch("abcd", "d*"));
        assert(!isMatch("ab", ".*c"));
        //assert(isMatch("aaa", "ab*a*c*a"));
        assert(isMatch("dd", "dddd*"));
    }
}
