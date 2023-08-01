package org.example;

public class Main {
    public static void main(String[] args) {
        Node node10 = new Node(null,null,10);
        Node node9 = new Node(null,null,9);
        Node node8 = new Node(node10,null,8);
        Node node7 = new Node(null,node9,7);
        Node node6 = new Node(node8,null,6);
        Node node5 = new Node(null,null,5);
        Node node4= new Node(node7,null,4);
        Node node3 = new Node(node5,node6,3);
        Node node2 = new Node(node4,null,2);
        Node node1 = new Node(node2,node3,1);

        BinaryTree binaryTree = new BinaryTree(node1);
        Node root = binaryTree.getRoot();

        binaryTree.BFS(root);
        binaryTree.DFS(root);
    }
}