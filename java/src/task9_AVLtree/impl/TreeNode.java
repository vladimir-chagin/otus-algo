package task9_AVLtree.impl;

public class TreeNode {
    private TreeNode parentNode;
    private TreeNode leftChild;
    private TreeNode rightChild;

    private int key;

    public TreeNode(int key) {
        this(null, null, key);
    }

    public TreeNode(TreeNode leftChild, TreeNode rightChild, int key) {
        this(null, leftChild, rightChild, key);
    }

    public TreeNode(TreeNode parentNode, TreeNode leftChild, TreeNode rightChild, int key) {
        this.parentNode = parentNode;
        this.leftChild = leftChild;
        this.rightChild = rightChild;
        this.key = key;
    }


    public TreeNode getLeftChild() {
        return leftChild;
    }

    public void setLeftChild(TreeNode node) {
        leftChild = node;
    }

    public boolean hasLeftChild() {
        return leftChild != null;
    }

    public TreeNode getRightChild() {
        return rightChild;
    }

    public void setRightChild(TreeNode node) {
        rightChild = node;
    }

    public boolean hasRightChild() {
        return rightChild != null;
    }

    public void setKey(int val) {
        key = val;
    }

    public int getKey() {
        return key;
    }

    public TreeNode getParentNode() {
        return parentNode;
    }

    public void setParentNode(TreeNode node) {
        parentNode = node;
    }

    public boolean isLeaf() {
        return !hasLeftChild() && !hasRightChild();
    }

    public boolean isRoot() {
        return parentNode == null;
    }

    public void detach() {
        if (!isLeaf()) {
            throw new RuntimeException("Couldn't detach node with children");
        }

        if (isRoot()) {
            return;
        }

        if (parentNode.getLeftChild() == this) {
            parentNode.setLeftChild(null);
        } else if (parentNode.getRightChild() == this) {
            parentNode.setRightChild(null);
        }

        parentNode = null;
    }

    public void reset() {
        parentNode = null;
        leftChild = null;
        rightChild = null;
        key = 0;
    }
}
