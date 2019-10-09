package top.erricliu.huatai.matchingsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.erricliu.huatai.matchingsystem.entity.billList.BuyBillList;
import top.erricliu.huatai.matchingsystem.entity.billList.SaleBillList;
import top.erricliu.huatai.matchingsystem.entity.transaction.Bill;
import top.erricliu.huatai.matchingsystem.entity.transaction.BillType;
import top.erricliu.huatai.matchingsystem.entity.transaction.BuyBill;
import top.erricliu.huatai.matchingsystem.entity.transaction.SaleBill;
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
    @Autowired
    private ClearingService clearingService;

    public void activate(Bill bill) {
        if (bill.getTradeType().equals(BillType.BUY)) {
            activateByBuy((BuyBill) bill);
        } else {
            activateBySale((SaleBill) bill);
        }
    }

    private void activateByBuy(BuyBill buyBill) {
        SaleBillList saleList = transRepo.getSaleList(buyBill.getBoundId());
        BuyBillList buyList = transRepo.getBuyList(buyBill.getBoundId());
        while (buyBill.getQuantity() != 0) {
            if (saleList.isEmpty()) {
                buyList.offerBill(buyBill);
                return;
            } else {
                SaleBill salePeek = (SaleBill) saleList.peekBill();
                int[] res = pricing(buyBill, salePeek);
                if (res == null) {
                    buyList.offerBill(buyBill);
                    return;
                } else {
                    clearingService.clearBuy(buyBill, saleList, res);
                }
            }
        }
    }

    private void activateBySale(SaleBill saleBill) {
        SaleBillList saleList = transRepo.getSaleList(saleBill.getBoundId());
        BuyBillList buyList = transRepo.getBuyList(saleBill.getBoundId());
        while (saleBill.getQuantity() != 0) {
            if (buyList.isEmpty()) {
                saleList.offerBill(saleBill);
                return;
            } else {
                BuyBill buyBill = (BuyBill) buyList.peekBill();
                int[] res = pricing(buyBill, saleBill);
                if (res == null) {
                    saleList.offerBill(saleBill);
                    return;
                } else {
                    clearingService.clearSale(saleBill, buyList, res);
                }
            }
        }
    }

    private int[] pricing(BuyBill buyBill, SaleBill saleBill) {
        // 成交价格为 先进入系统的价格 int[0]
        // 成交数量为二者较小值 int[1]
        if (buyBill.getPrice() < saleBill.getPrice()) {
            // 无法撮合
            return null;
        } else {
            int price;
            int delt = buyBill.getInitTime().compareTo(saleBill.getInitTime());
            if (delt < 0) {
                price = buyBill.getPrice();
            } else if (delt > 0) {
                price = saleBill.getPrice();
            } else {
                delt = buyBill.getCurrentTime().compareTo(saleBill.getCurrentTime());
                if (delt < 0) {
                    price = buyBill.getPrice();
                } else {
                    price = saleBill.getPrice();
                }
            }
            int quantity = Math.min(buyBill.getQuantity(), saleBill.getQuantity());
            return new int[]{price, quantity};
        }
    }

}
