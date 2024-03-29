package string;

/* Question Link: https://www.geeksforgeeks.org/kmp-algorithm-for-pattern-searching/ 
    Reference: https://www.youtube.com/watch?v=KG44VoDtsAA&ab_channel=TusharRoy-CodingMadeSimple
            https://www.youtube.com/watch?v=GTJr8OvyEVQ&ab_channel=TusharRoy-CodingMadeSimple
*/

public class KMPAlgo {

    public static void main(String args[]) {
        String txt = "ababcababaad";
        String pat = "ababa";

        KMP(pat, txt);
    }

    /* Time: O(M + N) */
    static void KMP(String pat, String txt) {
        int N = txt.length();
        int M = pat.length();

        int[] lps = new int[M];
        fillLPS(pat, lps);

        int i = 0, j = 0;
        while (i < N) {
            if (pat.charAt(j) == txt.charAt(i)) {
                i++;
                j++;
            }

            if (j == M) {
                System.out.println("Found pattern at index " + (i - j));
                j = lps[j - 1];
            } else if (i < N && pat.charAt(j) != txt.charAt(i)) {
                if (j == 0)
                    i++;
                else
                    j = lps[j - 1];
            }
        }
    }

    // Longest Proper Prefix which is also Suffix
    static void fillLPS(String str, int lps[]) {

        int n = str.length(), len = 0;
        lps[0] = 0;

        int i = 1;
        while (i < n) {
            if (str.charAt(i) == str.charAt(len)) {
                len++;
                lps[i] = len;
                i++;
            } else {
                if (len == 0) {
                    lps[i] = 0;
                    i++;
                } else {
                    len = lps[len - 1];
                }
            }
        }
    }
}
