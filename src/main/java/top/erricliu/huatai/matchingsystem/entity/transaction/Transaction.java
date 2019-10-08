package top.erricliu.huatai.matchingsystem.entity.transaction;

import lombok.Data;

import java.sql.Timestamp;

/**
 * @author liubi
 * @date 2019-10-08 11:00
 **/
@Data
public class Transaction {
    int userId;
    int boundId;
    TransType tradeType;
    // true sale false buy
    int price;
    int quantity;
    Timestamp initTime;
    // 初始创建时间

    Timestamp currentTime;
    // 部分交易后, 剩余创建交易时间

    public Transaction(int userId, int boundId, TransType tradeType, int price, int quantity) {
        this.userId = userId;
        this.boundId = boundId;
        this.tradeType = tradeType;
        this.price = price;
        this.quantity = quantity;
        this.initTime = new java.sql.Timestamp(System.currentTimeMillis());
        this.currentTime = initTime;
    }

    public Transaction(int userId, int boundId, TransType tradeType, int price, int quantity, Timestamp initTime, Timestamp currentTime) {
        this.userId = userId;
        this.boundId = boundId;
        this.tradeType = tradeType;
        this.price = price;
        this.quantity = quantity;
        this.initTime = initTime;
        this.currentTime = currentTime;
    }

    public Transaction remainTrans(int quantity) {
        return new Transaction(this.userId, this.boundId, this.tradeType, this.price, this.quantity - quantity,
                this.initTime, new java.sql.Timestamp(System.currentTimeMillis()));
    }

    public Transaction trade(int quantity) {
        this.quantity -= quantity;
        this.currentTime = new java.sql.Timestamp(System.currentTimeMillis());
        return this;
    }


    public static void main(String[] args) {
        BuyTransaction transaction1 = new BuyTransaction(0, 0, 10, 10);
        BuyTransaction transaction2 = new BuyTransaction(0, 0, 20, 10);
        System.out.println(transaction1.compareTo(transaction2));
    }
}
