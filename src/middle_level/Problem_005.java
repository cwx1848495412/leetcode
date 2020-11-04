package middle_level;

import java.util.Arrays;

/**
 * 给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000。
 * <p>
 * 示例 1：
 * <p>
 * 输入: "babad"
 * 输出: "bab"
 * 注意: "aba" 也是一个有效答案。
 * 示例 2：
 * <p>
 * 输入: "cbbd"
 * 输出: "bb"
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-palindromic-substring
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @Auther: 苏察哈尔丶灿
 * @Date: 2020/10/31 16:19
 * @Slogan: 我自横刀向天笑，笑完我就去睡觉。
 */
public class Problem_005 {

    public static String longestPalindrome(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }

        // 处理字符串 填充虚轴
        char[] str = handleString(s);

        // 每个字符下标位对应的可以确定的最长回文半径
        int[] lenArr = new int[str.length];

        // 右边界 不断往右侧扩展
        // 第一个不成轴对称的位置
        // 即为最右侧成轴对称位置的下一个
        int R = -1;
        // 成轴对称的下标位置
        int C = -1;

        int maxValue = Integer.MIN_VALUE;
        // 保存索引最大时的中心点下标
        int maxIndex = 0;
        // a b c d

        // 遍历数组 开始
        for (int i = 0; i < str.length; i++) {
            // i < R 在回文区域内
            //       对称节点没超过左边界 为对称节点的长度
            //           对称节点坐标为 2C - i 可以自己画图想一想为什么 很好理解
            //       对称节点超越了左边界 受右边界限制
            //           右边界长度为R - i
            // i >= R 在回文区域外
            // 当前毋须交验的长度为
            lenArr[i] = i < R ? Math.min(lenArr[2 * C - i], R - i) : 1;
            // 左右边界判断不越界
            while (i + lenArr[i] < str.length && i - lenArr[i] > -1) {
                if (str[i + lenArr[i]] == str[i - lenArr[i]]) {
                    lenArr[i]++;
                } else {
                    break;
                }
            }

            if (i + lenArr[i] > R) {
                R = i + lenArr[i];
                C = i;
            }

            if (lenArr[i] > maxValue) {
                maxValue = lenArr[i];
                maxIndex = i;
            }

        }

        // 5
        //           m
        // 0 1 2 3 4 5 6 7 8 9 10
        // # a # b # c # b # a #

        // 4
        //         m
        // 0 1 2 3 4 5 6 7 8
        // # a # b # b # a #

        // 0 1 2 3 4 5 6 7 8 9 0 1 2 3 4 5 6 7 8 9 0 1 2
        // # a # a # c # a # b # d # k # a # c # a # a #

        // 0 1 2 3 4 5 6 7 8
        // # a # c # a # a #
        int centerIndex = maxIndex / 2;
        // maxValue - 1 为长度  R半径长度 - 1 或者 直径 / 2
        int length = (maxValue - 1);
        int strLength = length / 2;
        return s.substring(centerIndex - strLength,
                length % 2 == 0 ?
                        centerIndex + strLength :
                        centerIndex + strLength + 1);
    }

    public static char[] handleString(String s) {
        // 补充字符防止虚轴错过
        char[] chars = s.toCharArray();
        char[] result = new char[chars.length * 2 + 1];

        for (int i = 0; i < chars.length; i++) {
            result[2 * i] = '#';
            result[2 * i + 1] = chars[i];
        }

        result[result.length - 1] = '#';
        return result;
    }


    public static void main(String[] args) {
//        String res = longestPalindrome("cbbd");
//        String res = longestPalindrome("abcba");
//        String res = longestPalindrome("aacabdkacaa");
        String res = longestPalindrome("acaa");
        System.out.println(res);
    }
}
