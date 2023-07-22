package Problem;

import Problem.Problem02.*;

public class Problem05Node {
    static class Queue {
        ListNode node;
        int index;
        Queue() {
            node = null;
            index = -1;
        }
        void push(int num) {
            if(node==null) {
                node = new ListNode(num);
                index++;
            }
            else {
                ListNode pushNode = new ListNode(num);
                node.add(node,pushNode,++index);
            }
        }
        int pop() {
            int num = node.num;
            if(index>-1) {
                node = node.next;
                index--;
            }
            return num;
        }
        String print() {
            if(index==-1) return "empty";
            else return node.print();
        }
    }
    public static void main(String[] args) {
        Queue queue = new Queue();
        for(int i=1; i<5; i++) {
            queue.push(i);
        }
        System.out.println(queue.print());
        queue.pop();
        System.out.println(queue.print());
        queue.push(20);
        System.out.println(queue.print());
        for(int i=1; i<4; i++) {
            queue.pop();
        }
        System.out.println(queue.print());
    }
}
