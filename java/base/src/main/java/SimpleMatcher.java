//import org.junit.Test;
//
//public class SimpleMatcher {
//    @Test
//    public void test() {
//
//    }
//
//    public static boolean match(CharSequence regex, CharSequence str) {
//        int anyNum = 0;
//        int endChNum = 0;
//        for (int i = 0; i < regex.length(); i++) {
//            if (regex.charAt(i) == '*') {
//                endChNum = 0;
//                anyNum++;
//            } else {
//                endChNum++;
//            }
//        }
//        if (anyNum == 0) {
//            return simpleMatch(regex, str);
//        }
//        int[] strMatchSign = new int[anyNum];
//        int[] regexMatchSign = new int[anyNum];
//        int strMatchSignIndex = 0;
//        int regexMatchSignIndex = 0;
//        int regIndex = 0;
//        boolean any = false;
//        for (int i = 0; i < str.length(); i++) {
//            char regCh = regex.charAt(i);
//            char ch = str.charAt(i);
//            switch (regCh) {
//                case '*':
//                    strMatchSign[strMatchSignIndex++] = i;
//                    regexMatchSign[regexMatchSignIndex++] = regIndex;
//                    any = true;
//                    break;
//                case '?':
//                    continue;
//                default:
//                    if (regCh != regCh && !any) {
//                        if (str.length() - strMatchSign[strMatchSignIndex] <= endChNum) {
//                            return false;
//                        }
//                    }
//            }
//        }
//    }
//    private static boolean simpleMatch(CharSequence regex, CharSequence str) {
//        int regIndex = 0;
//        for (int i = 0; i < str.length(); i++) {
//            char c = regex.charAt(regIndex++);
//            if (c != str.charAt(i) && c != '?') {
//                return false;
//            }
//        }
//    }
//}
