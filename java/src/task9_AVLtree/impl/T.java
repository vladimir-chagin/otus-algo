package task9_AVLtree.impl;

import util.Performance;
import util.U;

public class T {

    public static void testBinaryTree(final BinarySearchTree tree, final String msg, final boolean ordered, final int maxSize, StringBuilder r) {
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

    public static void testSearch(final BinarySearchTree tree, final int searchCount) {
        final int size = tree.size();
        int i = searchCount;
        while(i-- > 0) {
            int key = U.randomInt(0, size);
            tree.search(key);
        }
    }

    public static void testInsert(final BinarySearchTree tree, final boolean ordered, final int maxSize) {
        for (int i = 0; i < maxSize; i += 1) {
            int key = ordered ? i : U.randomInt(0, maxSize);
            tree.insert(key);
        }
    }

    public static void testRemove(final BinarySearchTree tree, final int removeCount) {
        final int size = tree.size();
        int i = removeCount;
        while(i-- > 0) {
            int key = U.randomInt(0, size);
            tree.remove(key);
        }
    }
}
