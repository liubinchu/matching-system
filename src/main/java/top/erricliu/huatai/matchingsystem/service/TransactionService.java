package top.erricliu.huatai.matchingsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.erricliu.huatai.matchingsystem.entity.Responce;
import top.erricliu.huatai.matchingsystem.entity.User;
import top.erricliu.huatai.matchingsystem.entity.transaction.BuyTransaction;
import top.erricliu.huatai.matchingsystem.entity.transaction.SaleTransaction;
import top.erricliu.huatai.matchingsystem.entity.transaction.TransType;
import top.erricliu.huatai.matchingsystem.entity.transaction.Transaction;
import top.erricliu.huatai.matchingsystem.repo.BondRepo;
import top.erricliu.huatai.matchingsystem.repo.TransRepo;
import top.erricliu.huatai.matchingsystem.repo.UserRepo;

import java.util.Map;

/**
 * @author liubi
 * @date 2019-10-08 19:43
 **/

@Service
public class TransactionService {
    @Autowired
    private PreCheckService preCheckService;
    @Autowired
    private BondRepo bondRepo;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private TransRepo transRepo;


    public Responce transaction(Map<String, Object> buyBody, TransType transType) {
        if (!preCheckService.checkBody(buyBody)) {
            return Responce.build(4101, buyBody);
        }
        int userId = (Integer) buyBody.get("userId");
        int bondId = (Integer) buyBody.get("bondId");
        int quantity = (Integer) buyBody.get("quantity");
        int price = (Integer) buyBody.get("price");

        if (!preCheckService.existUser(userId)) {
            return Responce.build(4102, userId);
        }
        if (!preCheckService.existBond(bondId)) {
            return Responce.build(4103, bondId);
        }
        if (transType.equals(TransType.SALE)) {
            if (!preCheckService.userHavingBond(userId, bondId)) {
                return Responce.build(4103, bondId);
            }
        }
        User user = userRepo.get(userId);
        // 冻结操作
        if (transType.equals(TransType.BUY)) {
            //买交易只冻结金钱，债券不足时等待交易
            if (!user.freezeMoney(quantity * price)) {
                return Responce.build(4104, new int[]{userId, quantity * price});
            }
        } else {
            // 卖交易只冻结债券
            if (!user.freezeBond(bondId, quantity)) {
                return Responce.build(4105, new int[]{userId, bondId, quantity});
            }
        }


        // 生成交易
        Transaction transaction;
        if (transType.equals(TransType.BUY)) {
            transaction = new BuyTransaction(userId, bondId, price, quantity);
            //transRepo.getBuyList(bondId).offerTransaction(transaction);
        } else {
            transaction = new SaleTransaction(userId, bondId, price, quantity);
            //transRepo.getSaleList(bondId).offerTransaction(transaction);
        }


        if (transType.equals(TransType.BUY)) {
            return Responce.build(2101, null);
        } else {
            return Responce.build(2102, null);
        }

    }
}
