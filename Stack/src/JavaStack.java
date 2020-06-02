import java.util.Scanner;
import java.util.Stack;

public class JavaStack {
    public static void main(String[] args) {
        Stack<Integer> s1 = new Stack<>();
        createStack(s1);
        Stack<Integer> s2 = new Stack<>();
        reverseStack(s1, s2);
        display(s1);
        display(s2);
    }

    public static void reverseStack(Stack<Integer> s1, Stack<Integer> s2){
        int size = s1.size();
        for(int i=0 ; i<size ; i++) {
            s2.push(s1.pop());
        }

        for(int i=0 ; i<size ; i++){
            s1.add(0, s2.pop());
        }
    }

    public static void createStack(Stack<Integer> s1){
        Scanner s = new Scanner(System.in);
        int i, n;

        System.out.print("Elements : ");
        n = s.nextInt();

        for(i=0 ; i<n ; i++) { s1.push(s.nextInt()); }
    }

    public static <T> void display(Stack<T> s1) {
        while (!s1.isEmpty()){
            System.out.print(s1.pop() + " ");
        }
        System.out.println("");
    }
}
