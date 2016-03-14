import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Kaede on 2016/3/14.
 * A simple implementation of PhilosopherImp Problem
 */
public class PhilosopherImp{
    static Chopstick[] chops = new Chopstick[5];//五支筷子

    static{
        for(int i=0;i<chops.length;i++){
            chops[i] = new Chopstick(i);
        }
    }
    public static void main(String[] args){
        ExecutorService threadPool = Executors.newFixedThreadPool(5);
        for(int j=0;j<chops.length;j++){
            threadPool.execute(new Philosopher(chops[j],chops[(j+1)%5],j));
        }
    }

    static class Philosopher implements Runnable{
        private Chopstick left;
        private Chopstick right;
        private int num;
        private boolean flag = true;
        private Random random = new Random();
        public Philosopher(Chopstick left,Chopstick right,int num){
            this.num = num;
            this.left = left;
            this.right = right;
        }

        @Override
        public void run() {
            while (flag){
                left.take();
                right.take();
                eat();
                left.back();
                right.back();
            }
        }

        public void die(){
            flag = false;
        }

        public void eat(){
            System.out.println("Philosopher "+num+" is eating...");
            int timeMills = random.nextInt(2000) + 2000;//吃2到4秒
            try {
                Thread.sleep(timeMills);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Philosopher "+num+" ate complete...");
        }

    }

    static class Chopstick{
        private boolean isTaken = false;
        private int num;
        public Chopstick(int num){
            this.num = num;
        }
        public synchronized void take(){
            if(isTaken) {
                try {
                    System.out.println("Wait for chopstick "+num + " ...");
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("Chopstick "+num + " is taken...");
            isTaken =true;
        }

        public synchronized void back(){
            System.out.println("Chopstick "+num + " is back...");
            isTaken = false;
            notifyAll();
        }
    }
}
