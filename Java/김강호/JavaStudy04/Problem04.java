package Problem;

import Problem.Problem02.*;

public class Problem04 {
    static class Stack {
        ListNode node;
        int index;
        Stack() {
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
            ListNode next = node;
            for(int i=0; i<index; i++) {
                next = next.next;
            }
            int num = next.num;
            node.remove(node,index--);
            return num;
        }
        String print() {
            if(index==-1) return "empty";
            else return node.print();
        }
    }
    public static void main(String[] args) {
        Stack stack = new Stack();
        for(int i=1; i<5; i++) {
            stack.push(i);
        }
        System.out.println(stack.print());
        for(int i=1; i<5; i++) {
            System.out.println(stack.pop());
        }
        System.out.println(stack.print());
    }
}
