package top.erricliu.huatai.matchingsystem.entity.transaction;

/**
 * @author liubi
 * @date 2019-10-08 21:21
 **/
public enum BillType {
    /**
     *
     */
    SALE, BUY;

    public static void main(String[] args) {
        System.out.println(BillType.BUY.equals(BillType.BUY));
        System.out.println(BillType.BUY.equals(BillType.SALE));
    }
}
