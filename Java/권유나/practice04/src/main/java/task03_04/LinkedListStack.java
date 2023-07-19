package task03_04;

import task02.LinkedList;
import task02.ListNode;

public class LinkedListStack {
    LinkedList list;

    LinkedListStack() {
        list = new LinkedList();
    }

    void push(int data) {
//        list.add(new ListNode(data,null),list.size());
        list.add(new ListNode(data,null),0);
    }

    int pop() {
//        return list.remove(list.size()-1).getNum();
        return list.remove(0).getNum();
    }

}
