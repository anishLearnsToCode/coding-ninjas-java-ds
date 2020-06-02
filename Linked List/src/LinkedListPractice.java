import java.util.Scanner;

class LinkedList{
    int data;
    LinkedList next;

    LinkedList(int data){
        this.data = data;
    }
    LinkedList(){
        //Default Constructor
    }
}

class stack{

    private static int LinkedListSize=0;

    public static void getData(LinkedList head){
        Scanner s = new Scanner(System.in);
        int data, n;

        System.out.print("Please enter the no. of data entries : ");
        n = s.nextInt();
        while(n-- > 0){
            System.out.print("Enter data : ");
            data = s.nextInt();
            addData(data, head);
            LinkedListSize++;
        }
    }

    public static void addData(int data, LinkedList head){
        LinkedList node = new LinkedList(data);

        if(LinkedListSize == 0){
            head.data = node.data;
            return;
        }

        LinkedList tempNode = head;
        while(tempNode.next != null){
            tempNode = tempNode.next;
        }
        tempNode.next = node;
    }

    public static void output(LinkedList head){
        LinkedList tempNode = head;
        while (tempNode != null){
            System.out.print(tempNode.data + " ");
            tempNode = tempNode.next;
        }
    }

    public static void output(int arr[]){
        for(int i=0 ; i<arr.length ; System.out.print(arr[i] + " "), i++);
    }

    public static LinkedList insertValue(LinkedList head){
        Scanner s = new Scanner(System.in);
        int data, position;
        System.out.print("Enter data : ");
        data = s.nextInt();
        System.out.print("Enter position : ");
        position = s.nextInt();
        LinkedListSize++;

        if(position == 0){
            LinkedList node = new LinkedList(data);
            node.next = head;
            return node;
        }

        LinkedList tempNode = head;
        for(int i=0 ; i<position-1 ; tempNode = tempNode.next,  i++);
        LinkedList node = new LinkedList(data);
        node.next = tempNode.next;
        tempNode.next = node;
        return head;
    }

    public static LinkedList deleteElement(LinkedList head){
        Scanner s = new Scanner(System.in);
        int position, i;
        System.out.print("Enter position to delete : ");
        position = s.nextInt();

        if(position > LinkedListSize - 1){
            System.out.println("Invalid Position, exceeds List Size ");
            return head;
        }

        LinkedListSize--;

        if(position == 0){
            LinkedList tempNode = head.next;
            return tempNode;
        }

        LinkedList tempNode = head;
        for(i=0 ; i<position-1 ; tempNode = tempNode.next, i++);
        tempNode.next = tempNode.next.next;
        return head;
    }

    public static LinkedList deleteSegment(LinkedList head){
        Scanner s = new Scanner(System.in);
        int index1, index2, i;

        System.out.print("Enter index1 and index2 : ");
        index1 = s.nextInt();
        index2 = s.nextInt();

        if((index1 < 0) || (index1 > LinkedListSize-1) || (index2 < index1) || (index2 > LinkedListSize-1)){
            System.out.println("Invalid position ");
        }

        if(index2 - index1 == LinkedListSize-1){
            LinkedList newNode = new LinkedList();
            LinkedListSize = 0;
            return newNode;
        }

        if(index1 == 0){
            LinkedList tempTailNode = head;
            for(i=0 ; i<index2+1 ; tempTailNode = tempTailNode.next, i++);
            LinkedListSize -= (index2 - index1 + 1);
            return tempTailNode;
        }

        LinkedList tempHeadNode = head;
        for(i=0 ; i<index1-1 ; tempHeadNode = tempHeadNode.next, i++);
        LinkedList tempTailNode = tempHeadNode;
        for(; i<index2+1 ; tempTailNode = tempTailNode.next, i++);
        tempHeadNode.next = tempTailNode;
        LinkedListSize -= (index2 - index1 + 1);
        return head;
    }

    public static LinkedList merge(LinkedList list1, LinkedList list2){
        LinkedList traversal1, traversal2, tempNode;
        for(traversal1 = list1, traversal2 = list2, tempNode = list1 ; traversal1 != null && traversal2 != null ; ){

            if(traversal2.data <= traversal1.data){
                LinkedList node = new LinkedList(traversal2.data);
                node.next = traversal1;
                tempNode.next = node;
                tempNode = node;
                traversal2 = traversal2.next;
            }
            else {
                traversal1 = traversal1.next;
            }

            if(traversal1 == null){
                tempNode.next.next = traversal2;
                break;
            }
        }

        return list1;
    }

    public static LinkedList ArrToLinkedList(int arr[]){
        LinkedList head = new LinkedList(arr[0]);
        LinkedList tempNode = head;
        for(int i=1 ; i<arr.length ; i++){
            LinkedList node = new LinkedList(arr[i]);
            tempNode.next = node;
            tempNode = node;
        }
        return head;
    }

    public static void LinkedListSizeDisplay(){
        System.out.println(LinkedListSize);
    }
}

class array{
    private static int size;
    private static int arr[];

    public static void createArr(){
        Scanner s = new Scanner(System.in);
        System.out.print("Size : ");
        size = s.nextInt();
        getValue();
    }

    private static void getValue(){
        Scanner s = new Scanner(System.in);
        arr = new int[size];
        for(int i=0 ; i<arr.length ; i++){
            System.out.print("Enter value : ");
            arr[i] = s.nextInt();
        }
    }

    public static int[] returnArr(){
        return arr;
    }
}

public class LinkedListPractice {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        LinkedList head = new LinkedList();
        int i, n;

        do {
            System.out.println("\n\n\nSelect any of the following functions : ");
            System.out.println("1) Enter values ");
            System.out.println("2) Output Values");
            System.out.println("3) Insert Value at new Position");
            System.out.println("4) Delete value from Linked List");
            System.out.println("5) Delete Segment from Linked List");
            System.out.println("6) Merge Sort");
            System.out.println("7) Reverse Linked List");
            System.out.println("8) LinkedList Size");
            System.out.println("9) Convert Array to Linked List");
            System.out.print("10) Exit : ");
            n = s.nextInt();

            if(n==1){
                stack.getData(head);
            }else if(n == 2){
                stack.output(head);
            } else if(n == 3){
                head = stack.insertValue(head);
            } else if(n == 4){
                head = stack.deleteElement(head);
            } else if(n == 5){
                head = stack.deleteSegment(head);
            } else if(n == 6){
                LinkedList list1 = new LinkedList();
                LinkedList list2 = new LinkedList();

                System.out.println("Enter List 1 :- ");
                stack.getData(list1);
                System.out.println("Enter List 2 :- ");
                stack.getData(list2);

                list1 = stack.merge(list1, list1);
                stack.output(list1);
            } else if(n == 7){

            } else if(n == 8){
                stack.LinkedListSizeDisplay();
            } else if(n == 9){
                array.createArr();
                LinkedList ans = stack.ArrToLinkedList(array.returnArr());
                stack.output(ans);
            }

        }while (n!= 10);
    }
}