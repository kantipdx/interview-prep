package CommonProblems;
/*
* https://leetcode.com/problems/jump-game-ii/
* Given an array of non-negative integers nums, you are initially positioned at the first index of the array.
Each element in the array represents your maximum jump length at that position.
Your goal is to reach the last index in the minimum number of jumps.
You can assume that you can always reach the last index.
*
* Example 1:
Input: nums = [2,3,1,1,4]
Output: 2
Explanation: The minimum number of jumps to reach the last index is 2. Jump 1 step from index 0 to 1, then 3 steps to the last index.
* Example 2:
Input: nums = [2,3,0,1,4]
Output: 2
*
* */
public class JumpGameII {
    /*
    * Algorithm:
        Initialize three integer variables: jumps to count the number of jumps, currentJumpEnd to mark the end of the
        range that we can jump to, and farthest to mark the farthest place that we can reach. Set each variable to zero.
        Iterate over nums. Note that we exclude the last element from our iteration because as soon as we reach the last
        element, we do not need to jump anymore.
        Update farthest to i + nums[i] if the latter is larger.
        If we reach currentJumpEnd, it means we finished the current jump, and can begin checking the next jump by
        setting currentJumpEnd = farthest.
        Return jumps.
    * */
    public static int jump(int[] nums) {
        int jumps = 0, currentJumpEnd = 0, farthest = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            // we continuously find how far we can reach in the current jump
            farthest = Math.max(farthest, i + nums[i]);
            // if we have come to the end of the current jump,
            // we need to make another jump
            if (i == currentJumpEnd) {
                jumps++;
                currentJumpEnd = farthest;
            }
        }
        return jumps;
    }
    /*
    * Complexity Analysis:
        Time Complexity: O(N) because there are NN elements in the array, and we visit each element in the array only once.
        Space Complexity: O(1) because we don't use any additional data structures.
    * */

    public static void main(String[] args) {
        System.out.println(jump(new int[]{2,3,0,1,4}));
        System.out.println(jump(new int[]{2,3,1,1,4}));
    }
}
