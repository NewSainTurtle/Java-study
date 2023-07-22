package Problem;

import java.util.Arrays;

public class Problem03 {
    private static class Stack {
        int[] arr;
        int index;
        // defalut 생성자 100 size 생성
        Stack() {
            arr = new int[100];
            index = -1;
        }
        Stack(int size) {
            arr = new int[size];
            index = -1;
        }
        void push(int num) {
            arr[++index] = num;
        }
        int pop() {
            int num = arr[index];
            arr[index--] = 0;
            return num;
        }
        void print() {
            System.out.println(Arrays.toString(arr));
        }
    }
    public static void main(String[] args) {
        Stack stack = new Stack(5);
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        stack.print();
        System.out.println(stack.pop());
        stack.print();
        System.out.println(stack.pop());
        stack.print();
    }
}
