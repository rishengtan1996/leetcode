/**
 * Given an integer array nums, find the contiguous subarray (containing at least one number) which has the largest sum and return its sum.
 *
 * Example:
 *
 * Input: [-2,1,-3,4,-1,2,1,-5,4],
 * Output: 6
 * Explanation: [4,-1,2,1] has the largest sum = 6.
 * Follow up:
 *
 * If you have figured out the O(n) solution, try coding another solution using the divide and conquer approach, which is more subtle.
 */
public class MaximunSubarray {
    public static void main(String[] args) {
        System.out.println(new MaximunSubarray().MaxSubarray(new int[]{-2,1,-3,4,-1,2,1,-5,4}));
    }

    public int MaxSubarray(int[] nums){
        //return greedy(nums); 容易记也容易记错。。。。。
        return dividConquer(nums,0,nums.length-1);
    }

    public int dividConquer(int[] nums, int left, int right){
        if(left == right) return nums[left];/***这里是关键中， 理解一下？？？ 废话递归没有出口能成吗*/
        int mid = (left+right)>>>1;
        int maxleft = dividConquer(nums, left, mid);
        int maxright = dividConquer(nums, mid+1, right);
        int maxCross = helper(nums, left, mid, right);
        return Math.max(Math.max(maxleft,maxright),maxCross);
    }

    public int helper(int[] nums, int left, int mid, int right){
        if(left == right) return nums[left];
        int leftsum = Integer.MIN_VALUE;
        int maxCross = 0;
        for(int i = mid; i>=left; i--){/**为什么非要倒着写？？目前理解不了希望多做题然后顿悟吧哈哈哈哈哈哈*/
            maxCross+=nums[i];
            leftsum = Math.max(leftsum,maxCross);
        }
        int rightsum = Integer.MIN_VALUE;
        maxCross = 0;
        for(int i = mid+1; i<=right; i++){
            maxCross +=nums[i];
            rightsum = Math.max(rightsum, maxCross);
        }
        return rightsum + leftsum;
    }

    public int greedy(int[] nums){
        int currentSum = nums[0];
        int maxSum = nums[0];
        for(int i = 1; i<nums.length; i++){
            currentSum = Math.max(nums[i],currentSum+nums[i]);
            maxSum = Math.max(currentSum,maxSum);
        }
        return maxSum;
    }
}
