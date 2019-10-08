package top.erricliu.huatai.matchingsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.erricliu.huatai.matchingsystem.list.UserList;

import java.util.Map;

/**
 * @author liubi
 * @date 2019-10-08 14:12
 **/
@Service
public class ParamCheckService {
    @Autowired
    private UserList userList;

    public boolean checkBody(Map<String, Object> body) {
        // 简单 没有判断 类型正确
        return body.containsKey("userId")
                && body.containsKey("bondId")
                && body.containsKey("quantity")
                && body.containsKey("price");
    }

    public boolean existUser(int userId) {
        return userList.containsKey(userId);
    }
}
