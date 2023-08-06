package org.example.practice04;

/**
 * int 배열을 사용해서 정수를 저장하는 Queue 구현.
 * 참고: https://freestrokes.tistory.com/83
 */

public class Queue {
    int[] queue;
    int front;
    int rear;
    int size;

    public Queue (int size) {
        this.queue = new int[size];
        this.front = -1;
        this.rear = -1;
        this.size = size;
    }

    public void enqueue (int data) {
        if (rear == this.size -1) {
            System.out.println("full");
            return;
        }
        queue[++rear] = data;
    }

    public int dequeue () {
        if (front == rear) {
            System.out.println("empty");
            return -1;
        } else {
            front = (front + 1) % this.size;
            return queue[front];
        }
    }

}
