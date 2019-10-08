package top.erricliu.huatai.matchingsystem.repo;

import org.springframework.stereotype.Component;
import top.erricliu.huatai.matchingsystem.entity.User;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author liubi
 * @date 2019-10-08 10:32
 **/
@Component
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

/*    public User replace(int userId, User user) {
        return userList.replace(userId, user);
    }*/
}
