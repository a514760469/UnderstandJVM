package com.algorithm;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 二叉树遍历, 前序 中序 后序
 */
public class BinaryTreeTraversal {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.right = new TreeNode(2);
        root.right.left = new TreeNode(3);
        List<Integer> ls =  new BinaryTreeTraversal().preorderTraversal2(root);
        System.out.println(ls);
    }

    /**
     * 递归前序遍历
     * @param node
     */
    void preorder(TreeNode node) {
        if (node != null) {
            res.add(node.val);
            System.out.println(node);
            preorder(node.left);
            preorder(node.right);
        }
    }

    List<Integer> res = new ArrayList<>();

    /**
     * 使用递归方法前序
     * @param root
     * @return
     */
    public List<Integer> preorderTraversal(TreeNode root) {
        preorder(root);
        return res;
    }

    /**
     * 前序
     * 使用栈模拟系统的递归， 非递归的方法
     * @param root
     * @return
     */
    public List<Integer> preorderTraversal2(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        Stack<Command> stack = new Stack<>();
        if(root != null) {
            // 根节点入栈
            stack.push(new Command(Command.Cmd.GO, root));
        }
        while (!stack.empty()) {
            Command pop = stack.pop();
            if (pop.cmd == Command.Cmd.PRINT) {
                res.add(pop.treeNode.val);
            } else if(pop.cmd == Command.Cmd.GO) {
                // 前序 中序 后序 改顺序即可
                // 1右孩子入栈
                if(pop.treeNode.right != null) {
                    stack.push(new Command(Command.Cmd.GO, pop.treeNode.right));
                }
                // 2左孩子入栈
                if(pop.treeNode.left != null) {
                    stack.push(new Command(Command.Cmd.GO, pop.treeNode.left));
                }
                // 3打印
                stack.push(new Command(Command.Cmd.PRINT, pop.treeNode));
            }
        }
        return res;
    }

    /**
     * 指令
     */
    private static class Command {
//        String msg;
        TreeNode treeNode;
        Cmd cmd;

        Command(Cmd cmd, TreeNode treeNode) {
            this.cmd = cmd;
            this.treeNode = treeNode;
        }

        // go执行  print打印
        enum Cmd {
            GO, PRINT;
        }
    }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }

    @Override
    public String toString() {
        return "TreeNode {val=" + val + ", left=" + left + ", right=" + right + "}";
    }

}