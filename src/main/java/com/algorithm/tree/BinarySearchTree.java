package com.algorithm.tree;

/**
 * 二叉查找树
 * @param <AnyType>
 */
public class BinarySearchTree<AnyType extends Comparable<? super AnyType>> {
    /**
     * 树的节点
     * @param <AnyType>
     */
    private static class BinaryNode<AnyType> {

        AnyType element;

        BinaryNode<AnyType> left;

        BinaryNode<AnyType> right;

        BinaryNode (AnyType element, BinaryNode<AnyType> lt, BinaryNode<AnyType> rt) {
            this.element = element;
            this.left = lt;
            this.right = rt;
        }
    }

    private BinaryNode<AnyType> root;

    public BinarySearchTree() {
        root = null;
    }

    public void makeEmpty() {
        root = null;
    }

    public boolean isEmpty() {
        return root == null;
    }

    public boolean contains(AnyType x) {
        return contains(x, root);
    }

    private boolean contains(AnyType x, BinaryNode<AnyType> root) {
        //TODO
        return false;
    }
}





