package top.erricliu.huatai.matchingsystem.repo;

import lombok.ToString;
import org.springframework.stereotype.Component;
import top.erricliu.huatai.matchingsystem.Util.GsonUtil;
import top.erricliu.huatai.matchingsystem.entity.User;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author liubi
 * @date 2019-10-08 10:32
 **/
@Component
@ToString
public class UserRepo {
    private Map<Integer, User> userList = new ConcurrentHashMap<>();

    public boolean containsKey(int userId) {
        return userList.containsKey(userId);
    }

    public User get(int userId) {
        return userList.get(userId);
    }

    public User add(User user) {
        userList.put(user.getId(), user);
        return user;
        //return userList.put(user.getId(), user);
    }

    public String toJson() {
        return GsonUtil.getGson().toJson(this);
    }

    public UserRepo fromJson(String data) {
        return GsonUtil.getGson().fromJson(data, UserRepo.class);
    }

/*    public User replace(int userId, User user) {
        return userList.replace(userId, user);
    }*/
}
