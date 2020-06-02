import java.util.ArrayList;
import java.util.Scanner;

public class HeapBasic {
    public static void main(String[] args) {

    }
}


class BinaryTree<T> {
    T data;
    int priority;
    BinaryTree<T> left;
    BinaryTree<T> right;

    BinaryTree(){
        left = null;
        right = null;
    }
    BinaryTree(T data, int priority){
        this.data = data;
        this.priority = priority;
        left = null;
        right = null;
    }
}


abstract class priorityQueue<T> {
    ArrayList<BinaryTree<T>> heap;
    final static private int MAX_SIZE = 50;

    priorityQueue(){
        heap = new ArrayList<>();
    }

    public static BinaryTree<T> get(){
        BinaryTree<T> node = heap.get(0);

    }
}