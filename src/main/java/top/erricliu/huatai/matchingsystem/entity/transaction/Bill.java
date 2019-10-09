package top.erricliu.huatai.matchingsystem.entity.transaction;

import lombok.Data;
import top.erricliu.huatai.matchingsystem.Util.GsonUtil;
import top.erricliu.huatai.matchingsystem.repo.UserRepo;

import java.sql.Timestamp;

/**
 * @author liubi
 * @date 2019-10-08 11:00
 **/
@Data
public class Bill {
    int userId;
    int boundId;
    BillType tradeType;
    // true sale false buy
    int price;
    int quantity;
    Timestamp initTime;
    // 初始创建时间

    Timestamp currentTime;
    // 部分交易后, 剩余创建交易时间

    public Bill(int userId, int boundId, BillType tradeType, int price, int quantity) {
        this.userId = userId;
        this.boundId = boundId;
        this.tradeType = tradeType;
        this.price = price;
        this.quantity = quantity;
        this.initTime = new java.sql.Timestamp(System.currentTimeMillis());
        this.currentTime = initTime;
    }

    public Bill(int userId, int boundId, BillType tradeType, int price, int quantity, Timestamp initTime, Timestamp currentTime) {
        this.userId = userId;
        this.boundId = boundId;
        this.tradeType = tradeType;
        this.price = price;
        this.quantity = quantity;
        this.initTime = initTime;
        this.currentTime = currentTime;
    }

    public Bill remainTrans(int quantity) {
        return new Bill(this.userId, this.boundId, this.tradeType, this.price, this.quantity - quantity,
                this.initTime, new java.sql.Timestamp(System.currentTimeMillis()));
    }

    public Bill trade(int quantity, Timestamp timestamp) {
        this.quantity -= quantity;
        this.currentTime = timestamp;
        return this;
    }

    public String toJson() {
        return GsonUtil.getGson().toJson(this);
    }

    public Bill fromJson(String data) {
        return GsonUtil.getGson().fromJson(data, Bill.class);
    }

    public static void main(String[] args) {
        BuyBill transaction1 = new BuyBill(0, 0, 10, 10);
        BuyBill transaction2 = new BuyBill(0, 0, 20, 10);
        System.out.println(transaction1.compareTo(transaction2));
    }
}
