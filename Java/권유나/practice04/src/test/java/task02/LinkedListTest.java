package task02;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class LinkedListTest {

    @Nested
    class addTest {

        @Test
        @DisplayName("범위를 벗어난 position: IndexOutOfBoundsException 발생")
        void IndexOutOfBoundsException발생() {
            LinkedList list = new LinkedList();
            assertThrows(IndexOutOfBoundsException.class, () -> {
                ListNode node = list.add(new ListNode(127, null), 3);
            });
        }

        @Nested
        @DisplayName("올바른 입력 position")
        class 올바른입력 {

            @Test
            @DisplayName("0번째 값넣기")
            void 값넣기_0번째() {
                LinkedList list = new LinkedList();
                ListNode node = list.add(new ListNode(127, null), 0);
                assertEquals(node, list.search(0));
            }


            @Test
            @DisplayName("0번째에 두번값넣기")
            void 두번값넣기_0번째() {
                LinkedList list = new LinkedList();
                list.add(new ListNode(127, null), 0);
                ListNode node = list.add(new ListNode(29, null), 0);
                assertEquals(node, list.search(0));
            }

            @Test
            @DisplayName("0번째와 1번째에 값넣기")
            void 값넣기_0번째1번째() {
                LinkedList list = new LinkedList();
                ListNode node1 = list.add(new ListNode(127, null), 0);
                ListNode node2 = list.add(new ListNode(29, null), 1);
                assertAll(
                        () -> assertEquals(node1, list.search(0)),
                        () -> assertEquals(node2, list.search(1))
                );
            }

            @Test
            @DisplayName("0번째와 1번째 2번째 1번째에 값넣기")
            void 값넣기_0번째1번째2번째1번째() {
                LinkedList list = new LinkedList();
                ListNode node1 = list.add(new ListNode(127, null), 0);
                ListNode node2 = list.add(new ListNode(29, null), 1);
                ListNode node3 = list.add(new ListNode(34, null), 2);
                ListNode node4 = list.add(new ListNode(1, null), 1);
                assertAll(
                        () -> assertEquals(node1, list.search(0)),
                        () -> assertEquals(node4, list.search(1)),
                        () -> assertEquals(node2, list.search(2)),
                        () -> assertEquals(node3, list.search(3))
                );
            }
        }
    }

    @Nested
    class removeTest {

        @Test
        @DisplayName("범위를 벗어난 position: IndexOutOfBoundsException 발생")
        void IndexOutOfBoundsException발생() {
            LinkedList list = new LinkedList();
            list.add(new ListNode(127, null), 0);
            assertThrows(IndexOutOfBoundsException.class, () -> {
                ListNode node = list.remove(3);
            });
        }

        @Nested
        @DisplayName("올바른 입력 position")
        class 올바른입력 {

            @Test
            @DisplayName("하나의 값만 넣고 0번째 값빼기")
            void 값넣기_0번째_값빼기_0번째() {
                LinkedList list = new LinkedList();
                ListNode addNode = list.add(new ListNode(127, null), 0);
                ListNode removeNode = list.remove(0);
                assertEquals(removeNode, addNode);
            }


            @Test
            @DisplayName("두개의 값 넣고 0번째 두번 값빼기")
            void 두번값넣기_두번값빼기() {
                LinkedList list = new LinkedList();
                ListNode addNode1 = list.add(new ListNode(127, null), 0);
                ListNode addNode2 = list.add(new ListNode(23, null), 1);
                ListNode removeNode1 = list.remove(0);
                ListNode removeNode2 = list.remove(0);

                assertAll(
                        () -> assertEquals(addNode1, removeNode1),
                        () -> assertEquals(addNode2, removeNode2)
                );
            }

            @Test
            @DisplayName("0번째와 1번째에 값넣기")
            void 값빼기_0번째1번째() {
                LinkedList list = new LinkedList();
                ListNode addNode1 = list.add(new ListNode(127, null), 0);
                ListNode addNode2 = list.add(new ListNode(23, null), 1);
                ListNode addNode3 = list.add(new ListNode(468, null), 2);
                ListNode removeNode1 = list.remove(0);
                ListNode removeNode2 = list.remove(1);
                ListNode removeNode3 = list.remove(0);

                assertAll(
                        () -> assertEquals(addNode1, removeNode1),
                        () -> assertEquals(addNode2, removeNode3),
                        () -> assertEquals(addNode3, removeNode2)
                );
            }
        }
    }

    @Nested
    class containsTest {

        @Test
        @DisplayName("리스트에 없는 값 찾기")
        void 리스트에_없는_값찾기() {
            LinkedList list = new LinkedList();
            list.add(new ListNode(127, null), 0);
            list.add(new ListNode(29, null), 0);
            assertFalse(list.contains(new ListNode(9,null)));
        }

        @Test
        @DisplayName("리스트에 있는 값 찾기")
        void 리스트에_있는_값_찾기() {
            LinkedList list = new LinkedList();
            ListNode node = list.add(new ListNode(127, null), 0);
            list.add(new ListNode(29, null), 0);
            assertTrue(list.contains(node));
        }

        @Test
        @DisplayName("리스트에 없는 값 찾기: 정수값이 같은 노드가 있는 경우")
        void 리스트에_없는_값찾기_정수값이_값은_노드() {
            LinkedList list = new LinkedList();
            list.add(new ListNode(127, null), 0);
            list.add(new ListNode(29, null), 0);
            assertFalse(list.contains(new ListNode(127,null)));
        }

    }
}