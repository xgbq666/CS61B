import java.util.Iterator;
import java.util.Set;

public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V> {
    private static class BSTNode<K, V> {
        private K key;
        private V value;
        private BSTNode<K, V> leftChild;
        private BSTNode<K, V> rightChild;

        BSTNode(K key, V value) {
            this.key = key;
            this.value = value;
            this.leftChild = null;
            this.rightChild = null;
        }
    }

    private BSTNode<K, V> root;
    private int size;

    public BSTMap() {
        root = new BSTNode<>(null, null);
        size = 0;
    }

    @Override
    public void clear() {
        root = new BSTNode<>(null, null);
        size = 0;
    }

    private boolean searchTree(BSTNode<K, V> node, K key) {
        if (node == null) {
            return false;
        }
        if (key.compareTo(node.key) > 0) {
            return searchTree(node.rightChild, key);
        } else if (key.compareTo(node.key) == 0) {
            return true;
        } else {
            return searchTree(node.leftChild, key);
        }
    }

    @Override
    public boolean containsKey(K key) {
        if (size == 0) {
            return false;
        }
        return searchTree(root, key);
    }

    private V searchValueInTree(BSTNode<K, V> node, K key) {
        if (node == null) {
            return null;
        }
        if (key.compareTo(node.key) > 0) {
            return searchValueInTree(node.rightChild, key);
        } else if (key.compareTo(node.key) == 0) {
            return node.value;
        } else {
            return searchValueInTree(node.leftChild, key);
        }
    }

    @Override
    public V get(K key) {
        if (size == 0) {
            return null;
        }
        return searchValueInTree(root, key);
    }

    @Override
    public int size() {
        return this.size;
    }

    private BSTNode<K, V> putHelper(BSTNode<K, V> node, K key, V value) {
        if (node == null) {
            return new BSTNode<>(key, value);
        }
        if (key.compareTo(node.key) < 0) {
            node.leftChild = putHelper(node.leftChild, key, value);
        } else if (key.compareTo(node.key) > 0) {
            node.rightChild = putHelper(node.rightChild, key, value);
        }
        return node;
    }

    @Override
    public void put(K key, V value) {
        if (size == 0) {
            root.key = key;
            root.value = value;
        }
        putHelper(root, key, value);
        size += 1;
    }

    @Override
    public Set<K> keySet() {
        throw new UnsupportedOperationException();
    }

    @Override
    public V remove(K key) {
        throw new UnsupportedOperationException();
    }

    @Override
    public V remove(K key, V value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterator<K> iterator() {
        throw new UnsupportedOperationException();
    }

    private void printInOrderHelper(BSTNode<K, V> node) {
        if (node.leftChild != null) {
            printInOrderHelper(node.leftChild);
        }
        System.out.println(node.key);
        if (node.rightChild != null) {
            printInOrderHelper(node.rightChild);
        }
    }

    public void printInOrder() {
        printInOrderHelper(root);
    }
}
