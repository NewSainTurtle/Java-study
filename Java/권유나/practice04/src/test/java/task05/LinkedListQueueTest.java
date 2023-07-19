package task05;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LinkedListQueueTest {

    @Test
    @DisplayName("값넣지않고 뺌")
    void pollTest() {
        LinkedListQueue queue = new LinkedListQueue();
        assertThrows(IndexOutOfBoundsException.class, ()->{
            queue.poll();
        });
    }

    @Test
    @DisplayName("값넣고 빼서 비교")
    void offerAndPollTest() {
        LinkedListQueue queue = new LinkedListQueue();
        queue.offer(127);
        assertEquals(queue.poll(),127);
    }

    @Test
    @DisplayName("값 두번넣고 빼서 비교")
    void offerTwiceAndPollTest() {
        LinkedListQueue queue = new LinkedListQueue();
        queue.offer(127);
        queue.offer(34);
        assertEquals(queue.poll(),127);
    }

}