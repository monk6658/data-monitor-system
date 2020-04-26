package com.monitor.datamonitorsystem.service;

import com.monitor.datamonitorsystem.pojo.BaseResponse;
import com.monitor.datamonitorsystem.pojo.MonitorWarningPo;

/**
 * @author zxl
 * @Date 2020/4/24 9:46
 */
public interface MonitorWarningService {

    /**
     * 保存预警信息业务处理类
     * @author zxl
     * @time 2020/4/24 9:56
     */
    BaseResponse saveWarnInfo(MonitorWarningPo monitorWarningPo);

    /**
     * 增加金额
     * @author zxl
     * @time 2020/4/26 15:20
     */
    BaseResponse changeEveryDayMoney(String amount);
}
