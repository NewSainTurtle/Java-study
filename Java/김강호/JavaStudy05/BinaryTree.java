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
}
