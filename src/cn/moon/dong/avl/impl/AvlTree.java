package cn.moon.dong.avl.impl;

public class AvlTree {
    private AvlNode mRoot;

    public void insert(int key){
        mRoot = insert(mRoot, key);
    }

    private AvlNode insert(AvlNode node, int key) {
        if (node == null){
            return new AvlNode(key, null, null);
        }else {
            insertKey(node, key);
            return NodeStatus.status(node).rotate(node);
        }
    }

    private void insertKey(AvlNode node, int key) {
        if (node.value > key){
            node.left = insert(node.left, key);
        }else if (node.value < key){
            node.right = insert(node.right, key);
        }else {
            throw new RuntimeException("duplicated key!");
        }
        node.calculateHeight();
    }

    public void printInOrder(AvlNode node){

        if (node != null){
            printInOrder(node.left);
            System.out.println(node.value);
            printInOrder(node.right);
        }
    }

    @Override
    public String toString() {
        return "AvlTree{" +
                "mRoot=" + mRoot +
                '}';
    }

    public static void main(String[] args) {
        AvlTree tree = new AvlTree();
        tree.insert(3);
        tree.insert(1);
        tree.insert(2);
        tree.insert(5);
        tree.insert(7);
        tree.insert(0);
        tree.insert(17);
        tree.insert(20);
        tree.insert(11);
        tree.insert(9);
        tree.printInOrder(tree.mRoot);
    }
}
