package com.algorithm.tree;

import java.util.LinkedList;

/**
 * 二叉查找树
 * @param <K>
 * @param <V>
 */
public class BST<K extends Comparable<? super K>, V> {
    /**
     * 节点
     */
    private class Node {
        K key;
        V value;
        Node left;
        Node right;

        Node(K k, V v) {
            this.key = k;
            this.value = v;
            this.left = null;
            this.right = null;
        }
    }

    private Node root;
    private int count;

    public BST() {

    }

    int size() {
        return count;
    }

    boolean isEmpty() {
        return count == 0;
    }

    /**
     * 查找
     * @param key
     * @return
     */
    V search(K key) {
        return search(root, key);
    }


    /**
     * 是否包含k这个元素的节点
     * @param key
     * @return
     */
    boolean contains(K key) {
        return contains(root, key);
    }

    /**
     * 查找
     * @param node
     * @param k
     * @return
     */
    private V search(Node node, K k) {
        if (node == null) {
            return null;
        }

        if (k.compareTo(node.key) == 0) {
            return node.value;
        } else if (k.compareTo(node.key) < 0) {
            return search(node.left, k);
        } else {
            return search(node.right, k);
        }
    }
    /**
     * 是否包含k这个元素的节点 递归
     * @param node
     * @param k
     * @return
     */
    private boolean contains(Node node, K k) {
        if (node == null) {
            return false;
        }
        if (k == node.key) {
            return true;
        }
        else if (k.compareTo(node.key) < 0) {
            return contains(node.left, k);
        } else  {
            return contains(node.right, k);
        }
    }

    /**
     * 插入
     * @param key
     * @param value
     */
    void insert(K key, V value) {
        root = insert(root, key, value);

    }

    /**
     * 进行递归的方法
     * 以node为根的二叉树中插入(k, v) , 返回插入新节点后的二叉树的根
     * @param node
     * @param k
     * @param v
     * @return
     */
    private Node insert(Node node, K k, V v) {
        if (node == null) {
            count++;
            return new Node(k, v);
        }

        if (k.compareTo(node.key) == 0) {
            node.value = v;
        }
        else if (k.compareTo(node.key) < 0) {
            node = insert(node.left, k, v);
        } else if (k.compareTo(node.key) > 0) {
            node = insert(node.right, k, v);
        }
        return node;
    }

    /**
     * 前序遍历
     */
    void preOrder() {
        preOrder(root);
    }

    /**
     * 中序遍历
     */
    void inOrder() {
        inOrder(root);
    }

    /**
     * 后序遍历
     */
    void postOrder() {
        postOrder(root);
    }

    /**
     * 层序遍历
     */
    void levelOrder() {
        LinkedList<Node> queue = new LinkedList<>();
        queue.push(root);
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            System.out.println(node.key);
            if (null != node.left) {
                queue.push(node.left);
            }
            if (null != node.right) {
                queue.push(node.right);
            }
        }
    }

    private void postOrder(Node node) {
        if (node != null) {
            postOrder(node.left);
            postOrder(node.right);
            System.out.println(node.key);
        }
    }
    /**
     * 中序遍历node
     * @param node
     */
    private void inOrder(Node node) {
        if (node != null) {
            inOrder(node.left);
            System.out.println(node.key);
            inOrder(node.right);
        }
    }
    /**
     * 对node为根进行前序遍历
     */
    private void preOrder(Node node) {
        if (node != null) {
            System.out.println(node.key);
            preOrder(node.left);
            preOrder(node.right);
        }
    }

    /**
     * 查找最小值
     * @return
     */
    K minimum() {
        assert count != 0;

        Node minNode = minimum(root);
        return minNode.key;
    }

    /**
     * 查找最大值
     * @return
     */
    K maximum() {
        assert count != 0;

        Node minNode = maximum(root);
        return minNode.key;
    }

    /**
     *
     * @param node
     * @return
     */
    private Node minimum(Node node) {
        if (node.left == null) {
            return node;
        }
        return minimum(node.left);
    }

    /**
     *
     * @param node
     * @return
     */
    private Node maximum(Node node) {
        if (node.right == null) {
            return node;
        }
        return minimum(node.right);
    }

    void removeMin() {
        if (root != null) {
            root = removeMin(root);
        }
    }

    void removeMax() {
        if (root != null) {
            root = removeMax(root);
        }
    }
    private Node removeMax(Node node) {
        if (node.right == null) {
            Node leftNode = node.left;
            count--;
            return leftNode;
        }
        node.right = removeMax(node.right);
        return node;
    }


    /**
     * 删除以node为根的最小节点，返回删除节点后的根
     * @param node
     */
    private Node removeMin(Node node) {
        if (node.left == null) {
            Node rightNode = node.right;
            count--;
            return rightNode;
        }
        node.left = removeMin(node.left);
        return node;
    }

    void remove(K key) {
        root = remove(root, key);
    }

    /**
     * 删除以node为根的树中的键为key的节点
     * @param node
     * @param key
     * @return 返回删除节点之后的新的树的根
     */
    private Node remove(Node node, K key) {
        if (node == null) {
            return null;
        }

        if (key.compareTo(node.key) < 0) {
            node.left = remove(node.left, key);
            return node;
        } else if (key.compareTo(node.key) > 0) {
            node.right = remove(node.right, key);
            return node;
        } else {
            // key == node.key
            if (node.left == null) {
                count--;
                return node.right;
            }
            if (node.right == null) {
                count--;
                return node.left;
            }
            /* 左右都不为空的情况 */
            Node successor = minimum(node.right);
            count++;
            successor.right = removeMin(node.right);
            successor.left = node.left;
            count--;
            return successor;
        }
    }
}
