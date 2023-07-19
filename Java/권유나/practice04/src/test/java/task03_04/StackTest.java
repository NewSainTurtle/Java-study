package task03_04;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StackTest {


    @Test
    @DisplayName("값넣지않고 뺌")
    void popTest() {
        Stack stack = new Stack();
        assertThrows(ArrayIndexOutOfBoundsException.class, ()->{
           stack.pop();
        });
    }

    @Test
    @DisplayName("값넣고 빼서 비교")
    void pushAndPopTest() {
        Stack stack = new Stack();
        stack.push(127);
        assertEquals(stack.pop(),127);
    }

    @Test
    @DisplayName("값 두번넣고 빼서 비교")
    void pushTwiceAndPopTest() {
        Stack stack = new Stack();
        stack.push(127);
        stack.push(34);
        assertEquals(stack.pop(),34);
    }

    @Test
    @DisplayName("11번 값을 넣어 resize 실행")
    void resizeTest() {
        Stack stack = new Stack();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        stack.push(5);
        stack.push(6);
        stack.push(7);
        stack.push(8);
        stack.push(9);
        stack.push(10);
        stack.push(11);
        assertEquals(stack.pop(),11);
    }

}