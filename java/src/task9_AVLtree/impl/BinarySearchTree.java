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
//        return searchNodeRecursive(rootNode, key) != null;
        return searchNodeIterative(key) != null;
    }

    @Override
    public boolean remove(int key) {
//        TreeNode node = searchNodeRecursive(rootNode, key);
        return remove(rootNode, key);
    }

    private boolean remove(final TreeNode root, final int key) {
        TreeNode node = searchNodeIterative(root, key);
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

            childNode.setParentNode(parentNode);

            if (parentNode != null && parentNode.getLeftChild() == node) {
                parentNode.setLeftChild(childNode);
            } else if (parentNode != null && parentNode.getRightChild() == node) {
                parentNode.setRightChild(childNode);
            } else if (parentNode == null) {
                rootNode = childNode;
            } else {
                throw new RuntimeException("This should never happen");
            }
        } else {    //node has both left and right children
            final TreeNode newNode = findMinKeyNode(node.getRightChild());
            node.setKey(newNode.getKey());
            remove(node.getRightChild(), key);
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
