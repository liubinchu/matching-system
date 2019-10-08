package top.erricliu.huatai.matchingsystem.entity.transList;

import top.erricliu.huatai.matchingsystem.entity.transaction.BuyTransaction;
import top.erricliu.huatai.matchingsystem.entity.transaction.SaleTransaction;
import top.erricliu.huatai.matchingsystem.entity.transaction.Transaction;

import java.util.Queue;
import java.util.concurrent.PriorityBlockingQueue;

/**
 * @author liubi
 * @date 2019-10-08 21:30
 **/
public class TransList {
    int bondid;
    Queue<Transaction> transList;

    public TransList(int bondid) {
        this.bondid = bondid;
        this.transList = new PriorityBlockingQueue<>();
    }

    public boolean isEmpty() {
        return transList.isEmpty();
    }

    @Override
    public String toString() {
        return "TransList{" +
                "bondid=" + bondid +
                ", transList=" + transList +
                '}';
    }

    public int getBondid() {
        return bondid;
    }

    public boolean offerTransaction(Transaction transaction) {
        return this.transList.offer(transaction);
    }

    public Transaction peekTransaction() {
        return this.transList.peek();
    }

    public Transaction pollTransaction() {
        return this.transList.poll();
    }

    public static void main(String[] args) {
        BuyTransList list1 = new BuyTransList(0);
        BuyTransaction transaction1 = new BuyTransaction(0, 0, 10, 10);
        BuyTransaction transaction2 = new BuyTransaction(0, 0, 20, 10);
        list1.offerTransaction(transaction1);
        list1.offerTransaction(transaction2);
        System.out.println(list1);

        SaleTransList list2 = new SaleTransList(0);
        SaleTransaction transaction3 = new SaleTransaction(0, 0, 10, 10);
        SaleTransaction transaction4 = new SaleTransaction(0, 0, 20, 10);
        list2.offerTransaction(transaction3);
        list2.offerTransaction(transaction4);
        System.out.println(list2);
    }
}
