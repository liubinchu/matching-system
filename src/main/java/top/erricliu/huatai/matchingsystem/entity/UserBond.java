package top.erricliu.huatai.matchingsystem.entity;

/**
 * @author liubi
 * @date 2019-10-08 18:54
 **/
public class UserBond extends Bond {
    // user 下
    private int frezonQuantity;
    private int aliveQuantity;
    public UserBond(int id, int quantity) {
        super();
        this.id = id;
        this.quantity = quantity;
    }
}
