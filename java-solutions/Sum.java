public class Sum {
    public static void main(String[] args) {
        String s, a ;
        int  ans = 0,j;
        char c;
        for (int i = 0; i < args.length; i++) {
            s = args[i];
            j = 0;
            a = "";
            while(j < s.length()){
                c = s.charAt(j);
                if (!Character.isWhitespace(c)) {
                    a = a + c;
                } else if (!a.isEmpty()) {
                    ans += Integer.parseInt(a);
                    a = "";
                }
                j++;
            }
            if (!a.isEmpty()) {
                ans += Integer.parseInt(a);
            }
        }
        System.out.println(ans);
    }
}