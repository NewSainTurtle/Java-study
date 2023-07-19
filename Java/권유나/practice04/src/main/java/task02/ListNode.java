package task02;

public class ListNode {
    private int num;
    private ListNode next;

    public ListNode(int num, ListNode next) {
        this.num = num;
        this.next = next;
    }

    public ListNode(int num) {
        new ListNode(num,null);
    }

    public int getNum() {
        return num;
    }

    public ListNode getNext() {
        return next;
    }

    public void setNext(ListNode next) {
        this.next = next;
    }
}
