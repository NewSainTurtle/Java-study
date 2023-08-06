package org.example.practice04;


/**
 * LinkedList 구현
 * 참고: https://hardenkim.github.io/java/java-study-linkedlist/
 */
public class ListNode {
    protected int data;
    public ListNode next;

    public ListNode (int data) {
        this.data = data;
        this.next = null;
    }

    public ListNode add (ListNode head, ListNode nodeToAdd, int position) {
        // head 가 null 이거나, 추가할 위치가 범위 밖인 경우
        if (head == null || position < 0)
            throw new IndexOutOfBoundsException();

        // 추가할 노드가 head 에 위치할 경우
        if (position == 0) {
            nodeToAdd.next = head;
            return nodeToAdd;
        }

        // 추가할 노드가 중간에 위치할 경우
        ListNode node = head;
        try {
            for (int i = 0; i < position - 1; i++) {
                node = node.next; // 추가 노드 이전까지 이동
            }
            nodeToAdd.next = node.next;
            node.next = nodeToAdd;
        } catch (NullPointerException e) {
            throw new IndexOutOfBoundsException();
        }

        return head;
    }
    public ListNode remove (ListNode head, int positionToRemove) {
        if (head == null || positionToRemove < 0)
            throw new IndexOutOfBoundsException();

        ListNode node = head;

        // head 를 삭제할 경우
        if (positionToRemove == 0) {
            head = node.next; // head 의 다음 노드를 head 로 변경
            return head;
        }

        // 삭제할 노드가 중간에 있는 경우
        try {
            for (int i = 0; i < positionToRemove - 1; i++) {
                node = node.next; // 삭제 이전까지 이동
            }
            node.next = node.next.next;
        } catch (NullPointerException e) {
            throw new IndexOutOfBoundsException();
        }
        return head;
    }

    public boolean contains (ListNode head, ListNode nodeTocheck) {
        ListNode node = head;
        while (node != null) {
            if (node.data == nodeTocheck.data) return true;
            node = node.next;
        }
        return false;
    }
}
