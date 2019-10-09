package top.erricliu.huatai.matchingsystem.entity.transaction;

import com.google.gson.Gson;
import lombok.Data;
import top.erricliu.huatai.matchingsystem.Util.GsonUtil;

import java.sql.Timestamp;

/**
 * @author liubi
 * @date 2019-10-09 08:13
 **/
@Data
public class Transaction {
    private static Gson gson = GsonUtil.getGson();

    int buyerId;
    int sellerId;
    int bondId;
    int quantity;
    int dealingPrice;
    Timestamp timestamp;

    public Transaction(int buyerId, int sellerId, int bondId, int quantity, int dealingPrice) {
        this.buyerId = buyerId;
        this.sellerId = sellerId;
        this.bondId = bondId;
        this.quantity = quantity;
        this.dealingPrice = dealingPrice;
        this.timestamp = new java.sql.Timestamp(System.currentTimeMillis());
    }

    public String toJson() {
        return gson.toJson(this);
    }

    public Transaction fromJson(String data) {
        return gson.fromJson(data, Transaction.class);
    }
}