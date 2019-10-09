package top.erricliu.huatai.matchingsystem.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.erricliu.huatai.matchingsystem.entity.Responce;
import top.erricliu.huatai.matchingsystem.service.MonitorService;

/**
 * @author liubi
 * @date 2019-10-09 11:19
 **/
@RestController
@RequestMapping("/monitor")
@Log4j2
public class MonitorController {
    @Autowired
    private MonitorService monitorService;

    @GetMapping("/userRepo")
    public Responce userRepo() {
        return monitorService.userRepo();
    }

    @GetMapping("/billRepo")
    public Responce billRepo() {
        return monitorService.billRepo();
    }

    @GetMapping("/bondRepo")
    public Responce bondRepo() {
        return monitorService.bondRepo();
    }
}