package task9_AVLtree.impl;

public class BinarySearchTree extends Tree {

    @Override
    public void insert(int key) {
        if (rootNode == null) {
            rootNode = new TreeNode(key);
        } else {
            TreeNode currentParent = searchParentIterative(key);
            final TreeNode newNode = new TreeNode(currentParent, null, null, key);

            if (key <= currentParent.getKey()) {
                currentParent.setLeftChild(newNode);
            } else {
                currentParent.setRightChild(newNode);
            }
        }
        size += 1;
    }

    @Override
    public boolean search(int key) {
        return searchNodeRecursive(rootNode, key) != null;
    }

    @Override
    public boolean remove(int key) {
        TreeNode node = searchNodeRecursive(rootNode, key);
        if (node == null) {
            return false;
        }

        TreeNode parentNode = node.getParentNode();

        if (node.isLeaf()) {//isLeaf
            if (parentNode != null) {
                if (node == parentNode.getLeftChild()) {
                    parentNode.setLeftChild(null);
                } else if (node == parentNode.getRightChild()) {
                    parentNode.setRightChild(null);
                } else {
                    throw new RuntimeException("This shouldn never happen");
                }
            } else {
                rootNode = null;
            }

            node.reset();

        } else if (!node.hasLeftChild() || !node.hasRightChild()) { //node has only one child
            TreeNode childNode = node.hasLeftChild() ? node.getLeftChild() : node.getRightChild();
            replaceChildNode(parentNode, node, childNode);
        } else {    //node has both left and right children
            final TreeNode newNode = findMinKeyNode(node.getRightChild());
            newNode.detach();
            newNode.setLeftChild(node.getLeftChild());
            newNode.setRightChild(node.getRightChild());

            replaceChildNode(parentNode, node, newNode);

            node.reset();
        }

        size -= 1;
        return true;
    }

    private TreeNode searchNodeRecursive(final TreeNode currentNode, final int key) {
        if (currentNode == null || currentNode.getKey() == key) {
            return currentNode;
        }

        return key <= currentNode.getKey() ? searchNodeRecursive(currentNode.getLeftChild(), key) : searchNodeRecursive(currentNode.getRightChild(), key);
    }

    private TreeNode searchNodeIterative(final int key) {
        TreeNode node = rootNode;

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

    private TreeNode searchParentIterative(final int key) {
        TreeNode currentParent = null;
        TreeNode current = rootNode;
        while(current != null) {
            currentParent = current;
            current = key <= current.getKey() ? current.getLeftChild() : current.getRightChild();
        }
        return currentParent;
    }

    private void replaceChildNode(final TreeNode parentNode, final TreeNode oldNode, final TreeNode newNode) {
        newNode.setParentNode(parentNode);

        if (parentNode != null && parentNode.getLeftChild() == oldNode) {
            parentNode.setLeftChild(newNode);
        } else if (parentNode != null && parentNode.getRightChild() == oldNode) {
            parentNode.setRightChild(newNode);
        } else if (parentNode == null) {
            rootNode = newNode;
        } else {
            throw new RuntimeException("This should never happen");
        }
    }

    public TreeNode findMinKeyNode(TreeNode node) {
        while(node.hasLeftChild()) {
            node = node.getLeftChild();
        }
        return node;
    }

    public TreeNode findMaxKeyNode(TreeNode node) {
        while(node.hasRightChild()) {
            node = node.getRightChild();
        }
        return node;
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
