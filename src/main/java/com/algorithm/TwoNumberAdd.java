package com.algorithm;

public class TwoNumberAdd {
	/**
	 * 输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
	 * 输出：7 -> 0 -> 8
	 * 原因：342 + 465 = 807
	 * @param args
	 */
	public static void main(String[] args) {
		ListNode l1 = new ListNode(2);
		l1.next = new ListNode(4);
		l1.next.next = new ListNode(3);
		l1.next.next.next = new ListNode(1);
		System.out.println(l1);

		ListNode l2 = new ListNode(5);
		l2.next = new ListNode(6);
		l2.next.next = new ListNode(4);
		System.out.println(l2);
		
		ListNode result = addTwoNumbers(l1, l2);
		System.out.println(result);
	}
	
	/**
	 * 两数相加
	 * @param l1
	 * @param l2
	 * @return
	 */
	public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
		ListNode listNode = null;// 返回的listNode
		boolean flag = false;// 是否大于10
        int res = l1.val + l2.val;
        if(res >= 10) {
            res %= 10;
            flag = true;
        }
        ListNode resNode = new ListNode(res);
        listNode = resNode;
        l1 = l1.next == null ? null : l1.next;
        l2 = l2.next == null ? null : l2.next;
        while (null != l1 || null != l2) {	// l1 l2 都有值
            int num1 = l1 == null ? 0 : l1.val;
            int num2 = l2 == null ? 0 : l2.val;
            res = num1 + num2;
            if(flag) {
                res += 1;
                flag = false;
            }
            if(res >= 10) {
                res %= 10;
                flag = true;
            }
            resNode.next = new ListNode(res);
            resNode = resNode.next;
            if(l1 != null) 
            	l1 = l1.next == null ? null : l1.next;
            if(l2 != null) 
            	l2 = l2.next == null ? null : l2.next;
        }
        if(flag) {
            resNode.next = new ListNode(1);
        }
        return listNode;
    }
	
}

class ListNode {
	int val;
	ListNode next;

	ListNode(int x) {
		val = x;
	}

	@Override
	public String toString() {
		return "{val=" + val + ", next=" + next + "}";
	}
	
}