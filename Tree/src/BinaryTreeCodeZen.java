import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

class BinaryTreeNode<T> {
    T data;
    BinaryTreeNode<T> left;
    BinaryTreeNode<T> right;

    BinaryTreeNode(T data ) {
        this.data = data;
    }
}

class BTMethods {
    static Scanner s = new Scanner(System.in);
    public static BinaryTreeNode<Integer> createTree() {
        System.out.print("Enter data : ");
        int data = s.nextInt();

        if(data == -1) {return null;}
        BinaryTreeNode<Integer> root = new BinaryTreeNode<>(data);

        root.left = createTree();
        root.right = createTree();

        return root;
    }

    public static <T>void printTree(BinaryTreeNode<T> root){
        if(root == null) { return; }

        System.out.println(root.data + " : " + (root.left == null ? "null" : root.left.data) + " " + (root.right == null ?  "null" : root.right.data) );
        printTree(root.left);
        printTree(root.right);
    }

    public static void printTreePreOrder(BinaryTreeNode<Integer> root) {
        if(root == null){return;}
        System.out.print(root.data + " ");
        printTreePreOrder(root.left);
        printTreePreOrder(root.right);
    }

    public static <T>void printTreeInOrder(BinaryTreeNode<T> root) {
        if(root == null) {return;}
        printTreeInOrder(root.left);
        System.out.print(root.data + " ");
        printTreeInOrder(root.right);
    }

    public static <T>void printTreePostOrder(BinaryTreeNode<T> root) {
        if(root == null) {return;}
        printTreePostOrder(root.left);
        printTreePostOrder(root.right);
        System.out.print(root.data + " ");
    }

    public static int sumOfAllNodes(BinaryTreeNode<Integer> root) {
        if(root == null) {return 0;}
        return root.data + sumOfAllNodes(root.left) + sumOfAllNodes(root.right);
    }

    public static BinaryTreeNode<Integer> maximumData(BinaryTreeNode<Integer> root) {
        if(root == null) {return null; }

        BinaryTreeNode<Integer> left = maximumData(root.left);
        BinaryTreeNode<Integer> right =maximumData(root.right);

        if(left == null) left = new BinaryTreeNode<>(Integer.MIN_VALUE);
        if(right == null) right = new BinaryTreeNode<>(Integer.MIN_VALUE);

        if( (int)left.data > (int)right.data ) {
            if ((int) left.data > (int) root.data)
                return left;
            else
                return root;
        }
        else{
            if((int)right.data > (int)root.data)
                return right;
            else
                return root;
        }
    }

    public static <T>int leafNodes(BinaryTreeNode<T> root) {
        if(root == null)
            return 0;
        if(root.left == null && root.right == null)
            return 1;

        return leafNodes(root.left) + leafNodes(root.right);
    }

    public static int nodesGreaterThanX(BinaryTreeNode<Integer> root, int x){
        if(root == null)
            return 0;

        return nodesGreaterThanX(root.left, x) + nodesGreaterThanX(root.right, x) + ((int)root.data > x ? 1 : 0);
    }

    public static boolean isNodePresent(BinaryTreeNode<Integer> root, int x) {
        if(root == null) {return false; }
        return isNodePresent(root.left, x) || isNodePresent(root.right, x) || (int)root.data == x ;
    }

    public static void mirror(BinaryTreeNode<Integer> root) {
        if(root == null)
            return;

        mirror(root.left);
        mirror(root.right);

        BinaryTreeNode<Integer> temp = root.left;
        root.left = root.right;
        root.right = temp;
    }

    public static TreeProperty diameterOfBT(BinaryTreeNode<Integer> root) {
        if(root == null)
            return new TreeProperty(0, 0);

        TreeProperty left = diameterOfBT(root.left);
        TreeProperty right = diameterOfBT(root.right);
        int maxPath = left.height + right.height + 1;

        int ansDiameter = Math.max(Math.max(left.diameter, right.diameter), maxPath);
        int newHeight = 1 + Math.max(left.height, right.height);

        return new TreeProperty(ansDiameter, newHeight);
    }

    public static <T>TreeProperty diameterTree(BinaryTreeNode<T> root) {
        if(root == null){
            TreeProperty ans = new TreeProperty(1, 0);
            return ans;
        }

        TreeProperty leftDiameter = diameterTree(root.left);
        TreeProperty rightDiameter= diameterTree(root.right);
        int height = leftDiameter.height + rightDiameter.height + 1;

        int diameter = Math.max(Math.max(leftDiameter.diameter, rightDiameter.diameter), height);
        TreeProperty ans = new TreeProperty(diameter, Math.max(leftDiameter.height, rightDiameter.height));

        return ans;
    }

    public static int sumOfNodes(BinaryTreeNode<Integer> root) {
        if(root == null)
            return 0;

        int left = sumOfNodes(root.left);
        int right = sumOfNodes(root.right);
        return root.data + left + right;
    }

    public static <T>TreeP isBalanced(BinaryTreeNode<T> root) {
        if(root == null) {
            return new TreeP(true, 0);
        }
        TreeP left = isBalanced(root.left);
        TreeP right = isBalanced(root.right);

        boolean newIsBalanced = left.isBalanced && right.isBalanced && Math.abs(left.nodes - right.nodes) <=1 ;
        int newNodes = 1 + Math.max(left.nodes, right.nodes);

        return new TreeP(newIsBalanced, newNodes);
    }

    public static LinkedList<BinaryTreeNode<Integer>> treeQueue(BinaryTreeNode<Integer> root) {
        LinkedList<BinaryTreeNode<Integer>> list = new LinkedList<>();
        LinkedList<BinaryTreeNode<Integer>> ans = new LinkedList<>();
        list.add(root);
        list.add(null);

        while (!list.isEmpty()) {
            BinaryTreeNode<Integer> smallNode = list.pop();
            ans.add(smallNode);
            if(smallNode == null && list.size() == 0) {
                break;
            }
            else if(smallNode == null) {
                System.out.println("");
                list.add(null);
                continue;
            }
            else
                System.out.print(smallNode.data + " ");

            if(smallNode.left != null)
                list.add(smallNode.left);
            if(smallNode.right != null)
                list.add(smallNode.right);
//            if(smallNode.left == null || smallNode.right == null)
//                list.add(null);
        }

        return ans;
    }

    public static LinkedList<BinaryTreeNode<Integer>> prepareQueue(BinaryTreeNode<Integer> root) {
        LinkedList<BinaryTreeNode<Integer>> ans = new LinkedList<>();
        LinkedList<BinaryTreeNode<Integer>> list = new LinkedList<>();
        list.add(root);
        list.add(null);

        while (!list.isEmpty()) {
            BinaryTreeNode<Integer> smallNode = list.pop();
            ans.add(smallNode);
            if(smallNode == null && list.size() == 0) {
                break;
            }
            else if(smallNode == null) {
                list.add(null);
                continue;
            }

            if(smallNode.left != null)
                list.add(smallNode.left);
            if(smallNode.right != null)
                list.add(smallNode.right);
        }

        return ans;
    }

    public static void printQueue(LinkedList<BinaryTreeNode<Integer>> list) {
        System.out.println("");
        while (!list.isEmpty()){
            BinaryTreeNode<Integer> smallNode = list.pop();
            System.out.print(smallNode == null ? "Null " : smallNode.data + " ");
        }
    }

    public static <T>void printLinkedList(LinkedList<T> list) {
        LinkedList<T> temp = list;
        System.out.println("");
        while (!list.isEmpty()){
            System.out.print(temp.pop() + " ");
        }
    }

    public static ArrayList<Node<BinaryTreeNode<Integer>>> LLForEachLevel(BinaryTreeNode<Integer> root) {

        LinkedList<BinaryTreeNode<Integer>> list = prepareQueue(root);

        ArrayList<Node<BinaryTreeNode<Integer>>> ans = new ArrayList<>();

        Node<BinaryTreeNode<Integer>> LL = new Node<>(new BinaryTreeNode<>(0));
        Node<BinaryTreeNode<Integer>> temp = LL;

        while (!list.isEmpty()) {
            BinaryTreeNode<Integer> smallNode = list.pop();
            if(smallNode == null) {
                ans.add(LL.next);
                LL = new Node<BinaryTreeNode<Integer>>(new BinaryTreeNode<Integer>(0));
                temp = LL;
            }
            else {
                Node<BinaryTreeNode<Integer>> small = new Node<>(smallNode);
                temp.next = small;
                temp = temp.next;
            }
        }

        return ans;
    }

    public static void printList(Node<BinaryTreeNode<Integer>> list) {
        Node<BinaryTreeNode<Integer>> head = list;
        System.out.println("");
        while (head != null){
            System.out.print(head.data.data + " ");
            head = head.next;
        }
    }

    public static BinaryTreeNode<Integer> removeLeafNodes (BinaryTreeNode<Integer> root) {
        if(root == null) {
            return null;
        }
        if(root.left == null && root.right == null){
            return null;
        }
        root.left = removeLeafNodes(root.left);
        root.right = removeLeafNodes(root.right);
        return root;
    }

    public static void printZigZag (BinaryTreeNode<Integer> root) {
        ArrayList<Node<BinaryTreeNode<Integer>>> list = LLForEachLevel(root);
        for(int i=0 ; i<list.size() ; i++){
            if(i%2 == 1)
                printList(reverse(list.get(i)));
            else
                printList(list.get(i));
        }
    }

    public static Node<BinaryTreeNode<Integer>> reverseLL(Node<BinaryTreeNode<Integer>> head) {
        int size = LLSize(head);
        if(size == 0){
            return null;
        }
        else if(size == 1){
            return head;
        }
        else if(size == 2){
            Node<BinaryTreeNode<Integer>> temp = head.next;
            head.next.next = head;
            head.next = null;
            return temp;
        }

        Node<BinaryTreeNode<Integer>> temp = head, current = head.next, postCurrent = current.next;
        while (postCurrent != null){
            current.next = temp;
            temp.next = null;
            temp = current;
            current = postCurrent;
            postCurrent = postCurrent.next;
        }current.next = temp;
        return current;
    }

    public static Node<BinaryTreeNode<Integer>> reverse(Node<BinaryTreeNode<Integer>> head){
        Node<BinaryTreeNode<Integer>> tempNode, currentNode, flag;

        if(head.next == null){
            return head;
        }

        for(tempNode = head, currentNode = head.next, tempNode.next = null ; currentNode.next!=null ; ){
            flag = currentNode.next;
            currentNode.next = tempNode;
            tempNode = currentNode;
            currentNode = flag;
        }currentNode.next = tempNode;

        return currentNode;
    }

    public static <T>int LLSize(Node<T> head) {
        Node temp = head;
        int count =0;
        while (temp != null){
            temp = temp.next;
            count++;
        }
        return count;
    }

    public static void nodesWithoutSibling(BinaryTreeNode<Integer> root) {
        if(root == null)
            return;

        if( (root.left == null && root.right != null) || (root.right == null && root.left != null) )
            System.out.print((root.left != null ? root.left.data : root.right.data) + " ");

        if(root.left != null) nodesWithoutSibling(root.left);
        if(root.right != null) nodesWithoutSibling(root.right);
    }

    public static BinaryTreeNode<Integer> constructTree(int preOrder[], int inOrder[]) {
//        System.out.println("");
//        printArr(preOrder);
//        System.out.print("  ");
//        printArr(inOrder);

        if(preOrder.length == 0){
            return null;
        }
        if(inOrder.length == 0){
            return null;
        }
        if(inOrder.length == 1){
            return new BinaryTreeNode<>(inOrder[0]);
        }

        BinaryTreeNode<Integer> root = new BinaryTreeNode<>(preOrder[0]);
        int index = getIndex(preOrder[0], inOrder);
//        System.out.print(" Index : " + index);

        if(index == -1){
            int newPreOrder[] = new int[preOrder.length-1];
            for(int i=0 ; i<newPreOrder.length ; newPreOrder[i] = preOrder[i+1], i++);
            return constructTree(newPreOrder, inOrder);
        }

        int i;
        int newPreOrder[] = new int[preOrder.length-1];
        for(i=0 ; i<preOrder.length-1 ; newPreOrder[i] = preOrder[i+1], i++);

        if(index > 0){
            int inOrder1[] = new int[index];
            for(i=0 ; i<index ; inOrder1[i] = inOrder[i], i++);
            root.left = constructTree(newPreOrder, inOrder1);
        }
        else root.left = null;

        if(inOrder.length - index - 1 > 0){
            int inOrder2[] = new int[inOrder.length - index -1];
            for(i=index+1 ; i<inOrder.length ; inOrder2[i - index -1] = inOrder[i], i++);
            root.right = constructTree(newPreOrder, inOrder2);
        }
        else root.right = null;

        return root;
    }

    public static BinaryTreeNode<Integer> treeFromPostOrder_Inorder(int postOrder[], int inOrder[]) {
        System.out.println("");
        printArr(postOrder);
        System.out.print("  ");
        printArr(inOrder);

        if(postOrder.length == 0 || inOrder.length == 0){
            return null;
        }
        if(inOrder.length == 1){
            return new BinaryTreeNode<>(inOrder[0]);
        }

        int index = getIndex(postOrder[postOrder.length - 1], inOrder);
        System.out.print(" " + index);

        int newPostOrder[] = new int[postOrder.length-1];
        for(int i=0 ; i<newPostOrder.length ; newPostOrder[i] = postOrder[i], i++);
        if(index == -1){
            return treeFromPostOrder_Inorder(newPostOrder, inOrder);
        }

        BinaryTreeNode<Integer> root = new BinaryTreeNode<>(postOrder[postOrder.length-1]);

        int i;
        if(index > 0){
            int newInOrder[] = new int[index];
            for(i=0 ; i<index ; newInOrder[i] = inOrder[i], i++);
            root.left = treeFromPostOrder_Inorder(newPostOrder, newInOrder);
        }
        else root.left = null;

        if(inOrder.length - index - 1 > 0){
            int newInOrder[] = new int[inOrder.length - index - 1];
            for(i=index+1 ; i<inOrder.length ; newInOrder[i-index-1] = inOrder[i], i++);
            root.right = treeFromPostOrder_Inorder(newPostOrder, newInOrder);
        }
        else root.right = null;

        return root;
    }

    public static int getIndex(int element, int arr[]) {
        int i;
        boolean flag = false;
        for(i = 0 ; i<arr.length ; i++){
            if(element ==  arr[i]) {
                flag = true;
                break;
            }
        }
        return flag ? i : -1;
    }
    public static void printArr(int arr[]) {
        for(int i=0 ; i<arr.length ; System.out.print(arr[i]), i++);
    }

    public static void printPairSum(BinaryTreeNode<Integer> root, int sum) {
        if(root == null) {
            return;
        }

        if((root.data + (root.left == null ? sum + root.data + 1 : root.left.data)) == sum)
            System.out.println(root.data < root.left.data ? root.data + " " + root.left.data : root.left.data + " " + root.data);
        if((root.data + (root.right == null ? sum + root.data + 1 : root.right.data)) == sum)
            System.out.println(root.data < root.right.data ? root.data + " " + root.right.data : root.right.data + " " + root.data);

        printPairSum(root.left, sum);
        printPairSum(root.right, sum);
    }

    public static int lowestCommonAncestor_BT(BinaryTreeNode<Integer> root, int Node1, int Node2) {
        LinkedList<BinaryTreeNode<Integer>> list = prepareQueue(root);
        LinkedList<Integer> Ancestor1 = null, Ancestor2=null;
        int level1=0, level2=0, rootNode1=0, rootNode2=0;
        boolean Node1Check = false, Node2Check = false;

        //For Node1
        while (!list.isEmpty()){
            BinaryTreeNode<Integer> smallNode = list.pop();
            if(smallNode == null)
                level1++;
            else if((int)smallNode.data == Node1){
                Ancestor1 = Ancestor(root, Node1);
                Node1Check = true;
                break;
            }
        }

        list = prepareQueue(root);
        //Node2
        while (!list.isEmpty()){
            BinaryTreeNode<Integer> smallNode = list.pop();
            if(smallNode == null)
                level2++;
            else if((int)smallNode.data == Node2) {
                Ancestor2 = Ancestor(root, Node2);
                Node2Check = true;
                break;
            }
        }

        if(!Node1Check || !Node2Check){
            System.out.println("Condition 1");
            return Node1Check ? Node1 : Node2;
        }
        else if(!Node1Check && !Node2Check){
            System.out.println("Condition 2");
            return -1;
        }

        while (level1 != level2){
            if(level1 < level2) {
                Ancestor2.pop();
                level2--;
            }
            else {
                Ancestor1.pop();
                level1--;
            }
        }

        while (Ancestor1 != null ){
            rootNode1 = (int)Ancestor1.pop();
            rootNode2 = (int)Ancestor2.pop();
            if(rootNode1 == rootNode2){
                break;
            }
        }

        return rootNode1;
    }

    public static boolean flag = false;
    public static LinkedList<Integer> Ancestor(BinaryTreeNode<Integer> root, int NodeData) {
        if(root == null){
            LinkedList<Integer> ans =  new LinkedList<>();
            return ans;
        }

        if((int)root.data == NodeData){
            flag = true;
            LinkedList<Integer> ans = new LinkedList<>();
            ans.add(NodeData);
            return ans;
        }

        LinkedList<Integer> smallListLeft = Ancestor(root.left, NodeData);
        if(flag){
            smallListLeft.add(root.data);
            return smallListLeft;
        }
        LinkedList<Integer> smallListRight = Ancestor(root.right, NodeData);
        if(flag){
            smallListRight.add(root.data);
            return smallListRight;
        }

        return new LinkedList<>();
    }

    public static Property maxHeightBST(BinaryTreeNode<Integer> root){
        if(root == null){
            return new Property(0, true, 0);
        }
        Property left = maxHeightBST(root.left);
        Property right = maxHeightBST(root.right);

        int newHeight = 1 + Math.max(left.height, right.height);
        boolean newBST = left.isBST && right.isBST && (root.data < (root.right == null ? Integer.MAX_VALUE : root.right.data)) && (root.data > (root.left == null ? Integer.MIN_VALUE : root.left.data));
        int newAns = Math.max(Math.max(left.ans, right.ans), newBST ? newHeight : Integer.MIN_VALUE);

        return new Property(newHeight, newBST, newAns);
    }

    public static void replaceWithLargerNodeSums(BinaryTreeNode<Integer> rootCurrent, BinaryTreeNode<Integer> mainRoot){
        if(rootCurrent == null) return;

        replaceWithLargerNodeSums(rootCurrent.left, mainRoot);
        replaceWithLargerNodeSums(rootCurrent.right, mainRoot);
        rootCurrent.data = largerNodeSums(mainRoot, rootCurrent.data);

    }

    public static int largerNodeSums(BinaryTreeNode<Integer> root, int size){
        if(root == null){
            return 0;
        }

        return largerNodeSums(root.left, size) + largerNodeSums(root.right, size) + (root.data >= size ? root.data : 0);
    }

    public static BinaryTreeNode<Integer> clone(BinaryTreeNode<Integer> root){
        if(root == null){
            return null;
        }

        BinaryTreeNode<Integer> ans = new BinaryTreeNode<>(root.data);
        ans.left = clone(root.left);
        ans.right = clone(root.right);

        return ans;
    }

    public static LinkedList<Integer> clone(LinkedList<Integer> list){
        LinkedList<Integer> ans = new LinkedList<>();
        for(int i=0 ; i<list.size() ; i++){
            ans.add(list.get(i));
        }
        return ans;
    }

    public static void printPathSumK(BinaryTreeNode<Integer> root, int k, int sum, LinkedList<Integer> list){
        list.add(root.data);
        if(root.left == null && root.right == null){
            if(sum == k) {
                printLinkedList(clone(list));
            }
            return;
        }

        if(sum < k){
            if(root.left != null)  printPathSumK(root.left, k, sum + root.left.data, clone(list));
            if(root.right != null) printPathSumK(root.right, k, sum + root.right.data, clone(list));
        }
    }

    public static int nodesAtDistanceK(BinaryTreeNode<Integer> root, int nodeData, int k, int level){
        if(root == null){
            return 0;
        }

        if(!flag) {
            if (root.data == nodeData)
                flag = true;
        }

        int distance1=0, distance2=0;

        if(flag){
            if(k == 0) {
                System.out.println(root.data);
                return 0;
            }else {
                distance1 = nodesAtDistanceK(root.left, nodeData, k-1, level+1);
                distance2 = nodesAtDistanceK(root.right, nodeData, k-1, level+1);
            }
        }else {
            distance1 = nodesAtDistanceK(root.left, nodeData, flag ? k-distance1 : k, level+1);
            if(distance1 == k){
                System.out.println(root.data);
                return distance1+1;
            }
            distance2 = nodesAtDistanceK(root.right, nodeData, flag ? k-distance1-1 : k,level+1);
        }

        return 1 + distance1;
    }
}


class BST_Methods {
    public static void insertDuplicateNodes(BinaryTreeNode<Integer> root){
        if(root == null || root.left == null){
            return;
        }
        root.left.data = root.data;
        insertDuplicateNodes(root.left.left);
        insertDuplicateNodes(root.right);
    }
}


class Property {
    boolean isBST;
    int height, ans;

    Property(int height, boolean isBST, int ans){
        this.height = height;
        this.isBST = isBST;
        this.ans = ans;
    }
}


class TreeP {
    boolean isBalanced;
    int nodes;

    TreeP(boolean isBalanced, int nodes){
        this.isBalanced = isBalanced;
        this.nodes = nodes;
    }
}


public class BinaryTreeCodeZen {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int NodeData;

//        BinaryTreeNode<Integer> root = BTMethods.createTree();
//        BTMethods.printTree(root);

        System.out.print("Enter size : ");
        int n = s.nextInt();
        int preOrder[] = new int[n];
        int inOrder[] = new int[n];
        int postOrder[] = new int[n];
        System.out.print("Pre order : ");  arrInput(preOrder);
        System.out.print("In Order : ");   arrInput(inOrder);

        BinaryTreeNode<Integer> root = BTMethods.constructTree(preOrder, inOrder);
        System.out.println("");
        BTMethods.printTree(root);

        System.out.print("\nPre Order : ");
        BTMethods.printTreePreOrder(root);
        System.out.print("\nPost Order : ");
        BTMethods.printTreePostOrder(root);
        System.out.print("\nIn Order : ");
        BTMethods.printTreeInOrder(root);

//        System.out.print("Enter Node : ");
//        int node = s.nextInt();
//        LinkedList<Integer> list = BTMethods.Ancestor(root, node);
//        BTMethods.printLinkedList(list);

//        do {
//            System.out.println("Enter Node : ");
//            NodeData= s.nextInt();
//
//            if(NodeData == -1) {break;}
//
//            LinkedList<Integer> list = BTMethods.Ancestor(root, NodeData);
//            BTMethods.printLinkedList(list);
//
//        }while (NodeData != -1);

        System.out.print("Enter K : ");
        int k = s.nextInt();
        BTMethods.printPathSumK(root, k, root.data, new LinkedList<Integer>());
    }

    public static void arrInput(int arr[]){
        Scanner s = new Scanner(System.in);
        for(int i=0 ; i<arr.length ; arr[i] = s.nextInt(), i++);
    }
}