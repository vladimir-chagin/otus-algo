package task9_AVLtree;

import task9_AVLtree.impl.AVLTree;
import task9_AVLtree.impl.BinarySearchTree;
import task9_AVLtree.impl.T;


public class Program {
    public static void main(String[] args) {
        testBST();
    }

    private static void testBST() {
        final int maxSize = 100_000;
        final StringBuilder report = new StringBuilder();
        final BinarySearchTree orderedTree = new BinarySearchTree();
        final BinarySearchTree randomTree = new BinarySearchTree();
        final BinarySearchTree avlOrderedTree = new AVLTree();
        final BinarySearchTree avlRandomTree = new AVLTree();

        T.testBinaryTree(orderedTree, "BST Ordered", true, maxSize, report);
        report.append("\r\n");
        T.testBinaryTree(randomTree, "BST Random", false, maxSize, report);
        report.append("\r\n");
        T.testBinaryTree(avlOrderedTree, "AVL Ordered", true, maxSize, report);
        report.append("\r\n");
        T.testBinaryTree(avlRandomTree, "AVL Random", false, maxSize, report);
        report.append("\r\n");
        System.out.println(report.toString());
    }

}
