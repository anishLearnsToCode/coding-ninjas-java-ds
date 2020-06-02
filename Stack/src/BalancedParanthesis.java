import java.util.Scanner;

class stackBracket {
    char bracket;
    stackBracket next;

    stackBracket(char ch) {
        bracket = ch;
    }
}

class BracketStackMethods {
    public static stackBracket push(stackBracket head, char data){
        stackBracket node = new stackBracket(data);

        if(head.bracket == 'a') {return node; }

        node.next = head;
        return node;
    }

    public static stackBracket pop(stackBracket head){
        return head.next;
    }

    public static char peek(stackBracket head){
        stackBracket temp = head;
        return temp.bracket;
    }

    public static void output(stackBracket head){
        stackBracket temp = head;
        while (temp != null){
            System.out.print(temp.bracket + " ");
            temp = temp.next;
        }
        System.out.println("");
    }

    public static boolean isEmpty(stackBracket head) {return head == null; }
}

public class BalancedParanthesis {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        String str;

        stackBracket tower = new stackBracket('a');
        //for(int i=0 ; i<10 ; tower = BracketStackMethods.push(tower, (char)i), i++);
        //for(int i=0 ; i<10 ; System.out.println(BracketStackMethods.peek(tower)), tower = BracketStackMethods.pop(tower), i++);

        str = s.next();
        boolean ans = checkForBrackets(str);
        System.out.println(ans);
    }

    public static boolean checkForBrackets(String str){
        int i;
        stackBracket tower = new stackBracket('a');
        for(i=0 ; i<str.length() ; i++){
            if(isBracket(str.charAt(i))){
                if(isOpenBracket(str.charAt(i))){
                    tower = BracketStackMethods.push(tower, str.charAt(i));
                } else{
                    if(inverseOf(str.charAt(i), BracketStackMethods.peek(tower))) {
                        tower = BracketStackMethods.pop(tower);
                        if(BracketStackMethods.isEmpty(tower)) {tower = new stackBracket('a'); }
                    }
                    else {
                        return false;
                    }
                }
            }
        }
        if(!BracketStackMethods.isEmpty(tower) && tower.bracket != 'a')
            return false;

        return true;
    }

    public static boolean isBracket(char ch){
        return ((ch == '[')  || (ch == ']') || (ch == '{') || (ch == '}') || (ch =='(') || (ch == ')'));
    }

    public static boolean isOpenBracket(char ch){
        return ((ch == '(') || (ch == '[') || (ch == '{'));
    }

    public static boolean inverseOf(char ch1, char ch2){
        return ( ((ch1 == '(') && (ch2 == ')')) || ((ch1 == '[') && (ch2 == ']')) || ((ch1 == '{') && (ch2 == '}')) || ((ch1 == ')') && (ch2 == '(')) || ((ch1 == ']') && (ch2 == '[')) || ((ch1 == '}') && (ch2 == '{')) );
    }
}
