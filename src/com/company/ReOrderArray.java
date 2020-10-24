package com.company;

import java.util.Arrays;

public class ReOrderArray {
    public static void main(String[] args) {
        ReOrderArray s = new ReOrderArray();
        int[] array = new int[] {1, 2, 3, 4};
        System.out.println(Arrays.toString(s.reorder(array)));

        array = new int[] {1, 2, 3};
        System.out.println(Arrays.toString(s.reorder(array)));

        array = new int[] {1, 2, 3, 4, 5, 6, 7};
        System.out.println(Arrays.toString(s.reorder(array)));
    }

    /**
     * Array reorder in place implementation
     *
     * Suppose I have a array of chars, the requirement is as follow
     * 1). [c1, c2, ... c2k]
     * --> [c1, ck+1, c2, ck+2, ... , ck, c2k]
     * 2). [c1, c2, ... c2k]
     * --> [c1, ck+1, c2, ck+2, ... , ck, c2k, c2k+1]
     */
    // Time O(nlogn)
    // Space O(1)
    public int[] reorder(int[] array) {
        // Assumptions: array is not null
        if (array.length % 2 == 0) {
            reorder(array, 0, array.length - 1);
        } else {
            reorder(array, 0, array.length - 2);
        }
        return array;
    }

    private void reorder(int[] array, int left, int right) {
        int length = right - left + 1;
        // if the subarray has 2 or 0 elements, we can just return
        // as this should be the base case
        if (length <= 2) {
            return;
        }
        // calculate the important mid points
        // 0 1 2 3 4 5 6 7
        // lm: 2, m: 4, rm: 6
        // 0 1 2 3 4 5 6 7 8 9
        // lm: 2, m: 5, rm: 7
        int mid = left + length / 2;
        int lmid = left + length / 4;
        int rmid = left + length * 3 / 4;
        reverse(array, lmid, mid - 1);
        reverse(array, mid, rmid - 1);
        reverse(array, lmid, rmid - 1);
        // half of the left partition's size = lmid - left
        reorder(array, left, left + (lmid - left) * 2 - 1);
        reorder(array, left + (lmid - left) * 2, right);
    }

    public void reverse(int[] array, int i, int j) {
        while (i < j) {
            int tmp = array[i];
            array[i] = array[j];
            array[j] = tmp;
            i++;
            j--;
        }
    }
}
