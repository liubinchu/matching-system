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

    public User(int money) {
        this.id = currentid++;
        this.money = money;
        this.bonds = new HashMap<>();
    }

    int id;
    int money;
    Map<Integer, Bond> bonds;

    public User buyBond(int bondId, int quantity) {
        if (this.bonds.containsKey(bondId)) {
            this.bonds.get(bondId).quantity += quantity;
        } else {
            this.bonds.put(bondId, new Bond(bondId, quantity));
        }
        return this;
    }

    public User saleBond(int bondId, int quantity) {
        if (this.bonds.get(bondId).quantity == quantity) {
            this.bonds.remove(bondId);
        } else {
            this.bonds.get(bondId).quantity -= quantity;
        }
        return this;
    }

    @Override
    public int compareTo(Object o) {
        User other = (User) o;
        if (other.id != this.id) {
            return this.id - other.id;
        } else {
            return this.money - other.money;
        }
    }
}
