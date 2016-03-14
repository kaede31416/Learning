/**
 * Created by asus on 2016/3/7.
 */
import java.util.ArrayList;
public class Solution {
    ArrayList<Integer> result = new ArrayList<>();
    public ArrayList<Integer> printMatrix(int [][] matrix) {
        int row = matrix.length;
        if(row == 0){
            return null;
        }
        int column = matrix[0].length;
        if(row == 1 && column == 1){
            result.add(matrix[0][0]);
            return result;
        }
        int left = 0,top = 0,right = column-1,bottom = row-1;
        while(left<=right&&top<=bottom){
            addCircle(left,top,right,bottom,matrix);
            left++;
            top++;
            right--;
            bottom--;
        }

        if(left==right&&top==bottom){
            result.add(matrix[left][top]);
        }
        return result;

    }

    /* left表示左上角的列号 top表示左上角行号 right表示右下角的列号 bottom表示右下角的行号 */
    public void addCircle(int left,int top,int right,int bottom,int [][] matrix){
        for(int i=left;i<=right;i++){
            result.add(matrix[top][i]);
        }

        for(int j=top+1;j<=bottom;j++){
            result.add(matrix[j][right]);
        }

       if(top == bottom || left == right){
           return;
       }

        for(int k=right-1;k>=left;k--){
            result.add(matrix[bottom][k]);
        }

        for(int l=bottom-1;l>=top+1;l--){
            result.add(matrix[l][left]);
        }
    }

    public static void main(String[] args){
        Solution s = new Solution();
        int[][] matrix = {{1},{2},{3},{4},{5}};
        ArrayList<Integer> list = s.printMatrix(matrix);
        System.out.println(list);
    }
}

