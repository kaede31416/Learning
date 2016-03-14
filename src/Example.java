import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

/**
 * Created by asus on 2016/3/5.
 */
public class Example{

    public static void main(String args[]){
        Example ex = new Example();
        TreeNode node8 = new TreeNode(8);
        TreeNode node6 = new TreeNode(6);
        TreeNode node10 = new TreeNode(10);
        TreeNode node5 = new TreeNode(5);
        TreeNode node7 = new TreeNode(7);
        TreeNode node9 = new TreeNode(9);
        TreeNode node11 = new TreeNode(11);
        node8.left = node6;
        node8.right = node10;
        node6.left = node5;
        node6.right = node7;
        node10.left = node9;
        node10.right = node11;
        ArrayList<ArrayList<Integer>> print = ex.Print(node8);
        for(ArrayList<Integer> list:print){
            for(Integer i:list){
                System.out.println(i);
            }
        }
    }
    public ArrayList<ArrayList<Integer>> Print(TreeNode pRoot) {
        ArrayList<ArrayList<Integer>> resultList = new ArrayList<>();
        if(pRoot == null){
            return resultList;
        }

        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.push(pRoot);
        boolean leftToRight = true;
        while(!queue.isEmpty()){
            int count = queue.size();
            ArrayList<Integer> list = new ArrayList<>();
            while(count>0){
                if(leftToRight){
                    TreeNode node1 = queue.pollFirst();
                    list.add(node1.val);
                    if(node1.left != null){
                        queue.offerLast(node1.left);
                    }
                    if(node1.right!=null){
                        queue.offerLast(node1.right);
                    }
                }else{
                    TreeNode node2 = queue.pollLast();
                    list.add(node2.val);
                    if(node2.right != null){
                        queue.offerFirst(node2.right);
                    }
                    if(node2.left!=null){
                        queue.offerFirst(node2.left);
                    }
                }
                count--;
            }
            if(leftToRight){
                leftToRight = false;
            }else{
                leftToRight = true;
            }
            resultList.add(list);
        }
        return resultList;
    }


    static class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;

        public TreeNode(int val) {
            this.val = val;

        }

    }
}
