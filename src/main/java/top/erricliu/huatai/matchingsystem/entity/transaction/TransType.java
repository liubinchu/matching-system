package top.erricliu.huatai.matchingsystem.entity.transaction;

/**
 * @author liubi
 * @date 2019-10-08 21:21
 **/
public enum TransType {
    /**
     *
     */
    SALE, BUY;

    public static void main(String[] args) {
        System.out.println(TransType.BUY.equals(TransType.BUY));
        System.out.println(TransType.BUY.equals(TransType.SALE));
    }
}
