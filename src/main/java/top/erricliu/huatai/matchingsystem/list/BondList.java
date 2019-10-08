package top.erricliu.huatai.matchingsystem.list;

import org.springframework.stereotype.Component;
import top.erricliu.huatai.matchingsystem.entity.Bond;

import java.util.HashMap;
import java.util.Map;

/**
 * @author liubi
 * @date 2019-10-08 10:33
 **/
@Component
public class BondList {
    // 市场上所有 bond 的统计信息
    private Map<Integer, Bond> bondList = new HashMap<>();

    public Bond add(Bond bond) {
        bondList.put(bond.getId(), bond);
        return bond;
    }

    public Bond get(int bondId) {
        return bondList.get(bondId);
    }
}
