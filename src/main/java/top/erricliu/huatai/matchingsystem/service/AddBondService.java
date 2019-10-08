package top.erricliu.huatai.matchingsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.erricliu.huatai.matchingsystem.entity.Bond;
import top.erricliu.huatai.matchingsystem.list.BondList;
import top.erricliu.huatai.matchingsystem.list.UserList;

/**
 * @author liubi
 * @date 2019-10-08 15:32
 **/
@Service
public class AddBondService {
    @Autowired
    private BondList bondList;
    @Autowired
    private UserList userList;
    @Autowired
    ParamCheckService paramCheckService;

    public Bond addBond(int quantity, int userId) {
        if (paramCheckService.existUser(userId)) {
            Bond bond = new Bond(quantity);
            bondList.add(bond);
            userList.get(userId).buyBond(bond.getId(), bond.getQuantity());
            return bond;
        } else {
            return null;
        }

    }
}
