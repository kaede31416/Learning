import java.util.Random;

/**
 * Created by asus on 2016/2/26.
 *
 */
public class Data {
    private int[] data = new int[20];
    public Data(){
        Random random = new Random();
        for(int i=0;i<20;i++) {
            int randomInt = random.nextInt(100);
            data[i] = randomInt;
        }
    }

    public int[] getData() {
        return data;
    }

    public void setData(int[] data) {
        this.data = data;
    }

    public String toString() {
        String str = "";
        for (int i=0;i<data.length;i++){
            str= str+ data[i]+"\n";
        }
        return str;
    }
}
