package task10_RBtree.impl;

import task9_AVLtree.impl.TreeNode;

public class RBTreeNode extends TreeNode {

    public static final RBTreeNode NIL = new NilRBTreeNode();
    private boolean black;

    public RBTreeNode(final int key, final boolean black) {
        this(key);
        this.black = black;
    }

    public RBTreeNode(int key) {
        super(key);
    }

    public boolean isRed() {
        return !black;
    }

    public void setRed() {
        black = false;
    }

    public boolean isBlack() {
        return black;
    }

    public void setBlack() {
        black = true;
    }

    @Override
    public RBTreeNode getLeftChild() {
        return (RBTreeNode)super.getLeftChild();
    }

    @Override
    public RBTreeNode getRightChild() {
        return (RBTreeNode)super.getRightChild();
    }

    @Override
    public RBTreeNode getParentNode() {
        return (RBTreeNode)super.getParentNode();
    }

    @Override
    public RBTreeNode getGrandParentNode() {
        return (RBTreeNode)super.getGrandParentNode();
    }

    @Override
    public RBTreeNode getUncleNode() {
        return (RBTreeNode)super.getUncleNode();
    }

    @Override
    public RBTreeNode getSiblingNode() {
        return (RBTreeNode)super.getSiblingNode();
    }

}
