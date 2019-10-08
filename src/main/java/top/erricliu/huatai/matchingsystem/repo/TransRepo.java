package top.erricliu.huatai.matchingsystem.repo;

import org.springframework.stereotype.Component;
import top.erricliu.huatai.matchingsystem.entity.transList.BuyTransList;
import top.erricliu.huatai.matchingsystem.entity.transList.SaleTransList;

import java.util.HashMap;
import java.util.Map;

/**
 * @author liubi
 * @date 2019-10-08 21:48
 **/
@Component
public class TransRepo {
    Map<Integer, BuyTransList> buyRepo = new HashMap<>();
    Map<Integer, SaleTransList> saleRepo = new HashMap<>();

    public void addList(int bondId) {
        buyRepo.put(bondId, new BuyTransList(bondId));
        saleRepo.put(bondId, new SaleTransList(bondId));
    }

    public SaleTransList getSaleList(int bondId) {
        return saleRepo.get(bondId);
    }

    public BuyTransList getBuyList(int bondId) {
        return buyRepo.get(bondId);
    }
}
