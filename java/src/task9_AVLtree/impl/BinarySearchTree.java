package task9_AVLtree.impl;

public class BinarySearchTree extends AbstractTree {

    @Override
    public void insert(int key) {
        insert(new TreeNode(key));
    }

    public TreeNode insert(final TreeNode newNode) {
        if (rootNode == null) {
            rootNode = newNode;
        } else {
            TreeNode currentParent = searchParentToInsert(newNode.getKey());

            if (newNode.getKey() <= currentParent.getKey()) {
                currentParent.setLeftChild(newNode);
            } else {
                currentParent.setRightChild(newNode);
            }
        }

        size += 1;
        return newNode;
    }

    @Override
    public boolean search(int key) {
//        return searchNodeRecursive(rootNode, key) != null;
        return searchNodeIterative(key) != null;
    }

    @Override
    public void remove(int key) {
        final TreeNode node = remove(rootNode, key);
        balance(node);
    }

    protected final TreeNode remove(final TreeNode root, final int key) {
        TreeNode node = searchNodeIterative(root, key);

        if (node == null) {
            return null;
        }

        TreeNode parentNode = node.getParentNode();
        TreeNode nodeToStartRebalance;
        if (node.isLeaf()) {//isLeaf
            if (parentNode != null && node.isLeftChildOf(parentNode)) {
                parentNode.setLeftChild(null);
            } else if (parentNode != null && node.isRightChildOf(parentNode)) {
                parentNode.setRightChild(null);
            } else if (parentNode == null) {
                rootNode = null;
            }
            node.reset();
            nodeToStartRebalance = parentNode;
        } else if (!node.hasLeftChild() || !node.hasRightChild()) { //node has only one child
            TreeNode childNode = node.hasLeftChild() ? node.getLeftChild() : node.getRightChild();

            childNode.setParentNode(parentNode);

            if (parentNode != null && node.isLeftChildOf(parentNode)) {
                parentNode.setLeftChild(childNode);
            } else if (parentNode != null && node.isRightChildOf(parentNode)) {
                parentNode.setRightChild(childNode);
            } else if (parentNode == null) {
                rootNode = childNode;
            }
            nodeToStartRebalance = parentNode;
        } else {    //node has both left and right children
            final TreeNode newNode = findMinKeyNode(node.getRightChild());
            nodeToStartRebalance = newNode.getParentNode();
            node.setKey(newNode.getKey());
            remove(node.getRightChild(), key);
        }
        size -= 1;
        return nodeToStartRebalance;
    }

    protected void balance(final TreeNode node) {

    }

    private TreeNode searchNodeRecursive(final TreeNode currentNode, final int key) {
        if (currentNode == null || currentNode.getKey() == key) {
            return currentNode;
        }

        return key <= currentNode.getKey() ? searchNodeRecursive(currentNode.getLeftChild(), key) : searchNodeRecursive(currentNode.getRightChild(), key);
    }

    private TreeNode searchNodeIterative(final int key) {
        return searchNodeIterative(rootNode, key);
    }

    private TreeNode searchNodeIterative(final TreeNode root, final int key) {
        TreeNode node = root;

        while(node != null) {
            if (key == node.getKey()) {
                return node;
            } else if (key <= node.getKey()) {
                node = node.getLeftChild();
            } else {
                node = node.getRightChild();
            }
        }

        return null;
    }

    private TreeNode searchParentToInsert(final int key) {
        TreeNode currentParent = null;
        TreeNode current = rootNode;

        while(current != null) {
            currentParent = current;
            current = key <= current.getKey() ? current.getLeftChild() : current.getRightChild();
        }

        return currentParent;
    }


    protected TreeNode findMinKeyNode(TreeNode node) {
        while(node.hasLeftChild()) {
            node = node.getLeftChild();
        }
        return node;
    }

    protected TreeNode findMaxKeyNode(TreeNode node) {
        while(node.hasRightChild()) {
            node = node.getRightChild();
        }
        return node;
    }

    protected void rotateLeft(TreeNode x) {
        if (x == null) {
            return;
        }

        final TreeNode parent = x.getParentNode();
        final TreeNode y = x.getRightChild();
        if (y.isLeaf()) {
            return;
        }

        final TreeNode b = y.getLeftChild();

        x.setRightChild(b);
        y.setLeftChild(x);

        if (parent != null) {
            if (parent.getLeftChild() == x) {
                parent.setLeftChild(y);
            } else if (parent.getRightChild() == x) {
                parent.setRightChild(y);
            }
        } else {
            y.setParentNode(null);
            rootNode = y;
        }
    }

    protected void rotateRight(TreeNode y) {
        if (y == null) {
            return;
        }

        final TreeNode parent = y.getParentNode();
        final TreeNode x = y.getLeftChild();
        if (x.isLeaf()) {
            return;
        }

        final TreeNode b = x.getRightChild();

        y.setLeftChild(b);
        x.setRightChild(y);

        if (parent != null) {
            if (parent.getRightChild() == y) {
                parent.setRightChild(x);
            } else if (parent.getLeftChild() == y) {
                parent.setLeftChild(x);
            }
        } else {
            rootNode = x;
        }
    }

    public TreeNode successor(TreeNode node) {
        if (node.hasRightChild()) {
            return findMinKeyNode(node.getRightChild());
        }

        TreeNode n = node;
        TreeNode p = node.getParentNode();
        while( p != null && p.getRightChild() == n) {
            n = p;
            p = p.getParentNode();
        }
        return p;
    }

    public TreeNode predecessor(TreeNode node) {
        if (node.hasLeftChild()) {
            return findMaxKeyNode(node.getLeftChild());
        }

        TreeNode n = node;
        TreeNode p = node.getParentNode();
        while( p != null && p.getLeftChild() == n) {
            n = p;
            p = p.getParentNode();
        }
        return p;
    }

    public void inOrderTreeWalk(TreeNode node) {
        if (node != null) {
            inOrderTreeWalk(node.getLeftChild());
            System.out.println(node.getKey());
            inOrderTreeWalk(node.getRightChild());
        }
    }

    public void preOrderTreeWalk(TreeNode node) {
        if (node != null) {
            System.out.println(node.getKey());
            preOrderTreeWalk(node.getLeftChild());
            preOrderTreeWalk(node.getRightChild());
        }
    }

    public void postOrderTreeWalk(TreeNode node) {
        if (node != null) {
            postOrderTreeWalk(node.getLeftChild());
            postOrderTreeWalk(node.getRightChild());
            System.out.println(node.getKey());
        }
    }

}
