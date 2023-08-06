package org.example.practice04;

import java.util.EmptyStackException;

/**
 * LinkedList 를 이용해 Stack 구현
 * 참고: https://freestrokes.tistory.com/85
 */
public class ListNodeStack {
    private ListNode head;
    private ListNode top;
    private int size; // 스택 사이즈

    public ListNodeStack(int size) {
        head = null;
        top = null;
        this.size = size;
    }

    void push (int data) {
        ListNode newNode = new ListNode(data);
        // 스택이 찬 경우
        if (isFull()) {
            System.out.println("full");
            return;
        }
        else if (isEmpty()) { // 스택이 빈 경우
            // head 에 새로운 노드를 연결, top 이 head 를 가리킴.
            this.head = newNode;
            this.top = this.head;
        } else {
            ListNode tmpNode = top;
            while (tmpNode != null) { // 마지막 노드 찾기
                tmpNode = tmpNode.next;
            }
            tmpNode.next = newNode; // 마지막 노드에 새로운 노드 연결
        }
    }

    int pop () {
        ListNode preNode;
        ListNode tmpNode;

        if (isEmpty())
            throw new EmptyStackException();
        else if (top.next == null) { // 스택의 노드가 1개 있는 경우
            ListNode last = top;
            head = null;
            top = null;
            return last.data;
        } else {
            preNode = top;
            tmpNode = top.next;

            while (tmpNode.next != null) {
                preNode = tmpNode;
                tmpNode = tmpNode.next;
            }
            ListNode last = preNode;
            preNode.next = null;
            return last.data;
        }
    }

    boolean isEmpty () {
        return top == null;
    }

    boolean isFull() {
        if (isEmpty())
            return false;
        else {
            int nodeCnt = 0;
            ListNode tmpNode = head;

            while (tmpNode.next != null) {
                ++nodeCnt;
                tmpNode = tmpNode.next;
            }

            return this.size - 1 == nodeCnt;
        }
    }
}
