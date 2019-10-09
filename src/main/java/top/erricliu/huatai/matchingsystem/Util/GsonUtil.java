package top.erricliu.huatai.matchingsystem.Util;

import com.google.gson.Gson;

/**
 * @author liubi
 * @date 2019-10-09 08:23
 **/
public class GsonUtil {
    private static volatile Gson gson;

    public static Gson getGson() {
        if (gson == null) {
            synchronized (GsonUtil.class) {
                if (gson == null) {
                    gson = new Gson();
                }
            }
        }
        return gson;
    }

    private GsonUtil() {
    }
}
