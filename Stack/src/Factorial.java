import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

public class Factorial {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        System.out.print("Enter number : ");
        int n = s.nextInt();

        factorail(n);
    }

    public static void factorail(int n) {
        long ans;
        int i;
        for (i = 1, ans = 1; i <= n; ans *= i, i++) ;

        if(n <= 20) {
            System.out.println(ans);
        }

        else{
            ArrayList<Integer> list = Int64ToArr(ans);
        }
    }

    public static ArrayList<Integer> Int64ToArr(long n) {
        ArrayList<Integer> list = new ArrayList<>();
        return list;
    }
}
