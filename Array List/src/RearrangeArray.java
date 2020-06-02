import java.util.ArrayList;
import java.util.Scanner;

public class RearrangeArray {

    private static Scanner in = new Scanner(System.in);

    public static void main(String[] args) {
        int size = in.nextInt();
        int arr[] = new int[size];
        input(arr);
        rearrange(arr);
        print(arr);
    }

    private static void input(int arr[]){
        for(int index=0 ; index<arr.length ; index++){
            arr[index] = in.nextInt();
        }
    }

    private static void rearrange(int arr[]){
        ArrayList<Integer> positive = new ArrayList<>();
        ArrayList<Integer> negative = new ArrayList<>();
        for(int index=0 ; index<arr.length ; index++){
            if(arr[index] < 0)
                negative.add(arr[index]);
            else positive.add(arr[index]);
        }

        for(int pSlider=0, nSlider=0, index=0; pSlider<=positive.size() && nSlider<=negative.size() && index<arr.length ;
                index++){

            if(nSlider == negative.size()){
                while (pSlider < positive.size()){
                    arr[index++] = positive.get(pSlider);
                    pSlider++;
                }
                break;
            } else if(pSlider == positive.size()){
                while (nSlider < negative.size()){
                    arr[index++] = negative.get(nSlider);
                    nSlider++;
                }
                break;
            }

            if((index+2) % 2 == 0){
                arr[index] = negative.get(nSlider);
                nSlider++;
            } else {
                arr[index] = positive.get(pSlider);
                pSlider++;
            }
        }
    }

    private static void print(int arr[]){
        for(int index=0 ; index<arr.length ; index++){
            System.out.print(arr[index] + " ");
        }
    }
}
