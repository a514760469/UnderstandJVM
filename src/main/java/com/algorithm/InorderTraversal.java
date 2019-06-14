package com.algorithm;

import java.util.ArrayList;
import java.util.List;

/**
 * 二叉树的中序遍历
 * 左根右
 */
public class InorderTraversal {

	/**
	 *     1 
	 *       2
	 *     3 
	 * @return 初始化树
	 */
	public static TreeNode initTree() {
		TreeNode root = new TreeNode(1);
		root.right = new TreeNode(2);
		root.right.left = new TreeNode(3);
		return root;
	}
	
	private static List<Integer> res = new ArrayList<>();
	
	public static void main(String[] args) {
		TreeNode treeNode = initTree();
		System.out.println(inorderTraversal(treeNode));
	}
	
	public static List<Integer> inorderTraversal(TreeNode root) {
		recursion(initTree());
		return res;
	}
	
	/**
	 * 中序遍历: 递归的方式
	 * @param treeNode
	 */
	public static void recursion(TreeNode treeNode) {
		if(treeNode == null) {
			return;
		}
		if(treeNode.left == null) {
			res.add(treeNode.val);
			recursion(treeNode.right);
		} else {
			recursion(treeNode.left);
			res.add(treeNode.val);
		}
	}
}
