package org.example.practice04;

import java.util.Arrays;
import java.util.EmptyStackException;

/**
 * int 배열을 사용해서 정수를 저장하는 Stack 구현.
 * 참고: https://choo.oopy.io/66090696-50d1-4fa0-ac83-dd4e63e92dbc#28ca9153-fbd7-4aab-b890-40681ee22a09
 */
public class Stack {
    int[] stack;
    int top; // 삽입과 삭제가 일어나는 위치
    int size; // 스택의 크기

    public Stack(int size) {
        this.stack = new int[size];
        this.top = -1;
    }

    public void push (int data) {
        if (top == size - 1){
            System.out.println("full");
            return;
        }
        stack[++top] = data;
    }

    public int pop () {
        if (top == -1)
            throw new EmptyStackException();
        int pop = stack[top];
        stack[top--] = 0;
        return pop;
    }
}
