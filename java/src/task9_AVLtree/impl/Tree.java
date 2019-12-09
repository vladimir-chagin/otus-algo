package task9_AVLtree.impl;

public abstract class Tree {
    protected TreeNode rootNode;
    protected int size;

    public Tree() {
        rootNode = null;
        size = 0;
    }

    public int size() {
        return size;
    }

    public abstract void insert(int value);

    public abstract boolean search(int value);

    public abstract boolean remove(int value);
}
