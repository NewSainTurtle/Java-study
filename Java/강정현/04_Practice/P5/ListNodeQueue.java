package org.example.practice04;

/**
 * LinkedList 를 이용해 Queue 구현
 * 참고: https://freestrokes.tistory.com/86
 */
public class ListNodeQueue {
    private ListNode head;
    private ListNode front;
    private ListNode rear;
    private int size;

    public ListNodeQueue(int size) {
        head = null;
        front = null;
        rear = null;
        this.size = size;
    }

    public void enqueue (int data) {
        ListNode newNode = new ListNode(data);
        if (isFull()) {
            System.out.println("full");
            return;
        }
        else if (isEmpty()) {
            this.head = newNode;
            this.front = this.head;
            this.rear = this.head;
        } else {
            rear.next = newNode;
            rear = newNode;
        }
    }

    public void dequeue () {
        ListNode tmpNode;
        if (isEmpty()) {
            System.out.println("empty");
            return;
        } else if (front.next == null) { // 큐에 노드가 하나 남았을 경우
            head = null;
            front = null;
            rear = null;
        } else {
            tmpNode = front.next;
            head = tmpNode;
            front.next = null;
            front = head;
        }
    }

    public boolean isFull() {
        if (isEmpty())
            return false;
        else {
            int nodeCnt = 0;
            ListNode tmpNode = head;

            while (tmpNode.next != null) {
                ++nodeCnt;
                tmpNode = tmpNode.next;
            }

            return this.size-1 == nodeCnt;
        }
    }

    public boolean isEmpty () {
        return front == null && rear == null;
    }
}
