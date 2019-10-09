package top.erricliu.huatai.matchingsystem.service;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;
import top.erricliu.huatai.matchingsystem.mapper.TransactionMapper;
import top.erricliu.huatai.matchingsystem.entity.transaction.Transaction;

/**
 * @author liubi
 * @date 2019-10-08 16:52
 **/

@Service
@Log4j2
@EnableAsync
public class PersistenceService {

 @Autowired
 private StringRedisTemplate redisTemplate;
 @Autowired
 private TransactionMapper transactionMapper;


 @Async("taskExecutor")
 public synchronized  void messageGetAndEndurance(String content) {
  try {
   Transaction transaction=new Transaction();
   transaction=transaction.fromJson(content);
   transactionMapper.install(transaction);
   log.info("get transaction from cache and endurance succeed: " + content);
  } catch (Exception e) {
   e.printStackTrace();
   log.error("save fail:" + content);
  }
 }

}
