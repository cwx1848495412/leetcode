import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 合并区间
 *
 * @Auther: 苏察哈尔丶灿
 * @Date: 2020/8/6 20:04
 * @Slogan: 我自横刀向天笑，笑完我就去睡觉。
 */
public class Solution02 {
    public static void main(String[] args) {
//        int[][] arr = {{1, 4}, {5, 4}, {0, 1}};
//        int[][] arr = {{1, 3}, {6, 2}, {10, 8}, {15, 18}};
//        int[][] arr = {{1, 4}, {2, 3}};
        int[][] arr = {{2, 3}, {4, 5}, {6, 7}, {8, 9}, {1, 10}};
//        int[][] arr = {};

        int[][] newArr = merge(arr);
        echo(newArr);
    }

    public static int[][] merge(int[][] intervals) {

        /*
         * 首先，区间就表明 深层数组的0，1两个下表值一定不同，有大有小
         * 先给这两个0，1下标排序，排序为0下标存小值，1下标存大值
         *
         * 然后，按照0下标给所有数组进行排序，让后边数组的0下标一定比前边数组的0下标大
         *
         * 再然后
         * 后边数组的0下标值 < 前边数组的1下标值，合并
         * 后边数组的0下标值 > 前边数组的1下标值，另外起一个区间
         * 因为再往后面的数组的0下标不可能在比之前的1下标小了
         * 原因是因为我们规定过了他是一个按0下标排好序的数组了
         *
         * 开始写代码
         */

        if (intervals.length == 0) {
            return new int[0][0];
        }

        //先给这两个0，1下标排序
        for (int i = 0; i < intervals.length; i++) {
            if (intervals[i][0] > intervals[i][1]) {
                // 原地赋值更改变量值
                intervals[i][0] = intervals[i][0] + intervals[i][1];
                intervals[i][1] = intervals[i][0] - intervals[i][1];
                intervals[i][0] = intervals[i][0] - intervals[i][1];
            }
        }

        // 然后，按照0下标给所有数组进行排序
        Arrays.sort(intervals, (o1, o2) -> {
            return o1[0] - o2[0];
        });

//        echo(intervals);
//        System.out.println("=====排序完成=======");
        // 开始对比
        List<int[]> arrayList = new ArrayList<int[]>();
        // 先把初始第一个塞进去
        arrayList.add(new int[]{intervals[0][0], intervals[0][1]});
        for (int i = 1; i < intervals.length; i++) {
            int[] tempArr = arrayList.get(arrayList.size() - 1);

            // 后面的小值比前面的大值 还大
            if (tempArr[1] < intervals[i][0]) {
                // 就得另起一个区间
                arrayList.add(new int[]{intervals[i][0], intervals[i][1]});
                continue;
            }
            // 否则 合并区间
            // [1,4][2,3]防止这种坑壁情况
            arrayList.set(arrayList.size() - 1, new int[]{tempArr[0], intervals[i][1] > tempArr[1] ? intervals[i][1] : tempArr[1]});
        }

        int[][] returnArr = new int[arrayList.size()][2];

        for (int i = 0; i < arrayList.size(); i++) {
//            System.out.println(arrayList.get(i)[0] + "\t" + arrayList.get(i)[1]);
            returnArr[i][0] = arrayList.get(i)[0];
            returnArr[i][1] = arrayList.get(i)[1];
        }

        return returnArr;
    }

    // 打印数组
    public static void echo(int[][] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.println(Arrays.toString(arr[i]));
        }
    }

}
