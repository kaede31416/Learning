import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.HashMap;

/**
 * Created by asus on 2016/2/29.
 */
public class TestApiTool {
    String newAppkey = "85eb6835b0a1034e";//搜索功能需要新版appKey配合appSecret使用
    String newAppSecret = "2ad42749773c441109bdc0191257a664";
    HashMap<String, String> params = new HashMap<>();

    public static void main(String[] args) {
        TestApiTool test = new TestApiTool();
        test.params.put("keyword", "传颂之物");
        test.params.put("page", "1");
        test.params.put("pagesize", "20");
        test.params.put("order", "default");
        test.params.put("appkey",test.newAppkey);
        String keyword = "keyword";
        String page = "page";
        String pagesize = "pagesize";
        String order = "order";
        String appkey= "appkey";
        String[] paramsArr = {keyword, page, pagesize, order,appkey};
        System.out.println(test.getSign(paramsArr));
    }

    //需要对参数名进行排序 然后对参数值进行URL编码 并将他们用等号连接 最后还要附上appSecret 然后进行MD5加密
    public String getSign(String[] arr) {
        Arrays.sort(arr);
        String result = "";
        for (String key : arr) {
            String value = params.get(key);
            if(key .equals("keyword")){
                value = encodeValue(value);
            }
            result += (result.equals("")? "" : "&") + key + "=" + value;
        }
        System.out.println(result);
        return md5Encryption(result+newAppSecret);
    }

    public String encodeValue(String keyword) {
        try {
            return URLEncoder.encode(keyword, "UTF-8").toUpperCase();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }

    public String md5Encryption(String source){
        try {
            MessageDigest digest = MessageDigest.getInstance("md5");
            byte[] bs = digest.digest(source.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b:bs){
                int val = b & 0xff;
                if(val<16){
                    sb.append("0");
                }
                sb.append(Integer.toHexString(val));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }
}
