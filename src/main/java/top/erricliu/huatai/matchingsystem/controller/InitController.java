package top.erricliu.huatai.matchingsystem.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import top.erricliu.huatai.matchingsystem.entity.Responce;
import top.erricliu.huatai.matchingsystem.service.AddBondService;
import top.erricliu.huatai.matchingsystem.service.AddUserService;

/**
 * @author liubi
 * @date 2019-10-08 15:33
 **/
@RestController
@RequestMapping("/init")
@Log4j2
public class InitController {
    @Autowired
    private AddBondService addBondService;
    @Autowired
    private AddUserService addUserService;

    @PostMapping("/addUser")
    public Responce addUser(@RequestParam int money) {
        return addUserService.addUser(money);
    }

    @PostMapping("/addBond")
    public Responce addBond(@RequestParam int quantity, @RequestParam int userId) {
        return addBondService.addBond(quantity, userId);
    }
}
