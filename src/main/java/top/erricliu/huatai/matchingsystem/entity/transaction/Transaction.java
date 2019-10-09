package top.erricliu.huatai.matchingsystem.entity.transaction;

import lombok.Data;
import top.erricliu.huatai.matchingsystem.Util.GsonUtil;

import java.sql.Timestamp;

/**
 * @author liubi
 * @date 2019-10-09 08:13
 **/
@Data
public class Transaction {
    int buyerId;
    int sellerId;
    int bondId;
    int quantity;
    int dealingPrice;
    Timestamp timestamp;

    public int getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(int buyerId) {
        this.buyerId = buyerId;
    }

    public int getSellerId() {
        return sellerId;
    }

    public void setSellerId(int sellerId) {
        this.sellerId = sellerId;
    }

    public int getBondId() {
        return bondId;
    }

    public void setBondId(int bondId) {
        this.bondId = bondId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getDealingPrice() {
        return dealingPrice;
    }

    public void setDealingPrice(int dealingPrice) {
        this.dealingPrice = dealingPrice;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public Transaction(int buyerId, int sellerId, int bondId, int quantity, int dealingPrice) {
        this.buyerId = buyerId;
        this.sellerId = sellerId;
        this.bondId = bondId;
        this.quantity = quantity;
        this.dealingPrice = dealingPrice;
        this.timestamp = new java.sql.Timestamp(System.currentTimeMillis());
    }
    public Transaction(){}


    public String toJson() {
        return GsonUtil.getGson().toJson(this);
    }

    public Transaction fromJson(String data) {
        return GsonUtil.getGson().fromJson(data, Transaction.class);
    }
}
