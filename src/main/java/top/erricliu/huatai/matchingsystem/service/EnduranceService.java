package top.erricliu.huatai.matchingsystem.service;

import lombok.extern.log4j.Log4j2;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class EnduranceService {




 @Async("taskExecutor")
 public void executeAsync() {
  log.warn("开始了");
  try{
   Thread.sleep(5000);
  }catch(Exception e){
   e.printStackTrace();
  }
  log.warn("你好，吓你一跳");
 }

 @Async("taskExecutor")
 public void putClearingInfo(String info ) {
  log.warn("开始存储");
  log.warn("存储内容"+info);
  log.warn("完成存储");
 }

}
