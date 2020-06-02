import sun.reflect.generics.tree.Tree;

import java.util.Scanner;

class BinaryTree<T> {
    T data;
    BinaryTree<T> left;
    BinaryTree<T> right;

    BinaryTree(T data) {this.data = data; }
    BinaryTree() {}
}

class BinaryTreeMethods {
    static Scanner s = new Scanner(System.in);

    public static BinaryTree<Integer> generateTree (){
        System.out.print("Enter data : ");
        int data = s.nextInt();

        if(data == -1) { return null; }

        BinaryTree<Integer> root = new BinaryTree<>(data);

        root.left = generateTree();
        root.right = generateTree();

        return root;
    }

    public static <T>void printTree(BinaryTree<T> root){
        if(root == null) { return; }

        System.out.println(root.data + " : " + (root.left == null ? "null" : root.left.data) + " " + (root.right == null ?  "null" : root.right.data) );
        printTree(root.left);
        printTree(root.right);
    }

    public static <T>int hieghtTree(BinaryTree<T> root, int currentHieght, int maxHieght){
        if(root == null){
            return Math.max(currentHieght, maxHieght);
        }

        maxHieght = Math.max(hieghtTree(root.left, currentHieght+1, maxHieght), maxHieght);
        maxHieght = Math.max(hieghtTree(root.right, currentHieght+1, maxHieght), maxHieght);
        return maxHieght;
    }

    public static <T>int maxHeightTree(BinaryTree<T> root, int currentHeight){
        if(root == null){
            return currentHeight;
        }
        int maxHeight = 0;
        maxHeight = Math.max(maxHeightTree(root.left, currentHeight+1), maxHeight);
        maxHeight = Math.max(maxHeightTree(root.right, currentHeight+1), maxHeight);
        return maxHeight;
    }

    public static <T>TreeProperty diameterTree(BinaryTree<T> root) {
        if(root == null){
            TreeProperty ans = new TreeProperty(0, 0);
            return ans;
        }

        TreeProperty leftDiameter = diameterTree(root.left);
        TreeProperty rightDiameter= diameterTree(root.right);
        int height = leftDiameter.height + rightDiameter.height + 1;

        int diameter = Math.max(Math.max(leftDiameter.diameter, rightDiameter.diameter), height);
        TreeProperty ans = new TreeProperty(diameter, Math.max(leftDiameter.height, rightDiameter.height));

        return ans;
    }

    public static <T>void mirrorTree(BinaryTree<T> root) {
        if(root == null) {
            return;
        }

        BinaryTree<T> leftNode = root.left;
        root.left = root.right;
        root.right = leftNode;

        mirrorTree(root.left);
        mirrorTree(root.right);
    }

    public static BinaryTree<Integer> createTree(int inOrder[], int preOrder[], int index) {
        if(index == preOrder.length){
            return null;
        }

        BinaryTree<Integer> root = new BinaryTree<>(preOrder[index]);

        int i;
        for(i=0 ; i<inOrder.length ; i++){
            if(inOrder[i] == preOrder[index])
                break;
        }

        int smallArr1[] = new int[i];
        int smallArr2[] = new int[inOrder.length - smallArr1.length];

        for(i=0 ; i<smallArr1.length ; smallArr1[i] = inOrder[i], i++);
        for( ; i<inOrder.length ; smallArr2[i - smallArr1.length] = inOrder[i], i++);

        if(smallArr1.length != 0)
            root.left = createTree(smallArr1, preOrder, index+1);
        else
            root.left = null;

        if(smallArr2.length != 0)
            root.right = createTree(smallArr2, preOrder, index+1);
        else
            root.right = null;

        return root;
    }

    public static boolean isBST(BinaryTree<Integer> root, int min, int max) {
        if(root == null)
            return true;

        boolean left = false, right = false;

        if(root.left != null)
            left = ( (int)root.left.data > (int)root.data) ||((int)root.left.data < min );
        if(root.right != null)
            right = (int) root.right.data < (int) root.data || (int)root.right.data > max;

        return !(left || right) && isBST(root.left, Integer.MIN_VALUE, root.data) && isBST(root.right, root.data, Integer.MAX_VALUE);
    }

    public static <T>TreeProperty isBalanced (BinaryTree<T> root) {
        if(root == null){
            TreeProperty ans = new TreeProperty(true, 0);
            return ans;
        }

        TreeProperty left = isBalanced(root.left);
            left.nodes++;
        TreeProperty right = isBalanced(root.right);
            right.nodes++;

        if(left.isBalanced || right.isBalanced){
            return new TreeProperty(false, 0);
        }

        if(left.nodes != right.nodes){
            return new TreeProperty(false, 0);
        }

        return new TreeProperty(true, 0);
    }

    public static int maxValue (BinaryTree<Integer> root) {
        if(root.right == null)
            return root.data;

        return maxValue(root.right);
    }

    public static int minValue (BinaryTree<Integer> root) {
        if(root.left == null)
            return root.data;

        return minValue(root.left);
    }

    public static BinaryTree<Integer> createBST(int arr[]) {
        if(arr.length == 1){
            BinaryTree<Integer> ans = new BinaryTree<>(arr[0]);
            return ans;
        }
        else if(arr.length == 0){
            return null;
        }

        int i, j;
        BinaryTree<Integer> root = new BinaryTree<>(arr[arr.length/2]);

        int smallArr1[] = new int[(arr.length)/2 ];
        int smallArr2[] = new int[arr.length - smallArr1.length - 1];

        for(j=0 ; j<smallArr1.length ; smallArr1[j] = arr[j], j++);
        for(j++ ; j<arr.length ; smallArr2[j-smallArr1.length-1] = arr[j], j++);

        root.left = createBST(smallArr1);
        root.right = createBST(smallArr2);

        return root;
    }
}

class TreeProperty {
    public int diameter;
    public int height;
    public boolean isBalanced;
    public int nodes;

    TreeProperty(int diameter, int height) {
        this.diameter = diameter;
        this.height = height;
    }
    TreeProperty(boolean isBalanced, int nodes){
        this.isBalanced = isBalanced;
        this.nodes = nodes;
    }
}

public class BinarySearchTree_BST {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        BinaryTree<Integer> root = BinaryTreeMethods.generateTree();
        BinaryTreeMethods.printTree(root);

//        TreeProperty ans = BinaryTreeMethods.diameterTree(root);
//        System.out.println(ans.diameter);
//
//        BinaryTreeMethods.mirrorTree(root);
//        BinaryTreeMethods.printTree(root);

//        System.out.print("Enter size : ");
//        int size = s.nextInt();
//        int inOrder[] = new int[size];
//        int preOrder[] = new int[size];
//
//        System.out.println("Enter In-order");
//        for(int i=0 ; i<size ; inOrder[i] = s.nextInt(), i++);
//
//        System.out.println("Enter pre-order ");
//        for(int i=0 ; i<size ; preOrder[i] = s.nextInt(), i++);

        boolean ans = BinaryTreeMethods.isBST(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
        System.out.println(ans);

        System.out.println(BinaryTreeMethods.maxValue(root) + " " + BinaryTreeMethods.minValue(root));

        System.out.print("Enter size : ");
        int n = s.nextInt();
        int arr[] = new int[n];

        for(int i=0 ; i<n ; arr[i] = s.nextInt(), i++);

        BinaryTree<Integer> node = BinaryTreeMethods.createBST(arr);
        BinaryTreeMethods.printTree(node);
    }
}
