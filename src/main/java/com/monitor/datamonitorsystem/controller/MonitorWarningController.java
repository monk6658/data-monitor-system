package com.monitor.datamonitorsystem.controller;

import com.monitor.datamonitorsystem.pojo.BaseResponse;
import com.monitor.datamonitorsystem.pojo.MonitorWarningPo;
import com.monitor.datamonitorsystem.service.MonitorWarningService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;


/**
 * 系统监控预警
 * @Date 2020/4/24 9:44
 * @author zxl
 */
@Controller
@RequestMapping("/")
public class MonitorWarningController {

    @Autowired
    private MonitorWarningService monitorWarningService;

    @RequestMapping("/")
    public ModelAndView page(){
        return new ModelAndView("websocket");
    }

    /**
     * 插入系统预警信息
     * @author zxl
     * @time 2020/4/24 9:47
     */
    @ResponseBody
    @RequestMapping("/warn/add")
    public BaseResponse saveWarnInfo(@Valid MonitorWarningPo monitorWarningPo) {
        return monitorWarningService.saveWarnInfo(monitorWarningPo);
    }


    @ResponseBody
    @PostMapping("/warn/addMoney/{amount}")
    public BaseResponse changeEveryDayMoney(@PathVariable("amount") String amount){
        return monitorWarningService.changeEveryDayMoney(amount);
    }





}
