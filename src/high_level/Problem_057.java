package high_level;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * 插入区间
 * 给出一个无重叠的 ，按照区间起始端点排序的区间列表。
 * <p>
 * 在列表中插入一个新的区间，你需要确保列表中的区间仍然有序且不重叠（如果有必要的话，可以合并区间）。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：intervals = [[1,3],[6,9]], newInterval = [2,5]
 * 输出：[[1,5],[6,9]]
 * 示例 2：
 * <p>
 * 输入：intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8]
 * 输出：[[1,2],[3,10],[12,16]]
 * 解释：这是因为新的区间 [4,8] 与 [3,5],[6,7],[8,10] 重叠。
 *
 * @Auther: 苏察哈尔丶灿
 * @Date: 2020/11/4 19:53
 * @Slogan: 我自横刀向天笑，笑完我就去睡觉。
 */
public class Problem_057 {
    /**
     * @param intervals
     * @param newInterval
     * @return
     */
    public int[][] insert(int[][] intervals, int[] newInterval) {

        ArrayList<int[]> list = new ArrayList<>();

        int i = 0;
        while (i < intervals.length && intervals[i][1] < newInterval[0]) {
            list.add(intervals[i]);
            i++;
        }

        while (i < intervals.length && intervals[i][0] <= newInterval[1]) {
            newInterval[0] = Math.min(newInterval[0], intervals[i][0]);
            newInterval[1] = Math.max(newInterval[1], intervals[i][1]);
            i++;
        }

        list.add(newInterval);

        while (i < intervals.length) {
            list.add(intervals[i]);
            i++;
        }
//        printList(list);
        int[][] res = new int[list.size()][2];
        for (int j = 0; j < list.size(); j++) {
            res[j][0] = list.get(j)[0];
            res[j][1] = list.get(j)[1];
        }

        return res;
    }

    public static void main(String[] args) {
//        [1,2],[3,5],[6,7],[8,10],[12,16]
        int[][] intervals = new int[][]{
                {1, 2}, {3, 5}, {6, 7}, {8, 10}, {12, 16}
        };
        int[] newInterval = new int[]{4, 8};

        Problem_057 problem_057 = new Problem_057();
        int[][] insert = problem_057.insert(intervals, newInterval);

        for (int i = 0; i < insert.length; i++) {
            System.out.println(Arrays.toString(insert[i]));
        }
    }

    public void printList(List<int[]> list) {
        for (int[] arr : list) {
            System.out.print(arr[0]);
            System.out.print("-");
            System.out.print(arr[1]);
            System.out.print(",");
        }
        System.out.println();
    }
}
