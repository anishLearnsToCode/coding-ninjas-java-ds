import kotlin.reflect.jvm.internal.impl.resolve.OverridingUtil;
import sun.awt.image.ImageWatched;

import java.util.Scanner;

class LinkedListNode <T> {
    T data;
    LinkedListNode<T> next;

    LinkedListNode (T data){
        this.data = data;
    }
}

class stack {
    public static void output(LinkedListNode head){
        LinkedListNode temp = head;
        while (temp != null){
            System.out.print(temp.data + " ");
            temp = temp.next;
        }
        System.out.println("");
    }

    public static int len (LinkedListNode<Integer> head){
        LinkedListNode temp = head;
        int i=0;
        while(temp != null){
            i++;
            temp = temp.next;
        }
        return i;
    }

    public static void printNode(LinkedListNode head, int i){
        LinkedListNode temp = head;
        int k=0;

        for(k=0 ; k<i ; k++){

            if(temp.next != null)
                temp = temp.next;
        }
        System.out.println(temp.data);
    }

    public static LinkedListNode<Integer> insert(LinkedListNode<Integer> head, int data, int position){
        int length = len(head);

        if(position > length){
            return head;
        }

        LinkedListNode temp = head;
        LinkedListNode<Integer> node = new LinkedListNode<>(data);

        if(position == 0){
            node.next = head;
            return node;
        }

        for(int i= 0 ; i<position-1 ; temp = temp.next, i++);

        node.next = temp.next;
        temp.next = node;
        return head;
    }


    public static LinkedListNode<Integer> removeNode(LinkedListNode<Integer> head, int position){
        int len = len(head);

        if(position > len-1){
            return head;
        }

        if(position == 0){
            return head.next;
        }

        LinkedListNode temp = head;
        for(int i=0 ; i<position-1 ; temp = temp.next, i++);
        temp.next = temp.next.next;

        return head;
    }

    public static LinkedListNode<Integer> removeDuplicates (LinkedListNode<Integer> head){
        LinkedListNode temp1 = head, temp2 = head.next;

        while (temp2 != null){
            if(temp1.data == temp2.data){
                temp1.next = temp2.next;
                temp2 = temp1.next;
            } else{
                temp1 = temp1.next;
                temp2 = temp2.next;
            }
        }

        return head;
    }

    public static LinkedListNode newLL(LinkedListNode head){
        LinkedListNode temp = head;
        LinkedListNode start = new LinkedListNode<>(0);
        LinkedListNode temp2 = start;
        while (temp != null){
            LinkedListNode node = new LinkedListNode<>(temp.data);
            temp2.next = node;
            temp2 = node;
            temp = temp.next;
        }

        return start.next;
    }

    public static boolean isPalindrome(LinkedListNode<Integer> head){
        LinkedListNode head2;
        head2 = newLL(head);
        head2 = reverse(head2);
        output(head);
        output(head2);
        LinkedListNode temp1 = head, temp2 = head2;

        while (temp1 != null){
            if(temp1.data != temp2.data){
                return false;
            }

            temp1 = temp1.next;
            temp2 = temp2.next;
        }

        return true;
    }

    public static LinkedListNode reverse(LinkedListNode head){
        LinkedListNode tempNode, currentNode, flag;

        if(head.next == null){
            return head;
        }

        for(tempNode = head, currentNode = head.next, tempNode.next = null ; currentNode.next!=null ; ){
            flag = currentNode.next;
            currentNode.next = tempNode;
            tempNode = currentNode;
            currentNode = flag;
        }currentNode.next = tempNode;

        return currentNode;
    }

    public static LinkedListNode<Integer> getData(LinkedListNode<Integer> head){
        Scanner s = new Scanner(System.in);
        System.out.print("Enter the no. of elements : ");
        int n = s.nextInt();

        while (n-- > 0){
            System.out.print("Enter data : ");
            int data = s. nextInt();
            addData(head, data);
        }

        return head.next;
    }

    public static void addData(LinkedListNode<Integer> head, int data){
        LinkedListNode<Integer> node = new LinkedListNode<>(data);
        LinkedListNode temp = head;
        while (temp.next != null){
            temp = temp.next;
        }
        temp.next = node;
    }

    public static int printMiddel(LinkedListNode<Integer> head){
        int length = (len(head) - 1)/2;
        LinkedListNode temp = head;
        for(int i=0 ; i<length ; i++){
            temp = temp.next;
        }
        return (int)(temp.data);
    }

    public static LinkedListNode<Integer> swap_nodes (LinkedListNode<Integer> head, int index1, int index2){
        LinkedListNode temp1 , temp2;
        LinkedListNode<Integer> dummyNode = new LinkedListNode<>(0);
        dummyNode.next = head;
        temp1 = dummyNode;
        temp2 = dummyNode;
        int i;

        for(i=0 ; i<index1 ; temp1 = temp1.next, i++);
        for(i=0 ; i<index2 ; temp2 = temp2.next, i++);

        LinkedListNode<Integer> Node1 = new LinkedListNode<>((int)temp1.next.data);
        LinkedListNode<Integer> Node2 = new LinkedListNode<>((int)temp2.next.data);

        Node1.next = temp2.next.next;
        temp2.next = Node1;
        Node2.next = temp1.next.next;
        temp1.next = Node2;

        System.out.println(Node1.data);
        System.out.println(Node2.data);

        if(index1 == 0){
            head.data = Node2.data;
        }

        return dummyNode.next;
    }

    public static int searchNode (LinkedListNode<Integer> head, int n){
        LinkedListNode temp = head;
        int i= 0;

        while (temp != null){
            if((int)temp.data == n){
                return i;
            }
            i++;
            temp = temp.next;
        }
        return -1;
    }

    public static LinkedListNode<Integer> NthNodeFromLast (LinkedListNode<Integer> head, int n){
        head = reverse(head);
        output(head);
        LinkedListNode temp = head;

        for(int i=0 ; i<n ; i++){
            if(temp.next != null)
                temp = temp.next;
            else
                return null;
        }

        return temp;
    }

    public static LinkedListNode<Integer> mergeSort(LinkedListNode<Integer> head ){
        if(head.next == null){
            return head;
        }

        int i;
        int length = len(head);
        LinkedListNode t1, t2;

        for(i=0, t1=head ; i<length/2-1 ; i++){
            t1=t1.next;
        }t2 = t1.next;
        t1.next=null;

        LinkedListNode head1 = mergeSort(head);
        LinkedListNode head2 = mergeSort(t2);

        return merge(head1, head2);
    }

    public static LinkedListNode<Integer> merge(LinkedListNode<Integer> head1, LinkedListNode<Integer> head2){
        LinkedListNode traversal1, traversal2, ansTempNode;
        LinkedListNode<Integer> ans = new LinkedListNode<>(0);

        for(traversal1 = head1, traversal2 = head2, ansTempNode = ans ; traversal1!=null && traversal2!=null ; ){
            if( (int)traversal2.data <= (int)traversal1.data){
                LinkedListNode<Integer> node = new LinkedListNode<Integer>( (int) traversal2.data);
                traversal2 = traversal2.next;
                ansTempNode.next = node;
                ansTempNode = node;
            } else{
                LinkedListNode<Integer> node = new LinkedListNode<Integer>( (int) traversal1.data);
                traversal1 = traversal1.next;
                ansTempNode.next = node;
                ansTempNode = node;
            }

            if(traversal1 == null){
                ansTempNode.next = traversal2;
                break;
            }else if(traversal2 == null){
                ansTempNode.next = traversal1;
                break;
            }
        }

        return ans.next;
    }

    public static LinkedListNode<Integer> append(LinkedListNode<Integer> head, int n){
        if(n == 0)
            return head;

        LinkedListNode head2 = newLL(head);
        head2 = reverse(head2);
        LinkedListNode temp = head2;

        for(int i=0 ; i<n-1 ; temp = temp.next, i++);
        LinkedListNode subList = temp.next;
        temp.next = null;

        head2 = reverse(head2);
        subList = reverse(subList);

        temp = head2;
        while (temp.next != null){
            temp = temp.next;
        }
        temp.next = subList;

        return head2;
    }

    public static LinkedListNode<Integer> kReverse (LinkedListNode<Integer> head, int k){
        int i, j, length = len(head);
        LinkedListNode subList, temp, ansTemp, nextList;
        LinkedListNode<Integer> ans = new LinkedListNode<>(0);

        for(i=0, subList=head, temp=head, ansTemp = ans ; i<(length)/k +parse(length%k) ; i++){
            for(j=0 ; j<k-1 ; j++){
                if(temp.next == null){
                    subList = newLL(subList);
                    subList = reverse(subList);
                    ansTemp.next = subList;
                    return ans.next;
                }
                temp = temp.next;
            }
            nextList = temp.next;

            temp.next = null;
            subList = reverse(subList);
            ansTemp.next = subList;

            subList = nextList;
            temp = subList;

            while (ansTemp.next != null){
                ansTemp = ansTemp.next;
            }
        }

        return ans.next;
    }

    public static int parse(int n){
        if(n > 0)
            return 1;

        return 0;
    }

    public static LinkedListNode<Integer> evenAfterOdd (LinkedListNode<Integer> head){
        LinkedListNode<Integer> ans = new LinkedListNode<>(0);
        LinkedListNode temp = head, ansNode = ans;
        while (temp != null){

            if((int)temp.data % 2 == 1){
                LinkedListNode<Integer> node = new LinkedListNode<Integer>((int)temp.data);
                ansNode.next = node;
                ansNode = ansNode.next;
            }
            temp = temp.next;
        }

        temp = head;
        while (temp != null){
            if((int) temp.data % 2 == 0){
                LinkedListNode<Integer> node = new LinkedListNode<>((int) temp.data);
                ansNode.next = node;
                ansNode = ansNode.next;
            }
            temp = temp.next;
        }

        return ans.next;
    }

    public static LinkedListNode<Integer> skip (LinkedListNode<Integer> head, int retain, int delete){
        LinkedListNode temp = head, retainNode=head, deleteNode=head;
        LinkedListNode<Integer> ans = new LinkedListNode<>(0);
        ans.next = head;
        int i;

        if(retain == 0){
            return null;
        }

        while (temp!= null){
            //Retention
            for(i=0 ; i<retain-1 ; i++){
                if(retainNode.next == null){
                    return head;
                }

                retainNode = retainNode.next;
            }deleteNode = retainNode.next;

            //Deleteion
            for(i=0 ; i<delete ; i++){
                if(deleteNode.next == null){
                    retainNode.next = null;
                    return head;
                }
                deleteNode = deleteNode.next;
            }
            retainNode.next = deleteNode;
            retainNode = deleteNode;
            temp = retainNode;
        }

        return head;
    }

    public static LinkedListNode<Integer> changeList (LinkedListNode<Integer> head){
        LinkedListNode ans = new LinkedListNode<Integer>(0);
        LinkedListNode temp = ans;
        LinkedListNode tail = newLL(head);
        tail = reverse(tail);
        int length = len(head), i;

        for(i=0 ; i<length ; i++){
            if(i%2 == 0) {
                LinkedListNode value = new LinkedListNode<Integer>((int) head.data);
                temp.next = value;
                temp = temp.next;
                head = head.next;
            }

            else {
                LinkedListNode value2 = new LinkedListNode<Integer>((int) tail.data);
                temp.next = value2;
                temp = temp.next;
                tail = tail.next;
            }
        }

        return ans.next;
    }
}

public class GenericLinkedList {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        LinkedListNode<Integer> node1 = new LinkedListNode<>(0);
        node1 = stack.getData(node1);

        stack.output(node1);

        LinkedListNode ans = stack.changeList(node1);
        stack.output(ans);
    }
}
