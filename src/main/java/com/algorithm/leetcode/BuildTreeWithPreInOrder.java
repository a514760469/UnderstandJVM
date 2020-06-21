package com.algorithm.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * 105. 从前序与中序遍历序列构造二叉树
 * 前序遍历 preorder = [3,9,20,15,7]
 * 中序遍历 inorder = [9,3,15,20,7]
 * @author zhanglifeng
 * @since 2020-05-15 18:12
 */
public class BuildTreeWithPreInOrder {

    /**
     * @param args
     */
    public static void main(String[] args) {
        int[] preorder = {3, 9, 20, 15, 7};
        int[] inorder = {9, 3, 15, 20, 7};
        TreeNode tree = new BuildTreeWithPreInOrder().buildTree(preorder, inorder);
        System.out.println(tree);
    }

    Map<Integer, Integer> inMap = new HashMap<>();
    int[] preorder;
    int[] inorder;
    private int startPreIdx;// 前序遍历索引，创建节点后索引向右移 表示删除

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        this.preorder = preorder;
        this.inorder = inorder;
        for (int i = 0; i < inorder.length; i++) {
            inMap.put(inorder[i], i);
        }

        return buildHelper(0, preorder.length - 1, 0, inorder.length - 1);
    }

    public TreeNode buildHelper(int startPreIdx, int endPreIdx, int startInIdx, int endInIdx) {
        if (startPreIdx > endPreIdx || endInIdx < startInIdx) {
            return null;
        }

        int val = preorder[startPreIdx];// 从preorder中取值
        this.startPreIdx += 1;
        TreeNode root = new TreeNode(val);

        int inorderIdx = inMap.get(val);// 中序遍历序列的idx

        // 左子树
        root.left = buildHelper(this.startPreIdx, endPreIdx, startInIdx, inorderIdx - 1);
        root.right = buildHelper(this.startPreIdx, endPreIdx, inorderIdx + 1, endInIdx);

        return root;
    }
}
