package task10_RBtree;

import task10_RBtree.impl.RBTree;
import task9_AVLtree.impl.T;

public class Program {
    public static void main(String[] args) {
        testBST();
    }

    private static void testBST() {
        final int maxSize = 100_000;
        final RBTree orderedTree = new RBTree();
        final RBTree randomTree = new RBTree();

        final StringBuilder report = new StringBuilder();

        T.testBinaryTree(orderedTree, "RB Ordered", true, maxSize, report);
        report.append("\r\n");
//        T.testBinaryTree(randomTree, "RB Random", false, maxSize, report);
//        report.append("\r\n");

        System.out.println(report.toString());
    }
}
