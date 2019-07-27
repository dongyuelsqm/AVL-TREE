package cn.moon.dong.avl.impl;

import java.util.Arrays;
import java.util.function.Function;
import java.util.function.Predicate;

public enum NodeStatus {
    LL(node -> node.leftHeight() - node.rightHeight() >= 2 && node.left.leftHeight() < node.left.rightHeight(), NodeStatus::rightRotation),
    LR(node -> node.leftHeight() - node.rightHeight() >= 2 && node.left.leftHeight() > node.left.rightHeight(), node -> {
        node.left = leftRotation(node.left);
        return rightRotation(node);
    }),
    RL(node -> node.rightHeight() - node.leftHeight() >=2 && node.right.leftHeight() > node.rightHeight(), node -> {
        rightRotation(node.right);
        return leftRotation(node);
    }),
    RR(node -> node.rightHeight() - node.leftHeight() >=2 && node.right.leftHeight() < node.rightHeight(), node -> {
        return leftRotation(node);
    }),
    BALANCE(node -> node.leftHeight() - node.rightHeight() < 2, node -> node);

    NodeStatus(Predicate<AvlNode> predicate, Function<AvlNode, AvlNode> rotate) {
        this.predicate = predicate;
        this.rotate = rotate;
    }

    private static AvlNode rightRotation(AvlNode node){
        AvlNode newRoot = node.left;
        node.left = newRoot.right;
        node.calculateHeight();

        newRoot.right = node;
        newRoot.calculateHeight();
        return newRoot;
    }

    private static AvlNode leftRotation(AvlNode node){
        AvlNode newRoot = node.right;
        node.right = newRoot.left;
        node.calculateHeight();
        newRoot.left = node;
        newRoot.calculateHeight();
        return newRoot;
    }

    private Predicate<AvlNode> predicate;
    private Function<AvlNode, AvlNode> rotate;
    public AvlNode rotate(AvlNode node){
        return rotate.apply(node);
    }

    public static NodeStatus status(AvlNode node){
        return Arrays.stream(NodeStatus.values())
                .filter(nodeStatus -> nodeStatus.predicate.test(node)).findFirst()
                .orElse(BALANCE);
    }
}
