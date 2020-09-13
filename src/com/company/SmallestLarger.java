package com.company;

import java.util.Arrays;

public class SmallestLarger {
    public int smallestElementLargerThanTarget(int[] array, int target) {
        if (array == null || array.length == 0) {
            return -1;
        }
        int left = 0, right = array.length - 1;
        while (left < right - 1) {
            int mid = left + (right - left) / 2;
            if (array[mid] <= target) {
                left = mid; 	// left = mid + 1; both are ok
            } else {
                right = mid;	// right = mid - 1 is NOT ok
            }
        }
        if (array[left] > target) {
            return left;
        }
        if (array[right] > target) {
            return right;
        }
        return -1;
    }

    public static void main(String[] args) {
        SmallestLarger s = new SmallestLarger();

        int[] array = null;
        System.out.println(s.smallestElementLargerThanTarget(array, 2));

        array = new int[] {};
        System.out.println(s.smallestElementLargerThanTarget(array, 2));

        array = new int[] {1, 3, 5};
        System.out.println(s.smallestElementLargerThanTarget(array, 2));
    }
}
