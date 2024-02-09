package com.algorithm.leetcode;

/**
 * 二叉树节点的定义(leetcode)
 *
 * @author zhanglifeng
 * @since 2020-05-15 14:05
 */
public class TreeNode {

    public int val;
    public TreeNode left;
    public TreeNode right;

    public TreeNode() {
    }

    public TreeNode(int val) {
        this.val = val;
    }

    public TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }

    @Override
    public String toString() {
        return "{ val=" + val + ", left=" + left + ", right=" + right + '}';
    }
}
