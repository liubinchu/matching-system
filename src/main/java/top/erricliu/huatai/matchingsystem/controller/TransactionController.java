package top.erricliu.huatai.matchingsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.erricliu.huatai.matchingsystem.entity.Responce;
import top.erricliu.huatai.matchingsystem.entity.transaction.TransType;
import top.erricliu.huatai.matchingsystem.service.TransactionService;

import java.util.Map;


/**
 * @author liubi
 * @date 2019-10-08 15:33
 **/
@RestController
@RequestMapping("/transaction")
public class TransactionController {
    @Autowired
    private TransactionService transactionService;

    @RequestMapping("/buy")
    public Responce buyTransaction(@RequestBody Map<String, Object> buyBody) {
        return transactionService.transaction(buyBody, TransType.BUY);
    }

    @RequestMapping("/sale")
    public Responce saleTransaction(@RequestBody Map<String, Object> saleBody) {
        return transactionService.transaction(saleBody, TransType.SALE);
    }
}
