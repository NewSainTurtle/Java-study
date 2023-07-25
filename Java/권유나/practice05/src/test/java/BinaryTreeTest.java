import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class BinaryTreeTest {

    @Nested
    class bfsTest {

        @Test
        @DisplayName("데이터 하나만 넣고 출력하기")
        void 데이터하나만넣고출력() {
            BinaryTree binaryTree = new BinaryTree();
            binaryTree.insert(9);
            Node root = binaryTree.getRoot();
            assertEquals(binaryTree.bfs(root), Arrays.asList(9));
        }

        @Test
        @DisplayName("데이터 5개 넣고 출력하기")
        void 데이터5개넣고출력() {
            BinaryTree binaryTree = new BinaryTree();
            binaryTree.insert(9);
            binaryTree.insert(127);
            binaryTree.insert(7);
            binaryTree.insert(2);
            binaryTree.insert(8);
            Node root = binaryTree.getRoot();
            assertEquals(binaryTree.bfs(root), Arrays.asList(9,7,127,2,8));
        }

    }

    @Nested
    class dfsTest {

        @Test
        @DisplayName("데이터 하나만 넣고 출력하기")
        void 데이터하나만넣고출력() {
            BinaryTree binaryTree = new BinaryTree();
            binaryTree.insert(9);
            Node root = binaryTree.getRoot();
            assertEquals(binaryTree.dfs(root), Arrays.asList(9));
        }

        @Test
        @DisplayName("데이터 5개 넣고 출력하기")
        void 데이터5개넣고출력() {
            BinaryTree binaryTree = new BinaryTree();
            binaryTree.insert(9);
            binaryTree.insert(127);
            binaryTree.insert(7);
            binaryTree.insert(2);
            binaryTree.insert(8);
            Node root = binaryTree.getRoot();
            assertEquals(binaryTree.dfs(root), Arrays.asList(2,7,8,9,127));
        }

    }

}