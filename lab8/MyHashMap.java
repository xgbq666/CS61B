import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class MyHashMap<k, v> implements Map61B<k, v>{
    private int initialSize;
    private int size;
    private double loadFactor;
    private ArrayList<HashNode> hashList;
    class HashNode{
        k key;
        v value;
        HashNode next;

        HashNode(k key, v value) {
            this.key = key;
            this.value = value;
            this.next = null;
        }

        HashNode(HashNode node) {
            key = node.key;
            value = node.value;
            next = null;
        }
    }

    public MyHashMap() {
        this(16, 0.75);
    }

    public MyHashMap(int initialSize) {
        this(initialSize, 0.75);
    }

    public MyHashMap(int initialSize, double loadFactor) {
        this.initialSize = initialSize;
        this.loadFactor = loadFactor;
        this.size = 0;
        hashList = new ArrayList<>();
        for (int i = 0; i < this.initialSize; i += 1) {
            hashList.add(new HashNode(null, null));
        }
    }

    private void resize() {
        this.initialSize *= 2;
        ArrayList<HashNode> newHashList = new ArrayList<>();
        for (int i = 0; i < initialSize; i += 1) {
            newHashList.add(new HashNode(null, null));
        }
        for (int i = 0; i < initialSize/2; i += 1) {
            HashNode node = hashList.get(i);
            while (node != null) {
                if (node.key == null) {
                    node = node.next;
                    continue;
                }
                int index = node.key.hashCode() % initialSize;
                if (index < 0) {
                    index = 0;
                }
                HashNode tempNode = newHashList.get(index);
                while (tempNode.next != null) {
                    tempNode = tempNode.next;
                }
                tempNode.next = new HashNode(node);
                node = node.next;
            }
        }
        hashList = newHashList;
    }

    @Override
    public void clear() {
        for (int i = 0; i < initialSize; i += 1) {
            hashList.set(i, new HashNode(null, null));
        }
        size = 0;
    }

    @Override
    public boolean containsKey(k key) {
        int index = key.hashCode() % initialSize;
        if (index < 0) {
            index = 0;
        }
        HashNode node = hashList.get(index);
        while (node != null) {
            if (node.key != null && node.key.equals(key)) {
                return true;
            }
            node = node.next;
        }
        return false;
    }

    @Override
    public v get(k key) {
        int index = key.hashCode() % initialSize;
        if (index < 0) {
            index = 0;
        }
        HashNode node = hashList.get(index);
        while (node != null) {
            if (node.key != null && node.key.equals(key)) {
                return node.value;
            }
            node = node.next;
        }
        return null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void put(k key, v value) {
        int index = key.hashCode() % initialSize;
        if (index < 0) {
            index = 0;
        }
        HashNode node = hashList.get(index);
        while (node.next != null) {
            if (node.key != null && node.key.equals(key)) {
                node.value = value;
                return;
            }
            node = node.next;
        }
        if (node.key != null && node.key.equals(key)) {
            node.value = value;
            return;
        }
        node.next = new HashNode(key, value);
        size += 1;
        if ((double) size / (double) initialSize >= loadFactor) {
            resize();
        }
    }

    @Override
    public Set<k> keySet() {
        Set<k> set = new HashSet<>();
        for (int i = 0; i < size; i += 1) {
            for (int p = 0; p < initialSize; p += 1) {
                HashNode node = hashList.get(p).next;
                while (node != null) {
                    set.add(node.key);
                    node = node.next;
                    i += 1;
                }
            }
        }
        return set;
    }

    @Override
    public v remove(k key) {
        throw new UnsupportedOperationException();
    }

    @Override
    public v remove(k key, v value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterator<k> iterator() {
        return new MyHashMapIterator<k>();
    }

    private class MyHashMapIterator<k> implements Iterator<k>{
        private HashNode node;
        private int index;
        private int number;

        MyHashMapIterator() {
            index = 0;
            number = 0;
            node = hashList.get(index);
            while (node.next == null) {
                index += 1;
                node = hashList.get(index);
            }
        }

        @Override
        public boolean hasNext() {
            if (number < size) {
                return true;
            }
            return false;
        }

        @Override
        public k next() {
            if (hasNext()) {
                node = node.next;
                while (node == null) {
                    index += 1;
                    node = hashList.get(index).next;
                }
                number += 1;
                return (k) node.key;
            }
            return null;
        }
    }
}
