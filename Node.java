public class Node<K, V>{



    private Node<K, V> parent;
    private Node<K, V> leftChild;
    private Node<K, V> rightChild;
    private K key;
    private V value;

    Node(){
        key = null;
        value = null;
    }

    Node(K key, V value){

        this.key = key;
        this.value = value;

    }

    public K getKey() {
        return key;
    }

    public V getValue() {
        return value;
    }

    public Node<K, V> getParent() {
        return parent;
    }
    public Node<K, V> getLeftChild() {
        return leftChild;
    }

    public Node<K, V> getRightChild() {
        return rightChild;
    }

    public void setParent(Node<K, V> parent) {
        this.parent = parent;
    }

    public void setLeftChild(Node<K, V> leftChild) {
        this.leftChild = leftChild;
    }

    public void setRightChild(Node<K, V> rightChild) {
        this.rightChild = rightChild;
    }

    public void setKey(K key) {
        this.key = key;
    }

    public void setValue(V value) {
        this.value = value;
    }
}
