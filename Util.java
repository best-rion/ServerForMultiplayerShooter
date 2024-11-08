public class Util {
    
    public static String fillGap(String s)
    {
        for (int i=0; i<s.length(); i++)
        {
            if (s.charAt(i)==0)
            {
                char[] sc = s.toCharArray();
                sc[i] = '_';
                s = String.valueOf(sc);
            }
        }
        return s;
    }
}
