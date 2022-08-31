public class VerificationUtils {
    private static final String EMPTY = "";

    /**
     * 简易命令注入类支付校验
     * @param str
     * @param targetChar
     * @param escapeChar
     * @return
     */
    public static boolean verifyLegality(CharSequence str, char targetChar, char escapeChar) {
        if (str == null) {
            return true;
        }
        boolean sign = false;
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (c == escapeChar) {
                sign = !sign;
            } else if (!sign && c == targetChar) {
                return false;
            }
        }
        return true;
    }

    /**
     * 简易命令注入类支付过滤
     * @param str
     * @param targetChar
     * @param escapeChar
     * @return
     */
    public static String handleLegality(CharSequence str, char targetChar, char escapeChar) {
        if (str == null) {
            return null;
        }if (str.length() == 0) {
            return EMPTY;
        }
        StringBuilder builder = new StringBuilder(str.length());
        boolean sign = false;
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (c == escapeChar) {
                sign = !sign;
            } else if (!sign && c == targetChar) {
                builder.append(escapeChar);
            }
            builder.append(c);
        }
        return builder.toString();
    }

    /**
     * IPv4格式校验
     * @param str
     * @return
     */
    public static boolean verifyIpv4(CharSequence str) {
        if (str == null || str.length() < 7 || str.length() > 15) {
            return false;
        }
        int item = 0;
        int num = 0;
        int chNum = 0;
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            switch (c) {
                case '.':
                    if (item > 2 || chNum == 0 || num > 255) {
                        return false;
                    }
                    item++;
                    chNum = num = 0;
                    break;
                case '0':case '1':case '2':case '3':case '4':
                case '5':case '6':case '7':case '8':case '9':
                    if (chNum++ > 2) {
                        return false;
                    }
                    num = (num << 1) + (num << 3) + (c - '0');
                    break;
                default:
                    return false;
            }
        }
        return item == 3 && chNum != 0 && num < 256;
    }

//    public static boolean verifyIpv6(CharSequence str) {
//    }

    /**
     * 文件或目录路径校验
     * @param str
     * @return
     */
    public static boolean verifyPath(CharSequence str) {
        if (str == null || str == EMPTY) {
            return false;
        }
        if (SystemUtils.OS_TYPE == OsType.OSX && str.charAt(0) == ':') {
            return false;
        }
        int deep = 0;
        int len = 0;
        int pointNum = 0;
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            switch (c) {
                case ':':
                    if (SystemUtils.OS_TYPE == OsType.OSX) {
                        return false;
                    }
                case '*':
                case '?':
                case '<':
                case '>':
                case '|':
                    if (SystemUtils.OS_TYPE == OsType.WINDOWS) {
                        return false;
                    }
                    break;
                case '\\':
                    if (SystemUtils.OS_TYPE != OsType.WINDOWS) {
                        len++;
                        break;
                    }
                case '/':
                    if (len == 0) {
                        switch (pointNum) {
                            case 0:
                            case 1:
                                break;
                            case 2:
                                deep--;
                                break;
                            default:
                                deep++;
                        }
                    } else {
                        deep++;
                    }
                    if (deep < 0) {
                        return false;
                    }
                    pointNum = len = 0;
                    break;
                case '.':
                    pointNum++;
                    break;
                default:
                    len++;
            }
        }
        if (len == 0 && pointNum == 2) {
            deep--;
        }
        return deep >= 0;
    }

    public static int charToInt(char c) {
        switch (c) {
            case '0':
                return 0;
            case '1':
                return 1;
            case '2':
                return 2;
            case '3':
                return 3;
            case '4':
                return 4;
            case '5':
                return 5;
            case '6':
                return 6;
            case '7':
                return 7;
            case '8':
                return 8;
            case '9':
                return 9;
//            case 'a':case 'A':
//                return 10;
//            case 'b':case 'B':
//                return 11;
//            case 'c':case 'C':
//                return 12;
//            case 'd':case 'D':
//                return 13;
//            case 'e':case 'E':
//                return 14;
//            case 'f':case 'F':
//                return 15;
            default:
                return -1;
        }
    }
}
