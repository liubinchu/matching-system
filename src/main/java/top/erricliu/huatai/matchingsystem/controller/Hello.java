package top.erricliu.huatai.matchingsystem.controller;

import lombok.extern.log4j.Log4j2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import top.erricliu.huatai.matchingsystem.Mapper.testUserMapper;
import top.erricliu.huatai.matchingsystem.entity.transaction.testUser;
import top.erricliu.huatai.matchingsystem.service.EnduranceService;

@RestController
@RequestMapping("/e")
@Log4j2
public class Hello {


    private static final Logger logger = LoggerFactory.getLogger(Hello.class);
    @Autowired
    private EnduranceService enduranceService;

    @GetMapping("/hello")
    public String submit(){
        log.info("start submit");

        //调用service层的任务
        enduranceService.executeAsync();

        log.info("end submit");

        return "success";
    }


    @Autowired
    private testUserMapper userMapper;

    @RequestMapping("/save")
    public ModelAndView index() {
        testUser user = new testUser();
        user.setAge(18);
        user.setName("Adam");
        user.setPwd("123456");
        userMapper.install(user);
        ModelAndView modelAndView = new ModelAndView("/index");
        modelAndView.addObject("count", userMapper.getAll().size());
        return modelAndView;
    }
}