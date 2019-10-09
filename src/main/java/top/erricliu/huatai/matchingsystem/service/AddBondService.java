package top.erricliu.huatai.matchingsystem.service;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.erricliu.huatai.matchingsystem.entity.Bond;
import top.erricliu.huatai.matchingsystem.entity.Responce;
import top.erricliu.huatai.matchingsystem.repo.BondRepo;
import top.erricliu.huatai.matchingsystem.repo.BillRepo;
import top.erricliu.huatai.matchingsystem.repo.UserRepo;

/**
 * @author liubi
 * @date 2019-10-08 15:32
 **/
@Service
@Log4j2
public class AddBondService {
    @Autowired
    private BondRepo bondRepo;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    PreCheckService preCheckService;
    @Autowired
    BillRepo billRepo;

    public Responce addBond(int quantity, int userId) {
        if(preCheckService.checkQuantity(quantity)){
            if (preCheckService.existUser(userId)) {
                Bond bond = new Bond(quantity);
                bondRepo.add(bond);
                userRepo.get(userId).addBond(bond.getId(), bond.getQuantity());
                billRepo.addList(bond.getId());
                log.info("bond: "+bond.toJson()+" userId: "+userId);
                return Responce.build(2202, bond);
            } else {
                return Responce.build(4201, userId);
            }
        }
        else{
            return Responce.build(4202, "param: quantity="+quantity);
        }
    }
}
