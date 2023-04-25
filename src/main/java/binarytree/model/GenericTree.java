package binarytree.model;

public abstract class GenericTree<T> {
    protected Node <T> root;

    public boolean insert(T value) {
        root = insert(root, value);
        return root != null;
    }

    private Node insert(Node<T> currentNode, T value) {
        if (currentNode == null) {
            return new Node(value);
        }

        if (valueLessThanNode(currentNode.data, value)) {
            currentNode.left = insert(currentNode.left, value);
        } else if (valueGreaterThanNode(currentNode.data, value)) {
            currentNode.right = insert(currentNode.right, value);
        }

        return currentNode;
    }

    public boolean remove(T value) {
        root = remove(root, value);
        return root != null;
    }

    private Node remove(Node<T> currentNode, T value) {
        if (currentNode == null) {
            return null;
        }
        if (valueEqualNode(currentNode.data, value)) {
            return removeNode(currentNode);
        }
        if (valueLessThanNode(currentNode.data, value)) {
            currentNode.left = remove(currentNode.left, value);
        }
        else {
            currentNode.right = remove(currentNode.right, value);
        }
        return currentNode;
    }

    private Node<T> removeNode(Node<T> node) {
        if (node.left == null && node.right == null) {
            return null;
        }

        if (node.right == null) {
            return node.left;
        }

        if (node.left == null) {
            return node.right;
        }

        T maxValue = findMaxValue(node.left);
        node.data = maxValue;
        node.left = remove(node.left, maxValue);

        return node;
    }

    private T findMaxValue(Node<T> root) {
        return root.right == null ? root.data : findMaxValue(root.right);
    }

    public boolean contains(T value) {
        return contains(root, value);
    }

    private boolean contains(Node<T> currentNode, T value) {
        if (currentNode == null) {
            return false;
        }

        if (valueEqualNode(currentNode.data, value)) {
            return true;
        }

        if (valueGreaterThanNode(currentNode.data, value)) {
            return contains(currentNode.right, value);
        }

        return contains(currentNode.left, value);
    }

    public boolean isEmpty() {
        return root == null;
    }

    public int count() {
        return count(root);
    }

    private int count(Node<T> currentNode) {
        if (currentNode == null) {
            return 0;
        }

        return count(currentNode.left) + 1 + count(currentNode.right);
    }

    public void traverseInOrder() {
        traverseInOrder(root);
    }

    private void traverseInOrder(Node<T> node) {
        if (node == null) {
            return;
        }

        traverseInOrder(node.left);
        visit(node.data);
        traverseInOrder(node.right);
    }

    public GenericTree<T> clearTree() {
        this.root = null;
        return this;
    }

    public abstract boolean valueGreaterThanNode(T nodeData, T value);

    public abstract boolean valueEqualNode(T nodeData, T value);

    public abstract boolean valueLessThanNode(T nodeData, T value);

    public void visit(T value) {
        System.out.println(value);
    }
}