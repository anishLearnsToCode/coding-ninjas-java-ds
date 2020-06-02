import java.util.Map;
import java.util.Scanner;
import java.util.HashMap;
import java.util.Set;

public class File1 {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        String str;

        str = s.nextLine();
        HashMap<String, Integer> map = createHashMap(str);
//        printHashMap(map);
        System.out.println(getFrequency(map));
    }

    public static HashMap<String, Integer> createHashMap(String str) {
        int i, j;
        HashMap<String, Integer> map = new HashMap<>();
        String words[] = createStringArr(str);

//        for(i=0 ; i<words.length ; System.out.println(words[i]), i++);

        for(i=0 ; i<words.length ; i++) {
            if(!map.containsKey(words[i]))
                map.put(words[i], 1);
            else {
                int newValue = map.get(words[i]) + 1;
                map.put(words[i], newValue);
            }
        }
        return map;
    }

    public static String getFrequency(HashMap<String, Integer> map) {
        int frequency=0;
        String word="";

        for (Map.Entry<String, Integer> currentEntry : map.entrySet()) {
            if(currentEntry.getValue() > frequency){
                frequency = currentEntry.getValue();
                word = currentEntry.getKey();
            }
        }

        return word;
    }

    public static void printHashMap(HashMap<String, Integer> map) {
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }
    }

    public static String[] createStringArr(String str){
        int i, j, k;
        String[] ans = new String[0];

        for(i=1, j=0 ; i<str.length() ; i++){
            if(i == str.length() - 1){
                String smallAns[] = new String[ans.length + 1];
                for(k=0 ; k<ans.length ; smallAns[k] = ans[k], k++);
                smallAns[smallAns.length - 1] = str.substring(j);
                ans = smallAns;
                break;
            }

            if(str.charAt(i) == ' '){
                String[] smallAns = new String[ans.length + 1];
                for(k=0 ; k<ans.length ; smallAns[k] = ans[k], k++);
                smallAns[smallAns.length -1 ] = str.substring(j, i);
                ans = smallAns;
                j = i + 1;
            }
        }
        return ans;
    }
}
