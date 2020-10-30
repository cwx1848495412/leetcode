/**
 * 搜索插入位置
 *
 * @Auther: 苏察哈尔丶灿
 * @Date: 2020/8/6 20:04
 * @Slogan: 我自横刀向天笑，笑完我就去睡觉。
 */
public class Solution01 {
    public static void main(String[] args) {
        int[] arr = {1, 3, 5, 6};
        System.out.println("插入位置索引为：" + searchInsert(arr, 5));
    }

    public static int searchInsert(int[] nums, int target) {

        for (int i = 0; i < nums.length; i++) {

            if (nums[i] >= target) {
                return i;
            }

        }

        return nums.length;
    }
}
