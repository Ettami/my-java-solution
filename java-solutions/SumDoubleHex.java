public class SumDoubleHex {
    public static double parsing(String a) {
        if ((a.indexOf("0x") != -1 || a.indexOf("0X") != -1) && (a.indexOf("P") == -1 && a.indexOf("p") == -1)) {
            a = a + "p0";
        }
        if (a.isEmpty())
            return 0;
        else
            return Double.parseDouble(a);
    }
    public static void main(String[] args) {
        double ans = 0;
        for (String arg : args) {
//            arg = arg + " ";
            int i_begin = 0, i_end = 0;
            while (i_end <= arg.length()) {
                if (i_end == arg.length() || Character.isWhitespace(arg.charAt(i_end))) {
                    if (i_begin != i_end) {
                        String currentNumber = arg.substring(i_begin, i_end);
                        ans += parsing(currentNumber);
                    }
                    i_begin = i_end + 1;
                }
                i_end++;
            }
        }
        System.out.println(ans);
    }
}