package top.erricliu.huatai.matchingsystem.service;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.erricliu.huatai.matchingsystem.entity.Responce;
import top.erricliu.huatai.matchingsystem.entity.User;
import top.erricliu.huatai.matchingsystem.entity.transaction.Bill;
import top.erricliu.huatai.matchingsystem.entity.transaction.BillType;
import top.erricliu.huatai.matchingsystem.entity.transaction.BuyBill;
import top.erricliu.huatai.matchingsystem.entity.transaction.SaleBill;
import top.erricliu.huatai.matchingsystem.repo.BondRepo;
import top.erricliu.huatai.matchingsystem.repo.BillRepo;
import top.erricliu.huatai.matchingsystem.repo.UserRepo;

import java.util.Map;

/**
 * @author liubi
 * @date 2019-10-08 19:43
 **/

@Service
@Log4j2
public class TransactionService {
    @Autowired
    private PreCheckService preCheckService;
    @Autowired
    private BondRepo bondRepo;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private BillRepo billRepo;
    @Autowired
    private MatchingService matchingService;


    public Responce transaction(Map<String, Object> buyBody, BillType billType) {
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
        if (billType.equals(BillType.SALE)) {
            if (!preCheckService.userHavingBond(userId, bondId)) {
                return Responce.build(4103, bondId);
            }
        }
        User user = userRepo.get(userId);
        // 冻结操作
        if (billType.equals(BillType.BUY)) {
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
        Bill bill;
        if (billType.equals(BillType.BUY)) {
            bill = new BuyBill(userId, bondId, price, quantity);
            log.info("bill: "+bill.toJson());
        } else {
            bill = new SaleBill(userId, bondId, price, quantity);
            log.info("bill: "+bill.toJson());
        }
        matchingService.activate(bill);
        if (billType.equals(BillType.BUY)) {
            return Responce.build(2101, null);
        } else {
            return Responce.build(2102, null);
        }
    }
}
