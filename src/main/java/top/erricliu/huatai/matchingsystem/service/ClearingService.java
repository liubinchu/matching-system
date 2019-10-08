package top.erricliu.huatai.matchingsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.erricliu.huatai.matchingsystem.entity.transaction.Transaction;
import top.erricliu.huatai.matchingsystem.repo.TransRepo;
import top.erricliu.huatai.matchingsystem.repo.UserRepo;

/**
 * @author liubi
 * @date 2019-10-08 23:53
 **/
@Service
public class ClearingService {
    @Autowired
    private TransRepo transRepo;
    @Autowired
    private UserRepo userRepo;

    public synchronized void clear(Transaction t1, Transaction t2, int[] pricing) {
        // t1 未加入repo ,t2 已经加入repo
        // 成交价格为 先进入系统的价格 int[0]
        // 成交数量为二者较小值 int[1]
        t1.trade(pricing[1]);
        t2.trade(pricing[1]);

    }
}
