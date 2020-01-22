package task10_RBtree.impl;

import task9_AVLtree.impl.BinarySearchTree;
import task9_AVLtree.impl.TreeNode;

public class RBTree extends BinarySearchTree {

    @Override
    public void insert(final int key) {
        final RBTreeNode node = new RBTreeNode(key, false);
        super.insert(node);
        balanceOnInsert(node);
    }

    @Override
    public RBTreeNode searchNodeIterative(final int key) {
        return (RBTreeNode)super.searchNodeIterative(key);
    }

    @Override
    public RBTreeNode successor(final TreeNode node) {
        return (RBTreeNode)super.successor(node);
    }

    public void remove(final int key) {
        final RBTreeNode z = searchNodeIterative(key);

        final RBTreeNode y = (!z.hasLeftChild() || !z.hasRightChild()) ? z : successor(z);
        final RBTreeNode x = y.hasLeftChild() ? y.getLeftChild() : y.getRightChild();

        if (y.isLeftChild()) {
            y.getParentNode().setLeftChild(x);
        } else if (y.isRightChild()) {
            y.getParentNode().setRightChild(x);
        } else {    //node is root
            rootNode = x;
        }

        size -= 1;
        if (y != z) {
            z.setKey(y.getKey());
        }


        if (y.isBlack()) {
            balanceOnRemove(x);
        }

        y.reset();
    }

    private void balanceOnInsert(RBTreeNode x) {
        if (x == null || x.isBlack()) {
            return;
        }

        if (x.isRoot()) {
            x.setBlack();
            return;
        }

        if (x.getParentNode().isBlack()) {
            return;
        }

        final RBTreeNode p = x.getParentNode();
        final RBTreeNode u = x.getUncleNode();
        final RBTreeNode g = x.getGrandParentNode();

        if (u == null || u.isBlack()) {     //null uncle means black colored NIL
            if (p.isLeftChild()) {
                if (x.isLeftChild()) {
                    rotateRight(g);
                    p.setBlack();
                    g.setRed();
                } else if (x.isRightChild()) {
                    rotateLeft(p);
                    balanceOnInsert(p);
                }
            } else if (p.isRightChild()) {
                if (x.isLeftChild()) {
                    rotateRight(p);
                    balanceOnInsert(p);
                } else if (x.isRightChild()) {
                    rotateLeft(g);
                    p.setBlack();
                    g.setRed();
                }
            }
        } else if (u.isRed()) {
            p.setBlack();
            u.setBlack();
            g.setRed();
            balanceOnInsert(g);
        }
    }

    private void balanceOnRemove(RBTreeNode x) {
        while(x != null && !x.isRoot() && x.isBlack()) {
            if (x.isLeftChild()) {
                RBTreeNode b = x.getSiblingNode();
                if (b.isRed()) {
                    b.setBlack();
                    x.getParentNode().setRed();
                    rotateLeft(x.getParentNode());
                    b = x.getSiblingNode();
                }
                if (isBlack(b.getLeftChild()) && isBlack(b.getRightChild())) {
                    b.setRed();
                    x = x.getParentNode();
                } else if (isBlack(b.getRightChild())) {
                    RBTreeNode l = b.getLeftChild();
                    if (l != null) {
                        l.setBlack();
                        b.setRed();
                        rotateRight(b);
                        b = x.getSiblingNode();
                    }
                }
                RBTreeNode p = x.getParentNode();
                if (p == null || p.isBlack()) {
                    b.setBlack();
                } else {
                    b.setRed();
                }
                if (p != null) {
                    p.setBlack();
                }
                RBTreeNode r = b.getRightChild();
                if (r != null) {
                    r.setBlack();
                }
                rotateLeft(p);
                x = (RBTreeNode)rootNode;
            } else if (x.isRightChild()) {
                RBTreeNode b = x.getSiblingNode();
                if (b.isRed()) {
                    b.setBlack();
                    x.getParentNode().setRed();
                    rotateRight(x.getParentNode());
                    b = x.getSiblingNode();
                }
                if (isBlack(b.getLeftChild()) && isBlack(b.getRightChild())) {
                    b.setRed();
                    x = x.getParentNode();
                } else if (isBlack(b.getLeftChild())) {
                    RBTreeNode l = b.getRightChild();
                    if (l != null) {
                        l.setBlack();
                        b.setRed();
                        rotateLeft(b);
                        b = x.getSiblingNode();
                    }
                }
                RBTreeNode p = x.getParentNode();
                if (p == null || p.isBlack()) {
                    b.setBlack();
                } else {
                    b.setRed();
                }
                if (p != null) {
                    p.setBlack();
                }
                RBTreeNode r = b.getLeftChild();
                if (r != null) {
                    r.setBlack();
                }
                rotateRight(p);
                x = (RBTreeNode)rootNode;
            }
        }
        x.setBlack();
    }

    private static boolean isBlack(final RBTreeNode node) {
        return node == null || node.isBlack();
    }
}
