package top.erricliu.huatai.matchingsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author liubi
 * @date 2019-10-08 14:05
 **/
@Service
public class TransactionService {
    @Autowired
    private ParamCheckService paramCheckService;

    public boolean buyTransaction(Map<String, Object> buyBody) {
        if(!paramCheckService.checkBody(buyBody)){
            return false;
        }
        return true;
    }

    public boolean saleTransaction(Map<String, Object> saleBody) {
        if(!paramCheckService.checkBody(saleBody)){
            return false;
        }
        return true;
    }
}
