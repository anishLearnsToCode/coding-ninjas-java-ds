import java.util.Scanner;

class DoublyLinkedList{
    int data;
    DoublyLinkedList next, previous;

    DoublyLinkedList(int data){
        this.data = data;
        next = null;
        previous = null;
    }
    DoublyLinkedList(){
        this.data = 0;
        next = null;
        previous = null;
    }
}

class DoublyLinkedListMethods{
    private static int LinkedListSize = 0;

    public static void output(DoublyLinkedList list){
        DoublyLinkedList tempNode = list;
        while (tempNode != null){
            System.out.print(tempNode.data + " ");
            tempNode = tempNode.next;
        }
    }

    public static int LinkedListSizeDisplay(){
        return LinkedListSize;
    }

    public static void getData(DoublyLinkedList head){
        Scanner s = new Scanner(System.in);
        int n, data;

        System.out.println("How many entries do you wish to make : ");
        n = s.nextInt();
        while (n-- > 0){
            System.out.print("Enter value : ");
            data = s.nextInt();
            addData(data, head);
            LinkedListSize++;
        }
    }

    public static void addData(int data, DoublyLinkedList head) {
        if(LinkedListSize == 0){
            head.data = data;
            return;
        }

        DoublyLinkedList node = new DoublyLinkedList(data);
        DoublyLinkedList tempNode = head;
        while (tempNode.next != null){
            tempNode = tempNode.next;
        }
        tempNode.next = node;
        node.previous = tempNode;
    }

    public static DoublyLinkedList insertNode(DoublyLinkedList list){
        Scanner s = new Scanner(System.in);
        int data, position, i;

        System.out.print("Enter data : ");
        data = s.nextInt();
        System.out.print("Enter position : ");
        position = s.nextInt();

        if(position > LinkedListSize - 1){
            System.out.println("Invalid position ");
            return list;
        }

        LinkedListSize++;

        if(position == 0){
            DoublyLinkedList node = new DoublyLinkedList(data);
            node.next = list;
            list.previous = node;
            return node;
        }

        DoublyLinkedList tempNode = list;
        for(i=0 ; i<position-1 ; tempNode = tempNode.next, i++);
        DoublyLinkedList node = new DoublyLinkedList(data);
        node.next = tempNode.next;
        tempNode.next.previous = node;
        tempNode.next = node;
        node.previous = tempNode;

        return list;
    }

    public static DoublyLinkedList deleteNode(DoublyLinkedList list){
        Scanner s = new Scanner(System.in);
        int position, i;
        System.out.print("Enter position : ");
        position = s.nextInt();

        if(position > LinkedListSize - 1){
            System.out.println("Invalid position ");
            return list;
        }

        LinkedListSize--;

        if(position == 0){
            list.next.previous = null;
            return list.next;
        }

        DoublyLinkedList tempNode = new DoublyLinkedList();
        tempNode = list;
        for(i=0 ; i<position-1 ; tempNode = tempNode.next, i++);
        tempNode.next.next.previous = tempNode;
        tempNode.next = tempNode.next.next;
        return list;
    }

    public static DoublyLinkedList deleteSegment(DoublyLinkedList list){
        Scanner s = new Scanner(System.in);
        int index1, index2, i;
        System.out.print("Enter index1 and index2 : ");
        index1 = s.nextInt();
        index2 = s.nextInt();

        if((index1 < 0) || (index2 > LinkedListSize-1) || (index2 - index1 + 1 > LinkedListSize)){
            System.out.println("Invalid Index");
            return list;
        }

        LinkedListSize -= (index2 - index1 + 1);

        if(LinkedListSize == 0){
            DoublyLinkedList head = new DoublyLinkedList();
            return head;
        }

        if(index1 == 0){
            DoublyLinkedList head = new DoublyLinkedList();
            head = list;
            for(i=0 ; i<index2+1 ; head = head.next, i++);
            head.previous = null;
            return head;
        }

        DoublyLinkedList tempNode1 = new DoublyLinkedList();
        DoublyLinkedList tempNode2 = new DoublyLinkedList();
        for(i=0 ; i<index1-1 ; tempNode1 = tempNode1.next, i++);
        for( ; i<index2+1 ; tempNode2 = tempNode2.next, i++);
        tempNode1.next = tempNode2;
        tempNode2.previous = tempNode1;
        return list;
    }

    public static boolean linearSearch(DoublyLinkedList list, int x){
        DoublyLinkedList tempNode = list;
        while (tempNode != null){
            if(tempNode.data == x)
                return true;
            tempNode = tempNode.next;
        }

        return false;
    }
}

public class DoublyLinkedListPracticeMethods {

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        DoublyLinkedList list = new DoublyLinkedList(0);
        int i, n, x;

        do {
            System.out.println("\n\nSelect any of the following functions :- ");
            System.out.println("1) Add Data ");
            System.out.println("2) Display data");
            System.out.println("3) Insert Value at new Position");
            System.out.println("4) Delete Node");
            System.out.println("5) Delete Segment ");
            System.out.println("6) Search For Element ");
            System.out.print("7) Exit");
            n = s.nextInt();

            if(n == 1){
                DoublyLinkedListMethods.getData(list);
            } else if(n == 2){
                DoublyLinkedListMethods.output(list);
            } else if(n == 3){
                list = DoublyLinkedListMethods.insertNode(list);
            } else if(n == 4){
                list = DoublyLinkedListMethods.deleteNode(list);
            } else if(n == 5){
                list = DoublyLinkedListMethods.deleteSegment(list);
            } else if(n == 6){
                System.out.print("Enter element : ");
                x = s.nextInt();
                System.out.println(DoublyLinkedListMethods.linearSearch(list, x));
            }
        }while (n != 7);
    }
}
