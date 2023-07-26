import java.util.*;
import java.io.*;

public class BinaryTree {
    Node head;
    int size;
    public void add(int value) {
        if(size==0) {
            head = new Node(value);
            size++;
            return;
        }
        Queue<Node> que = new ArrayDeque<>();
        que.add(head);
        while(!que.isEmpty()) {
            Node now = que.poll();
            if(now.left==null) {
                now.left = new Node(value);
                break;
            }
            else {
                que.add(now.left);
            }
            if(now.right==null){
                head.right = new Node(value);
                break;
            }
            else {
                que.add(now.right);
            }
        }
        size++;
    }
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
}

