public class Question1 {
//    implement a function/method that is given two strings and returns whether one can be obtained by the other after removing exactly one character.
//    Specifically, given two strings x and y, return true if and only if (1) x can be obtained by removing one character from y and/or (2) if y can be obtained by removing one character from x.
//    Assume that both strings only contain English alphabets and that neither is an empty string.
//    Note that x and y can be quite long (each containing millions of characters).

    public static void main(String[] args) {
        // The following inputs should return True:
        System.out.println(equalsWhenOneCharRemoved("abcd", "abxcd"));
        System.out.println(equalsWhenOneCharRemoved("xyz", "xz"));
        System.out.println(equalsWhenOneCharRemoved("ABCdE", "ABCE"));
        System.out.println(equalsWhenOneCharRemoved("abcde", "abcd"));
        // The following inputs should return False:
        System.out.println(equalsWhenOneCharRemoved("x", "y"));
        System.out.println(equalsWhenOneCharRemoved("x", "XX"));
        System.out.println(equalsWhenOneCharRemoved("yy", "yx"));
        System.out.println(equalsWhenOneCharRemoved("abcde", "ab"));
    }

    public static boolean equalsWhenOneCharRemoved(String x, String y){
        // l represents the string with longer length
        // s represents the string with shorter length
        String l = x.length() > y.length() ? x : y;
        String s = x.length() < y.length() ? x : y;

        if (s.length() + 1 != l.length()) {
            return false;
        }

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) != l.charAt(i)) {
                return l.substring(i + 1).equals(s.substring(i));
            }
        }
        return true;
    }
}
