/**
 * 寻找数组的中心索引
 *
 * @Auther: 苏察哈尔丶灿
 * @Date: 2020/8/6 19:35
 * @Slogan: 我自横刀向天笑，笑完我就去睡觉。
 */
public class Solution {
    public static void main(String[] args) {
        int[] arr = {1, 7, 3, 6, 5, 6};
//        int[] arr = {1, 2, 3};
        System.out.println("中心索引为：" + pivotIndex(arr));
    }

    // 数组中心索引
    public static int pivotIndex(int[] nums) {
        int front, back;

        for (int i = 0; i < nums.length; i++) {
            front = back = 0;

            for (int j = 0; j < i; j++) {
                front += nums[j];
            }

            for (int k = i + 1; k < nums.length; k++) {
                back += nums[k];
            }

            System.out.print(front + "\t");

            System.out.print(back);
            if (front == back) {
                return i;
            }
            System.out.println();
        }

        return -1;
    }
}
