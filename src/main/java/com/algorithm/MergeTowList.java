package com.algorithm;

/**
 * 合并两个有序链表
 */
public class MergeTowList {
	
	public static void main(String[] args) {
		ListNode l1 = new ListNode(1);
		l1.next = new ListNode(2);
		l1.next.next = new ListNode(4);
		System.out.println(l1);

		ListNode l2 = new ListNode(1);
		l2.next = new ListNode(3);
		l2.next.next = new ListNode(4);
		System.out.println(l2);
		
		ListNode res = mergeTwoLists(null, l2);
		System.out.println(res);
	}
	
	@SuppressWarnings("Duplicates")
	public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode resNode = null;
        ListNode curNode = null; // 当前指针
        while(l1 != null || l2 != null) {
            if(l2 == null || (l1 != null && l1.val < l2.val)) {
            	if(resNode == null) {
                    resNode = new ListNode(l1.val);
                    curNode = resNode;
                }else {
                	curNode.next = new ListNode(l1.val);
                	curNode = curNode.next;
                }
            	l1 = l1.next;
            } else { // if(l1 == null || (l2 != null && l2.val <= l1.val))
            	if(resNode == null) {
                    resNode = new ListNode(l2.val);
                    curNode = resNode;
                }else {
                	curNode.next = new ListNode(l2.val);
                	curNode = curNode.next;
                }
            	l2 = l2.next;
            }
            
        }
        return resNode;
    }
}
