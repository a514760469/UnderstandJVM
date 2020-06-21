package com.algorithm.leetcode;

/**
 * 222. 完全二叉树的节点个数
 * @author zhanglifeng
 * @since 2020-06-05 15:42
 */
public class CountNodes {

    public static void main(String[] args) {
        CountNodes solution = new CountNodes();
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        int res = solution.countNodes(root);
        System.out.println(res);

    }

    public int countNodes(TreeNode root) {
        int hl = 0;
        int hr = 0;
        TreeNode left = root;
        TreeNode right = root;
        // 先算左子树高度
        while (left != null) {
            hl++;
            left = left.left;
        }
        // 再算右子树高度
        while (right != null) {
            hr++;
            right = right.right;
        }
        // 如果高度相同 按满二叉树计算
        if (hl == hr) {
            return (int) Math.pow(2, hl) - 1;
        }
        // 高度不同按普通二叉树计算
        return countNodes(root.left) + countNodes(root.right) + 1;
    }
}
