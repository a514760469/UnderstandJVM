package com.algorithm;

/**
 * 二叉树的最大深度
 */
public class BinaryTreeMaxDepth {

    public static void main(String[] args) {
        System.out.println(new BinaryTreeMaxDepth().maxDepth(new TreeNode(0)));
    }

    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    }
}
