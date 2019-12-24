package task9_AVLtree.impl;

public class TreeNode {
    private TreeNode parentNode;
    private TreeNode leftChild;
    private TreeNode rightChild;

    private int key;

    private int height;


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
        this.height = 0;
    }


    public void setKey(int val) {
        key = val;
    }

    public int getKey() {
        return key;
    }


    public TreeNode getLeftChild() {
        return leftChild;
    }

    public void setLeftChild(TreeNode node) {
        this.leftChild = node;
        if (node != null) {
            node.setParentNode(this);
        }
        this.resetHeight();
    }

    public boolean hasLeftChild() {
        return leftChild != null;
    }

    public TreeNode getRightChild() {
        return rightChild;
    }

    public void setRightChild(TreeNode node) {
        rightChild = node;
        if (node != null) {
            node.setParentNode(this);
        }
        this.resetHeight();
    }

    public boolean hasRightChild() {
        return rightChild != null;
    }

    public TreeNode getParentNode() {
        return parentNode;
    }

    public void setParentNode(TreeNode node) {
        parentNode = node;
    }

    public boolean hasParentNode() {
        return this.parentNode != null;
    }

    public boolean isLeftChild() {
        return this.parentNode != null && this.parentNode.getLeftChild() == this;
    }

    public boolean isRightChild() {
        return this.parentNode != null && this.parentNode.getRightChild() == this;
    }

    public boolean isLeaf() {
        return !hasLeftChild() && !hasRightChild();
    }

    public boolean isRoot() {
        return parentNode == null;
    }

    public void reset() {
        if (parentNode != null) {
            if (parentNode.getLeftChild() == this) {
                parentNode.setLeftChild(null);
            } else if (parentNode.getRightChild() == this) {
                parentNode.setRightChild(null);
            }
            parentNode = null;
        }
        if (leftChild != null) {
            leftChild.setParentNode(null);
            leftChild = null;
        }
        if (rightChild != null) {
            rightChild.setParentNode(null);
            rightChild = null;
        }
        key = 0;
    }

    public void resetHeight() {
        this.height = 0;
    }

    public int getHeight() {
        if (height <= 0) {
            final int lh = this.leftChild != null ? this.leftChild.getHeight() : 0;
            final int rh = this.rightChild != null ? this.rightChild.getHeight() : 0;
            this.height = Math.max(lh, rh) + 1;
        }

        return height;
    }

}
