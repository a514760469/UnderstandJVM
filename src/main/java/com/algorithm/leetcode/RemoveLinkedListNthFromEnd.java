package com.algorithm.leetcode;

/**
 * 19. 删除链表的倒数第N个节点
 */
public class RemoveLinkedListNthFromEnd {


    public static void main(String[] args) {
        ListNode listNode = ReverseList.initListNode(new int[]{1,2,3,4,5});
        System.out.println(listNode);
        System.out.println(removeNthFromEnd(listNode, 2));
    }

    /**
     * 一趟扫描实现
     * @param head
     * @param n
     * @return
     */
    public static ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode p = dummy; //
        ListNode q = dummy;
        // 找到q的位置   p 和q 相差n+1
        for (int i = 0; i < n + 1; i++ ) {
            q = q.next;
        }
        while (q != null) {
            p = p.next;
            q = q.next;
        }
        // 移除
        p.next = p.next.next;

        return dummy.next;
    }
}
