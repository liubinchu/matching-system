package top.erricliu.huatai.matchingsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.erricliu.huatai.matchingsystem.entity.transList.BuyTransList;
import top.erricliu.huatai.matchingsystem.entity.transList.SaleTransList;
import top.erricliu.huatai.matchingsystem.entity.transaction.SaleTransaction;
import top.erricliu.huatai.matchingsystem.entity.transaction.TransType;
import top.erricliu.huatai.matchingsystem.entity.transaction.Transaction;
import top.erricliu.huatai.matchingsystem.repo.TransRepo;
import top.erricliu.huatai.matchingsystem.repo.UserRepo;

/**
 * @author liubi
 * @date 2019-10-08 23:20
 **/
@Service
public class MatchingService {
    @Autowired
    private TransRepo transRepo;
    @Autowired
    private UserRepo userRepo;

    public void activate(Transaction transaction) {
        if (transaction.getTradeType().equals(TransType.BUY)) {
            activateByBuy(transaction);
        } else {
            activateBySale(transaction);
        }
    }

    private void activateByBuy(Transaction transaction) {
        SaleTransList saleList = transRepo.getSaleList(transaction.getBoundId());
        BuyTransList buyList = transRepo.getBuyList(transaction.getBoundId());
        if (saleList.isEmpty()) {
            buyList.offerTransaction(transaction);
        } else {
            SaleTransaction salePeek = (SaleTransaction) saleList.peekTransaction();
            int[] res = pricing(transaction, salePeek);

        }
    }

    private void activateBySale(Transaction transaction) {

    }

    private int[] pricing(Transaction t1, Transaction t2) {
        // t1 未加入repo ,t2 已经加入repo
        // 成交价格为 先进入系统的价格 int[0]
        // 成交数量为二者较小值 int[1]
        int price;
        int delt = t1.getInitTime().compareTo(t2.getInitTime());
        if (delt < 0) {
            price = t1.getPrice();
        } else if (delt > 0) {
            price = t2.getPrice();
        } else {
            delt = t1.getCurrentTime().compareTo(t2.getCurrentTime());
            if (delt < 0) {
                price = t1.getPrice();
            } else {
                price = t2.getPrice();
            }
        }
        int quantity = Math.min(t1.getQuantity(), t2.getQuantity());
        return new int[]{price, quantity};
    }

}
