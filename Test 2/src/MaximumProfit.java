import java.util.Scanner;
public class MaximumProfit {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int n, ans;

        System.out.print("Size : ");
        n = s.nextInt();

        int arr[] = new int[n];
        arrInput(arr);

        ans = maxProfit(arr);
        System.out.println(ans);
    }

    public static void arrInput(int arr[]){
        Scanner s = new Scanner(System.in);
        for(int i=0 ; i<arr.length ; arr[i] = s.nextInt(), i++);
    }

    public static int maxProfit(int arr[]){
        int profit, m, i;
        arr = mergeSort(arr);
        Output(arr);

        for(i=arr.length-1, profit=0 ; i>=0 ; i--){
            m = arr[i] * (arr.length-i);
            if(m > profit)
                profit = m;
        }

        return profit;
    }

    public static int[] mergeSort(int arr[]){
        if(arr.length == 1 || arr.length == 0){
            return arr;
        }

        int i;
        int smallArr1[] = new int[arr.length/2];
        int smallArr2[] = new int[arr.length - smallArr1.length];

        for(i=0 ; i<smallArr1.length ; smallArr1[i] = arr[i], i++);
        for( ; i<arr.length ; smallArr2[i- smallArr1.length] = arr[i], i++);

        int smallAns1[] = mergeSort(smallArr1);
        int smallAns2[] = mergeSort(smallArr2);

        return merge(smallAns1,smallAns2);
    }

    public static int[] merge(int arr1[], int arr2[]){
        int i, j, k, t;
        int ans[] = new int[arr1.length + arr2.length];

        for(i=0, j=0, k=0 ; i<=arr1.length && j<=arr2.length ; ){
            if(i == arr1.length){
                for(t=0 ; t<arr2.length - j ; ans[k++] = arr2[j+t], t++);
                break;
            }
            else if(j == arr2.length){
                for(t=0 ; t<arr1.length - i ; ans[k++] = arr1[i+t], t++);
                break;
            }

            if(arr1[i] <= arr2[j]){
                ans[k++] = arr1[i];
                i++;
            }else{
                ans[k++] = arr2[j];
                j++;
            }
        }

        return ans;
    }

    public static void Output(int arr[]){
        for(int i=0 ; i<arr.length ; System.out.print(arr[i] + " "), i++);
    }
}
