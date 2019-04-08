package com.algorithm.hash.question1;
/*
哈希表实现
tSize：哈希表结点的个数
count：哈希表元素的个数
table：哈希表结点的数组
LOAD_FACTOR：负载因子临界值
 */
public class HashTable {
    private int tSize;
    private int count;
    private HashTableNode[] table;
    public final int LOAD_FACTOR = 20;

    public HashTable(int size){
        this.settSize(size/LOAD_FACTOR + 1);
    }

    public int gettSize() {
        return tSize;
    }

    public void settSize(int tSize) {
        this.tSize = tSize;
        table = new HashTableNode[tSize];
        for(int i=0;i<tSize;i++){
            table[i] = new HashTableNode();
        }
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public HashTableNode[] getTable() {
        return table;
    }

    public void setTable(HashTableNode[] table) {
        this.table = table;
    }


    /*
    哈希函数
     */
    private int hash(int key){
        return key % this.gettSize();
    }

    /*
    查找：1、使用哈希函数计算索引 2、比较索引对应的链表是否存在值为key的元素
     */
    public boolean hashSearch(int key){
        ListNode temp;
        temp = this.getTable()[hash(key)].getStartNode();
        while(temp!=null){
            if(temp.getKey()==key){
                return true;
            }
            temp = temp.getNext();
        }
        return false;
    }

    /*
    插入：1、查找key值是否已经存在 2、使用hash计算索引 3、创建链表结点，将结点插入索引对应链表的表头
     */
    public boolean hashInsert(int key){
        int index;
        ListNode temp,newNode;
        if(hashSearch(key)){
            return false;
        }

        index = hash(key);
        temp = this.getTable()[index].getStartNode();
        newNode = new ListNode();
        newNode.setKey(key);
        newNode.setNext(temp);
        this.getTable()[index].setStartNode(newNode);
        this.getTable()[index].setBlockCount(this.getTable()[index].getBlockCount()+1);
        this.setCount(this.getCount()+1);
        if(this.getCount()/this.gettSize() >= LOAD_FACTOR){
            rehash();
        }
        return true;
    }

    /*
    删除：1、查询key值是否存在 2、使用哈希函数计算索引 3、遍历索引对应链表，找到对应元素，删除结点
     */
    public boolean hashDelete(int key){
        int index;
        ListNode temp,pre;
        if(!hashSearch(key)){
            return false;
        }

        index = hash(key);
        temp = this.getTable()[index].getStartNode();
        pre = null;
        while(temp!=null){
            if(temp.getKey()==key){
                if(pre!=null){
                    pre.setNext(temp.getNext());
                }else{
                    this.getTable()[index].setStartNode(temp.getNext());
                }
                this.getTable()[index].setBlockCount(this.getTable()[index].getBlockCount()-1);
                this.setCount(this.getCount()-1);
                return true;
            }
            pre = temp;
            temp = temp.getNext();
        }
        return false;
    }



    /*
    扩容，重新构建哈希表：
    1、记录旧哈希表的哈希结点数，元素个数，哈希结点数组
    2、将哈希表的哈希节点数设置为原来的两倍，元素个数与原来一致
    3、创建新的哈希结点数组，长度为原来的两倍
    4、遍历旧哈希结点数组，计算每个元素的新哈希值，将元素一个一个加入新哈希结点数组
     */
    public boolean rehash(){
        int oldSize,oldCount,index;
        ListNode temp,temp2;
        HashTableNode[] oldTable;

        oldSize = this.gettSize();
        oldCount = this.getCount();
        oldTable = this.getTable();
        this.settSize(oldSize * 2);
        this.setCount(oldCount);

        for(int i=0;i<oldSize;i++){
            for(temp = oldTable[i].getStartNode();temp!=null;){
                index = hash(temp.getKey());
                temp2 = temp;
                temp = temp.getNext();
                temp2.setNext(this.getTable()[index].getStartNode());
                this.getTable()[index].setStartNode(temp2);
                this.getTable()[index].setBlockCount(this.getTable()[index].getBlockCount()+1);
            }
        }

        return true;
    }
}
