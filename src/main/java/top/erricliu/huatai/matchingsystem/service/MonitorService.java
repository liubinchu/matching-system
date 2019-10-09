package top.erricliu.huatai.matchingsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.erricliu.huatai.matchingsystem.entity.Responce;
import top.erricliu.huatai.matchingsystem.repo.BillRepo;
import top.erricliu.huatai.matchingsystem.repo.BondRepo;
import top.erricliu.huatai.matchingsystem.repo.UserRepo;

/**
 * @author liubi
 * @date 2019-10-09 11:19
 **/
@Service
public class MonitorService {
    @Autowired
    private BondRepo bondRepo;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    BillRepo billRepo;
    public Responce userRepo(){
        return Responce.build()
    }
}
