package task9_AVLtree.impl;

public abstract class AbstractTree {
    protected TreeNode rootNode;
    protected int size;

    public AbstractTree() {
        rootNode = null;
        size = 0;
    }

    public int size() {
        return size;
    }

    public abstract void insert(int key);

    public abstract boolean search(int key);

    public abstract void remove(int key);

    public TreeNode getRootNode() {
        return rootNode;
    }

}
