package middle_level;

/**
 * 给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。
 * <p>
 * 如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
 * <p>
 * 您可以假设除了数字 0 之外，这两个数都不会以 0 开头。
 * <p>
 * 示例：
 * <p>
 * 输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
 * 输出：7 -> 0 -> 8
 * 原因：342 + 465 = 807
 *
 * @Auther: 苏察哈尔丶灿
 * @Date: 2020/10/30 10:47
 * @Slogan: 我自横刀向天笑，笑完我就去睡觉。
 */
public class Problem_002 {

    /**
     * Definition for singly-linked list.
     * public class ListNode {
     * int val;
     * ListNode next;
     * ListNode() {}
     * ListNode(int val) { this.val = val; }
     * ListNode(int val, ListNode next) { this.val = val; this.next = next; }
     * }
     */
    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {

        int one = 0, two = 0;
        int i = 0, j = 0, k = 0;
        while (l1 != null) {
            one += l1.val * Math.pow(10, i++);
            l1 = l1.next;
        }
        while (l2 != null) {
            two += l2.val * Math.pow(10, j++);
            l2 = l2.next;
        }

        int sum = one + two;
        // 取每一位
        ListNode root = null;
        ListNode current = null;

        // 807 / 10 = 80
        // 807 / 100 = 8
        // 807 / 1000 = 0
        while ((sum / (int) Math.pow(10, k)) != 0) {
            int wei = ((sum % (int) Math.pow(10, k + 1)) / ((int) Math.pow(10, k)));
            k++;

            if (root == null) {
                root = new ListNode(wei);
                current = root;
                continue;
            }
            current.next = new ListNode(wei);
            current = current.next;
        }
        return root == null ? new ListNode(0) : root;
    }


    public static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    public static void printLinkedList(ListNode listNode) {
        while (listNode != null) {
            System.out.print(listNode.val);
            listNode = listNode.next;
        }
        System.out.println();
    }

    public static ListNode addTwoNumbers2(ListNode l1, ListNode l2) {
        if (l1 == null && l2 == null) {
            return new ListNode();
        }
        int carry = 0;
        ListNode root = null;
        ListNode current = null;

        while (l1 != null || l2 != null) {
            int temp = 0;

            if (l1 == null) {
                temp = l2.val + carry;
                l2 = l2.next;
            } else if (l2 == null) {
                temp = l1.val + carry;
                l1 = l1.next;
            } else {
                // l1 l2 都有值
                temp = l1.val + l2.val + carry;
                l1 = l1.next;
                l2 = l2.next;
            }

            if (temp / 10 != 0) {
                // 说明有进位
                carry = 1;
                temp %= 10;
            } else {
                carry = 0;
            }

            if (root == null) {
                current = root = new ListNode(temp);
            } else {
                current.next = new ListNode(temp);
                current = current.next;
            }

        }

        if (carry == 1) {
            current.next = new ListNode(1);
        }

        return root;
    }


    public static void main(String[] args) {
//        (2 -> 4 -> 3) + (5 -> 6 -> 4)
        ListNode l1 = new ListNode(2, new ListNode(4, new ListNode(3)));
        ListNode l2 = new ListNode(5, new ListNode(6, new ListNode(4)));
        ListNode newListNode = addTwoNumbers2(l1, l2);
        printLinkedList(newListNode);
    }
}
