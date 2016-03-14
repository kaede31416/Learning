import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kaede on 2016/1/20.
 * 排序测试
 */
public class SortTest {

    public static void main(String[] args){

        System.out.println("---------------------------冒泡排序---------------------------");

        Data data1 = new Data();
        bubbleSort(data1);
        System.out.println(data1);

        System.out.println("---------------------------选择排序---------------------------");

        Data data2 = new Data();
        selectSort(data2);
        System.out.println(data2);

        System.out.println("---------------------------插入排序---------------------------");

        Data data3 = new Data();
        insertSort(data3);
        System.out.println(data3);

        System.out.println("---------------------------希尔排序---------------------------");

        Data data4 = new Data();
        shellSort(data4);
        System.out.println(data4);

        System.out.println("--------------------------归并排序---------------------------");

        Data data5 = new Data();
        mergeSort(data5.getData(),0,data5.getData().length-1);
        System.out.println(data5);

        System.out.println("---------------------------快速排序---------------------------");

        Data data6 = new Data();
        quickSort(data6.getData(),0,data6.getData().length-1);
        System.out.println(data6);

        System.out.println("---------------------------堆排序---------------------------");

        Data data7 = new Data();
        heapSort(data7.getData());
        System.out.println(data7);//除却第一个元素 其他元素都是有序化的

    }

    /**
     * 冒泡排序 时间复杂度o(n^2)
     * @param d 数据源
     */
    public static void bubbleSort(Data d){
        int[] data = d.getData();
        for(int i=0;i<data.length;i++){
            for (int j=0;j<data.length-i-1;j++){
                if(data[j]>data[j+1]){
                    swap(data,j,j+1);
                }
            }
        }
    }

    /**
     * 选择排序 时间复杂度o(n^2)
     * @param d 数据源
     */
    public static void selectSort(Data d){
        int[] data = d.getData();
        for(int i=0;i<data.length;i++){
            int min = i;
            for(int j=i+1;j<data.length;j++){
                if(data[j]<data[min]){
                    min = j;
                }
            }
            swap(data,min,i);
        }
    }

    /**
     * 插入排序 时间复杂度o(n^2)
     * @param d 数据源
     */
    public static void insertSort(Data d){
        int[] data = d.getData();
        for(int i=1;i<data.length;i++){
            for(int j=i;j>=1;j--){
                if(data[j]<data[j-1]){
                    swap(data,j,j-1);
                }else {
                    break;
                }
            }
        }
    }

    /**
     * 希尔排序 时间复杂度o(nlogn)
     * 相对于插入 只需要改动第一个循环的起点 第二个循环的终点和递减值
     * @param d 数据源
     */
    public static void shellSort(Data d){
        int[] data = d.getData();
        int h = 1;// 分组因子
        while (h<data.length/3){
            h = h*3 +1;
        }

        while (h>=1){
            for(int i=h;i<data.length;i++){
                for(int j=i;j>=h;j=j-h){
                    if(data[j]<data[j-h]){
                        swap(data,j,j-h);
                    }else {
                        break;
                    }
                }
            }
            h = (h-1)/3;
        }
    }

    /**
     * 归并排序 时间复杂度o(nlogn)
     * @param data 要排序的数组
     * @param start 数组起点下标
     * @param end 数组终点下标
     */
    public static void mergeSort(int[] data,int start,int end){
        if(start >= end){
            return;
        }
        int mid = (end + start)/2; //注意求中点的方式！
        mergeSort(data,start,mid);
        mergeSort(data,mid+1,end);
        merge(data,start,mid+1,end);
    }

    public static void merge(int[] data,int start1,int start2,int end){
        int[] temp = new int[data.length];
        int a = start1;
        int b = start2;
        int c = start1;//临时数组指针
        while (a<start2&&b<=end){
            if(data[a]<data[b]){
                temp[c] = data[a];
                a++;
            }else {
                temp[c] = data[b];
                b++;
            }
            c++;
        }



        while (a<start2){
            temp[c] = data[a];
            a++;
            c++;
        }

        while (b<=end){
            temp[c]= data[b];
            b++;
            c++;
        }

        while (start1<=end){
            data[start1] = temp[start1];
            start1++;
        }
    }

    /**
     * 快速排序 时间复杂度o(nlogn)
     * @param data 数据源
     * @param start 数组起始位置
     * @param end 数组结束位置
     */
    public static void quickSort(int[] data,int start,int end){
        if(start>=end){
            return;
        }

        int partitionPoint = partition(data,start,end);
        quickSort(data,start,partitionPoint-1);//分割点位置已经正确 不再需要排序
        quickSort(data,partitionPoint+1,end);
    }

    public static int partition(int[] data,int start,int end){
        int insertPoint = data[start];
        int a = start;
        int b = end+1;
        while (true){
            while (data[++a]<insertPoint){
                if(a == end){
                    break;
                }
            }

            while (data[--b]>insertPoint){
                if(b == start){
                    break;
                }
            }

            if(a >= b){
                break;
            }

            swap(data,a,b);
        }
        swap(data,start,b); //插入点一定要和后面的b交换 这个位置才是正确位置！！
        return b;
    }

    /**
     * 堆排序 时间复杂度o(nlogn)
     * 所谓的二叉堆是一种双亲节点永远大于孩子节点的二叉树
     * @param data 数组源
     */
    public static void heapSort(int[] data){
        int length = data.length - 1;//不使用第一个位置 不然求孩子节点的公式就不成立了
        for(int i=length/2;i>=1;i--){//二叉堆有序化
            sink(data,i,length);
        }

        while (length>1){
            swap(data,1,length);
            sink(data,1,--length);
        }
    }

    public static void sink(int[] data,int num,int length){
        while (num*2<=length){//有孩子节点
            int parent = num;
            if(num*2+1<=length){//有右子树
                if(data[num*2]>data[num*2+1]){//找出最大的
                    num = num*2;
                }else {
                    num = num*2 +1;
                }
            }else {//左子树就是最大的
                num = num*2;
            }
            if(data[parent]<data[num]){//孩子比父亲大 父亲下沉
                swap(data,parent,num);
            }else {
                break;
            }
        }
    }

    public static void swap(int[] data,int a,int b){
        int temp = data[a];
        data[a] = data[b];
        data[b] = temp;
    }
}
