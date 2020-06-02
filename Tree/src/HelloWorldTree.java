import sun.reflect.generics.tree.Tree;

import java.util.ArrayList;
import java.util.Scanner;

class basicTree<T> {
    T data;
    ArrayList<basicTree<T>> children;

    basicTree(T data){
        this.data = data;
        children = new ArrayList<basicTree<T>>();
    }
}

class Node <T> {
    T data;
    Node<T> next;
    basicTree<T> root;

    Node(T data){ this.data = data; }
    Node(basicTree<T> root) {this.root = root; }
    Node() {}
}

class QueueMethods {
    static  Scanner s = new Scanner(System.in);

    public static <T>Node push(basicTree<T> root, Node<T> head){
        Node<T> smallNode = new Node<T>(root);
        Node temp = head;
        while (temp.next != null) { temp = temp.next; }
        temp.next = smallNode;
        return head;
    }

    public static <T>Node pop(Node<T> head){
        return head.next;
    }

    public static <T>basicTree peek(Node head) {return head.root; }

    public static void printQueue(Node head){
        Node temp = head;
        while (temp != null) {
            System.out.print(temp.root.data + " ");
        }
        System.out.println("");
    }

    public static boolean isEmpty(Node head) {return head == null; }
}

class TreeMethodsBasic{
    static Scanner s = new Scanner(System.in);

    public static basicTree<Integer> getTree(){
        System.out.print("Enter data : ");
        int data = s.nextInt();

        basicTree<Integer> root = new basicTree<>(data);
        System.out.print("Enter no. of children : ");
        int child = s.nextInt();

        for(int i=0 ; i<child ; i++){
            root.children.add(getTree());
        }

        return root;
    }

    public static <T>void printTree(basicTree<T> root){
        System.out.println("Head : " + root.data);
        //children
        for(int i=0 ; i<root.children.size() ; i++){
            System.out.print(root.children.get(i).data + " ");
        }
        System.out.println("");
        for(int i=0 ; i<root.children.size() ; i++){
            printTree(root.children.get(i));
        }
    }

    public static <T>int numberOfNodes (basicTree<T> root){
        int i, count;
        for(i=0, count=0 ; i<root.children.size() ; i++){
            count += numberOfNodes(root.children.get(i));
        }

        return 1 + count;
    }

    public static <T>int numberOfBranches(basicTree<T> root){
        return root.children.size();
    }

    public static int sumOfNodes (basicTree<Integer> root){
        int i, count;
        for(i=0, count=0 ; i<root.children.size() ; count += sumOfNodes(root.children.get(i)), i++);
        return count + root.data;
    }

    public static int largestNode(basicTree<Integer> root, int ans){
        int i;
        for(i=0 ; i<root.children.size() ; i++){
            ans = largestNode(root.children.get(i), ans);
        }

        return Math.max(ans, root.data);
    }

    public static int numberOfNodesGreaterThan(basicTree<Integer> root, int x){
        int i, count;

        for(i=0, count=0 ; i<root.children.size() ; i++){
            count += numberOfNodesGreaterThan(root.children.get(i), x);
        }

        return (root.data > x ? 1 : 0) + count;
    }

    public static <T>int hieghtOfTree(basicTree<T> root, int currentHieght, int maxHieght){
        int i, count;

        for(i=0 ; i<root.children.size() ; i++){
            maxHieght = hieghtOfTree(root.children.get(i), currentHieght+1, maxHieght);
        }
        return Math.max(currentHieght, maxHieght);
    }

    public static <T>Node makeQueue (basicTree<T> root){
        return new Node(12);
    }

    public static boolean containsX (basicTree<Integer> root, int x){
        boolean ans = (int)root.data == x;

        if(ans) return ans;

        for(int i=0 ; i<root.children.size() ; i++){
            ans = ans && containsX(root.children.get(i), x);
        }

        return ans;
    }
}

public class HelloWorldTree {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        basicTree root = TreeMethodsBasic.getTree();
        int ans, n, x;

        do {
            System.out.println("1) Print Tree");
            System.out.println("2) Sum Of Nodes");
            System.out.println("3) Largest Node");
            System.out.println("4) Number Of Nodes Greater Than x ");
            System.out.println("5) Hieght Of Tree");
            System.out.println("6) Print Queue ");
            System.out.println("7) Contains X");
            System.out.print("7) Exit : ");
            n = s.nextInt();

            switch (n){
                case 1 :
                    TreeMethodsBasic.printTree(root);
                    break;
                case 2 :
                    ans = TreeMethodsBasic.sumOfNodes(root);
                    System.out.println(ans);
                    break;
                case 3 :
                    ans = TreeMethodsBasic.largestNode(root, Integer.MIN_VALUE);
                    System.out.println(ans);
                    break;
                case 4 :
                    System.out.print("Enter x : ");
                    x = s.nextInt();
                    ans = TreeMethodsBasic.numberOfNodesGreaterThan(root, x);
                    System.out.println(ans);
                    break;
                case 5 :
                    ans = TreeMethodsBasic.hieghtOfTree(root, 1, 0);
                    System.out.println(ans);
                    break;
                case 6 :
                    Node head = TreeMethodsBasic.makeQueue(root);
                    QueueMethods.printQueue(head);
                    break;
                case 7 :
                    System.out.print("Enter x : ");
                     x = s.nextInt();
                    System.out.println(TreeMethodsBasic.containsX(root, x));
                    break;
                default :
                    System.out.println("Invalid input, please enter again");
            }
        } while (n != 7);
    }
}
