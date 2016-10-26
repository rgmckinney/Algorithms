import java.util.*;

/**
 * Created by Ryan on 10/25/2016.
 */
public class LongestPalindrome {
    public static String longestPalindrome(String s) {
        String maxLengthPal = "";
        String curLongestPal;

        for (int index = 0; index < s.length(); index++) {
            if ((curLongestPal = longestCenteredPal(s, index, false)).length() > maxLengthPal.length()) {
                maxLengthPal = curLongestPal;
            }
            if ((curLongestPal = longestCenteredPal(s, index, true)).length() > maxLengthPal.length()) {
                maxLengthPal = curLongestPal;
            }
        }

        return maxLengthPal;
    }

    public static String longestCenteredPal (String s, int centerIdx, boolean checkForEven) {
        int leftIdx, rightIdx;

        // If checking for an even length string centered around a pair of chars
        if (checkForEven) {
            leftIdx = centerIdx;
            rightIdx = centerIdx + 1;
            if (rightIdx >= s.length())
                return Character.toString(s.charAt(leftIdx));
            if (s.charAt(leftIdx) != s.charAt(rightIdx))
                return Character.toString(s.charAt(leftIdx));
        }
        // If checking for an odd length string centered around a single char
        else {
            leftIdx = rightIdx = centerIdx;
        }

        // While expansion is possible
        while (leftIdx - 1 >= 0 && rightIdx + 1 < s.length()) {
            if (s.charAt(leftIdx - 1) != s.charAt(rightIdx + 1)) {
                return s.substring(leftIdx, rightIdx + 1);
            }
            leftIdx--;
            rightIdx++;
        }

        return s.substring(leftIdx, rightIdx + 1);
    }

    public static void main(String[] args) {
        assert(longestPalindrome("abcddfgsgfjnknk").equals("fgsgf"));
        assert(longestPalindrome("bhracecarbaeye").equals("racecar"));
        assert(longestPalindrome("tgtesselgt").equals("esse"));
        assert(longestPalindrome("hi").equals("h") || longestPalindrome("hi").equals("i"));
        assert(longestPalindrome("bb").equals("bb"));
        assert(longestPalindrome("cbb").equals("bb"));
        assert(longestPalindrome("cbob").equals("bob"));
        assert(longestPalindrome("lololoololllolol").equals("ololllolo"));
    }
}
