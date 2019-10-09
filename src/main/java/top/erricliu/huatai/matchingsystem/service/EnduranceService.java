package top.erricliu.huatai.matchingsystem.service;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import top.erricliu.huatai.matchingsystem.Mapper.TransactionMapper;
import top.erricliu.huatai.matchingsystem.entity.transaction.Transaction;


@Service
@Log4j2
@EnableAsync
public class EnduranceService {

 @Autowired
 private StringRedisTemplate redisTemplate;
 @Autowired
 private TransactionMapper transactionMapper;

 @Async("taskExecutor")
 public synchronized  void messageGetAndEndurance(String content) {
  log.info("收到队列信息" + content);
  log.warn("存储内容"+content);
  Transaction transaction=new Transaction();
  transaction=transaction.fromJson(content);
  transactionMapper.install(transaction);
  log.warn("完成存储");
 }



}
