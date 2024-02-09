package com.algorithm.hj;

import com.algorithm.leetcode.TreeNode;

import java.util.*;

/**
 * @author zhanglifeng
 * @since 2023-08-31
 */
public class BinaryTreeLevelOrderTraversal {

    public static void main(String[] args) {

        TreeNode node = new TreeNode();
        node.val = 3;
        node.left = new TreeNode(9);
        node.right = new TreeNode(20, new TreeNode(15), new TreeNode(7));

        List<List<Integer>> lists = new BinaryTreeLevelOrderTraversal().levelOrder(node);

        System.out.println(lists);


//        ArrayList<Integer> list = new ArrayList<>();
//        list.indexOf(1);
    }


    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        Map<TreeNode, Integer> map = new HashMap<>();
        map.put(root, 0);// 先把root 放入

        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            Integer level = map.get(node);
            if (ans.size() == level) {
                ans.add(new ArrayList<>());
            }
            ans.get(level).add(node.val);

            if (node.left != null) {
                queue.add(node.left);
                map.put(node.left, level + 1);
            }
            if (node.right != null) {
                queue.add(node.right);
                map.put(node.right, level + 1);
            }
        }
        return ans;
    }


    TreeNode[] queue = new TreeNode[2001];
    int l = 0;
    int r = 0;

    /**
     *
     */
    public List<List<Integer>> levelOrder2(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();
        if (root == null) {
            return ans;
        }
        queue[r++] = root;

        while (r > l) {
            int size = r - l;
            ArrayList<Integer> list = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue[l++];
                list.add(node.val);
                if (node.left != null) {
                    queue[r++] = node.left;
                }
                if (node.right != null) {
                    queue[r++] = node.right;
                }
            }
            ans.add(list);
        }

        return ans;
    }


    public List<List<Integer>> levelOrder3(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();
        if(root == null) {
            return ans;
        }
        queue[r++] = root;
        boolean flag = false;

        while (r > l) {
            int size = r - l;
            LinkedList<Integer> list = new LinkedList<>();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue[l++];
                if (flag) {
                    list.addFirst(node.val);
                } else {
                    list.add(node.val);
                }
                if (node.left != null) {
                    queue[r++] = node.left;
                }
                if (node.right != null) {
                    queue[r++] = node.right;
                }
            }
            flag = flag ? false : true;
            ans.add(list);
        }
        return ans;
    }
}
