package com.company;
import java.util.*;

public class PostOrder {
    // Method 1: post-order is the reverse order of pre-order with traversing
    // right subtree before traversing left subtree
    public void postOrderI(TreeNode root) {
        if (root == null) {
            return;
        }
        Deque<TreeNode> stack = new ArrayDeque<>();
        Deque<Integer> temp = new ArrayDeque<>();
        stack.offerFirst(root);
        while (!stack.isEmpty()) {
            TreeNode curr = stack.pollFirst();
            // conduct the result for the special pre-order traversal
            temp.add(curr.key);
            // in pre-order the right subtree will be traversed before the
            // left subtree so pushing left child first
            if (curr.left != null) {
                stack.offerFirst(curr.left);
            }
            if (curr.right != null) {
                stack.offerFirst(curr.right);
            }
        }
        while (!temp.isEmpty()) {
            System.out.println(temp.pollFirst());
        }
    }

    // post order 用prev与curr(stack.top())的关系来分情况讨论
    //               Case 1: 从上面下来  有左压左 有右压右 都没有 pop并打印当前栈顶
    //               Case 2: 从左边上来 没有右边  或者 从右边上来 pop并打印当前栈顶
    //               Case 3: 从左边上来 并且有右边 右边压栈
    //               并注意每个循环要更新prev'
    // Method 2: check the relation between the current node and the previous node
    // to determine which direction should go next
    public List<Integer> postOrderII(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        Deque<TreeNode> stack = new ArrayDeque<>();
        stack.offerFirst(root);
        // to record the previous node on the way of DFS so that
        // we can determine the direction
        TreeNode prev = null;
        while (!stack.isEmpty()) {
            TreeNode cur = stack.peekFirst();
            // if we are going down, either left/right direction
            if (prev == null || cur == prev.left || cur == prev.right) {
                // if we can still go down, try to go left first
                if (cur.left != null) {
                    stack.offerFirst(cur.left);
                } else if (cur.right != null) {
                    stack.offerFirst(cur.right);
                } else {
                    // if we can not go either way, meaning cur is a leaf node
                    stack.pollFirst();
                    result.add(cur.key);
                }
            } else if (prev == cur.right || prev == cur.left && cur.right == null) {
                // if we are going up from the right side or going up from the left side
                // but we cannot go right afterwards
                stack.pollFirst();
                result.add(cur.key);
            } else {
                // otherwise, we are going up from the left side and we can go down
                // right side
                stack.offerFirst(cur.right);
            }
            prev = cur;
        }
        return result;
    }
}
