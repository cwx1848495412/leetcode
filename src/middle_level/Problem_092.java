package middle_level;

/**
 * 反转从位置 m 到 n 的链表。请使用一趟扫描完成反转。
 * <p>
 * 说明:
 * 1 ≤ m ≤ n ≤ 链表长度。
 * <p>
 * 示例:
 * <p>
 * 输入: 1->2->3->4->5->NULL, m = 2, n = 4
 * 输出: 1->4->3->2->5->NULL
 *
 * @Auther: 苏察哈尔丶灿
 * @Date: 2020/11/5 16:12
 * @Slogan: 我自横刀向天笑，笑完我就去睡觉。
 */
public class Problem_092 {
    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public ListNode reverseBetween(ListNode head, int m, int n) {
        if (head == null) return null;
        ListNode myNode = new ListNode(0);

        myNode.next = head;
        ListNode node = myNode;

        int i = 1;
        while (i != m) {
            i++;
            node = node.next;
        }

        node.next = recv(node.next, n - m + 1);

        return myNode.next;
    }

    private ListNode recv(ListNode head, int num) {
        ListNode pre = null;
        ListNode next = null;
        ListNode tail = head;
        for (int i = 0; i < num; i++) {
            next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }
        tail.next = head;
        return pre;
    }
}
