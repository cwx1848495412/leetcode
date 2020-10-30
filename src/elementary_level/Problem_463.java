package elementary_level;

/**
 * 给定一个包含 0 和 1 的二维网格地图，其中 1 表示陆地 0 表示水域。
 * <p>
 * 网格中的格子水平和垂直方向相连（对角线方向不相连）。整个网格被水完全包围，但其中恰好有一个岛屿（或者说，一个或多个表示陆地的格子相连组成的岛屿）。
 * <p>
 * 岛屿中没有“湖”（“湖” 指水域在岛屿内部且不和岛屿周围的水相连）。格子是边长为 1 的正方形。网格为长方形，且宽度和高度均不超过 100 。计算这个岛屿的周长。
 * <p>
 *  
 * <p>
 * 示例 :
 * <p>
 * 输入:
 * [[0,1,0,0],
 * [1,1,1,0],
 * [0,1,0,0],
 * [1,1,0,0]]
 * <p>
 * 输出: 16
 * <p>
 * 解释: 它的周长是下面图片中的 16 个黄色的边：
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/island-perimeter
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @Auther: 苏察哈尔丶灿
 * @Date: 2020/10/30 13:59
 * @Slogan: 我自横刀向天笑，笑完我就去睡觉。
 */
public class Problem_463 {
    public static int islandPerimeter(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }

        int hang = grid.length;
        int lie = grid[0].length;
        int res = 0;
        for (int i = 0; i < hang; i++) {
            for (int j = 0; j < lie; j++) {
                if (grid[i][j] == 1) {
                    res += 4 - getHave(grid, i - 1, j)
                            - getHave(grid, i + 1, j)
                            - getHave(grid, i, j - 1)
                            - getHave(grid, i, j + 1);
                }
            }
        }

        return res;
    }

    // 有 返回 1
    // 没有 返回 0
    public static int getHave(int[][] grid, int x, int y) {
        if (x < 0 || y < 0 || x >= grid.length || y >= grid[0].length) {
            return 0;
        }
        return grid[x][y] == 1 ? 1 : 0;
    }

    public static int islandPerimeter2(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }

        int res = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    return dfs(grid, i, j);
                }
            }
        }

        return res;
    }

    private static int dfs(int[][] grid, int x, int y) {
        // 到边界了 返回一个边长
        if (x < 0 || y < 0 || x >= grid.length || y >= grid[0].length) {
            return 1;
        }

        // 递归出口
        if (grid[x][y] == 2) {
            return 0;
        }

        // 相邻是湖 此方向长度+1
        if (grid[x][y] == 0) {
            return 1;
        }

        // 没走过的大陆 先标记来过了
        grid[x][y] = 2;

        // 相邻不是已经走过的 也不是湖水 就是没走过的岛
        return dfs(grid, x - 1, y)
                + dfs(grid, x + 1, y)
                + dfs(grid, x, y - 1)
                + dfs(grid, x, y + 1);
    }

    public static int islandPerimeter3(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }

        int hang = grid.length;
        int lie = grid[0].length;
        int res = 0;
        for (int i = 0; i < hang; i++) {
            for (int j = 0; j < lie; j++) {
                if (grid[i][j] == 1) {
                    res += 4 - getHaveLess(grid, i - 1, j)
                            - getHaveLess(grid, i, j - 1);
                }
            }
        }

        return res;
    }

    // 从上往下依次对比
    // 上面有 直接 -2
    // 左面有直接 -2
    // 减少上述常数项
    public static int getHaveLess(int[][] grid, int x, int y) {
        if (x < 0 || y < 0 || x >= grid.length || y >= grid[0].length) {
            return 0;
        }
        return grid[x][y] == 1 ? 2 : 0;
    }

    public static int islandPerimeter4(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }

        int hang = grid.length;
        int lie = grid[0].length;
        int res = 0;
        for (int i = 0; i < hang; i++) {
            for (int j = 0; j < lie; j++) {
                if (grid[i][j] == 1) {
                    res += 4;

                    if (i > 0 && grid[i - 1][j] == 1) {
                        res -= 2;
                    }

                    if (j > 0 && grid[i][j - 1] == 1) {
                        res -= 2;
                    }
                }
            }
        }

        return res;
    }

    /**
     * 输入:
     * [[0,1,0,0],
     * [1,1,1,0],
     * [0,1,0,0],
     * [1,1,0,0]]
     * <p>
     * 输出: 16
     *
     * @param args
     */
    public static void main(String[] args) {
        int[][] arr = new int[][]{
                {0, 1, 0, 0},
                {1, 1, 1, 0},
                {0, 1, 0, 0},
                {1, 1, 0, 0},
        };

//        int[][] arr = new int[][]{
//                {1, 0, 0, 0},
//        };
        int res = islandPerimeter3(arr);
        System.out.println(res);
    }
}
