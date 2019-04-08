package com.algorithm.hash.question1;

/*
哈希表结点
blockCount：哈希表该位置链表的元素个数
startNode：哈希表该位置链表的头节点
 */
public class HashTableNode {
    private int blockCount;
    private ListNode startNode;

    public int getBlockCount() {
        return blockCount;
    }

    public void setBlockCount(int blockCount) {
        this.blockCount = blockCount;
    }

    public ListNode getStartNode() {
        return startNode;
    }

    public void setStartNode(ListNode startNode) {
        this.startNode = startNode;
    }
}
