import java.util.Scanner;

class LinkedList {
    int data;
    LinkedList next;

    LinkedList(int data){
        this.data = data;
    }
    LinkedList(){
        this.data = 0;
    }
}

class StackMethods {
    private static int size = 0;

    public static LinkedList push(LinkedList head){
        Scanner s = new Scanner(System.in);

        System.out.print("Enter data : ");
        int data = s.nextInt();

        LinkedList node = new LinkedList(data);

        if(size == 0){
            size++;
            return node;
        }

        node.next = head;
        size++;
        return node;
    }

    public static LinkedList pop(LinkedList head){
        if(head == null)
            return head;

        return head.next;
    }

    public static void peek (LinkedList head){
        if(head == null)
            System.out.println("Empty stack");
        else
            System.out.println(head.data);
    }
}

public class LinkedListStack {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int n;
        LinkedList head = new LinkedList(0);

        do {
            System.out.println("Push");
            System.out.println("Pop");
            System.out.println("Peek");
            n = s.nextInt();

            if(n == 1){
                head = StackMethods.push(head);
            } else if(n == 2){
                head = StackMethods.pop(head);
            } else if(n == 3){
                StackMethods.peek(head);
            }
        } while (n != 4);
    }
}