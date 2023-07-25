import java.util.*;

public class BinaryTree {
    Node root;
    List<Integer> list;

    public BinaryTree() {
        this.root = null;
    }

    public Node getRoot() {
        return root;
    }

    public void insert(int value) {
        if (root == null) {
            root = new Node(value);
            return;
        }
        Node pt = root;
        while (true) {
            if (pt.getValue() >= value) {
                if (pt.getLeft() == null) {
                    pt.setLeft(new Node(value));
                    break;
                } else {
                    pt = pt.getLeft();
                }
            } else {
                if (pt.getRight() == null) {
                    pt.setRight(new Node(value));
                    break;
                } else {
                    pt = pt.getRight();
                }
            }
        }
    }

    public List<Integer> bfs(Node node) {
        list = new ArrayList<>();
        Queue<Node> queue = new LinkedList<>();
        queue.offer(node);

        while (!queue.isEmpty()) {
            Node item = queue.poll();
            if (item == null) continue;
            list.add(item.getValue());
            queue.offer(item.getLeft());
            queue.offer(item.getRight());
        }
        return list;
    }

    public List<Integer> dfs(Node node) {
        list = new ArrayList<>();
        dfsInorder(node);
        return list;
    }

    public void dfsInorder(Node node) {
        if (node == null) {
            return;
        }

        dfsInorder(node.getLeft());
        list.add(node.getValue());
        dfsInorder(node.getRight());

    }
}
