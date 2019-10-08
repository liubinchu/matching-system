package top.erricliu.huatai.matchingsystem.entity.transaction;

import java.sql.Timestamp;

/**
 * @author liubi
 * @date 2019-10-08 22:10
 **/

public class BuyTransaction extends Transaction implements Comparable {
    public BuyTransaction(int userId, int boundId, int price, int quantity) {
        super(userId, boundId, TransType.BUY, price, quantity);
    }

    public BuyTransaction(int userId, int boundId, int price, int quantity, Timestamp initTime, Timestamp currentTime) {
        super(userId, boundId, TransType.BUY, price, quantity, initTime, currentTime);
    }

    @Override
    public int compareTo(Object o) {
        BuyTransaction other = ((BuyTransaction) o);
        if (this.price != other.price) {
            return other.price -this.price ;
        } else if (this.initTime != other.initTime) {
            return this.initTime.compareTo(other.initTime);
        } else {
            return this.currentTime.compareTo(other.currentTime);
        }
    }
}
