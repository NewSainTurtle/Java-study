package task05;

import task02.LinkedList;
import task02.ListNode;

public class LinkedListQueue {
    LinkedList list;

    LinkedListQueue() {
        list = new LinkedList();
    }

    void offer(int data) {
        list.add(new ListNode(data, null),list.size());
    }

    int poll() {
        return list.remove(0).getNum();
    }
}
