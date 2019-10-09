package top.erricliu.huatai.matchingsystem.entity.billList;

import top.erricliu.huatai.matchingsystem.entity.transaction.Bill;
import top.erricliu.huatai.matchingsystem.entity.transaction.BuyBill;
import top.erricliu.huatai.matchingsystem.entity.transaction.SaleBill;

import java.util.Queue;
import java.util.concurrent.PriorityBlockingQueue;

/**
 * @author liubi
 * @date 2019-10-08 21:30
 **/
public class BillList {
    int bondid;
    Queue<Bill> transList;

    public BillList(int bondid) {
        this.bondid = bondid;
        this.transList = new PriorityBlockingQueue<>();
    }

    public boolean isEmpty() {
        return transList.isEmpty();
    }

    @Override
    public String toString() {
        return "BillList{" +
                "bondid=" + bondid +
                ", billList=" + transList +
                '}';
    }

    public int getBondid() {
        return bondid;
    }

    public boolean offerBill(Bill bill) {
        return this.transList.offer(bill);
    }

    public Bill peekBill() {
        return this.transList.peek();
    }

    public Bill pollBill() {
        return this.transList.poll();
    }

    public static void main(String[] args) {
        BuyBillList list1 = new BuyBillList(0);
        BuyBill transaction1 = new BuyBill(0, 0, 10, 10);
        BuyBill transaction2 = new BuyBill(0, 0, 20, 10);
        list1.offerBill(transaction1);
        list1.offerBill(transaction2);
        System.out.println(list1);

        SaleBillList list2 = new SaleBillList(0);
        SaleBill transaction3 = new SaleBill(0, 0, 10, 10);
        SaleBill transaction4 = new SaleBill(0, 0, 20, 10);
        list2.offerBill(transaction3);
        list2.offerBill(transaction4);
        System.out.println(list2);
    }
}
