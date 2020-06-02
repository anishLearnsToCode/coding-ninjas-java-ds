import java.util.Scanner;

class queue {
    private int arr[] = new int[10];
    private int size, front, rear;

    public void push(){
        if(size == 10){
            System.out.println("Size Full");
            return;
        }

        Scanner s = new Scanner(System.in);
        System.out.print("Enter data : ");
        int data = s.nextInt();
        arr[front] = data;
        size++;
        front = (front + 1) % 10;
    }

    public void pop (){
        if(size == 0){
            System.out.println("Empty Queue");
            return;
        }

        rear = (rear + 1) % 10;
        size--;
    }

    public int peek(){
        if(size == 0){
            System.out.println("Empty Stack");
        }
        return arr[rear] ;
    }

    //Constructors
    queue(){
        size = 0;
        front = 0;
        rear = 0;
    }
}

public class QueueClass {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        queue obj = new queue();
        int n;

        do {
            System.out.println("1) Push");
            System.out.println("2) Pop");
            System.out.println("3) Peek");
            System.out.print("4) Exit : ");
            n = s.nextInt();

            if(n == 1){
                obj.push();
            } else if(n == 2){
                obj.pop();
            } else if(n == 3){
                int ans = obj.peek();
                System.out.println(ans);
            }
        }while (n != 4);
    }
}
