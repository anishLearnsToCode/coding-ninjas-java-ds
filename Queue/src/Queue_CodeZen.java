import java.util.Scanner;

class Queue<T> {
    private int queueSize;
    private T data;
    Queue<T> next;

    Queue(){
        queueSize = 0;
    }

    public int size() {return queueSize; }
    public boolean isEmpty() {return queueSize == 0; }
    public T front () {return data; }
    public void enqueue (T data){

    }
}

public class Queue_CodeZen {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
    }
}
