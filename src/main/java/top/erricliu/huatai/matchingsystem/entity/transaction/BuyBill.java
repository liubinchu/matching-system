package top.erricliu.huatai.matchingsystem.entity.transaction;

import java.sql.Timestamp;

/**
 * @author liubi
 * @date 2019-10-08 22:10
 **/

public class BuyBill extends Bill implements Comparable {
    public BuyBill(int userId, int boundId, int price, int quantity) {
        super(userId, boundId, BillType.BUY, price, quantity);
    }

    public BuyBill(int userId, int boundId, int price, int quantity, Timestamp initTime, Timestamp currentTime) {
        super(userId, boundId, BillType.BUY, price, quantity, initTime, currentTime);
    }

    @Override
    public int compareTo(Object o) {
        BuyBill other = ((BuyBill) o);
        if (this.price != other.price) {
            return other.price -this.price ;
        } else if (this.initTime != other.initTime) {
            return this.initTime.compareTo(other.initTime);
        } else {
            return this.currentTime.compareTo(other.currentTime);
        }
    }
}
