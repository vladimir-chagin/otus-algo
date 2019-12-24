package task9_AVLtree;

import task9_AVLtree.impl.BinarySearchTree;
import task9_AVLtree.impl.TreeNode;
import util.Performance;
import util.U;

public class Program {
    public static void main(String[] args) {
        testBST();
        StringBuilder b = new StringBuilder();
    }

    private static void testBST() {
        final int maxSize = 100_000;
        final StringBuilder report = new StringBuilder();
        final BinarySearchTree orderedTree = new BinarySearchTree();
        final BinarySearchTree randomTree = new BinarySearchTree();
        final BinarySearchTree avlOrderedTree = new TreeNode.AVLTree();
        final BinarySearchTree avlRandomTree = new TreeNode.AVLTree();

        testBinaryTree(orderedTree, "BST Ordered", true, maxSize, report);
        report.append("\r\n");
        testBinaryTree(randomTree, "BST Random", false, maxSize, report);
        report.append("\r\n");
        testBinaryTree(avlOrderedTree, "AVL Ordered", true, maxSize, report);
        report.append("\r\n");
        testBinaryTree(avlRandomTree, "AVL Random", false, maxSize, report);
        report.append("\r\n");
        System.out.println(report.toString());
    }

    private static void testBinaryTree(final BinarySearchTree tree, final String msg, final boolean ordered, final int maxSize, StringBuilder r) {
        r.append(msg).append("|");
        final long insertDuration = Performance.measure(() -> {
            testInsert(tree, ordered, maxSize);
        });
        final long searchDuration = Performance.measure(() -> {
            testSearch(tree, maxSize / 10);
        });
        final long removeDuration = Performance.measure(() -> {
            testRemove(tree, maxSize / 10);
        });
        r.append(insertDuration + "ms").append("|").append(searchDuration + "ms").append("|").append(removeDuration + "ms");
    }

    private static void testSearch(final BinarySearchTree tree, final int searchCount) {
        final int size = tree.size();
        int i = searchCount;
        while(i-- > 0) {
            int key = U.randomInt(0, size);
            tree.search(key);
        }
    }

    private static void testInsert(final BinarySearchTree tree, final boolean ordered, final int maxSize) {
        for (int i = 0; i < maxSize; i += 1) {
            int key = ordered ? i : U.randomInt(0, maxSize);
            tree.insert(key);
        }
    }

    private static void testRemove(final BinarySearchTree tree, final int removeCount) {
        final int size = tree.size();
        int i = removeCount;
        while(i-- > 0) {
            int key = U.randomInt(0, size);
            tree.remove(key);
        }
    }
}
