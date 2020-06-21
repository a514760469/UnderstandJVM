package com.algorithm.leetcode;

/**
 * 124。二叉树中的最大路径和
 * @author zhanglifeng
 * @since 2020-05-15 14:07
 */
public class MaxPathSum {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(-3);
//        root.left = new TreeNode(2);
//        root.right = new TreeNode(3);
//        root.left.left = new TreeNode(1);
//        root.left.right = new TreeNode(1);
        System.out.println(root);
        int result = maxPathSum(root);
        System.out.println("res: " + result);
    }



    public static int maxPathSum(TreeNode root) {
        maxGain(root);
        return gain;
    }

    static int gain = Integer.MIN_VALUE;// 初始结果，是一个最小值

    private static int maxGain(TreeNode treeNode) {
        if (treeNode == null) {
            return 0;
        }
        // 当maxGain返回负数，应当置0表示最大路径不包含子树
        int l = Math.max(0, maxGain(treeNode.left));
        int r = Math.max(0, maxGain(treeNode.right));
        // 判断当前节点左右子树的路径和是否大于当前最大路径gain
        gain = Math.max(gain, l + r + treeNode.val);
        return Math.max(l, r) + treeNode.val;
    }
}
