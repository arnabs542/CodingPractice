package com.company;

import java.util.ArrayDeque;
import java.util.Deque;

public class SortArrayThreeStacks {
    /**
     * The numbers in s1 are original, after sorting, the numbers should be in
     * s1 as well and from top to bottom the numbers are sorted in ascending order.
     */
    public void sort(Deque<Integer> s1) {
        // if no sanity check, need to assume not null
        // and need to handle empty in recursion base case
        if (s1 == null || s1.size() == 0) {
            return;
        }
        Deque<Integer> s2 = new ArrayDeque<>();
        Deque<Integer> s3 = new ArrayDeque<>();
        sort(s1, s2, s3, s1.size());
    }

    private void sort(Deque<Integer> s1, Deque<Integer> s2, Deque<Integer> s3, int length) {
        if (length <= 1) {	// length < 1 can handle corner case -- empty
            return;
        }
        int mid1 = length / 2;
        int mid2 = length - length / 2;
        for (int i = 0; i < mid1; i++) {
            s2.offerFirst(s1.pollFirst());
        }
        // use the other stacks to sort s2 / s1
        // after sorting the numbers in s2 / s1 are in ascending order from top to bottom
        // in the two stacks
        sort(s1, s2, s3, mid2);
        sort(s2, s1, s3, mid1);

        int i = 0;
        int j = 0;
        while (i < mid1 && j < mid2) {
            if (s2.peekFirst() < s1.peekFirst()) {
                s3.offerFirst(s2.pollFirst());
                i++;
            } else {
                s3.offerFirst(s1.pollFirst());
                j++;
            }
        }
        while (i < mid1) {
            s3.offerFirst(s2.pollFirst());
            i++;
        }
        while (j < mid2) {
            s3.offerFirst(s1.pollFirst());
            j++;
        }
        // after merging, the numbers are in descending order from top to bottom in s3,
        // we need to push them back to s1 so that they are in ascending order
        for (int index = 0; index < length; index++) {
            s1.offerFirst(s3.pollFirst());
        }
    }

    public static void main(String[] args) {
        SortArrayThreeStacks s = new SortArrayThreeStacks();
        Deque<Integer> s1 = new ArrayDeque<>();
        s1.offerFirst(1);
        s1.offerFirst(2);
        s1.offerFirst(1);
        s1.offerFirst(3);
        s1.offerFirst(2);
        System.out.println(s1);
        s.sort(s1);
        System.out.println(s1);
    }
}
















