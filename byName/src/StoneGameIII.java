/**
 * Alice and Bob continue their games with piles of stones. There are several stones arranged in a row,
 * and each stone has an associated value which is an integer given in the array stoneValue.
 *
 * Alice and Bob take turns, with Alice starting first.
 * On each player's turn, that player can take 1, 2 or 3 stones from the first remaining stones in the row.
 *
 * The score of each player is the sum of values of the stones taken. The score of each player is 0 initially.
 *
 * The objective of the game is to end with the highest score, and the winner is the player with the highest score and there could be a tie.
 * The game continues until all the stones have been taken.
 *
 * Assume Alice and Bob play optimally.
 *
 * Return "Alice" if Alice will win, "Bob" if Bob will win or "Tie" if they end the game with the same score.
 *
 *
 *
 * Example 1:
 *
 * Input: values = [1,2,3,7]
 * Output: "Bob"
 * Explanation: Alice will always lose. Her best move will be to take three piles and the score become 6. Now the score of Bob is 7 and Bob wins.
 * Example 2:
 *
 * Input: values = [1,2,3,-9]
 * Output: "Alice"
 * Explanation: Alice must choose all the three piles at the first move to win and leave Bob with negative score.
 * If Alice chooses one pile her score will be 1 and the next move Bob's score becomes 5.
 * The next move Alice will take the pile with value = -9 and lose.
 * If Alice chooses two piles her score will be 3 and the next move Bob's score becomes 3.
 * The next move Alice will take the pile with value = -9 and also lose.
 * Remember that both play optimally so here Alice will choose the scenario that makes her win.
 * Example 3:
 *
 * Input: values = [1,2,3,6]
 * Output: "Tie"
 * Explanation: Alice cannot win this game. She can end the game in a draw if she decided to choose all the first three piles,
 * otherwise she will lose.
 * Example 4:
 *
 * Input: values = [1,2,3,-1,-2,-3,7]
 * Output: "Alice"
 * Example 5:
 *
 * Input: values = [-1,-2,-3]
 * Output: "Tie"
 *
 *
 * Constraints:
 *
 * 1 <= values.length <= 50000
 * -1000 <= values[i] <= 1000
 */
public class StoneGameIII {
    public static void main(String[] args) {
        System.out.println(new StoneGameIII().playII(new int[]{1,2,3,7}));
        System.out.println(new StoneGameIII().playII(new int[]{1,2,3,-9}));
        System.out.println(new StoneGameIII().playII(new int[]{1,2,3,6}));
        System.out.println(new StoneGameIII().playII(new int[]{1,2,3,-1,-2,-3,7}));
        System.out.println(new StoneGameIII().playII(new int[]{-1,-2,-3}));
    }

    /*********************************************  minimax  **************************************************************/
    /**
     * Let's Alice be a maxPlayer and Bob be a minPlayer. On each turn, we need to maximize score of the maxPlayer and minimize score of the minPlayer.
     * After all, we will check the final score. If score>0 Alice wins, score<0 Bob wins else they tie.
     * @param arr
     * @return
     */
    public String play(int[] arr) {
        int score = minimax(arr, 0, 1, new Integer[arr.length][2]);
        if (score > 0) return "Alice";
        if (score < 0) return "Bob";
        return "Tie";
    }
    int minimax(int[] arr, int i, int maxPlayer, Integer[][] dp) {
        if (i >= arr.length) return 0;
        if (dp[i][maxPlayer] != null) return dp[i][maxPlayer];
        int ans = maxPlayer == 1 ? Integer.MIN_VALUE : Integer.MAX_VALUE;
        int score = 0;
        for (int j = i; j < Math.min(arr.length, i + 3); j++) {
            if (maxPlayer == 1) {
                score += arr[j];
                ans = Math.max(ans, score + minimax(arr, j + 1, 0, dp));
            } else {
                score -= arr[j];
                ans = Math.min(ans, score + minimax(arr, j + 1, 1, dp));
            }
        }
        return dp[i][maxPlayer] = ans;
    }


    /**
     * dp[i] is the maximum difference in the score of the current player against the opponent in the ith turn.
     *
     * There are 3 options for the current player to choose:
     *
     * Take A[i], diff1 = take - dp[i+1]
     * Take A[i] + A[i+1], diff2 = take - dp[i+2]
     * Take A[i] + A[i+1] + A[i+2], diff3 = take - dp[i+3]
     * We want to maximize difference in the score of the current player against the opponent, so dp[i] = max(diff1, diff2, diff3)
     * @param stoneValue
     * @return
     */
    public String playII(int[] stoneValue) {
        int n = stoneValue.length;
        int[] dp = new int[n + 1];
        for (int i = n - 1; i >= 0; i--) {/**bottom-up是精髓！！！！！*/
            int take = 0;
            dp[i] = Integer.MIN_VALUE;
            for (int j = i; j < Math.min(n, i + 3); j++) {
                take += stoneValue[j];
                dp[i] = Math.max(dp[i], take - dp[j + 1]);
            }
        }
        int diff = dp[0]; // Alice goes first, starting from the first stone
        if (diff > 0) return "Alice";
        if (diff < 0) return "Bob";
        return "Tie";
    }

}