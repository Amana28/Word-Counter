import com.sun.javaws.IconUtil;

import java.util.Stack;

public class BinarySearchTree<K extends Comparable<K>, V>
        implements Dictionary<K, V> {

    private Node<K, V> root;
    private int size = 0;

    // if key is present add key at the appropriate position and return null
    // if a key is already present return old value and replace it with the new one
    public V add(K key, V value) {

        if (root == null) {

            // if BST is empty, set the (key, value) pair as root
            root = new Node<>(key, value);
        }

        Node<K, V> addPosition = findPosition(key);
        V oldValue;

        // if key is being added the first time
        // determine adding position (left or right)
        int compare = addPosition.getKey().compareTo(key);
        if (compare < 0) {
            addPosition.setRightChild(new Node<>(key, value));
            addPosition.getRightChild().setParent(addPosition); // set adding position as parent
        }

        // if key is already present in BST
        else if (compare == 0) {
            oldValue = addPosition.getValue();
            addPosition.setValue(value);
            return oldValue; // return old value without increasing size
        } else {
            addPosition.setLeftChild(new Node<>(key, value));
            addPosition.getLeftChild().setParent(addPosition); // set adding position as parent
        }

        // at this point increase size and return null
        size++;
        return null;



    }

    public V remove(K key) {

        Node<K, V> current = findPosition(key);

        // the cases
        // key is not in BST
        if (current.getKey() != key) {
            return null;
        }

        // key is a leaf
        // just remove key
        else if (current.getLeftChild().getKey() == null && current.getRightChild().getKey() == null) {

            int value = current.getParent().getKey().compareTo(key);
            if (value < 0) {
                current.getParent().setRightChild(null);
            } else {
                current.getParent().setLeftChild(null);
            }
        }

        // key doesn't have either left or right children
        // just shift positions
        else if (current.getLeftChild().getKey() == null || current.getRightChild().getKey() == null) {

            if (current.getLeftChild().getKey() == null) {
                current.getRightChild().setParent(current.getParent());
                current.getParent().setRightChild(current.getRightChild());
            } else {
                current.getLeftChild().setParent(current.getParent());
                current.getParent().setLeftChild(current.getLeftChild());
            }

        }

        // if key has two children
        // replace with successor
        else {

            Node<K, V> successor = current.getRightChild();

            while (successor != null) {

                successor = successor.getLeftChild();
            }

            // store value (val) in current before it gets replaced
            // replace (key and value) of current, with (key and value) of successor
            // remove successor

            V val = current.getValue();
            current.setKey(successor.getKey());
            current.setValue(successor.getValue());
            remove(successor.getKey());
            size--;
            return val;

        }

        size--;
        return current.getValue();
    }

    public V lookup(K key) {

        // return the value associated with the key if it is present in our tree
        // use 'tracker' to traverse our tree starting from root

        if (root == null) {
            return null;
        }

        Node<K, V> tracker = root;
        while (tracker != null) {

            int compare = tracker.getKey().compareTo(key);

            if (compare < 0)
                tracker = tracker.getRightChild();

            else if (compare > 0)
                tracker = tracker.getLeftChild();

            else
                return tracker.getValue();

        }// if this is reached, key is not present


        // return null
        return null;

    }

    public void inOrderTraverse() {

        // starting from the root and using a stack similar to a call stack
        // to keep track of our BST and while() loops to have recursive behavior
        if (root == null)
            return;

        Node<K, V> curr = root;
        Stack<Node<K, V>> orderedStack = new Stack<>();
        Node<K, V> toPrint;
        orderedStack.push(curr);

        while (orderedStack.size() > 0) {

            while (curr != null) {

                curr = curr.getLeftChild();
                if (curr != null) {
                    orderedStack.push(curr);
                } else {
                    toPrint = orderedStack.peek();
                    System.out.println("(" + toPrint.getKey() + ", " + toPrint.getValue() + ")" + "\n");
                    curr = orderedStack.pop().getRightChild();
                    if (curr != null) {
                        orderedStack.push(curr);
                    }

                }
            }
            if (orderedStack.size() > 0) {

                toPrint = orderedStack.peek();
                System.out.println("(" + toPrint.getKey() + ", " + toPrint.getValue() + ")" + "\n");
                curr = orderedStack.pop().getRightChild();
                if (curr != null) {
                    orderedStack.push(curr);
                }


            }

        }

    }

    private Node<K, V> findPosition(K key) {

        // starting from root traverse through BST
        // keep track the position 'key' can be added to
        if (root == null) {
            return null;
        }
        Node<K, V> tracker = root;
        Node<K, V> position = null;


        while (tracker != null) {

            position = tracker;

            int compare = key.compareTo(tracker.getKey());

            if (compare < 0)
                tracker = tracker.getLeftChild();
            else if (compare > 0)
                tracker = tracker.getRightChild();
            else
                return position;

        }// if this is reached key was not is our BST

        return position;
    }

}
