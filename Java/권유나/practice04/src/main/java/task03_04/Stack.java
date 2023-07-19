package task03_04;

import java.util.Arrays;

public class Stack {

    private int[] arr;
    private int size;

    Stack() {
        arr = new int[10];
        size = 0;
    }

    void push(int data) {
        if (size + 1 == arr.length) {
            resize();
        }
        arr[size++] = data;
    }

    int pop() {
        if(size == 0){
            throw new ArrayIndexOutOfBoundsException();
        }
        return arr[--size];
    }

    void resize() {
        arr = Arrays.copyOf(arr, arr.length + 10);
    }
}
