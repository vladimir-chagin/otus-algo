package task9_AVLtree.impl.avl;

import task9_AVLtree.impl.BinarySearchTree;
import task9_AVLtree.impl.TreeNode;

public class AVLTree extends BinarySearchTree {
    public void rotateRight(final TreeNode pivotNode) {
        if (pivotNode == null || !pivotNode.hasParentNode()) {
            return;
        }

        final TreeNode parent = pivotNode.getParentNode();
        if (height(pivotNode) - height(parent.getRightChild()) > 1 && height(pivotNode.getRightChild()) <= height(pivotNode.getLeftChild())) {
            super.rotateRight(pivotNode);
        }
    }

    public void rotateLeft(TreeNode pivotNode) {
        if (pivotNode == null || !pivotNode.hasParentNode()) {
            return;
        }

        final TreeNode parent = pivotNode.getParentNode();
        if (height(pivotNode) - height(parent.getLeftChild()) < -1 && height(pivotNode.getLeftChild()) <= height(pivotNode.getRightChild())) {
            super.rotateLeft(pivotNode);
        }
    }

    public void insert(int key) {
        final TreeNode newNode = new TreeNode(key);
        super.insert(newNode);
        balance(newNode);
    }

    public void remove(int key) {
        super.remove(key);
    }

    @Override
    protected void balance(final TreeNode node) {
        if (node == null) {
            return;
        }

        node.getHeight();

        if (balanceFactor(node) >= 2) {
            if (balanceFactor(node.getRightChild()) < 0) {
                rotateRight(node.getRightChild());
            }
            rotateLeft(node);
        } else if (balanceFactor(node) <= -2) {
            if (balanceFactor(node.getLeftChild()) > 0) {
                rotateLeft(node.getLeftChild());
            }
            rotateRight(node);
        }

        balance(node.getParentNode());
    }

    ////=========================
    private static int height(TreeNode node) {
        return node == null ? 0 : node.getHeight();
    }

    private static int balanceFactor(TreeNode node) {
        return height(node.getLeftChild()) - height(node.getRightChild());
    }
}
