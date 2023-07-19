package task05;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class QueueTest {

    @Test
    @DisplayName("값넣지않고 뺌")
    void pollTest() {
        Queue queue = new Queue();
        assertThrows(ArrayIndexOutOfBoundsException.class, ()->{
            queue.poll();
        });
    }


    @Test
    @DisplayName("값넣지않고 봄")
    void peekTest() {
        Queue queue = new Queue();
        assertThrows(ArrayIndexOutOfBoundsException.class, ()->{
            queue.peek();
        });
    }

    @Test
    @DisplayName("값넣고 빼서 비교")
    void offerAndPollTest() {
        Queue queue = new Queue();
        queue.offer(127);
        assertEquals(queue.poll(),127);
    }

    @Test
    @DisplayName("값넣고 봄")
    void offerAndPeekTest() {
        Queue queue = new Queue();
        queue.offer(127);
        assertEquals(queue.peek(),127);
    }

    @Test
    @DisplayName("값 두번넣고 빼서 비교")
    void offerTwiceAndPollTest() {
        Queue queue = new Queue();
        queue.offer(127);
        queue.offer(34);

        assertAll(
                () -> assertEquals(queue.poll(),127),
                () -> assertEquals(queue.poll(),34)
        );
    }

    @Test
    @DisplayName("11번 값을 넣어 resize 실행")
    void resizeTest() {
        Queue queue = new Queue();
        queue.offer(1);
        queue.offer(2);
        queue.offer(3);
        queue.offer(4);
        queue.offer(5);
        queue.offer(6);
        queue.offer(7);
        queue.offer(8);
        queue.offer(9);
        queue.offer(10);
        queue.offer(11);
        assertEquals(queue.poll(),1);
    }


    @Test
    @DisplayName("5번 값을 넣고 5번 값을 뺀 후 11번 값을 넣어 resize 실행")
    void offer5AndPoll5AndresizeTest() {
        Queue queue = new Queue();
        queue.offer(1);
        queue.offer(2);
        queue.offer(3);
        queue.offer(4);
        queue.offer(5);
        queue.poll();
        queue.poll();
        queue.poll();
        queue.poll();
        queue.poll();
        queue.offer(6);
        queue.offer(7);
        queue.offer(8);
        queue.offer(9);
        queue.offer(10);
        queue.offer(11);
        queue.offer(12);
        queue.offer(13);
        queue.offer(14);
        queue.offer(15);
        queue.offer(16);
        assertEquals(queue.poll(),6);
    }

}