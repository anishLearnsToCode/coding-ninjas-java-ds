import java.util.Stack;
import java.util.Scanner;

public class CheckRedundantBrackets {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        String str = s.next();
        boolean ans = checkForRedundantBracket(str);
        System.out.println(ans);
    }

    public static boolean checkForRedundantBracket(String str) {
        int i;
        boolean flag = false;
        Stack<Character> stack = new Stack<>();

        for(i=0 ; i<str.length() ; i++){
            if(isBracket(str.charAt(i))){
                if(isOpenBracket(str.charAt(i))){
                    stack.push(str.charAt(i));
                }
                else{
                    stack.pop();
                    if(!flag)
                        return false;
                    flag = false;
                }
            }
            else
                flag = true;
        }
        return true;
    }

    public static boolean isBracket(char ch) {
        return ((ch == '(') || (ch == ')'));
    }

    public static boolean isOpenBracket(char ch){
        return ch == '(';
    }
}
