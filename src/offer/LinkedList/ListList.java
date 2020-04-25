package offer.LinkedList;

import offer.BinaryTree.TreeNode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;

/**
 * @author lh
 * @version 1.0
 * @date 2020/3/21 17:55
 *
 * 【一】从尾到头打印链表
 * 【二】链表中倒数第K个结点
 * 【三】反转链表
 * 【四】合并两个排序的链表
 * 【五】两个链表的第一个公共结点
 * 【六】链表中环的入口节点
 * 【七】删除链表中重复的结点
 *
 */
public class ListList {

    /*
    【一】从尾到头打印链表
    输入一个链表，按链表从尾到头的顺序返回一个ArrayList。
    解题：栈 先进后出
     */
    public ArrayList<Integer> printListFromTailToHead(ListNode listNode) {
        ArrayList<Integer> arrayList = new ArrayList<>();
        Stack<Integer> stack = new Stack<>();
        while (listNode != null){
            stack.push(listNode.val);
            listNode = listNode.next;
        }
        while (!stack.isEmpty()){
            arrayList.add(stack.pop());
        }
        return arrayList;
    }

    public ArrayList<Integer> printListFromTailToHead1(ListNode listNode) {
        ArrayList<Integer> temp = new ArrayList<Integer>();//创建一个ArrayList依次放入链表结点
        if(listNode != null){
            temp.add(listNode.val);
            ListNode nextNode = listNode.next;
            while(nextNode!=null){
                temp.add(nextNode.val);
                nextNode = nextNode.next;
            }
            Collections.reverse(temp);//直接使用Collections.reverse进行列表反转
        }
        return temp;
    }

    /*
    【二】链表中倒数第K个结点
    输入一个链表，输出该链表中倒数第k个结点。
    解题：栈，先进后出，出栈时计算输出第K个结点
     */

    public ListNode FindKthToTail(ListNode head,int k) {
        if (head == null || k==0)
            return null;
        Stack<ListNode> stack = new Stack<>();
        while (head != null){
            stack.push(head);
            head = head.next;
        }
        int n = 0;
        while (!stack.isEmpty()){
            ListNode temp = stack.pop();
            n++;
            if (n == k)
                return temp;
        }
        return null;
    }

    /*
    【三】反转链表
    输入一个链表，反转链表后，输出新链表的表头。
    解题：直接控制指针的指向（控制指向一般就需要创建多个结点来辅助）
    思路过程：直接将当前指针结点与前结点两两指向互换，并在过程中首先创建一个辅助指针来保存着后续链表，
    完成后依次将当前指针和前指针后移一位，直至当前指针的下一个结点为空时跳出循环
     */
    public ListNode ReverseList(ListNode head) {
        if(head == null)
            return null;
        ListNode reversedHead = null;
        ListNode current = head;
        ListNode tmp = null;//用于保存后续链表
        ListNode pre = null;//前结点，用于反转指向
        while(current != null)
        {//pre->current->tmp  ===》 pre<-current tmp(先保存住) ==》后移，依次反转每两个结点间的指向
            tmp = current.next; //先保存后续链表，防止断裂后丢失
            current.next = pre; //反转，当前指针不再指向原始下个结点tmp，指向前一个结点pre
            if(tmp == null) //当下个结点为空时，说明当前结点已为尾结点
                reversedHead = current;//为什么返回的是最后一个结点？？？
            pre = current; //依次把前一个结点和当前结点后移一位，进行再一次的current与pre反转
            current = tmp;
        }
        return reversedHead;
    }

    /*
    【四】合并两个排序的链表
    输入两个单调递增的链表，输出两个链表合成后的链表，
    当然我们需要合成后的链表满足单调不减规则。
    解题：
    归并排序流程，链表形式
    直接两两比较两个链表的结点值；新创建一个链表，较小值的list将插入到新链表中
    特殊情况：其中一个list为空，直接返回另一个list；
    均不为空，但长度不同，当遍历完较短的链表后，将另一链表剩下结点全部直接插入新链表
     */
    public ListNode Merge(ListNode list1,ListNode list2) {
        if (list1 == null)
            return list2;
        if (list2 == null)
            return list1;
        //新建链表并初始化
        ListNode newList = new ListNode(-1);
        ListNode list = newList;
        while (list1 != null && list2 != null){
            if (list1.val <= list2.val){
                list.next = list1;
                list1 = list1.next;
            }else {
                list.next = list2;
                list2 = list2.next;
            }
            list = list.next;//勿忘此行 需要往后移至最尾部，以便连接下一插入结点
        }
        //当较短的一个链表遍历完结后
        if (list1 == null)
            list.next = list2;//直接将所有的剩余结点插入至新链表
        if (list2 == null)
            list.next = list1;
        return newList;//返回整个链表
//        return newList.next;//返回链表的头节点
    }

    /*
    【五】《两个链表的第一个公共结点》
    输入两个链表，找出它们的第一个公共结点。
   （注意因为传入数据是链表，所以错误测试数据的提示是用其他方式显示的，保证传入数据是正确的）

    解题思路：
    穷举遍历 两个链表结点两两依次遍历
     */
    public ListNode FindFirstCommonNode(ListNode pHead1, ListNode pHead2) {
        if (pHead1 == null || pHead2 == null)
            return null;
        ListNode p2 ;
        while (pHead1 != null){
            p2 = pHead2;
            while (p2 != null){
                if (pHead1.val == p2.val)
                    return pHead1;
                p2 = p2.next;
            }
            pHead1 = pHead1.next;
        }
        return null;
    }

    /*
    【六】链表中环的入口节点
    给一个链表，若其中包含环，请找出该链表的环的入口结点，否则，输出null。
    解题：利用数学中的不同速度相向而行的问题----相遇即有环
    参考：https://blog.csdn.net/u010983881/article/details/78896293
     */

    public ListNode EntryNodeOfLoop(ListNode pHead){
        if (pHead == null)
            return pHead;
        ListNode slowPointer,fastPointer ;

        //判断是否有环
        boolean isloop = false;
        slowPointer = fastPointer = pHead;
        while (fastPointer != null && fastPointer.next != null){
            slowPointer = slowPointer.next;
            fastPointer = fastPointer.next.next;
            //相遇则有环
            if (slowPointer == fastPointer){//相遇点
                isloop =true;
                break;
            }
        }
        //一个指针从链表头开始，一个从相遇点开始，每次一步，再次相遇的点即是入口点
        if (isloop){
            slowPointer = pHead;//链表头开始
            while (fastPointer != null && fastPointer.next != null){
                //再次相遇点
                if (slowPointer == fastPointer)
                    return slowPointer;
                slowPointer = slowPointer.next;
                fastPointer = fastPointer.next;
            }
        }
        return null;
    }

    private boolean IsLoop(ListNode head){
        ListNode slowPointer ;
        ListNode fastPointer ;
        //使用快慢指针，快指针每次两步，慢指针每次一步（借鉴数学相向的相遇问题，不同速度有环必定相遇）
        boolean isloop = false;
        slowPointer = fastPointer = head;
        while (fastPointer != null && fastPointer.next != null){//如果无环，肯定是快指针提前到终点
            slowPointer = slowPointer.next;
            fastPointer = fastPointer.next.next;

            //两者相遇则有环
            if (slowPointer == fastPointer){
                isloop = true;
                break;
            }
        }
        return isloop;
    }

    /*
    【七】删除链表中重复的结点
    在一个排序的链表中，存在重复的结点，
    请删除该链表中重复的结点，重复的结点不保留，返回链表头指针。
    例如，链表1->2->3->3->4->4->5 处理后为 1->2->5

    解题思路:排序？当前左右结点不同，则该链表其他地方也不会存在重复点
     */
    public ListNode deleteDuplication(ListNode pHead) {
        if (pHead == null)
            return pHead;
        ListNode head = new ListNode(0);
        head.next = pHead; //创建头节点，可能会出现第第一个结点与第二个就相等的情况
        ListNode pre = head;
        ListNode p = pHead;

        while (p != null){

            if (p.next != null && p.val == p.next.val){//删除自身结点，则可避免下一个结点与下下个结点也相同的情况
                while(p.next!=null && p.val == p.next.val)//必须采用while去找到最后一个结点，否则pre.next=p.next中p.next这个重复的结点并未被删除
                    p = p.next;//找到最后一个相等结点
                pre.next = p.next; //删除中间所有相等结点
                p = p.next;//此时两者指向同一个结点
            }else {
                //连续两个值不相等
                pre = pre.next;
                p = p.next;
            }
        }
        return head.next; //为什么不能直接返回pHead？返回pHead时链表结点均不变
    }

    /*
    【八】孩子们的游戏（圆圈中最后剩下的数）
    每年六一儿童节,牛客都会准备一些小礼物去看望孤儿院的小朋友,今年亦是如此。
    HF作为牛客的资深元老,自然也准备了一些小游戏。其中,有个游戏是这样的:
    首先,让小朋友们围成一个大圈。然后,他随机指定一个数m,让编号为0的小朋友开始报数。
    每次喊到m-1的那个小朋友要出列唱首歌,然后可以在礼品箱中任意的挑选礼物,并且不再回到圈中,
    从他的下一个小朋友开始,继续0...m-1报数....这样下去....
    直到剩下最后一个小朋友,可以不用表演,并且拿到牛客名贵的“名侦探柯南”典藏版(名额有限哦!!^_^)。
    请你试着想下,哪个小朋友会得到这份礼品呢？(注：小朋友的编号是从0到n-1)
    如果没有小朋友，请返回-1
     */
    /*
    思路一：
    数学问题：约瑟夫环问题
    约瑟夫环是一个数学的应用问题：已知n个人（以编号1，2，3...n分别表示）围坐在一张圆桌周围。
    从编号为k的人开始报数，数到m的那个人出列；他的下一个人又从1开始报数，数到m的那个人又出列；
    依此规律重复下去，直到圆桌周围的人全部出列。
     */
    public int LastRemaining(int n, int m) {//小朋友数1~n，报数1~m
        if (n == 0 || m==0)
            return -1;
        int k =0;
        for (int i=2;i<=n;i++){
           k=(k+m)%i;
        }
        return k;
    }
    /*
    思路二：循环链表实现
     */
    public int LastRemaining1(int n, int m) {//小朋友数1~n，报数1~m
        ListNode head = new ListNode(0);
        ListNode current = head;
        for (int i=1;i<n;i++){
            ListNode listNode = new ListNode(i);
            current.next = listNode;
            current = listNode;//将当前结点指向最后一个结点
        }
        //为了构建首尾相连的链表，将尾结点（当前指针）与头节点链接起来
        current.next = head;
        ListNode pre = current;//创建前结点，指向当前结点
        current = current.next;//将当前指针指向头节点

        int count = 0;
        //一旦首尾相接，当前结点指向了自身，证明队列中只有一个用户了，跳出循环，游戏结束
        while (current.next != current){
            count++;
            if (count == m){
                pre.next = current.next;//删除第m个结点
                count = 0;
            }
            pre = current;
            current = current.next;
        }
        return current.val;
    }

}
