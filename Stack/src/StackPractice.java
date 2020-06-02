import java.util.Scanner;

class Node {
    int data;
    Node next;

    public int getData(){ return data; }
    public void addData(int data) { this.data = data; }

    Node(int data){
        this.data = data;
    }
    Node(){
        this.data = 0;
    }
}

class LinkedListStackMethods {
    private static int size = 0;

    public static Node push(Node head){
        Scanner s = new Scanner(System.in);

        System.out.print("Enter data : ");
        int data = s.nextInt();
        Node newNode = new Node();
        newNode.addData(data);

        if(size == 0) {
            System.out.println("helloworld");
            size++;
            return newNode;
        }
        else
            newNode.next = head;

        size++;
        return newNode;
    }

    public static Node pop(Node head){
        if(head == null)
            return null;

        size--;
        return head.next;
    }

    public static int peek(Node head){
        return head.getData();
    }

    public static int stackSize(){ return size; }
    public static boolean isEmpty() { return size == 0 ;}
}

class stack {
    private int arr[] = new int[5];
    private int size = 0;

    public void push(int n){
        if(size >= arr.length)
            System.out.println("Stack Full");
        else
            arr[size++] = n;
    }

    public void pop(){
        if(size > 0)
            size--;
    }

    public void peek(){
        if(size == 0)
            System.out.println("Empty Stack");
        else
            System.out.println(arr[size-1]);
    }

    public int arrSize (){
        return size;
    }

    public boolean isEmpty(){
        return size == 0 ;
    }

    public boolean isFull() {
        return size == 5;
    }
}

public class StackPractice {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        stack arr = new stack();
        int n;

        System.out.println("1) Array Stack");
        System.out.print("2) Linked List Stack : ");
        n = s.nextInt();

        if( n == 1) {

            do {
                System.out.println("1) Push");
                System.out.println("2) Pop");
                System.out.println("3) Peek");
                System.out.println("4) Array length");
                System.out.println("5) IsEmpty");
                System.out.println("6) IsFull");
                System.out.println("7) Exit");
                n = s.nextInt();

                if (n == 1) {
                    System.out.print("Enter data : ");
                    int data = s.nextInt();
                    arr.push(data);
                } else if (n == 2) {
                    arr.pop();
                } else if (n == 3) {
                    arr.peek();
                } else if (n == 4) {
                    arr.arrSize();
                } else if (n == 5) {
                    System.out.println(arr.isEmpty());
                } else if (n == 6) {
                    System.out.println(arr.isFull());
                }
            } while (n != 7);
        }

        else if (n == 2){
            do {
                Node head = new Node(0);
                System.out.println("1) Push");
                System.out.println("2) Pop");
                System.out.println("3) Peek");
                System.out.println("4) Stack Length");
                System.out.println("5) IsEmpty");
                System.out.print("6) Exit : ");
                n = s.nextInt();

                if(n == 1){
                    head = LinkedListStackMethods.push(head);
                } else if (n == 2){
                    head = LinkedListStackMethods.pop(head);
                } else if (n == 3){
                    System.out.println(head.data);

                    Node temp = head;
                    while (temp != null){
                        System.out.println(temp.getData());
                        temp = temp.next;
                    }

                } else if (n == 4){
                    System.out.println(LinkedListStackMethods.stackSize());
                } else if(n == 5){
                    System.out.println(LinkedListStackMethods.isEmpty());
                }
            }while (n != 6);
        }
    }
}
