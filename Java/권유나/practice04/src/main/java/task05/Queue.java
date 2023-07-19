package task05;

public class Queue {

    private int[] arr;
    private int front;
    private int rear;
    private int size;

    Queue() {
        arr = new int[10];
        front = 0;
        rear = -1;
        size = 0;
    }

    void offer(int data) {
        if (size++ == arr.length) {
            resize();
        }
        rear = rear + 1 == arr.length ? 0 : rear + 1;
        arr[rear] = data;

    }

    int poll() {
        if (size-- == 0) {
            throw new ArrayIndexOutOfBoundsException();
        }

        int item = arr[front];
        front = front + 1 == arr.length ? 0 : front + 1;
        return item;
    }

    int peek() {
        if (size == 0) {
            throw new ArrayIndexOutOfBoundsException();
        }
        return arr[front];
    }

    void resize() {
        int[] tmp = new int[arr.length + 10];
        int idx = 0;
        while (front < arr.length) {
            tmp[idx++] = arr[front];
            if (front == rear) break;
            front++;
        }

        if (front != rear) {
            front = 0;
            while (front <= rear) {
                tmp[idx++] = arr[front++];
            }
        }

        front = 0;
        rear = size - 2;
        arr = tmp;
    }
}
