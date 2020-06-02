import java.util.Scanner;
public class StringsOfLenghtK {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int k;
        String str;
        String ans[];

        str = s.next();
        k = s.nextInt();
        ans = strOfLenK(str, k);
        for(int i=0 ; i<ans.length ; i++){
            System.out.println(ans[i]);
        }
    }

    public static String[] strOfLenK(String str, int k){
        if(k == 1){
            String ans[] = new String[str.length()];
            for(int i=0 ; i<ans.length ; ans[i] = str.charAt(i) + "", i++);
            return ans;
        }

        int i, j, t;
        String smallAns[] = strOfLenK(str, k-1);
        String ans[] = new String[smallAns.length * str.length()];

        for(i=0, t=0 ; i<smallAns.length ; i++){
            for(j=0 ; j<str.length() ; j++){
                ans[t++] = smallAns[i] + str.charAt(j);
            }
        }

        return ans;
    }
}
