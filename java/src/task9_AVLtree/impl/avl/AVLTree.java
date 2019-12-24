package task9_AVLtree.impl.avl;

import task9_AVLtree.impl.BinarySearchTree;
import task9_AVLtree.impl.TreeNode;

public class AVLTree extends BinarySearchTree {
    public void insert(int key) {
        final TreeNode newNode = new TreeNode(key);
        super.insert(newNode);
        balance(newNode.getParentNode());
    }

    public void remove(int key) {
        super.remove(key);
    }

    @Override
    protected void balance(final TreeNode node) {
        if (node == null) {
            return;
        }

        if (!node.isLeaf()) {
            node.getHeight();
            final int bf = balanceFactor(node);
            if (bf >= 2) {
                final int bfR = balanceFactor(node.getRightChild());
                if (bfR < 0) {
                    rotateRight(node.getRightChild());
                }
                rotateLeft(node);
            } else if (bf <= -2) {
                final int bfL = balanceFactor(node.getLeftChild());
                if (bfL > 0) {
                    rotateLeft(node.getLeftChild());
                }
                rotateRight(node);
            }
        }

        balance(node.getParentNode());
    }

    ////=========================
    private static int height(TreeNode node) {
        return node == null ? 0 : node.getHeight();
    }

    private static int balanceFactor(TreeNode node) {
        final int rh = height(node.getRightChild());
        final int lh = height(node.getLeftChild());
        return rh - lh;
    }
}
