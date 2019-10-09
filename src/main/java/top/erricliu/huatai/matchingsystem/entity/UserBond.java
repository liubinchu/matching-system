package top.erricliu.huatai.matchingsystem.entity;

import lombok.Data;
import top.erricliu.huatai.matchingsystem.Util.GsonUtil;

/**
 * @author liubi
 * @date 2019-10-08 18:54
 **/
@Data
public class UserBond extends Bond {
    // user 下
    int frozenQuantity;
    int aliveQuantity;

    public UserBond(int id, int quantity) {
        super();
        this.id = id;
        this.aliveQuantity = quantity;
        this.frozenQuantity = 0;
    }

    public synchronized boolean freeze(int quantity) {
        if (this.aliveQuantity < quantity) {
            return false;
            // 余额不足
        } else {
            this.aliveQuantity -= quantity;
            this.frozenQuantity += quantity;
            return true;
        }
    }

    @Override
    public int getQuantity() {
        return aliveQuantity + frozenQuantity;
    }

    @Override
    public UserBond fromJson(String data) {
        return GsonUtil.getGson().fromJson(data, UserBond.class);
    }
}
