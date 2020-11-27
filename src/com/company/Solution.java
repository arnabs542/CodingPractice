package com.company;
import java.util.*;

public class Solution {
    public static void main(String[] args) {
        Solution s = new Solution();
        ListNode one = new ListNode(1);
        ListNode two = new ListNode(2);
        ListNode three = new ListNode(3);
        ListNode zero = new ListNode(0);
        one.next = two;
        two.next = three;
        ListNode head = s.merge(one, zero);
        while (head != null) {
            System.out.println(head.value);
            head = head.next;
        }
        System.out.println();
    }

    public ListNode merge(ListNode one, ListNode two) {
        ListNode dummy = new ListNode(0);
        ListNode prev = dummy;
        while (one != null && two != null) {
            if (one.value < two.value) {
                prev.next = one;
                one = one.next;
            } else {
                prev.next = two;
                two = two.next;
            }
            prev = prev.next;
        }
        if (one != null) {
            prev.next = one;
            one = one.next;
            prev = prev.next;
        }
        if (two != null) {
            prev.next = two;
            two = two.next;
            prev = prev.next;
        }
        return dummy.next;
    }
}