package cn.moon.dong.avl.impl;

import java.util.Optional;

public class AvlNode {
    public int value;
    public AvlNode left;
    public AvlNode right;
    public int height;

    public AvlNode(int value, AvlNode left, AvlNode right) {
        this.value = value;
        this.left = left;
        this.right = right;
        height = 1;
    }

    public int leftHeight(){
        return Optional.ofNullable(left).map(AvlNode::getHeight).orElse(0);
    }

    public int rightHeight(){
        return Optional.ofNullable(right).map(AvlNode::getHeight).orElse(0);
    }

    public void calculateHeight(){
        height = Math.max(left.getHeight(), right.getHeight()) + 1;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public AvlNode getLeft() {
        return left;
    }

    public void setLeft(AvlNode left) {
        this.left = left;
    }

    public AvlNode getRight() {
        return right;
    }

    public void setRight(AvlNode right) {
        this.right = right;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
