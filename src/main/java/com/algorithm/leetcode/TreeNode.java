package com.algorithm.leetcode;

/**
 * 二叉树节点的定义
 * @author zhanglifeng
 * @since 2020-05-15 14:05
 */
public class TreeNode {

    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int val) {
        this.val = val;
    }

    @Override
    public String toString() {
        return "{ val=" + val + ", left=" + left + ", right=" + right + '}';
    }
}
