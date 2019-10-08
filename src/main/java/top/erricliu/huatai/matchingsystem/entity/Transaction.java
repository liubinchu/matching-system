package top.erricliu.huatai.matchingsystem.entity;

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
    boolean tradeType;
    // true sale false buy
    int price;
    int quantity;
    Timestamp timestamp;
}
