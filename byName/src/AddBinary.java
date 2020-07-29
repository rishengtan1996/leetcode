/**
 * Given two binary strings, return their sum (also a binary string).
 *
 * The input strings are both non-empty and contains only characters 1 or 0.
 *
 * Example 1:
 *
 * Input: a = "11", b = "1"
 * Output: "100"
 * Example 2:
 *
 * Input: a = "1010", b = "1011"
 * Output: "10101"
 *
 *
 * Constraints:
 *
 * Each string consists only of '0' or '1' characters.
 * 1 <= a.length, b.length <= 10^4
 * Each string is either "0" or doesn't contain any leading zero.
 */
public class AddBinary {
    public static void main(String[] args) {
        System.out.println(new AddBinary().addBinary("1010", "1011"));
    }
    /*****************这道题细节拉满啊， 是easy我是没想到的*/
        public String addBinary(String a, String b){
        int i = a.length()-1, j = b.length()-1, carry = 0;
        StringBuilder sb = new StringBuilder();
        while(i>=0 || j>=0){
            int sum = carry;
            if(i>=0) sum += a.charAt(i--) - '0';
            if(j>=0) sum += b.charAt(j--) - '0';
            sb.append(sum %= 2);
            carry = sum/2;
        }
        if(carry!=0) sb.append(carry);
        return sb.reverse().toString();
    }
}
