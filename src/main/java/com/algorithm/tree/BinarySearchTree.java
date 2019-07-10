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

    /**
     * constructors
     */
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
        // 从根开始递归
        return contains(x, root);
    }


    private boolean contains(AnyType x, BinaryNode<AnyType> node) {
        if (node == null) {
            return false;
        }
        int compareResult = x.compareTo(node.element);
        if (compareResult < 0) {
            return contains(x, node.left);
        } else if (compareResult > 0) {
            return contains(x, node.right);
        } else {
            // 匹配成功
            return true;
        }
    }

    /**
     * 查找最小
     * @param t 查找的起始节点
     * @return
     */
    private BinaryNode<AnyType> findMin(BinaryNode<AnyType> t) {
        if (t != null) {
            while (t.left != null) {
                t = t.left;
            }
        }
        return t;
    }

    /**
     * 查找最大
     * @param t 查找的起始节点
     * @return
     */
    private BinaryNode<AnyType> findMax(BinaryNode<AnyType> t) {
        if (t != null) {
            while (t.right != null) {
                t = t.right;
            }
        }
        return t;
    }

    /**
     * 插入
     * @param x
     * @param t 插入位置的父
     * @return 插入完成后的父
     */
    private BinaryNode<AnyType> insert(AnyType x, BinaryNode<AnyType> t) {
        if (t == null) {
            return new BinaryNode<>(x, null, null);
        }
        int compareResult = x.compareTo(t.element);
        if (compareResult < 0) {
            t.left = insert(x, t.left);
        } else if (compareResult > 0) {
            t.right = insert(x, t.right);
        } else {
            // 重复元素
            t.element = x;
        }

        return t;
    }
}





