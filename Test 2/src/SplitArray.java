import java.util.Scanner;
public class SplitArray {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int n;
        boolean ans;

        System.out.print("size : ");
        n =s.nextInt();

        int arr[] = new int[n];
        arrInput(arr);

        ans = splitArray(arr);
        System.out.println(ans);
    }

    public static void arrInput(int arr[]){
        Scanner s = new Scanner(System.in);
        for(int i=0 ; i<arr.length ; arr[i] = s.nextInt(), i++);
    }

    public static boolean splitArray(int arr[]){
        int sum , i, j, partialSum;
        boolean ans = true;

        arr = mergeSort(arr);
        for(i=0, sum=0 ; i<arr.length ; sum += arr[i], i++);

        if(sum % 2 == 1)
            return false;

        for(i=0, partialSum=0 ; i<arr.length ; i++){
            partialSum += arr[i];
            if(partialSum == sum/2){
                ans = false;
                break;
            }
        }

        if(ans)
            return false;

        //check wheteher the first roup has multiples of 5 or 3 r both
        boolean five=false, three=false;
        for(j=0 ; j<=i ; j++){
            if(arr[i] % 5 == 0)
                five = true;
            if(arr[i] % 3 == 0)
                three = true;

            if((five) && (three)){
                return false;
            }
        }

        return true;
    }

    public static int[] mergeSort(int arr[]){
        if(arr.length == 1 || arr.length == 0){
            return arr;
        }

        int smallArr1[] = new int[arr.length/2];
        int smallArr2[] = new int[arr.length - smallArr1.length];

        int i;
        for(i=0 ; i<smallArr1.length ; smallArr1[i] = arr[i], i++);
        for( ; i<arr.length ; smallArr2[i - smallArr1.length] = arr[i], i++);

        int smallAns1[] = mergeSort(smallArr1);
        int smallAns2[] = mergeSort(smallArr2);

        return merge(smallAns1, smallAns2);
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
                for(t=0 ; t< arr1.length - i ; ans[k++] = arr1[t+i], t++);
                break;
            }

            if(arr1[i] <= arr2[j]){
                ans[k++] = arr1[i];
                i++;
            }
            else{
                ans[k++] = arr2[j];
                j++;
            }
        }

        return ans;
    }
}
