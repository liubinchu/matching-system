package top.erricliu.huatai.matchingsystem.service;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.erricliu.huatai.matchingsystem.entity.Responce;
import top.erricliu.huatai.matchingsystem.entity.User;
import top.erricliu.huatai.matchingsystem.repo.UserRepo;

/**
 * @author liubi
 * @date 2019-10-08 15:31
 **/
@Service
@Log4j2
public class AddUserService {
    @Autowired
    private UserRepo userRepo;

    public Responce addUser(int money) {
        User user = new User(money);
        userRepo.add(user);
        return Responce.build(2201, user);
    }
}
