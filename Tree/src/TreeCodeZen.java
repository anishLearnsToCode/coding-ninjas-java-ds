import java.util.Scanner;
import java.util.ArrayList;

class TreeNode<T> {
    T data;
    ArrayList<TreeNode<T>> children;

    TreeNode(T data) {
        this.data = data;
        children = new ArrayList<>();
    }
    TreeNode() {}
}


class Methods {
    static Scanner s = new Scanner(System.in);

    public static TreeNode<Integer> createTree () {
        System.out.print("Enter node : ");
        int node = s.nextInt();

        TreeNode<Integer> root = new TreeNode<>(node);

        System.out.print("Enter no. of children : ");
        int child = s.nextInt();

        for(int i=0 ; i<child ; i++){
            root.children.add(createTree());
        }

        return root;
    }

    public static <T>void printTree (TreeNode<T> root) {
        System.out.print(root.data + " : ");
        for(int i=0 ; i<root.children.size() ; System.out.print(root.children.get(i).data + " "), i++);
        System.out.print(root.children.size() == 0 ? "Null" : "");

        System.out.println("");
        for(int i=0 ; i<root.children.size() ; printTree(root.children.get(i)), i++);
    }

    public static TreeNode<Integer> maxNode (TreeNode<Integer> root) {
        int i, j, childSum, smallAns, currentAns;
        TreeNode<Integer> smallNode, currentNode = root;

        for(i=0, childSum=0 ; i<currentNode.children.size() ;childSum += (int)currentNode.children.get(i).data, i++);
        currentAns = root.data + childSum;

        for(i=0 ; i<root.children.size() ; i++){
            smallNode = maxNode(root.children.get(i));
            for(j=0, childSum=0 ; j<smallNode.children.size() ; childSum += smallNode.children.get(j).data, j++);
            smallAns = smallNode.data + childSum;

            currentNode = currentAns > smallAns ? currentNode : smallNode ;
        }

        return currentNode;
    }

    public static boolean structurallyIdentical (TreeNode<Integer> root1, TreeNode<Integer> root2) {
        if(root1.children.size() != root2.children.size())
            return false;

        for (int i=0 ; i<root1.children.size() ; i++){
            if((int)root1.children.get(i).data != (int)root2.children.get(i).data)
                return false;
        }

        for(int i=0 ; i<root1.children.size() ; structurallyIdentical(root1.children.get(i), root2.children.get(i)), i++);

        return true;
    }

    public static TreeNode<Integer> nextLargestNode(TreeNode<Integer> root, int n) {
        TreeNode<Integer> smallNode , currentNode = root;

        for(int i=0 ; i<root.children.size() ; i++) {

            smallNode = nextLargestNode(root.children.get(i), n);

            if(smallNode == null) currentNode = root;
            else if((int)currentNode.data > n){
                if((int)smallNode.data > n && (int)smallNode.data < (int)currentNode.data) currentNode = smallNode;
            }
            else if((int)smallNode.data > n ) currentNode = smallNode;
        }

        return (int)currentNode.data > n ? currentNode : null;
    }

    public static TreeProperties secondLargestElement(TreeNode<Integer> root) {
        if(root.children.size()==0){
            return new TreeProperties(root, null);
        }

        TreeProperties smallAns;
        TreeProperties finalAns = new TreeProperties();

        int secondLargestElement;
        for(int i=0 ; i<root.children.size() ; i++){
            smallAns = secondLargestElement(root.children.get(i));

            finalAns.largest = (int)smallAns.largest.data > (int)root.data ? smallAns.largest : root;
            secondLargestElement = (int)smallAns.largest.data > (int)root.data ? root.data : smallAns.largest.data;

            if(smallAns.secondLargest == null) finalAns.secondLargest = new TreeNode<Integer>(secondLargestElement);
            else finalAns.secondLargest = (int)smallAns.secondLargest.data > secondLargestElement ? smallAns.secondLargest : new TreeNode<Integer>(secondLargestElement);
        }

        return finalAns;
    }

    public static TreeNode<Integer> largestNode(TreeNode<Integer> root) {
        TreeNode<Integer> smallAns;
        TreeNode<Integer> Ans = root;

        for(int i=0 ; i<root.children.size() ; i++){
            smallAns = largestNode(root.children.get(i));
            Ans = (int)smallAns.data > (int)Ans.data ? smallAns : Ans;
        }

        return Ans;
    }

    public static TreeNode<Integer> secondLargest(TreeNode<Integer> root, int n) {
        TreeNode<Integer> smallAns, Ans = root;

        for(int i=0; i<root.children.size() ; i++) {
            smallAns = secondLargest(root.children.get(i), n);

            if(smallAns == null){
                continue;
            }

            if((int)Ans.data > (int)smallAns.data){
                if(!((int)Ans.data < n)) Ans = smallAns;
            }
            else Ans = smallAns;

        }

        return (int)Ans.data < n ? Ans : null ;
    }

    public static int numberOfNodes(TreeNode<Integer> root) {
        int i, nodes;
        for(i=0, nodes=0 ; i<root.children.size() ; i++){
            nodes += numberOfNodes(root.children.get(i));
        }

        return 1 + nodes;
    }
    public static <T>int leafNodes(TreeNode<T> root) {
        if(root.children.size()==0)
            return 1;

        int leafN = 0;
        for(int i=0 ; i<root.children.size() ; i++)
            leafN += leafNodes(root.children.get(i));

        return leafN;
    }

    public static void level(TreeNode<Integer> root, int l) {
        root.data = l;
        for(int i=0 ; i<root.children.size() ; i++) {
            level(root.children.get(i), l+1);
        }
    }
}


class TreeProperties {
    TreeNode<Integer> largest ;
    TreeNode<Integer> secondLargest;

    TreeProperties(TreeNode<Integer> largest, TreeNode<Integer> secondLargest){
        this.largest = largest;
        this.secondLargest = secondLargest;
    }
    TreeProperties() {
        this.largest = null;
        this.secondLargest = null;
    }
}


public class TreeCodeZen {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        TreeNode<Integer> root = Methods.createTree();
        Methods.printTree(root);

//        int n = s.nextInt();
//        TreeNode<Integer> ans = Methods.nextLargestNode(root, n);
//        System.out.println(ans.data);

        TreeNode<Integer> ans = Methods.secondLargest(root, (int)Methods.largestNode(root).data);
        System.out.println(ans.data);
    }
}
