import java.util.Scanner;
public class MinimumLenghtWord {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        String str, ans;

        str = s.nextLine();
        ans = minLenWord(str);
        System.out.println(ans);
    }

    public static String minLenWord(String str){
        int i, len, j;
        String ans=str;

        for(i=1, j=0 ; i<str.length() ; i++){
            if(i == str.length() -1){
                len = i-j+1;
                if(len < ans.length()){
                    ans = str.substring(j);
                }
                break;
            }

            else if(str.charAt(i) == ' '){
                len = i -j;
                if(len < ans.length()){
                    ans = str.substring(j, i);
                }
                j=i+1;
            }
        }

        return ans;
    }
}
