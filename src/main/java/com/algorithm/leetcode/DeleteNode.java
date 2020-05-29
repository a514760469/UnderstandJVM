package com.algorithm.leetcode;

/**
 * 450. 删除二叉搜索树中的节点
 * @author zhanglifeng
 * @since 2020-05-29 17:02
 */
public class DeleteNode {

    public static void main(String[] args) {

        DeleteNode solution = new DeleteNode();
        TreeNode root = new TreeNode(1);
        root.right = new TreeNode(2);

        TreeNode treeNode = solution.deleteNode(root, 1);
        System.out.println(treeNode);
    }


    @SuppressWarnings("ConstantConditions")
    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) {
            return null;
        }
        if (root.val == key) {
            // 删除root节点 3中情况： 1、left、right都为空
            if (root.left == null && root.right == null) {
                return null;
            }
            // 2、左或右为空
            if (root.left == null) {
                return root.right;
            }
            if (root.right == null ) {
                return root.left;
            }
            // 3、都不为空    在右节点中找到最小的节点minNode, 和root交换后 删除minNode
            TreeNode minNode = getMin(root.right);
            root.val = minNode.val;
            root.right = deleteNode(root.right, minNode.val);

        } else if (root.val > key) {
            root.left = deleteNode(root.left, key);
        } else if (root.val < key) {
            root.right = deleteNode(root.right, key);
        }

        return root;
    }

    public TreeNode getMin(TreeNode node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }
}
