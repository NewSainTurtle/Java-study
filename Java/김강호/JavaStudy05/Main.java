import java.util.ArrayDeque;
import java.util.Queue;

public class Main {
    public static void bfs(Node node) {
        Queue<Node> que = new ArrayDeque<>();
        que.add(node);
        while(!que.isEmpty()) {
            Node now = que.poll();
            System.out.println(now.value);
            if(now.left!=null) que.add(now.left);
            if(now.right!=null) que.add(now.right);
        }
    }
    public static void dfs(Node node) {
        //왼쪽
        if(node.left!=null) dfs(node.left);
        //루트
        System.out.println(node.value);
        //오른쪽
        if(node.right!=null) dfs(node.right);
    }
    public static void main(String[] args) {
        BinaryTree bt = new BinaryTree();
        bt.add(1);
        bt.add(2);
        bt.add(3);
        bt.add(4);
        bfs(bt.head);
        dfs(bt.head);
    }
}
