package task02;

public class LinkedList {

    private int size;
    private ListNode head;

    public LinkedList() {
        head = new ListNode(0,null);
        size = 0;
    }

    public ListNode add(ListNode nodeToAdd, int position) throws IndexOutOfBoundsException{
        size++;
        checkValidationRange(position);

        if(head == null){
            head.setNext(nodeToAdd);
            return nodeToAdd;
        }

        ListNode pt = head;
        int idx = 0;
        for (; pt.getNext() != null; pt = pt.getNext()) {
            if (idx++ == position) {
                nodeToAdd.setNext(pt.getNext());
                pt.setNext(nodeToAdd);
                return nodeToAdd;
            }
        }

        pt.setNext(nodeToAdd);
        return nodeToAdd;
    }

    public ListNode remove(int positionToRemove) throws IndexOutOfBoundsException{
        checkValidationRange(positionToRemove);
        ListNode removeNode = null;
        int idx = 0;
        for (ListNode pt = head; pt != null; pt = pt.getNext()) {
            if (idx++ == positionToRemove) {
                removeNode = pt.getNext();
                pt.setNext(pt.getNext().getNext());
                break;
            }
        }
        size--;
        return removeNode;
    }

    public boolean contains(ListNode nodeTocheck) {
        for (ListNode pt = head; pt != null; pt = pt.getNext()) {
            if (pt.getNum() == nodeTocheck.getNum()) {
                return true;
            }
        }
        return false;
    }

    public ListNode search(int position) throws IndexOutOfBoundsException{
        checkValidationRange(position);
        int idx = 0;
        for (ListNode pt = head; pt != null; pt = pt.getNext()) {
            if (idx++ == position) {
                return pt.getNext();
            }
        }
        return null;
    }

    private void checkValidationRange(int position) {
        if (position < 0 || position >= size) throw new IndexOutOfBoundsException();
    }

    public int size(){
        return size;
    }
}
