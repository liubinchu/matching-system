package top.erricliu.huatai.matchingsystem.entity;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * @author liubi
 * @date 2019-10-08 10:31
 **/
@Data
public class User implements Comparable {
    private static int currentid = 0;
    int id;
    int aliveMoney;
    int frozenMoney;
    Map<Integer, UserBond> bonds;

    public User(int money) {
        this.id = currentid++;
        this.aliveMoney = money;
        this.frozenMoney = 0;
        this.bonds = new HashMap<>();
    }

    public synchronized boolean freezeMoney(int money) {
        if (this.aliveMoney < money) {
            return false;
        } else {
            this.aliveMoney -= money;
            this.frozenMoney += money;
            return true;
        }
    }

    public synchronized boolean freezeBond(int bondId, int quantity) {
        if (!this.bonds.containsKey(bondId)) {
            return false;
        } else {
            UserBond bond = this.bonds.get(bondId);
            return bond.freeze(quantity);
        }
    }

    public synchronized User buy(int bondId, int quantity, int price) {
        this.frozenMoney -= (quantity * price);
        if (this.bonds.containsKey(bondId)) {
            this.bonds.get(bondId).aliveQuantity += quantity;
        } else {
            this.bonds.put(bondId, new UserBond(bondId, quantity));
        }
        return this;
    }

    public synchronized User sale(int bondId, int quantity, int price) {
        this.bonds.get(bondId).frozenQuantity -= quantity;
        if (this.bonds.get(bondId).getQuantity() == 0) {
            // 卖完了
            this.bonds.remove(bondId);
        }
        this.aliveMoney += (quantity * price);
        return this;
    }

    public boolean owingBond(int boundId) {
        return this.bonds.containsKey(boundId);
    }

    public int getTotalMoney() {
        return aliveMoney + frozenMoney;
    }

    @Override
    public synchronized int compareTo(Object o) {
        User other = (User) o;
        if (other.id != this.id) {
            return this.id - other.id;
        } else if(this.getTotalMoney()!=other.getTotalMoney()){
            return this.getTotalMoney() - other.getTotalMoney();
        }else {
            return this.aliveMoney - other.aliveMoney;
        }
    }
}
