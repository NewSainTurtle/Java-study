package task03_04;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LinkedListStackTest {

    @Test
    @DisplayName("값넣지않고 뺌")
    void popTest() {
        LinkedListStack stack = new LinkedListStack();
        assertThrows(IndexOutOfBoundsException.class, ()->{
            stack.pop();
        });
    }

    @Test
    @DisplayName("값넣고 빼서 비교")
    void pushAndPopTest() {
        LinkedListStack stack = new LinkedListStack();
        stack.push(127);
        assertEquals(stack.pop(),127);
    }

    @Test
    @DisplayName("값 두번넣고 빼서 비교")
    void pushTwiceAndPopTest() {
        LinkedListStack stack = new LinkedListStack();
        stack.push(127);
        stack.push(34);
        assertEquals(stack.pop(),34);
    }

}