package top.erricliu.huatai.matchingsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.erricliu.huatai.matchingsystem.repo.BondRepo;
import top.erricliu.huatai.matchingsystem.repo.UserRepo;

import java.util.Map;

/**
 * @author liubi
 * @date 2019-10-08 14:12
 **/
@Service
public class PreCheckService {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private BondRepo bondRepo;

    public boolean checkMoney(int money){
        return money>=0;
    }
    public boolean checkQuantity(int quantity){
        return quantity>=0;
    }
    public boolean checkBody(Map<String, Object> body) {
        // 简单 没有判断 类型正确
        return body.containsKey("userId")
                && body.containsKey("bondId")
                && body.containsKey("quantity")
                && body.containsKey("price");
    }

    public boolean existUser(int userId) {
        return userRepo.containsKey(userId);
    }

    public boolean userHavingBond(int userId, int bondId) {
        if (!existUser(userId)) {
            return false;
        } else {
            return userRepo.get(userId).owingBond(bondId);
        }
    }

    public boolean existBond(int bondId) {
        return bondRepo.containsKey(bondId);
    }
}
