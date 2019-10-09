package top.erricliu.huatai.matchingsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.erricliu.huatai.matchingsystem.entity.Bond;
import top.erricliu.huatai.matchingsystem.entity.Responce;
import top.erricliu.huatai.matchingsystem.repo.BondRepo;
import top.erricliu.huatai.matchingsystem.repo.TransRepo;
import top.erricliu.huatai.matchingsystem.repo.UserRepo;

/**
 * @author liubi
 * @date 2019-10-08 15:32
 **/
@Service
public class AddBondService {
    @Autowired
    private BondRepo bondRepo;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    PreCheckService preCheckService;
    @Autowired
    TransRepo transRepo;

    public Responce addBond(int quantity, int userId) {
        if (preCheckService.existUser(userId)) {
            Bond bond = new Bond(quantity);
            bondRepo.add(bond);
            userRepo.get(userId).addBond(bond.getId(), bond.getQuantity());
            transRepo.addList(bond.getId());
            return Responce.build(2202, bond);
        } else {
            return Responce.build(4201, userId);
        }

    }
}
