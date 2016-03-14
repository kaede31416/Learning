import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by asus on 2016/2/29.
 */
public class TestHappensBefore {
    public CopyOnWriteArrayList cowa = new CopyOnWriteArrayList();
    public static void main(String[] args){
//        final TestHappensBefore test = new TestHappensBefore();
//        new Thread(){
//            public void run() {
//                System.out.print(test.cowa.get(0));
//            }
//        }.start();
//        new Thread(){
//            public void run() {
//                test.cowa.add(0,"abc");
//            }
//        }.start();
        System.out.println(getDeliverTimeDisplay("1455625229"));
    }

    public static String getDeliverTimeDisplay(String time){
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        calendar.set(year,month,day,0,0,0);
        long todayBeginTime = calendar.getTimeInMillis();
        calendar.add(Calendar.DAY_OF_MONTH,-1);
        long yesterdayTime = calendar.getTimeInMillis();
        calendar.add(Calendar.DAY_OF_MONTH,-1);
        long dayBeforeYesterdayTime = calendar.getTimeInMillis();
        calendar.add(Calendar.DAY_OF_MONTH,-1);
        long dayBeforeBeforeTime = calendar.getTimeInMillis();
        long replyTime;
        try {
            replyTime = Long.parseLong(time)*1000;
        }catch (NumberFormatException e){
            e.printStackTrace();
            return "未知";
        }
        long currentTime = System.currentTimeMillis();
        String result;
        if(replyTime - todayBeginTime>0){//今天发送的消息
            if(currentTime-replyTime<3600000){//一小时内发送
                result = (currentTime-replyTime)/1000/60+"分钟前";
            }else {
                result = (currentTime-replyTime)/1000/60/60+"小时前";
            }
        }else if(replyTime - yesterdayTime>0){
            result = "一天前投递";
        }else if(replyTime - dayBeforeYesterdayTime>0){
            result = "两天前投递";
        }else if(replyTime - dayBeforeBeforeTime>0){
            result = "三天前投递";
        }else {
            SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日", Locale.CHINA);
            result = format.format(new Date(replyTime));
        }
        return result;
    }

    public static String getStringTimeDisplay(String time){
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        calendar.set(year,month,day,0,0,0);
        long todayBeginTime = calendar.getTimeInMillis();
        long replyTime;
        try {
            replyTime = Long.parseLong(time)*1000;
        }catch (NumberFormatException e){
            e.printStackTrace();
            replyTime = 0;
        }
        long timeOffset = replyTime - todayBeginTime;
        long currentTime = System.currentTimeMillis();
        String result;
        if(timeOffset>0){//今天发送的消息
            if(currentTime-replyTime<3600000){//一小时内发送
                result = (currentTime-replyTime)/1000/60+"分钟前";
            }else {
                result = (currentTime-replyTime)/1000/60/60+"小时前";
            }
        }else {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss", Locale.CHINA);
            result = format.format(new Date(replyTime));
        }
        return result;
    }
}
