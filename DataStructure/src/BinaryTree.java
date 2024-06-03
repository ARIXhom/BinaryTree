import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BinaryTree {
    static Node root, root2;
    private int counter, max, min;
    private static boolean flag = false;
    private boolean searchflag;

    public static void main(String[] args) throws InterruptedException, IOException {
        BinaryTree a = new BinaryTree();

        Scanner input = new Scanner(System.in);
        boolean menuflag = true;
        int menunum;
        while(menuflag) {
            System.out.println("\n\n\n    Enter desired option by the number.");
            System.out.println("\n\n    1. ADD new node to tree.");
            System.out.println("    2. Find out NODE COUNT of tree.");
            System.out.println("    3. Find out HEIGHT of tree.");
            System.out.println("    4. Find out LEAVES COUNT of tree.");
            System.out.println("    5. Print the tree PREORDER traversal.");
            System.out.println("    6. Print the tree INORDER traversal.");
            System.out.println("    7. Print the tree POSTORDER traversal.");
            System.out.println("    8. Print the tree LEVELORDER traversal.");
            System.out.println("    9. DELETE the whole tree.");
            System.out.println("    10. Print the MINIMUM of the tree.");
            System.out.println("    11. Print the MAXIMUM of the tree.");
            System.out.println("    12. Is the tree COMPLETE or not.");
            System.out.println("    13. SEARCH in the tree by entered value.");
            System.out.println("    14. PRINT the BST tree.");
            System.out.println("    15. Check if two trees are EQUAL.");
            System.out.println("    -1. SWITCH between two trees");
            System.out.println("   0. EXIT the program.\n\n\n");

            menunum = input.nextInt();
            switch (menunum) {
                case 1:
                    System.out.println("    Enter the value you want to ADD : ");
                    a.add(input.nextInt());
                    System.out.println("\n    Value is inserted to the tree.\n");
                    break;
                case 2:
                    System.out.println("    Number of NODES is " + a.NodeCount() + ".\n");
                    break;
                case 3:
                    System.out.println("    Height is " + a.Height() + ".\n");
                    break;
                case 4:
                    System.out.println("    Number of LEAVES is " + a.LeafCount() + ".\n");
                    break;
                case 5:
                    System.out.println("    Preorder traversal of the tree is :\n   ");
                    a.PreOrderGetter();
                    System.out.println();
                    break;
                case 6:
                    System.out.println("    Inorder traversal of the tree is :\n   ");
                    a.InOrderGetter();
                    System.out.println();
                    break;
                case 7:
                    System.out.println("    Postorder traversal of the tree is :\n   ");
                    a.PostOrderGetter();
                    System.out.println();
                    break;
                case 8:
                    System.out.println("    Levelorder traversal of the tree is :\n   ");
                    a.LevelOrderGetter();
                    System.out.println();
                    break;
                case 9:
                    a.DeleteTree();
                    System.out.println("    Tree is DELETED now.\n");
                    break;
                case 10:
                    System.out.println("    The MINIMUM NODE of the tree is " + a.MinNodeGetter() + ".\n");
                    break;
                case 11:
                    System.out.println("    The MAXIMUM NODE of the tree is " + a.MaxNodeGetter() + ".\n");
                    break;
                case 12:
                    if (a.IsComplete()) System.out.println("    Tree is COMPLETE.\n");
                    else System.out.println("   Tree is NOT COMPLETE.\n");
                    break;
                case 13:
                    System.out.println("    Enter the value you want to SEARCH in the tree : ");
                    if (a.Searchtree(input.nextInt()))
                        System.out.println("    Tree has the value you wanted.\n");
                    else System.out.println("   Tree doesn't have the value you wanted.\n");
                    break;
                case 14:
                    a.Print();
                    break;
                case 15:
                    if (a.Equal()) System.out.println("    Two trees are EQUAL.");
                    else System.out.println("    Two trees are NOT EQUAL.");
                    break;
                case -1:
                    a.Switchtree();
                    if (!flag) System.out.println("    You are EDITING FIRST tree.");
                    else System.out.println("    You are EDITING SECOND tree.");
                    break;
                case 0:
                    System.out.println("    Data Structure project presented.\n");
                    System.out.println("    Arian Homayooni\n    Nahid Qasemi Moqadam");
                    System.out.println("\n\n    GOOD LUCK...");
                    menuflag = false;
                    break;
                default:
                    System.out.println("    You entered wrong number.");
            }
            //TimeUnit.SECONDS.sleep(3);
            System.out.println("Hit ENTER to continue...");
            input.nextLine();
            input.nextLine();
            for(int clear = 0; clear < 1000; clear++) System.out.println("\b");
        }
    }

    public static class Node {
        int value;
        Node left;
        Node right;
        Node(int value) {
            this.value = value;
            right = null;
            left = null;
        }
    } //structure of the node

    private Node Insert(Node node, int value) {
        if (node == null) {
            return new Node(value);
        }
        if (value < node.value) {
            node.left = Insert(node.left, value);
        }
        else if (value > node.value) {
            node.right = Insert(node.right, value);
        }
        else {
            System.out.println("    Value already exists.");
            return node;
        }
        return node;
    } //inserting a node to tree
    public void add(int value) {
        if (!flag) root = Insert(root, value);
        else root2 = Insert(root2, value);
    } //.......................................

    private void NodeCounter(Node node) {
        if (node.left != null) NodeCounter(node.left);
        counter++;
        if (node.right != null) NodeCounter(node.right);
    } //counting the nodes of the root tree
    public int NodeCount() {
        if (!flag) {
            if (root == null) return 0;
            counter=0;
            NodeCounter(root);
        }
        else {
            if (root2 == null) return 0;
            counter=0;
            NodeCounter(root2);
        }
        return counter;
    } //................................................

    private int HeightCounter(Node node) {
        if (node == null) return -1;
        return Math.max(HeightCounter(node.left) + 1, HeightCounter(node.right) + 1);
    } //calculating height of the tree
    public int Height() {
        if (!flag) {
            if (root == null) return 0;
            return HeightCounter(root);
        }
        else {
            if (root2 == null) return 0;
            return HeightCounter(root2);
        }
    } //...............................................

    private void LeafCounter(Node node) {
        if (node.left != null) LeafCounter(node.left);
        if (node.left == null && node.right == null) counter++;
        if (node.right != null) LeafCounter(node.right);
    } // counting the number of leaves
    public int LeafCount() {
        if (!flag) {
            if (root == null) return 0;
            counter = 0;
            LeafCounter(root);
        }
        else {
            if (root2 == null) return 0;
            counter = 0;
            LeafCounter(root2);
        }
        return counter;
    } //...........................................

    private void PreOrder(Node node) {
        System.out.print(node.value + " - ");
        if (node.left != null) PreOrder(node.left);
        if (node.right != null) PreOrder(node.right);
    } //printing tree by preorder traversal
    public void PreOrderGetter() {
        if (!flag) {
            if (root == null) System.out.println("Error... There is no root.");
            else PreOrder(root);
            System.out.println("\b\b\b");
        }
        else {
            if (root2 == null) System.out.println("Error... There is no root2");
            else PreOrder(root2);
            System.out.println("\b\b\b");
        }
    } //.......................................

    private void InOrder(Node node) {
        if (node.left != null) InOrder(node.left);
        System.out.print(node.value + " - ");
        if (node.right != null) InOrder(node.right);
    } //printing tree by inorder traversal
    public void InOrderGetter() {
        if (!flag) {
            if (root == null) System.out.println("Error... There is no root.");
            else InOrder(root);
            System.out.println("\b\b\b");
        }
        else {
            if (root2 == null) System.out.println("Error... There is no root2");
            else InOrder(root2);
            System.out.println("\b\b\b");
        }
    } //......................................

    private void PostOrder(Node node) {
        if (node.left != null) PostOrder(node.left);
        if (node.right != null) PostOrder(node.right);
        System.out.print(node.value + " - ");
    } //printing tree by postorder traversal
    public void PostOrderGetter() {
        if (!flag) {
            if (root == null) System.out.println("Error... There is no root.");
            else PostOrder(root);
            System.out.println("\b\b\b");
        }
        else {
            if (root2 == null) System.out.println("Error... There is no root2");
            else PostOrder(root2);
            System.out.println("\b\b\b");
        }
    } //........................................

    private void LevelOrder(Node node) {
        Queue<Node> queue = new LinkedList<Node>();
        queue.add(node);
        while (!queue.isEmpty()) {
            Node tempNode = queue.poll();
            System.out.print(tempNode.value + " - ");
            if (tempNode.left != null) queue.add(tempNode.left);
            if (tempNode.right != null) queue.add(tempNode.right);
        }
    } //printing tree by levelorder traversal
    public void LevelOrderGetter() {
        if (!flag) {
            if (root == null) System.out.println("Error... There is no root.");
            else LevelOrder(root);
            System.out.println("\b\b\b");
        }
        else {
            if (root2 == null) System.out.println("Error... There is no root2");
            else LevelOrder(root2);
            System.out.println("\b\b\b");
        }
    } //.........................................

    public void DeleteTree() {
        if (!flag) root = null;
        else root2 = null;
    }

    private void MaxNode(Node node) {
        while (node.right != null) node = node.right;
        max = node.value;
    }
    public int MaxNodeGetter() {
        if(!flag) {
            if (root == null) return 0;
            max = root.value;
            MaxNode(root);
        }
        else {
            if (root2 == null) return 0;
            max = root2.value;
            MaxNode(root2);
        }
        return max;
    }

    private void MinNode(Node node) {
        while (node.left != null) node = node.left;
        min = node.value;
    }
    public int MinNodeGetter() {
        if(!flag) {
            if (root == null) return 0;
            min = root.value;
            MinNode(root);
        }
        else {
            if (root2 == null) return 0;
            min = root2.value;
            MinNode(root2);
        }
        return min;
    }

    private boolean IsCompleteTree(Node node, int index, int numberNodes) {
        if (node == null) return true;
        if (index >= numberNodes) return false;
        return (IsCompleteTree(node.left, 2 * index + 1, numberNodes)
                && IsCompleteTree(node.right, 2 * index + 2, numberNodes));
    }
    public boolean IsComplete() {
        if (!flag) {
            return IsCompleteTree(root, 0, NodeCount());
        }
        else {
            return IsCompleteTree(root2, 0, NodeCount());
        }
    }

    private void Search(Node node, int value) {
        if (node.left != null) Search(node.left, value);
        if (value == node.value) searchflag = true;
        if (node.right != null) Search(node.right, value);
    }
    public boolean Searchtree(int value) {
        searchflag = false;
        if (!flag) Search(root, value);
        else Search(root2, value);
        return searchflag;
    }

    private void printBinaryTree(Node root) {
        LinkedList<Node> treeLevel = new LinkedList<Node>();
        treeLevel.add(root);
        LinkedList<Node> temp = new LinkedList<Node>();
        int counter = 0;
        int height = heightOfTree(root) - 1;
        // System.out.println(height);
        double numberOfElements
                = (Math.pow(2, (height + 1)) - 1);
        // System.out.println(numberOfElements);
        while (counter <= height) {
            Node removed = treeLevel.removeFirst();
            if (temp.isEmpty()) {
                printSpace(numberOfElements
                                / Math.pow(2, counter + 1),
                        removed);
            }
            else {
                printSpace(numberOfElements
                                / Math.pow(2, counter),
                        removed);
            }
            if (removed == null) {
                temp.add(null);
                temp.add(null);
            }
            else {
                temp.add(removed.left);
                temp.add(removed.right);
            }

            if (treeLevel.isEmpty()) {
                System.out.println("");
                System.out.println("");
                treeLevel = temp;
                temp = new LinkedList<>();
                counter++;
            }
        }
    }
    private void printSpace(double n, Node removed) {
        for (; n > 0; n--) {
            System.out.print("\t");
        }
        if (removed == null) {
            System.out.print(" ");
        }
        else {
            System.out.print(removed.value);
        }
    }
    private int heightOfTree(Node root) {
        if (root == null) {
            return 0;
        }
        return 1
                + Math.max(heightOfTree(root.left),
                heightOfTree(root.right));
    }
    public void Print() {
        if(!flag) printBinaryTree(root);
        else printBinaryTree(root2);
    }

    private boolean AreEqual(Node root,Node root2) {

        if (root == null && root2 == null) {
            return true;
        }

        if (root != null && root2 != null) {
            return ((root.value == root2.value) &&
                    AreEqual(root.left, root2.left) &&
                    AreEqual(root.right, root2.right));
        }

        return false;
    }
    public boolean Equal() {
        return AreEqual(root, root2);
    }

    public void Switchtree() {
        flag = !flag;
    }
}