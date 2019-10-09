package top.erricliu.huatai.matchingsystem.entity.transaction;

import java.sql.Timestamp;

/**
 * @author liubi
 * @date 2019-10-08 22:10
 **/
public class SaleBill extends Bill implements Comparable {
    public SaleBill(int userId, int boundId, int price, int quantity) {
        super(userId, boundId, BillType.SALE, price, quantity);
    }

    public SaleBill(int userId, int boundId, int price, int quantity, Timestamp initTime, Timestamp currentTime) {
        super(userId, boundId, BillType.SALE, price, quantity, initTime, currentTime);
    }

    @Override
    public int compareTo(Object o) {
        SaleBill other = ((SaleBill) o);
        if (this.price != other.price) {
            return this.price - other.price;
        } else if (this.initTime != other.initTime) {
            return this.initTime.compareTo(other.initTime);
        } else {
            return this.currentTime.compareTo(other.currentTime);
        }
    }
}
