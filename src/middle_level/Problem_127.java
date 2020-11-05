package middle_level;

import java.util.*;

/**
 * 127. 单词接龙
 * 给定两个单词（beginWord 和 endWord）和一个字典
 * 找到从 beginWord 到 endWord 的最短转换序列的长度。转换需遵循如下规则：
 * <p>
 * 每次转换只能改变一个字母。
 * 转换过程中的中间单词必须是字典中的单词。
 * 说明:
 * <p>
 * 如果不存在这样的转换序列，返回 0。
 * 所有单词具有相同的长度。
 * 所有单词只由小写字母组成。
 * 字典中不存在重复的单词。
 * 你可以假设 beginWord 和 endWord 是非空的，且二者不相同。
 * 示例 1:
 * <p>
 * 输入:
 * beginWord = "hit",
 * endWord = "cog",
 * wordList = ["hot","dot","dog","lot","log","cog"]
 * <p>
 * 输出: 5
 * <p>
 * 解释: 一个最短转换序列是 "hit" -> "hot" -> "dot" -> "dog" -> "cog",
 * 返回它的长度 5。
 * 示例 2:
 * <p>
 * 输入:
 * beginWord = "hit"
 * endWord = "cog"
 * wordList = ["hot","dot","dog","lot","log"]
 * <p>
 * 输出: 0
 * <p>
 * 解释: endWord "cog" 不在字典中，所以无法进行转换。
 *
 * @Auther: 苏察哈尔丶灿
 * @Date: 2020/11/5 14:02
 * @Slogan: 我自横刀向天笑，笑完我就去睡觉。
 */
public class Problem_127 {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {

        HashSet<String> wordSet = new HashSet<String>(wordList);
        if (wordSet.size() <= 0 || !wordSet.contains(endWord)) {
            // 词典里不存在终止词 再见
            return 0;
        }

        // 存在了 准备宽度优先的队列和set
        LinkedList<String> queue = new LinkedList<>();
        HashSet<String> visited = new HashSet<>();

        // 先进后出
        queue.addFirst(beginWord);
        // 起始词长度也算 所以从1开始
        int step = 1;
        while (!queue.isEmpty()) {
            // 队列不为空
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                // 先进后出
                String currentWord = queue.pollLast();
                if (isEnd(currentWord, endWord, queue, wordSet, visited)) {
                    // 结束了 返回
                    return step + 1;
                }
            }
            step++;
        }

        return 0;
    }

    // 是否结束
    private boolean isEnd(String currentWord,
                          String endWord,
                          LinkedList<String> queue,
                          HashSet<String> wordSet,
                          HashSet<String> visited) {
        char[] chars = currentWord.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            // *it h*t hi*
            char originChar = chars[i];
            for (int k = 'a'; k <= 'z'; k++) {
                if (k == originChar) {
                    continue;
                }

                chars[i] = (char) k;
                String tempString = String.valueOf(chars);
                if (!visited.contains(tempString) && wordSet.contains(tempString)) {
                    // 结果集里有当前词 且没访问过
                    if (tempString.equals(endWord)) {
                        // 等于终止词
                        // 结束喽
                        return true;
                    }

                    // 不等于终止词
                    // 将结果集中的比较值放入队列
                    // 并标记访问过
                    queue.addFirst(tempString);
                    visited.add(tempString);
                }
            }
            // 恢复
            chars[i] = originChar;
        }
        return false;
    }

    /**
     * 双向广度优先
     *
     * @param beginWord
     * @param endWord
     * @param wordList
     * @return
     */
    public int ladderLength2(String beginWord, String endWord, List<String> wordList) {

        HashSet<String> wordSet = new HashSet<String>(wordList);
        if (wordSet.size() <= 0 || !wordSet.contains(endWord)) {
            // 词典里不存在终止词 再见
            return 0;
        }

        // 字典中存在了 准备一个访问过的集合 不能重复访问
        HashSet<String> visited = new HashSet<>();
        // 和头尾开始走的两个集合 每个集合里是即将要走的东西
        HashSet<String> start = new HashSet<>();
        HashSet<String> end = new HashSet<>();
        start.add(beginWord);
        end.add(endWord);

        // 起始词长度也算 所以从1开始
        int step = 1;
        // 两个集合走的都不为空 才能有碰头 才能走过去 否则返回0
        while (!start.isEmpty() && !end.isEmpty()) {
            // 两个集合不为空
            // 选一个数据小一点的那头开始走
            if (start.size() > end.size()) {
                HashSet<String> temp = start;
                start = end;
                end = temp;
            }

            // 此时 start 永远是最小的
            HashSet<String> next = new HashSet<String>();
            for (String currentWord : start) {
                if (isEnd2(currentWord, end, visited, wordSet, next)) {
                    // 结束了 返回
                    return step + 1;
                }
            }
            step++;
            start = next;
        }

        return 0;
    }

    private boolean isEnd2(String currentWord,
                           HashSet<String> end,
                           HashSet<String> visited,
                           HashSet<String> wordSet,
                           HashSet<String> next) {

        char[] chars = currentWord.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            // *it h*t hi*
            char originChar = chars[i];
            for (int k = 'a'; k <= 'z'; k++) {
                if (k == originChar) {
                    continue;
                }

                chars[i] = (char) k;
                String tempString = String.valueOf(chars);
                if (wordSet.contains(tempString)) {
                    // 结果集里有当前词
                    if (end.contains(tempString)) {
                        // 下一步一定有终止词
                        // 结束喽
                        return true;
                    }

                    // 没访问过
                    // 下一步没有终止词
                    // 下一论要走的都有谁
                    if (!visited.contains(tempString)) {
                        next.add(tempString);
                        // 并标记访问过
                        visited.add(tempString);
                    }
                }
            }
            // 恢复
            chars[i] = originChar;
        }
        return false;
    }

    public static void main(String[] args) {

        String beginWord = "hit";
        String endWord = "cog";
        String[] wordArr = new String[]{"hot", "dot", "dog", "lot", "log", "cog"};
        List<String> wordList = Arrays.asList(wordArr);

        Problem_127 problem_127 = new Problem_127();
        int i = problem_127.ladderLength2(beginWord, endWord, wordList);
        System.out.println(i);
    }
}
