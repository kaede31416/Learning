/**
 * Created by asus on 2016/2/28.
 */
public class Algorithm {
    public String ReverseSentence(String str) {
        if(str.length()==0||str.length()==1){
            return str;
        }

        char[] cs = str.toCharArray();
        reverseChar(cs);
        String[] strs = new String(cs).split("\\s");
        String result = new String(reverseChar(strs[0].toCharArray()));
        for(int i=1;i<strs.length;i++){
            result = result + " " + new String(reverseChar(strs[i].toCharArray()));
        }
        return result;
    }

    public char[] reverseChar(char[] cs){
        if(cs.length == 0|| cs.length == 1){
            return cs;
        }

        for(int i=0;i<cs.length/2;i++){
            char temp = cs[i];
            cs[i] = cs[cs.length-i-1];
            cs[cs.length-i-1] = temp;
        }
        return cs;
    }

    public static void main(String[] args){
        Algorithm algorithm = new Algorithm();
        String s = algorithm.ReverseSentence("");
        System.out.println("结果为:"+s);
    }
}
