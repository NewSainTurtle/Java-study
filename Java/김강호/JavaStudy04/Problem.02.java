public class Problem02 {
    static class ListNode {
        int num;
        ListNode next;
        public ListNode(int num) {
            this.num = num;
            next = null;
        }
        public ListNode add(ListNode head, ListNode nodeToAdd, int position) {
            ListNode node = head;
            for(int i=1; i<position; i++) {
                node = node.next;
            }
            nodeToAdd.next = node.next;
            node.next = nodeToAdd;
            return head;

        }
        public ListNode remove(ListNode head, int positionToRemove) {
            ListNode node = head;
            if(positionToRemove==0) {
                ListNode nextNode = node.next;
                node.next = null;
                return nextNode;
            }
            else {
                for(int i=1; i<positionToRemove; i++) {
                    node = node.next;
                }
                ListNode next = node.next;
                node.next = next.next;
                next.next = null;
                return head;
            }
        }
        public boolean contains(ListNode head, ListNode nodeTocheck) {
            while(head.next!=null) {
                if(head.next==nodeTocheck) return true;
                head = head.next;
            }
            return false;
        }
    }
    public static void main(String[] args) {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        node1.add(node1,node2,1);
        node1.add(node1,node3,2);
        node1.add(node1,node4,3);
        System.out.println(node1.contains(node1,node2));
        System.out.println(node1.contains(node1,node3));
        node1.remove(node1,1);
        node1.remove(node1,2);
        System.out.println(node1.contains(node1,node2));
        System.out.println(node1.contains(node1,node4));
    }
}
