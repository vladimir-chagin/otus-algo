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
        this.height = -1;
    }


    public TreeNode getLeftChild() {
        return leftChild;
    }

    public void setLeftChild(TreeNode node) {
        leftChild = node;
        if (node != null) {
            node.setParentNode(this);
        }
        height = -1;
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
        height = -1;
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

    public boolean hasParentNode() {
        return this.parentNode != null;
    }

    public boolean isLeftChildOf(final TreeNode parent) {
        return parent != null && parent.getLeftChild() == this;
    }

    public boolean isRightChildOf(final TreeNode parent) {
        return parent != null && parent.getRightChild() == this;
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

    public int getHeight() {
        if (height < 0) {
            height = calcHeight(this);
        }
        return height;
    }

    private static int calcHeight(TreeNode node) {
        if (node == null || node.isLeaf()) {
            return 0;
        }

        final int lh = calcHeight(node.getLeftChild());
        final int rh = calcHeight(node.getRightChild());

        return Math.max(lh, rh) + 1;
    }
}
