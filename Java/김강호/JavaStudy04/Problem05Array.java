package Problem;

public class Problem05Array {
    static class Queue {
        int[] arr;
        int index;
        // defalut 생성자 100 size 생성
        Queue() {
            arr = new int[100];
            index=-1;
        }
        Queue(int size) {
            arr = new int[size];
            index=-1;
        }
        void push(int num) {
            arr[++index] = num;
        }
        int pop() {
            int num = arr[0];
            arr[0] = 0;
            for(int i=1; i<=index; i++) {
                arr[i-1] = arr[i];
            }
            index--;
            return num;
        }
        String print() {
            StringBuilder sb = new StringBuilder();
            for(int i=0; i<=index; i++) {
                sb.append(arr[i]).append(" ");
            }
            return sb.toString();
        }
    }
    public static void main(String[] args) {
        Queue queue = new Queue(10);
        for(int i=0; i<10; i++) {
            queue.push(i);
        }
        for(int i=0; i<10; i++) {
            queue.pop();
            System.out.println(queue.print());
        }
    }
}
