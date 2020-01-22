package task10_RBtree.impl;

public class NilRBTreeNode extends RBTreeNode {
    public NilRBTreeNode() {
        super(Integer.MIN_VALUE, true);
    }

    @Override
    public boolean isBlack() {
        return true;
    }

    @Override
    public boolean isRed() {
        return false;
    }

    @Override
    public void setRed() {}

    public void setLeftChild(final RBTreeNode child) {

    }

    public void setRightChild(final RBTreeNode child) {

    }
}
