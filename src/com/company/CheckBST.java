package com.company;
import java.util.ArrayDeque;
import java.util.Deque;

// assume the keys stored in the binary search tree can not be Integer.MIN_VALUE or Integer.MAX_VALUE.
public class CheckBST {
    // Iteration
    // Time O(n)
    // Space O(h) worst case O(n)
    public boolean isBST(TreeNode root) {
        // Write your solution here
        if (root == null) {
            return true;
        }
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode curr = root;
        TreeNode prev = null;
        while (curr != null || !stack.isEmpty()) {
            while (curr != null) {
                stack.push(curr);
                curr = curr.left;
            }
            curr = stack.pollFirst();
            if (prev != null && prev.key >= curr.key) {
                return false;
            }
            prev = curr;
            curr = curr.right;
        }
        return true;
    }

    // Recursion
    // Time O(n)
    // Space O(height) average O(logn) worst O(n)
    public boolean isBSTI(TreeNode root) {
        // Write your solution here
        if (root == null) {
            return true;
        }
        return isBST(Integer.MIN_VALUE, root, Integer.MAX_VALUE);
    }

    private boolean isBST(int min, TreeNode root, int max) {
        if (root == null) {
            return true;
        }
        if (root.key <= min || root.key >= max) {
            return false;
        }
        return isBST(min, root.left, root.key) && isBST(root.key, root.right, max);
    }
}
