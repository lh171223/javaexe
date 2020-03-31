package offer.LinkedList;

import java.util.LinkedList;
import java.util.Scanner;

/**
 * @author lh
 * @version 1.0
 * @date 2020/3/20 17:26
 *
 * 《复杂链表的复制》
 * 输入一个复杂链表
 * （每个节点中有节点值，以及两个指针，一个指向下一个节点，另一个特殊指针指向任意一个节点），
 * 返回结果为复制后复杂链表的head。
 * （注意，输出结果中请不要返回参数中的节点引用，否则判题程序会直接返回空）
 */

/*
1. 复制原始链表的任意结点N并创建新节点N'，再把N'链接到N的后面（即复制出来的N'是N的下一个结点next指向的结点）
2. 若原始链表中结点N的另一个特殊指针指向的是结点R，则复制链表N'指向的是R'，再把R'链接到R的后面
3. 把长链表拆分成两个链表：奇数位置的节点用next链接起来就是原始链表，偶数位置的结点用next链接起来就是复制出来的链表
4. 特殊情况考虑：只有一个结点的链表、random形成环的情况、random指向自己的情况

1. 遍历链表，复制每个结点，并将复制后的结点链接到原始结点之后，即N.next=N'
2. 重新遍历，复制原始链表结点N的random指针给新结点N'，即N.random=N.random.next
3. 拆分链表，将链表分为原链表和复制后的链表
 */

public class ComplexListCopy {
    public RandomListNode Clone(RandomListNode pHead)
    {
        if (pHead == null)
            return null;

        RandomListNode oldNode = pHead;
        //1.遍历链表，复制结点的next结点
        while(oldNode != null){
            RandomListNode newNode = new RandomListNode(oldNode.val);
            RandomListNode oldNextNode = oldNode.next;
            newNode.next = oldNextNode;
            oldNode.next = newNode;
            oldNode = oldNextNode; //遍历
        }
        oldNode = pHead;
        //2.重新遍历，复制N的随机结点R给新节点N'
        while (oldNode != null){
            if (oldNode.random == null)  //此处if..else语句可换为条件 oldNode.next.random = oldNode.random==null?null:oldNode.random.next;
                oldNode.next.random = null; // oldNode.next即为新节点newNode
            else
                oldNode.next.random = oldNode.random.next; //该新随机结点已在上回遍历中被复制，此处不同于再新插入结点，直接指向即可
            oldNode = oldNode.next.next;// 移动到下一个原始结点（遍历操作）
        }
        oldNode = pHead;
        //3.拆分链表，奇偶互分
        RandomListNode pNewHead = pHead.next;
        while (oldNode != null){
            RandomListNode newNode = oldNode.next;
            oldNode.next = newNode.next;//从原始链表中删除了newNode
            newNode.next=  newNode.next==null?null:newNode.next.next;// //新链表结点必须判空，遍历至尾结点时需要赋值为null，否则出现空指针异常
            oldNode = oldNode.next;
        }
        return pNewHead;
    }
}
